package me.stelios.object.objects;

import me.stelios.GamePanel;
import me.stelios.inventory.Inventory;
import me.stelios.object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Chest extends SuperObject {

    public Chest(SuperObject item) {
        obj = item;
        name = "Chest";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
        } catch (IOException e) { e.printStackTrace(); }

        collision = true;
    }

    public Chest() {
        name = "Chest";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
        } catch (IOException e) { e.printStackTrace(); }

        collision = true;
    }
}
