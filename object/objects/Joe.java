package me.stelios.object.objects;

import me.stelios.object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Joe extends SuperObject {
    public Joe() {
        name = "Joe";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/JoeDown.png"));
        } catch (IOException e) { e.printStackTrace(); }

        collision = true;
    }
}
