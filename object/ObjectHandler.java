package me.stelios.object;

import me.stelios.GamePanel;
import me.stelios.object.objects.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ObjectHandler {

    GamePanel gp;

    public List<SuperObject> objects = new ArrayList<>();

    public void implementObjects() {
        objects.add(new Axe());
        objects.add(new Chest());
        objects.add(new Door());
        objects.add(new Joe());
        objects.add(new JoesHead());
        objects.add(new Key());
        objects.add(new PotionHP());
        objects.add(new Sword());
        objects.add(new Tree());
        objects.add(new WoodShield());
    }

    public ObjectHandler(GamePanel gp) {
        this.gp = gp;
        implementObjects();
    }

    public void placeObjects() {

        for (int o = 0; o < gp.obj.length; o++) {
            if (gp.obj[o] != null) {
                gp.obj[o] = null;
            }
        }

        switch (gp.tileManager.currentMap ) {

            case "map02":
                gp.obj[0] = new Key();
                gp.obj[0].worldX = 19 * gp.tileSize;
                gp.obj[0].worldY = 2 * gp.tileSize;

                gp.obj[1] = new Door();
                gp.obj[1].worldX = 21 * gp.tileSize;
                gp.obj[1].worldY = 22 * gp.tileSize;

                gp.obj[2] = new Chest(new Sword());
                gp.obj[2].worldX = 21 * gp.tileSize;
                gp.obj[2].worldY = 19 * gp.tileSize;

                gp.obj[3] = new Chest(new Axe());
                gp.obj[3].worldX = 22 * gp.tileSize;
                gp.obj[3].worldY = 2 * gp.tileSize;

                gp.obj[4] = new Tree();
                gp.obj[4].worldX = 34 * gp.tileSize;
                gp.obj[4].worldY = 8 * gp.tileSize;

                gp.obj[5] = new Tree();
                gp.obj[5].worldX = 20 * gp.tileSize;
                gp.obj[5].worldY = 2 * gp.tileSize;

                gp.obj[6] = new Tree();
                gp.obj[6].worldX = 35 * gp.tileSize;
                gp.obj[6].worldY = 8 * gp.tileSize;

                gp.obj[7] = new Tree();
                gp.obj[7].worldX = 36 * gp.tileSize;
                gp.obj[7].worldY = 8 * gp.tileSize;

                gp.obj[8] = new Chest(new WoodShield());
                gp.obj[8].worldX = 26 * gp.tileSize;
                gp.obj[8].worldY = 3 * gp.tileSize;

                gp.obj[9] = new Joe();
                gp.obj[9].worldX = 37 * gp.tileSize;
                gp.obj[9].worldY = 27 * gp.tileSize;
                break;
        }
    }

}
