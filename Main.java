package me.stelios;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        //Creating a window
        JFrame window = new JFrame("SomeBoringAdventure");
        window.setDefaultCloseOperation(3); //Choosing what the close button does(=3 closes the game)
        window.setResizable(false);
        //Adding the game screen
        GamePanel gp = new GamePanel();
        window.add(gp);
        //Resizing window to fit components
        window.pack();
        //Displaying the window
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        //Starting the game0
        gp.setupGame();
        gp.startThread();

    }

}
