package me.stelios.misc;

import me.stelios.GamePanel;
import me.stelios.entity.Entity;
import me.stelios.object.ObjectHandler;
import me.stelios.object.SuperObject;

public class CollisionHandler {

    GamePanel gp;

    public CollisionHandler(GamePanel gp) {

        this.gp = gp;

    }

    public void checkTile(Entity e) {

        int eLeftWorldX = e.worldX + e.solidArea.x;
        int eRightWorldX = e.worldX + e.solidArea.x + e.solidArea.width;
        int eTopWorldY = e.worldY + e.solidArea.y;
        int eBottomWorldY = e.worldY + e.solidArea.y + e.solidArea.height;

        int eLeftCol = eLeftWorldX / gp.tileSize;
        int eRightCol = eRightWorldX / gp.tileSize;
        int eTopRow = eTopWorldY / gp.tileSize;
        int eBottomRow = eBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (e.direction) {
            case "up":
                eTopRow = (eTopWorldY - e.speed) / gp.tileSize;

                tileNum1 = gp.tileManager.mapTileNum[eLeftCol][eTopRow];
                tileNum2 = gp.tileManager.mapTileNum[eRightCol][eTopRow];

                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    e.collisionOn = true;
                }

                break;

            case "down":
                eBottomRow = (eBottomWorldY + e.speed) / gp.tileSize;

                tileNum1 = gp.tileManager.mapTileNum[eLeftCol][eBottomRow];
                tileNum2 = gp.tileManager.mapTileNum[eRightCol][eBottomRow];

                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    e.collisionOn = true;
                }

                break;

            case "left":
                eLeftCol = (eLeftWorldX - e.speed) / gp.tileSize;

                tileNum1 = gp.tileManager.mapTileNum[eLeftCol][eTopRow];
                tileNum2 = gp.tileManager.mapTileNum[eLeftCol][eBottomRow];

                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    e.collisionOn = true;
                }

                break;

            case "right":
                eRightCol = (eRightWorldX + e.speed) / gp.tileSize;

                tileNum1 = gp.tileManager.mapTileNum[eRightCol][eTopRow];
                tileNum2 = gp.tileManager.mapTileNum[eRightCol][eBottomRow];

                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    e.collisionOn = true;
                }

                break;
        }
    }

    public int checkObj(Entity e, boolean player) {
        int index = 999;

        for (int i = 0; i < gp.obj.length; i++) {
            SuperObject o = gp.obj[i];

            if (o != null) {
                //Get entity's solid area pos
                e.solidArea.x = e.worldX + e.solidArea.x;
                e.solidArea.y = e.worldY + e.solidArea.y;
                //Get object's solid area pos
                o.solidArea.x = o.worldX + o.solidArea.x;
                o.solidArea.y = o.worldY + o.solidArea.y;

                switch (e.direction) {
                    case "up":
                        e.solidArea.y -= e.speed;
                        if (e.solidArea.intersects(o.solidArea)) {
                            if (o.collision) e.collisionOn = true;
                            if (player) index = i;
                        }
                        break;
                    case "down":
                        e.solidArea.y += e.speed;
                        if (e.solidArea.intersects(o.solidArea)) {
                            if (o.collision) e.collisionOn = true;
                            if (player) index = i;
                        }
                        break;
                    case "left":
                        e.solidArea.x -= e.speed;
                        if (e.solidArea.intersects(o.solidArea)) {
                            if (o.collision) e.collisionOn = true;
                            if (player) index = i;
                        }
                        break;
                    case "right":
                        e.solidArea.x += e.speed;
                        if (e.solidArea.intersects(o.solidArea)) {
                            if (o.collision) e.collisionOn = true;
                            if (player) index = i;
                        }
                        break;
                }
                e.solidArea.x = e.solidAreaDefaultX;
                e.solidArea.y = e.solidAreaDefaultY;

                o.solidArea.x = o.solidAreaDefaultX;
                o.solidArea.y = o.solidAreaDefaultY;
            }
        }

        return index;
    }

}
