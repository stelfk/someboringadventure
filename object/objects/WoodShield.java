package me.stelios.object.objects;

import me.stelios.object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;

public class WoodShield extends SuperObject {
    public WoodShield() {
        name = "WoodShield";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/shield_wood.png"));
        } catch (IOException e) { e.printStackTrace(); }

        collision = true;
    }
}
