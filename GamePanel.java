package me.stelios;

import me.stelios.entity.entities.Player;
import me.stelios.inventory.Inventory;
import me.stelios.inventory.InventorySystem;
import me.stelios.messageBox.MessageBoxHandler;
import me.stelios.misc.CollisionHandler;
import me.stelios.misc.KeyHandler;
import me.stelios.quest.QuestHandler;
import me.stelios.tile.TileManager;
import me.stelios.object.ObjectHandler;
import me.stelios.object.SuperObject;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //Display settings
    final int ogTileSize = 16;
    final int scale = 3;

    public final int tileSize = ogTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    int FPS = 60;

    //World settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    //Handlers
    public KeyHandler keyHandler = new KeyHandler(this);
    public TileManager tileManager = new TileManager(this);
    public CollisionHandler collisionHandler = new CollisionHandler(this);
    public ObjectHandler oHandler = new ObjectHandler(this);
    public QuestHandler questHandler = new QuestHandler(this);
    public InventorySystem invHandler = new InventorySystem(this);
    public MessageBoxHandler msbHandler = new MessageBoxHandler(this);

    //Objects
    public SuperObject[] obj = new SuperObject[10];
    public SuperObject[] playerInv = new SuperObject[5];
    //public SuperObject[] playerWeapons = new SuperObject[2];

    //Entities
    public Player player = new Player(this, keyHandler);

    //Misc
    public void setupGame() {
        oHandler.placeObjects();
    }

    public Inventory hotBar = new Inventory(playerInv, this);

    //Thread
    Thread gThread;

    public void startThread() {

        gThread = new Thread(this);
        gThread.start();

    }

    public void update() {

        //if (!keyHandler.showQuest)
        msbHandler.update();
        player.update();
        invHandler.update();
        questHandler.update();

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tileManager.render(g2);

        for (SuperObject o : obj) {
            if (o != null) {
                o.render(g2, this);
            }
        }

        player.render(g2);

        if (!msbHandler.isActive) {
            questHandler.render(g2);
            hotBar.render(g2);
            invHandler.render(g2);
        }

        msbHandler.render(g2);

        g2.dispose();

    }

    @Override
    public void run() {

        //Game Loop
        long lastTime = System.nanoTime();
        double ns = 1000000000 / (double)FPS;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while(gThread != null){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                update();
                updates++;
                delta--;
            }
            repaint();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println(/*"FPS: " + frames + */" TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }

    }

    public GamePanel() {

        //Screen
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(0, 0, 0));
        this.setFocusable(true);
        //Better rendering
        this.setDoubleBuffered(true);
        //Handlers
        this.addKeyListener(keyHandler);

    }

}
