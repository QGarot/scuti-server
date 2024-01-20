package com.scuti.game.rooms.components.mapping;

public class RoomModel {
    private String name;
    private int doorX;
    private int doorY;
    private int doorZ;
    private int doorRotation;
    private String heightmap;
    private boolean clubOnly;
    private int mapSizeX;
    private int mapSizeY;
    private int[][] tileStates;
    private int[][] tileHeights;

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

    public int getMapSizeX() {
        return mapSizeX;
    }

    public int getMapSizeY() {
        return mapSizeY;
    }

    public int[][] getTileStates() {
        return tileStates;
    }

    public int[][] getTileHeights() {
        return tileHeights;
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
        this.mapSizeY = lines.length;
        this.mapSizeX = lines[0].length();
        this.tileStates = this.generate_array(mapSizeX, mapSizeY);
        this.tileHeights = this.generate_array(mapSizeX, mapSizeY);
        String temporaryHeightmap = "";
        String line;
        Character tile;

        for (int y = 0; y < this.getMapSizeY(); y++) {
            line = lines[y];
            for (int x = 0; x < this.getMapSizeX(); x++) {
                tile = line.charAt(x);

                try {
                    this.tileHeights[y][x] = Integer.parseInt(String.valueOf(tile));
                    this.tileStates[y][x] = 1;
                } catch (NumberFormatException e) {
                    this.tileHeights[y][x] = 0;
                    this.tileStates[y][x] = 0;
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
