package me.stelios.object.objects;

import me.stelios.object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Tree extends SuperObject {
    public Tree() {
        name = "Tree";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/tiles/016.png"));
        } catch (IOException e) { e.printStackTrace(); }

        collision = true;
    }
}
