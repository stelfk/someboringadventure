package me.stelios.inventory;

import me.stelios.GamePanel;
import me.stelios.object.SuperObject;
import me.stelios.object.objects.Key;
import me.stelios.object.objects.PotionHP;
import me.stelios.object.objects.Sword;

import java.awt.*;

public class Inventory {

    public SuperObject[] inv;
    GamePanel gp;

    public static int selected = 0;

    public Inventory(SuperObject[] inv, GamePanel gp) {
        this.inv = inv;
        this.gp = gp;

        setupInv();
    }

    private void setupInv() {
        //Items
    }

    public boolean hasItem(SuperObject o) {
        for (int i = 0; i < gp.playerInv.length; i++) {
            if (gp.playerInv[i] != null) {
                String name = gp.playerInv[i].name;
                if (name == o.name) {
                    return true;
                }
            }
        }
        return false;
    }

    public void add(SuperObject o) {
        for (int i = 0; i < 5; i++) {
            if (gp.playerInv[i] == null) {
                gp.playerInv[i] = o;
                break;
            }
        }
    }

    public boolean hasKey() {
        for (SuperObject o : inv) {
            if (o != null && o.name == "Key") {
                return true;
            }
        }
        return false;
    }

    public void render(Graphics2D g) {

        Stroke oldStroke = g.getStroke();
        g.setStroke(new BasicStroke(5));

        for (int i = 0; i < 5; i++) {

            if (i == selected) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.BLACK);
            }

            //g.drawRoundRect(5, i * (gp.tileSize + gp.tileSize / 2) + 5, gp.tileSize, gp.tileSize, 5, 5);
            g.drawRoundRect(i * (gp.tileSize + gp.tileSize) + 5, (gp.maxWorldRow * 10), gp.tileSize, gp.tileSize, 5, 5);
        }

        g.setStroke(oldStroke);

        int y = 0;

        for (SuperObject o : inv) {
            if (o == null) {
                continue;
            }
            //g.drawImage(o.image, 5, y * (gp.tileSize + gp.tileSize / 2) + 5, gp.tileSize, gp.tileSize, null);
            g.drawImage(o.image, y * (gp.tileSize + gp.tileSize) + 5, (gp.maxWorldRow * 10), gp.tileSize, gp.tileSize, null);
            y++;
        }
    }

}
