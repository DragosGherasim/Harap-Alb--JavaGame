package HarapAlb.Entities;

import HarapAlb.Graphics.Animation;
import HarapAlb.Graphics.Assets;
import HarapAlb.Main.Game;
import HarapAlb.Main.RefLinks;
import HarapAlb.Maps.Map1;
import HarapAlb.Maps.Map2;
import HarapAlb.States.InGameState;
import HarapAlb.Utils.Directions;
import HarapAlb.Utils.TemporaryEntity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Hero extends Entity {
    private final Animation animIdleRight;
    private final Animation animIdleLeft;
    private final Animation animRunRight;
    private final Animation animRunLeft;
    private final Animation animJumpRight;
    private final Animation animJumpLeft;
    private final Animation animAttackRight;
    private final Animation animAttackLeft;
    private final Animation animDamageRight;
    private final Animation animDamageLeft;
    private final Animation animDeadRight;
    private final Animation animDeadLeft;
    public static int health;
    public static boolean GAMEOVER;
    public static int numOfCoins;
    public static int numOfSmallPotions;
    public static int numOfBigPotions;

    public Hero(RefLinks rfl, float x, float y) {
        super(rfl, x, y, Entity.DEFAULT_WIDTH, Entity.DEFAULT_HEIGHT);

        this.animIdleRight = new Animation(rfl,100, Assets.playerIdleRight);
        this.animIdleLeft = new Animation(rfl,100, Assets.playerIdleLeft);
        this.animRunRight = new Animation(rfl,100, Assets.playerRunRight);
        this.animRunLeft = new Animation(rfl,100, Assets.playerRunLeft);
        this.animJumpRight = new Animation(rfl,70, Assets.playerJumpRight);
        this.animJumpLeft = new Animation(rfl,70, Assets.playerJumpLeft);
        this.animAttackRight = new Animation(rfl,150, Assets.playerAttackRight);
        this.animAttackLeft = new Animation(rfl,150, Assets.playerAttackLeft);
        this.animDamageRight = new Animation(rfl,140, Assets.playerDamageRight);
        this.animDamageLeft = new Animation(rfl,140, Assets.playerDamageLeft);
        this.animDeadRight = new Animation(rfl,150, Assets.playerDeadRight);
        this.animDeadLeft = new Animation(rfl,150, Assets.playerDeadLeft);
    }

    private void GetInput() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTimeAnimAttack;

        if (!isOnIce) {
            xMove = 0.0f;
        }

        yMove = 0.0f;

        if (!refLinks.GetKBInputs().up){
            canJump = false;
        }

        if (canJump && refLinks.GetKBInputs().up) {
            yMove = -DEFAULT_SPEED - 1.5f;

            isFalling = true;
            isJumping = true;
            verticalSpeed = 0.0f;
        }
        if (refLinks.GetKBInputs().right && !refLinks.GetKBInputs().left) {
            currentDirection = Directions.RIGHT;
            xMove = DEFAULT_SPEED * currentDirection*frictionOnX;
        }
        if (refLinks.GetKBInputs().left && !refLinks.GetKBInputs().right) {
            currentDirection = Directions.LEFT;
            xMove = DEFAULT_SPEED * currentDirection*frictionOnX;
        }
        if (elapsedTime >= 1250 && refLinks.GetKBInputs().space && !refLinks.GetKBInputs().right && !refLinks.GetKBInputs().left && !isJumping) {
            isAttacking = true;
            canGiveDamage = true;
        }
        if (isOnIce && !refLinks.GetKBInputs().left && !refLinks.GetKBInputs().right) {
            if (Math.abs(xMove) >= 0.1f){
                xMove -= currentDirection * 0.1f;
            }
            else{
                xMove = 0;
            }
        }
        if (refLinks.GetKBInputs().one && numOfSmallPotions != 0){
            if (health < 3) {
                health += SmallHealthPotion.HeartsToRestore;
                --numOfSmallPotions;
            }
        }
        if (refLinks.GetKBInputs().two && numOfBigPotions != 0){
            if (health == 2) {
                health += BigHealthPotion.HeartsToRestore - 1;
                --numOfBigPotions;
            }
            else if (health < 2){
                health += BigHealthPotion.HeartsToRestore;
                --numOfBigPotions;
            }
        }
    }

    protected void HandleFalling() {
        if (!isOnIce) {
            xMove = 0.0f;
        }

        yMove = 0.0f;

        if (!IsCollisionDownY() && isFalling) {
            isOnIce = false;

            if (IsCollisionUpY()) {
                verticalSpeed = (float) MAX_VELOCITY / 3 * 2;
            }
            if (verticalSpeed + GRAVITY > MAX_VELOCITY) {
                verticalSpeed = MAX_VELOCITY;
            }
            else {
                if (verticalSpeed > (float) MAX_VELOCITY / 2) {
                    verticalSpeed += GRAVITY / 1.5;
                }
                if (refLinks.GetKBInputs().left || refLinks.GetKBInputs().right) {
                    verticalSpeed += GRAVITY / 2.5;
                }
                if (isFalling && !refLinks.GetKBInputs().up) {
                    verticalSpeed -= GRAVITY / 1.5;
                }
                verticalSpeed += GRAVITY;
            }

            yMove += verticalSpeed;
        }
        else {
            yOnDownCollision = y;

            if (isFalling) {
                yMove += 3;
            }

            verticalSpeed = 0.0f;

            isFalling = false;
            isJumping = false;
            canJump = true;
        }
    }

    private void HandleBeingAttacked() {
        for (Entity currentEntity : refLinks.GetMap().GetEntityManager().GetEntityList()){
            if (currentEntity.canGiveDamage){
                isTakingDamage = true;
                TemporaryEntity.Entity = currentEntity;

                break;
            } else {
                isTakingDamage = false;
            }
        }
    }

    @Override
    protected void UpdateBounds() {
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
    }

    @Override
    protected void DrawBounds(Graphics g) {
        g.setColor(Color.PINK);
        g.drawRect((int)(bounds.x-refLinks.GetGameCamera().GetXOffs()),(int)(bounds.y-refLinks.GetGameCamera().GetYOffs()), bounds.width, bounds.height);

        g.setColor(Color.RED);
        g.drawRect((int)(attackingBounds.x-refLinks.GetGameCamera().GetXOffs()),(int)(attackingBounds.y-refLinks.GetGameCamera().GetYOffs()), attackingBounds.width, attackingBounds.height);
    }
    
    @Override
    public void Update() {
        HandleBeingAttacked();

        if (isDying) {
            if (animDeadRight.GetAnimIndex() < animDeadRight.GetFrameLength() - 1 || animDeadLeft.GetAnimIndex() < animDeadLeft.GetFrameLength() - 1){
                animDeadLeft.Update();
                animDeadRight.Update();

                health = 0;
            }
            else{
                GAMEOVER = true;
                InGameState.startTimeGameOver = System.currentTimeMillis();
            }

            return;
        }

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

        if (animAttackRight.GetAnimIndex() == animAttackRight.GetFrameLength() - 1 || animAttackLeft.GetAnimIndex() == animAttackLeft.GetFrameLength() - 1){
            animAttackLeft.Update();
            animAttackRight.Update();
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
                    TemporaryEntity.Entity.canGiveDamage = false;

                    health -= 1;
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
            animJumpRight.Update();
            animJumpLeft.Update();
            animDamageRight.Update();
            animDamageLeft.Update();

            HandleFalling();
            Move();
            UpdateBounds();
            GetInput();
            Move();

            refLinks.GetGameCamera().CenterOn(this);

            UpdateBounds();
        }
    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(GetCurrentAnimationFrame(), (int) (x - refLinks.GetGameCamera().GetXOffs()), (int) (y - refLinks.GetGameCamera().GetYOffs()), width, height, null);

        if (Game.OverlayDisplay) {
            DrawBounds(g);
        }

        if (refLinks.GetMap().GetKey() != null && refLinks.GetMap().GetKey().GetIsCollected()) {
            if (refLinks.GetMap() instanceof Map1) {
                g.drawImage(Assets.silverKey, (int) (x + 13 - refLinks.GetGameCamera().GetXOffs()), (int) (y - 19 - refLinks.GetGameCamera().GetYOffs()), 12, 16, null);
            }
            else if (refLinks.GetMap() instanceof Map2){
                g.drawImage(Assets.goldKey, (int) (x + 13 - refLinks.GetGameCamera().GetXOffs()), (int) (y - 19 - refLinks.GetGameCamera().GetYOffs()), 12, 16, null);
            }
            else{
                g.drawImage(Assets.mythrilKey, (int) (x + 13 - refLinks.GetGameCamera().GetXOffs()), (int) (y - 19 - refLinks.GetGameCamera().GetYOffs()), 12, 16, null);
            }
        }
    }

    private BufferedImage GetCurrentAnimationFrame() {
        if (isDying){
            if (currentDirection == Directions.RIGHT) {
                return animDeadRight.GetCurrentFrame();
            }
            else if (currentDirection == Directions.LEFT) {
                return animDeadLeft.GetCurrentFrame();
            }
        }
        if (isTakingDamage){
            if (currentDirection == Directions.RIGHT){
                return animDamageRight.GetCurrentFrame();
            } else {
                return animDamageLeft.GetCurrentFrame();
            }
        }
        if (isOnIce && xMove != DEFAULT_SPEED * currentDirection*frictionOnX && !isAttacking){
            if (currentDirection == Directions.RIGHT) {
                return animIdleRight.GetCurrentFrame();
            }
            else if (currentDirection == Directions.LEFT) {
                return animIdleLeft.GetCurrentFrame();
            }
        }
        if (xMove > 0 && !isJumping) {
            return animRunRight.GetCurrentFrame();
        }
        if (xMove < 0 && !isJumping) {
            return animRunLeft.GetCurrentFrame();
        }
        if (isJumping) {
            if (currentDirection == Directions.RIGHT) {
                return animJumpRight.GetCurrentFrame();
            }
            else if (currentDirection == Directions.LEFT) {
                return animJumpLeft.GetCurrentFrame();
            }
        }
        if (isAttacking) {
            if (currentDirection == Directions.RIGHT){
                return animAttackRight.GetCurrentFrame();
            }
            else if (currentDirection == Directions.LEFT) {
                return animAttackLeft.GetCurrentFrame();
            }
        }
        if (currentDirection == Directions.RIGHT) {
            return animIdleRight.GetCurrentFrame();
        }
        else {
            return animIdleLeft.GetCurrentFrame();
        }
    }
    public int GetAttackRightAnimIndx(){
        return animAttackRight.GetAnimIndex();
    }

    public int GetAttackLeftAnimIndx(){
        return animAttackLeft.GetAnimIndex();
    }
}