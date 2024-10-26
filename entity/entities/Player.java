package me.stelios.entity.entities;

import me.stelios.GamePanel;
import me.stelios.entity.Entity;
import me.stelios.inventory.Inventory;
import me.stelios.misc.KeyHandler;
import me.stelios.object.SuperObject;
import me.stelios.object.objects.JoesHead;
import me.stelios.object.objects.Key;
import me.stelios.object.objects.Sword;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    KeyHandler keyHandler;
    GamePanel gp;

    public final int screenX;
    public final int screenY;

    public final int maxHP = 4;
    public int hp = 4;

    public Player(GamePanel gp, KeyHandler keyHandler) {

        this.gp = gp;
        this.keyHandler = keyHandler;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidAreaDefaultX = 8;
        solidAreaDefaultY = 16;

        solidArea = new Rectangle(8, 16, 32, 32);

        setDefaultValues();
        getPlayerImage();

    }

    public void getPlayerImage() {
        try {

            //Up
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/playerUp1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/playerUp2.png"));
            //Down
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/playerDown1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/playerDown2.png"));
            //Left
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/playerLeft1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/playerLeft2.png"));
            //Right
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/playerRight1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/playerRight2.png"));

        } catch (IOException e) { e.printStackTrace(); }
    }

    public void setDefaultValues() {

        switch (gp.tileManager.currentMap) {

            case  "map02":
                speed = 2;
                worldX = 32 * gp.tileSize;
                worldY = 8 * gp.tileSize;

                direction = "down";
                break;
        }

    }

    public void action(boolean act, int index) {
        boolean breakLoop = false;

        for (int i = 0; i < gp.playerInv.length; i++) {
            if (breakLoop) break;

            boolean valid = i == gp.hotBar.selected ? !(gp.playerInv[i] == null) : false;

            if (act && valid && !gp.invHandler.toggled) {

                switch (gp.playerInv[i].name) {

                    case "HPPotion":
                        if (hp < maxHP) {
                            gp.playerInv[i] = null;
                            hp += 1;
                            breakLoop = true;
                        }
                        break;

                }

                if (index != 999 && gp.obj[index] != null) {
                    SuperObject o = gp.playerInv[i];

                    switch (gp.obj[index].name) {

                        case "Joe":

                            if (o != null) {
                                if (o.name == "Sword" && i == Inventory.selected && act) {
                                    gp.obj[index] = null;
                                    gp.invHandler.addItem(new JoesHead(), 1);
                                    breakLoop = true;
                                }
                            }
                            break;

                        case "Door":
                            if (o != null) {
                                if (o.name == "Key" && i == Inventory.selected && act) {
                                    gp.playerInv[i] = null;
                                    gp.obj[index] = null;
                                    breakLoop = true;
                                }
                            }
                            break;

                        case "Tree":
                            if (o != null) {
                                if (o.name =="Axe" && i == Inventory.selected && act) {
                                    gp.obj[index] = null;
                                    breakLoop = true;
                                }
                            }
                            break;

                    }
                }

            } else if (index != 999 && gp.obj[index] != null && act) {
                // Text
                switch (gp.obj[index].name) {

                    case "Joe":
                        if (gp.msbHandler.cBox == 1) gp.msbHandler.isActive = true;
                        break;

                    default: break;
                }
            }
        }
        keyHandler.keyAction = false;
    }

    public void pickupObject(int index) {
        if (index != 999) {

            boolean breakLoop = false;

            for (int i = 0; i < gp.playerInv.length; i++) {
                if (breakLoop) break;

                SuperObject o = gp.playerInv[i];

                switch (gp.obj[index].name) {

                    case "Chest":
                        gp.invHandler.addItem(gp.obj[index].obj, 1);
                        gp.obj[index] = null;
                        breakLoop = true;
                        break;

                    case "Key":
                        gp.invHandler.addItem(new Key(), 1);
                        gp.obj[index] = null;
                        breakLoop = true;
                        break;

                    case "Door":
                        if (o != null) {
                            if (o.name == "Key" && i == Inventory.selected && keyHandler.keyAction) {
                                gp.playerInv[i] = null;
                                gp.obj[index] = null;
                                breakLoop = true;
                            }
                        }
                        break;

                    case "Sword":
                        gp.invHandler.addItem(new Sword(), 1);
                        gp.obj[index] = null;
                        breakLoop = true;
                        break;

                }
            }
        }
    }

    public void update() {

            int oIndex = gp.collisionHandler.checkObj(this, true);
            action(keyHandler.keyAction, oIndex);

            if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {

            //Bindings
            //if (!gp.msbHandler.isActive) {
                if (keyHandler.upPressed) {
                    direction = "up";
                } else if (keyHandler.downPressed) {
                    direction = "down";
                } else if (keyHandler.leftPressed) {
                    direction = "left";
                } else if (keyHandler.rightPressed) {
                    direction = "right";
                }

                //Check collision
                collisionOn = false;
                gp.collisionHandler.checkTile(this);

                //Check object collision
                oIndex = gp.collisionHandler.checkObj(this, true);

                pickupObject(oIndex);

                if (!collisionOn) {
                    switch (direction) {
                        case "up":
                            worldY -= speed;
                            break;

                        case "down":
                            worldY += speed;
                            break;

                        case "left":
                            worldX -= speed;
                            break;

                        case "right":
                            worldX += speed;
                            break;
                    }
                }

                spriteCounter++;
                if (spriteCounter > 10) {
                    if (spriteNum == 1) {
                        spriteNum = 2;
                    } else if (spriteNum == 2) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            //}

        }
    }

    public void render(Graphics2D g) {

        BufferedImage image = null;

        switch (direction) {

            case "up":
                if (spriteNum == 1) {
                    image = up1;
                } else if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                } else if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                } else if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                } else if (spriteNum == 2) {
                    image = right2;
                }
                break;

        }

        //Idle
        if (image == left1 || image == left2) {
            if (!keyHandler.leftPressed) {
                image = left1;
            }
        } else if (image == right1 || image == right2) {
            if (!keyHandler.rightPressed) {
                image = right1;
            }
        }

        g.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        BufferedImage fullHeart = null, halfHeart = null, emptyHeart = null;

        try {
            fullHeart = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
            halfHeart = ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
            emptyHeart = ImageIO.read(getClass().getResourceAsStream("/objects/heart_blank.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (hp) {
            case 4:
                for (int i = 0; i < 2; i++) {
                    g.drawImage(fullHeart, (i * gp.tileSize) + 5, 5, gp.tileSize, gp.tileSize, null);
                }
                break;
            case 3:
                g.drawImage(fullHeart, 5, 5, gp.tileSize, gp.tileSize, null);
                g.drawImage(halfHeart, (gp.tileSize) + 5, 5, gp.tileSize, gp.tileSize, null);
                break;
            case 2:
                g.drawImage(fullHeart, 5, 5, gp.tileSize, gp.tileSize, null);
                g.drawImage(emptyHeart, (gp.tileSize) + 5, 5, gp.tileSize, gp.tileSize, null);
                break;
            case 1:
                g.drawImage(halfHeart, 5, 5, gp.tileSize, gp.tileSize, null);
                g.drawImage(emptyHeart, (gp.tileSize) + 5, 5, gp.tileSize, gp.tileSize, null);
                break;
            case 0:
                g.drawImage(emptyHeart, 5, 5, gp.tileSize, gp.tileSize, null);
                g.drawImage(emptyHeart, (gp.tileSize) + 5, 5, gp.tileSize, gp.tileSize, null);
                break;
        }

    }

}
