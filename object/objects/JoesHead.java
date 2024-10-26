package me.stelios.object.objects;

import me.stelios.object.SuperObject;

import javax.imageio.ImageIO;
import java.io.IOException;

public class JoesHead extends SuperObject {
    public JoesHead() {
        name = "JoesHead";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/JoesHead.png"));
        } catch (IOException e) { e.printStackTrace(); }

        collision = true;
    }
}
