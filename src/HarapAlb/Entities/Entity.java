package HarapAlb.Entities;

import HarapAlb.Main.RefLinks;
import HarapAlb.Tiles.Tile;
import HarapAlb.Utils.Directions;

import java.awt.*;

/*! \class public abstract class Entity extends Item
    \brief Defineste notiunea abstracta de caracter/individ/fiinta din joc.

 */

public abstract class Entity extends Item {
    public static final int DEFAULT_WIDTH = 32;         /*!< Latimea implicita a imaginii caracterului.*/
    public static final int DEFAULT_HEIGHT = 32;        /*!< Inaltimea implicita a imaginii caracterului.*/
    public final float GRAVITY = 0.25f;
    public int MAX_VELOCITY = 10;
    public int MAX_JUMP_HEIGHT = 80;
    public static final float DEFAULT_SPEED = 2.0f;     /*!< Viteza implicita a unui caracter.*/
    public float frictionOnX;
    protected float verticalSpeed;
    public boolean canJump;
    public boolean isJumping;
    protected boolean isFalling;
    protected float yOnDownCollision;
    protected float xMove;                              /*!< Retine noua pozitie a caracterului pe axa X.*/
    protected float yMove;                              /*!< Retine noua pozitie a caracterului pe axa Y.*/
    protected int currentDirection;
    protected Rectangle attackingBounds;
    protected boolean isAttacking;
    protected boolean isDying;
    protected boolean isDead;
    protected boolean isTakingDamage;
    protected boolean isAttacked;
    protected boolean isOnIce;
    protected long startTimeAnimAttack;
    protected boolean canGiveDamage;

    public Entity(RefLinks rfl, float x, float y, int width, int height) {
        super(rfl, x, y, width, height);

        this.xMove = 0;
        this.yMove = 0;

        this.verticalSpeed = 0.0f;

        this.currentDirection = Directions.RIGHT;

        this.isFalling = false;
        this.isJumping = false;
        this.canJump = true;
        this.isOnIce = false;
        this.isDying = false;
        this.isDead = false;
        this.isAttacking = false;
        this.isAttacked = false;

        this.attackingBounds = new Rectangle(bounds.x + bounds.width, (int)y, width/4, height);

        this.canGiveDamage = false;
    }

    private void MoveX(){
        if (xMove > 0) {//Playerul merge spre dreapta
            int tx = (int) (xMove + bounds.x + bounds.width-1) / Tile.TILE_WIDTH;//Muchia din dreapta a dreptunghiului de coliziune a playerului in pixeli/Tilewidth pentru a obtine pozitia in tiles.

            if (!CollisionWithTile(tx, bounds.y / Tile.TILE_WIDTH) && !CollisionWithTile(tx, (bounds.y + bounds.height-1) / Tile.TILE_HEIGHT)) {
                //Daca nu este coliziune pe muchia dreapta sus si jos
                if (bounds.x + bounds.width >= 1920) {
                    x = bounds.x;
                }
                else {
                    x += xMove;
                }
            }
            else {
                x = tx * Tile.TILE_WIDTH - bounds.width;//Seteaza pozitia playerlui langa Tile-1pixel pt a nu avea spatiu liber intre player si tile
            }
        }
        else if (xMove < 0) {//Playerul merge spre stanga
            int tx = (int) (xMove + bounds.x) / Tile.TILE_WIDTH;//Muchia din stanga

            if (!CollisionWithTile(tx, (bounds.y)/ Tile.TILE_WIDTH) && !CollisionWithTile(tx, (bounds.y + bounds.height-1) / Tile.TILE_HEIGHT)) {
                //Daca nu este coliziune pe muchia stanga sus si jos
                if (x < 0) {
                    x = bounds.x;
                }
                else {
                    x += xMove;
                }
            }
            else {
                x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH;
            }
        }
    }
    private void MoveY(){
        if (yMove < 0) {
            if (bounds.y + yMove < yOnDownCollision - MAX_JUMP_HEIGHT*frictionOnX) {
                canJump = false;
            }
            else {
                int ty = (int) (yMove + bounds.y) / Tile.TILE_HEIGHT;//Muchia de sus a coliziunii playerului

                if (!CollisionWithTile(bounds.x / Tile.TILE_WIDTH, ty) && (!CollisionWithTile((bounds.x + bounds.width-1) / Tile.TILE_WIDTH, ty))) {
                    //Daca nu este coliziune pe muchia de sus in dreapta si stanga
                    y += yMove;
                }
                else {
                    y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT;

                    canJump = false;
                }
            }
        } else if (yMove > 0) {//Playerul merge in jos
            int ty = (int) (bounds.y + yMove + bounds.height-1) / Tile.TILE_HEIGHT;//Muchia de jos

            if (!CollisionWithTile(bounds.x / Tile.TILE_WIDTH, ty) && (!CollisionWithTile(((bounds.x + bounds.width-1) / Tile.TILE_WIDTH), ty))) {
                //Daca nu este coliziune pe muchia de jos in dreapta si stanga
                y += yMove;
            }
            else {
                y = ty * Tile.TILE_HEIGHT - bounds.height;
            }
        }
    }

    private void MoveDown() {
        int ty = (int) (yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;

        if (!CollisionWithTile(bounds.x / Tile.TILE_WIDTH, ty) && (!CollisionWithTile((bounds.x + bounds.width-1) / Tile.TILE_WIDTH, ty))) {
            isFalling = true;
        }
    }

    public void Move() {
        MoveX();
        MoveY();

        MoveDown();
    }

    protected boolean CollisionWithTile(int x, int y) {
        return refLinks.GetMap().GetTile(x, y).IsSolid();
    }

    protected boolean IsCollisionDownY() {
        int ty = (bounds.y + 1 + bounds.height) / Tile.TILE_WIDTH;

        if (CollisionWithTile((bounds.x / Tile.TILE_WIDTH), ty) || CollisionWithTile((bounds.x + bounds.width-1) / Tile.TILE_WIDTH, ty)){
            Tile collisionDownYTile;

            if (currentDirection == Directions.RIGHT) {
                collisionDownYTile = refLinks.GetMap().GetTile((bounds.x + bounds.width - 16)/ Tile.TILE_WIDTH, ty);
            }
            else{
                collisionDownYTile = refLinks.GetMap().GetTile((bounds.x + 16) / Tile.TILE_WIDTH, ty);
            }

            frictionOnX = collisionDownYTile.GetFriction();

            isOnIce = collisionDownYTile.GetId() == 9;

            isDying = collisionDownYTile.IsDeadly();
        }

        return CollisionWithTile(bounds.x / Tile.TILE_WIDTH, ty) || (CollisionWithTile((bounds.x + bounds.width-1) / Tile.TILE_WIDTH, ty));
    }

    protected boolean IsCollisionUpY() {
        int ty = (bounds.y - 1) / Tile.TILE_HEIGHT;

        return CollisionWithTile(bounds.x / Tile.TILE_WIDTH, ty) || (CollisionWithTile((bounds.x + bounds.width-1) / Tile.TILE_WIDTH, ty));
    }



    public boolean IsCollisionRightX(){
        int tx=(int)(bounds.x+1+bounds.width)/ Tile.TILE_WIDTH;

        return CollisionWithTile(tx, (int) (bounds.y) / Tile.TILE_WIDTH) || CollisionWithTile(tx, (int) (bounds.y + bounds.height-1) / Tile.TILE_HEIGHT);
    }
    public boolean IsCollisionLeftX(){
        int tx=(int)(bounds.x-1)/ Tile.TILE_WIDTH;

        return CollisionWithTile(tx, (int) (bounds.y) / Tile.TILE_WIDTH) || CollisionWithTile(tx, (int) (bounds.y + bounds.height-1) / Tile.TILE_HEIGHT);
    }

    public boolean GetIsDead(){
        return isDead;
    }
    public abstract void Update();
    public abstract void Draw(Graphics g);
    protected abstract void DrawBounds(Graphics g);
    protected abstract void UpdateBounds();
}
