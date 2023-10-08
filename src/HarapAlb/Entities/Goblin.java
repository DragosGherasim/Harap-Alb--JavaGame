package HarapAlb.Entities;

import HarapAlb.Graphics.Animation;
import HarapAlb.Graphics.Assets;
import HarapAlb.Main.Game;
import HarapAlb.Main.RefLinks;
import HarapAlb.Utils.Directions;
import HarapAlb.Utils.ItemFactory;
import HarapAlb.Utils.ItemTypes;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Goblin extends Entity{
    private final Animation animIdleRight;
    private final Animation animIdleLeft;
    private final Animation animRunRight;
    private final Animation animRunLeft;
    private final Animation animAttackRight;
    private final Animation animAttackLeft;
    private final Animation animDamageRight;
    private final Animation animDamageLeft;
    private final Animation animDeadRight;
    private final Animation animDeadLeft;
    private Rectangle visionBounds;
    private boolean enemyDetected;
    private final float dropRateBigHealthPotion;
    private HealthPotion healthPotion;
    private int health;
    private Coin coinDropped;

    public Goblin(RefLinks rfl, float x, float y, int width, int height, float dropRateBigPotion) {
        super(rfl, x, y, width, height);

        this.animIdleRight = new Animation(rfl,100, Assets.goblinIdleRight);
        this.animIdleLeft = new Animation(rfl,100, Assets.goblinIdleLeft);
        this.animRunRight = new Animation(rfl,100, Assets.goblinRunRight);
        this.animRunLeft = new Animation(rfl,100, Assets.goblinRunLeft);
        this.animAttackRight = new Animation(rfl,150, Assets.goblinAttackRight);
        this.animAttackLeft = new Animation(rfl,150, Assets.goblinAttackLeft);
        this.animDamageRight = new Animation(rfl,150, Assets.goblinDamageRight);
        this.animDamageLeft = new Animation(rfl,150, Assets.goblinDamageLeft);
        this.animDeadRight = new Animation(rfl,150, Assets.goblinDeadRight);
        this.animDeadLeft = new Animation(rfl,150, Assets.goblinDeadLeft);

        this.visionBounds = new Rectangle((int) (x-64), (int) y, width*5, height);

        this.health = 4;

        this.dropRateBigHealthPotion = dropRateBigPotion;
    }

    protected void HandleFalling() {
        yMove = 0.0f;
        xMove = 0.0f;

        if (!IsCollisionDownY() && isFalling) {
            if (verticalSpeed + GRAVITY > MAX_VELOCITY) {
                verticalSpeed = MAX_VELOCITY;
            }
            else {
                if (verticalSpeed > (float) MAX_VELOCITY / 2) {
                    verticalSpeed += GRAVITY / 1.5;
                }

                verticalSpeed += GRAVITY;
            }

            yMove += verticalSpeed;
        }
        else {
            yOnDownCollision = y;

            if (isFalling) {
                yMove = 3;
            }

            verticalSpeed = 0.0f;

            isFalling = false;
        }
    }
    private void GetInput(){
        xMove = 0;

        if (!isFalling) {
            if (visionBounds.intersects(refLinks.GetMap().GetHero().bounds)) {
                enemyDetected = true;

                if (refLinks.GetMap().GetHero().bounds.x < bounds.x) {
                    currentDirection = Directions.LEFT;

                } else {
                    currentDirection = Directions.RIGHT;
                }

                if (!attackingBounds.intersects(refLinks.GetMap().GetHero().bounds)) {
                    xMove = DEFAULT_SPEED * currentDirection * frictionOnX * 1.2f;
                }
            } else {
                enemyDetected = false;

                if (IsCollisionRightX()) {
                    currentDirection = Directions.LEFT;
                } else if (IsCollisionLeftX()) {
                    currentDirection = Directions.RIGHT;
                }

                xMove = DEFAULT_SPEED * currentDirection * (frictionOnX * 0.75f);
            }
        }
        else{
            xMove = 0;
        }
    }

    private void HandleBeingAttacked() {
        if (bounds.intersects(refLinks.GetHero().attackingBounds) &&
                refLinks.GetMap().GetHero().canGiveDamage) {

            isTakingDamage = true;
        } else {
            isTakingDamage = false;
        }
    }

    private void HandleAttacking(){
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTimeAnimAttack;

        if (attackingBounds.intersects(refLinks.GetMap().GetHero().bounds) && !isTakingDamage && !isDying &&
                !isDead && !isAttacking && !refLinks.GetMap().GetHero().isAttacking && elapsedTime >= 1500) {

            isAttacking = true;
            canGiveDamage = true;
        }
    }

    @Override
    protected void DrawBounds(Graphics g) {
        g.setColor(Color.PINK);
        g.drawRect((int)(bounds.x-refLinks.GetGameCamera().GetXOffs()),(int)(bounds.y-refLinks.GetGameCamera().GetYOffs()), bounds.width, bounds.height);

        g.setColor(Color.RED);
        g.drawRect((int)(attackingBounds.x-refLinks.GetGameCamera().GetXOffs()),(int)(attackingBounds.y-refLinks.GetGameCamera().GetYOffs()), attackingBounds.width, attackingBounds.height);

        g.setColor(Color.BLACK);
        g.drawRect((int)(visionBounds.x-refLinks.GetGameCamera().GetXOffs()),(int)(visionBounds.y-refLinks.GetGameCamera().GetYOffs()), visionBounds.width, visionBounds.height);
    }

    @Override
    protected void UpdateBounds(){
        bounds.x = (int) x;
        bounds.y = (int) y;

        if (currentDirection == Directions.RIGHT){
            attackingBounds.x = bounds.x + bounds.width;
            attackingBounds.y = (int) y;
        }
        else{
            attackingBounds.x = bounds.x - 8;
            attackingBounds.y = (int) y;
        }

        visionBounds.x = (int) (x-64);
        visionBounds.y = (int) y;
    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(GetCurrentAnimationFrame(), (int) (x - refLinks.GetGameCamera().GetXOffs()), (int) (y - refLinks.GetGameCamera().GetYOffs()), width, height, null);

        if (enemyDetected) {
            g.setColor(Color.RED);
            g.fillRect((int) (bounds.x - refLinks.GetGameCamera().GetXOffs()), (int) (bounds.y - refLinks.GetGameCamera().GetYOffs() - 3), bounds.width*health/4, 7);
        }

        if (Game.OverlayDisplay) {
            DrawBounds(g);
        }
    }

    @Override
    public void Update() {
        HandleBeingAttacked();
        HandleAttacking();

        if (isAttacking){
            if (animAttackRight.GetAnimIndex() != animAttackRight.GetFrameLength() - 1 || animAttackLeft.GetAnimIndex() != animAttackLeft.GetFrameLength() - 1){
                animAttackLeft.Update();
                animAttackRight.Update();
            }
            else {
                startTimeAnimAttack = System.currentTimeMillis();
                isAttacking = false;

                animAttackLeft.Update();
                animAttackRight.Update();
            }

            return;
        }

        if (isDying){
            if (animDeadRight.GetAnimIndex() != animDeadRight.GetFrameLength() - 1 || animDeadLeft.GetAnimIndex() != animDeadLeft.GetFrameLength() - 1){
                animDeadLeft.Update();
                animDeadRight.Update();
            }
            else{
                isDead = true;

                if (Math.random() < dropRateBigHealthPotion){
                    healthPotion = (BigHealthPotion)ItemFactory.CreateItem(ItemTypes.BigHealthPotion, refLinks, x+5, y, 28, 28);
                }


                coinDropped = (Coin)ItemFactory.CreateItem(ItemTypes.Coin, refLinks, x+15, y, 32, 32);

                return;
            }

            return;
        }

        if (isTakingDamage) {
            if (animDamageRight.GetAnimIndex() != animDamageRight.GetFrameLength() - 1 || animDamageLeft.GetAnimIndex() != animDamageLeft.GetFrameLength() - 1) {
                animDamageRight.Update();
                animDamageLeft.Update();

                if (isAttacked)
                    isAttacked = false;

            } else {
                animDamageRight.Update();
                animDamageLeft.Update();

                if (!isAttacked) {
                    refLinks.GetMap().GetHero().canGiveDamage = false;

                    health -= 2;
                    isAttacked = true;
                }

                if (health == 0) {
                    isDying = true;
                }
            }
        }

        if (!isTakingDamage) {
            animIdleRight.Update();
            animIdleLeft.Update();
            animRunRight.Update();
            animRunLeft.Update();

            HandleFalling();
            GetInput();
            Move();
            UpdateBounds();
        }
    }
    private BufferedImage GetCurrentAnimationFrame() {
        if (isAttacking){
            if (currentDirection == Directions.RIGHT) {
                return animAttackRight.GetCurrentFrame();
            } else {
                return animAttackLeft.GetCurrentFrame();
            }
        }
        if (isDying){
            if (currentDirection == Directions.RIGHT) {
                return animDeadRight.GetCurrentFrame();
            } else {
                return animDeadLeft.GetCurrentFrame();
            }
        }
        if (isTakingDamage){
            if (currentDirection == Directions.RIGHT) {
                return animDamageRight.GetCurrentFrame();
            } else {
                return animDamageLeft.GetCurrentFrame();
            }
        }
        if (xMove > 0) {
            return animRunRight.GetCurrentFrame();
        }
        else if (xMove < 0) {
            return animRunLeft.GetCurrentFrame();
        }
        else {
            if (currentDirection == Directions.RIGHT) {
                return animIdleRight.GetCurrentFrame();
            } else {
                return animIdleLeft.GetCurrentFrame();
            }
        }
    }

    public int GetAttackRightAnimIndx(){
        return animAttackRight.GetAnimIndex();
    }

    public int GetAttackLeftAnimIndx(){
        return animAttackLeft.GetAnimIndex();
    }

    public HealthPotion GetHealthPotion(){
        return healthPotion;
    }

    public Coin GetCoinDropped(){
        return coinDropped;
    }
}

