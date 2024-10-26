package me.stelios.tile;

import me.stelios.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    public String currentMap;

    GamePanel gp;
    public Tile[] tile;

    public int[][] mapTileNum;

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[38];

        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        toggleCollisions(true);

        loadMap("map02");
        currentMap = "map02";

    }

    public void getTileImage() {

        try {

            for (int i = 0; i < 38; i++) {
                tile[i] = new Tile();
                tile[i].image = ImageIO.read(getClass().getResourceAsStream("/tiles/0" + (i < 10 ? "0" + i : i) + ".png"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void toggleCollisions(boolean toggle) {

        int colidableTiles[] = {16, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 35, 36, 37};

        for (int t : colidableTiles) {
            tile[t].collision = toggle;
        }

    }

    public void loadMap(String map) {

        try {

            InputStream is = getClass().getResourceAsStream("/maps/" + map + ".txt");
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                while (col < gp.maxWorldCol) {

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;

                }

                if (col == gp.maxWorldCol) {

                    col = 0;
                    row++;

                }

            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void render(Graphics2D g) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;

            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX) {
                if (worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                    g.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                }
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {

                worldCol = 0;
                worldRow++;

            }

        }

    }

}
