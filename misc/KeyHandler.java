package me.stelios.misc;

import me.stelios.GamePanel;
import me.stelios.inventory.Actions;
import me.stelios.inventory.Inventory;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean keyAction = false;

    public boolean showQuest = false;

    GamePanel gp;

    public KeyHandler(GamePanel gp) { this.gp = gp; }

    //Not useful
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        //Direction keys
        if (!gp.msbHandler.isActive) {
            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }

            switch (code) {

                case KeyEvent.VK_1:
                    Inventory.selected = 0;
                    break;

                case KeyEvent.VK_2:
                    Inventory.selected = 1;
                    break;

                case KeyEvent.VK_3:
                    Inventory.selected = 2;
                    break;

                case KeyEvent.VK_4:
                    Inventory.selected = 3;
                    break;

                case KeyEvent.VK_5:
                    Inventory.selected = 4;
                    break;
            }
        }

        if (code == KeyEvent.VK_V) {
            keyAction = true;
        }

        //GUI

        if (gp.invHandler.toggled) {
            switch (code) {
                case KeyEvent.VK_UP:
                    if (!gp.invHandler.extended) gp.invHandler.selected--;
                    if (gp.invHandler.extended) gp.invHandler.eSelected--;
                    break;
                case KeyEvent.VK_DOWN:
                    if (!gp.invHandler.extended) gp.invHandler.selected++;
                    if (gp.invHandler.extended) gp.invHandler.eSelected++;
                    break;
                case KeyEvent.VK_ENTER:
                    if (!gp.invHandler.extended) gp.invHandler.extended = true;
                    if (gp.invHandler.extended) {
                        switch (gp.invHandler.eSelected) {


                            case 1:
                                gp.invHandler.action = Actions.Trash;
                                break;
                            case 2:
                                gp.invHandler.action = Actions.Cancel;
                                break;
                            case 3:
                                gp.invHandler.action = Actions.Equip;
                                break;

                        }
                    }
                    break;
                case KeyEvent.VK_ESCAPE:
                    if (!gp.invHandler.extended) gp.invHandler.toggled = false;
                    else if (gp.invHandler.extended) gp.invHandler.extended = false;
                    break;
            }
        }

        switch (code) {
            case KeyEvent.VK_Q:
                showQuest = !showQuest;
                break;
            case KeyEvent.VK_I:
                gp.invHandler.toggled = !gp.invHandler.toggled;

                if (gp.invHandler.toggled) {
                    gp.invHandler.extended = false;
                }
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        //Direction keys
        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            rightPressed = false;
        }

    }

}
