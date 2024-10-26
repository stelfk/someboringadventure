package me.stelios.messageBox;

import me.stelios.GamePanel;
import me.stelios.messageBox.messageBoxes.JoeQuestline.Intro;
import me.stelios.messageBox.messageBoxes.JoeQuestline.Joe001;

import java.awt.*;

public class MessageBoxHandler {

    GamePanel gp;
    public MessageBoxHandler(GamePanel gp) { this.gp = gp; }

    void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }

    public boolean goOn = false;

    public int cBox = 0;
    public int cLine = 0;
    public boolean isActive = true;
    public MessageBox[] messageBoxes = {
            // Intro == 0
            new Intro(),
            // Joe's quest line = 1
            new Joe001()
    };

    public void update() {
        if (isActive) {
            if (gp.keyHandler.keyAction) goOn = true;
            if (goOn) {
                goOn = false;
                gp.keyHandler.keyAction = false;
                if (cLine == (messageBoxes[cBox].text.length - 1)) {
                    // Finished first box
                    cBox++;
                    cLine = 0;
                    isActive = false;
                } else if (cLine < (messageBoxes[cBox].text.length - 1)) {
                    cLine++;
                }
            }
        }
    }

    public void render(Graphics2D g) {
        if (isActive) {

            g.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            // Draw box
            g.setColor(Color.BLACK);
            g.fillRoundRect(10, 450, gp.screenWidth - 20, 100, 5, 5);

            g.setColor(Color.WHITE);
            if (messageBoxes[cBox].messageType == Type.Death) g.setColor(Color.RED);
            g.drawRoundRect(10, 450, gp.screenWidth - 20, 100, 5, 5);

            // Draw text
            drawString(g, messageBoxes[cBox].text[cLine], 13, 465);
        }
    }

}
