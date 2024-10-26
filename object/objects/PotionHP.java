package me.stelios.object.objects;

import me.stelios.object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;

public class PotionHP extends SuperObject {
    public PotionHP() {
        name = "HPPotion";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/potion_red.png"));
        } catch (IOException e) { e.printStackTrace(); }
    }
}
