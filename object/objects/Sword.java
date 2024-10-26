package me.stelios.object.objects;

import me.stelios.object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Sword extends SuperObject {
    public Sword() {
        name = "Sword";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/sword_normal.png"));
        } catch (IOException e) { e.printStackTrace(); }

        collision = true;
    }
}
