package com.scuti.game.rooms.components.mapping;

public class RoomModel {
    private String name;
    private int doorX;
    private int doorY;
    private int doorZ;
    private int doorRotation;
    private String heightmap;
    private boolean clubOnly;

    public RoomModel(String name, int doorX, int doorY, int doorZ, int doorRotation, String heightmap, boolean clubOnly) {
        this.name = name;
        this.doorX = doorX;
        this.doorY = doorY;
        this.doorZ = doorZ;
        this.doorRotation = doorRotation;
        this.heightmap = heightmap;
        this.clubOnly = clubOnly;

        this.parse();
    }

    public String getName() {
        return name;
    }

    public int getDoorX() {
        return doorX;
    }

    public int getDoorY() {
        return doorY;
    }

    public int getDoorZ() {
        return doorZ;
    }

    public int getDoorRotation() {
        return doorRotation;
    }

    public String getHeightmap() {
        return heightmap;
    }

    public boolean isClubOnly() {
        return clubOnly;
    }

    private int[][] generate_array(int x, int y) {
        int[][] matrix = new int[y][x];

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                matrix[i][j] = 0;
            }
        }

        return matrix;
    }

    public void parse() {
        String[] lines = this.getHeightmap().split("\r\n");
        int mapSizeY = lines.length;
        int mapSizeX = lines[0].length();
        int[][] tileStates = this.generate_array(mapSizeX, mapSizeY);
        int[][] tileHeights = this.generate_array(mapSizeX, mapSizeY);
        String temporaryHeightmap = "";
        String line;
        Character tile;

        for (int y = 0; y < mapSizeY; y++) {
            line = lines[y];
            for (int x = 0; x < mapSizeX; x++) {
                tile = line.charAt(x);

                try {
                    tileHeights[y][x] = Integer.parseInt(String.valueOf(tile));
                    tileStates[y][x] = 1;
                } catch (NumberFormatException e) {
                    tileHeights[y][x] = 0;
                    tileStates[y][x] = 0;
                }

                if (x == this.getDoorX() && y == this.getDoorY()) {
                    tileStates[y][x] = 1;
                    tileHeights[y][x] = this.getDoorZ();
                }

                temporaryHeightmap = temporaryHeightmap.concat(String.valueOf(tile));
            }
            temporaryHeightmap = temporaryHeightmap.concat("\r");
        }
        this.heightmap = temporaryHeightmap;
    }
}
