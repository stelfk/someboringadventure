package me.stelios.object.objects;

import me.stelios.object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Axe extends SuperObject {
    public Axe() {
        name = "Axe";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/axe.png"));
        } catch (IOException e) { e.printStackTrace(); }
    }
}
