package com.scuti.game.rooms.mapping;

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
        this.heightmap = heightmap.toLowerCase();
        this.clubOnly = clubOnly;

        this.parse();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDoorX(int doorX) {
        this.doorX = doorX;
    }

    public void setDoorY(int doorY) {
        this.doorY = doorY;
    }

    public void setDoorZ(int doorZ) {
        this.doorZ = doorZ;
    }

    public void setDoorRotation(int doorRotation) {
        this.doorRotation = doorRotation;
    }

    public void setClubOnly(boolean clubOnly) {
        this.clubOnly = clubOnly;
    }

    public void setMapSizeX(int mapSizeX) {
        this.mapSizeX = mapSizeX;
    }

    public void setMapSizeY(int mapSizeY) {
        this.mapSizeY = mapSizeY;
    }

    public void setTileStates(int[][] tileStates) {
        this.tileStates = tileStates;
    }

    public void setTileHeights(int[][] tileHeights) {
        this.tileHeights = tileHeights;
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

    public void setHeightmap(String newHeightMap) {
        this.heightmap = newHeightMap;
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
        this.setMapSizeY(lines.length);
        this.setMapSizeX(lines[0].length());
        this.setTileStates(this.generate_array(this.getMapSizeX(), this.getMapSizeY()));
        this.setTileHeights(this.generate_array(this.getMapSizeX(), this.getMapSizeY()));
        StringBuilder temporaryHeightmap = new StringBuilder();

        for (int y = 0; y < this.getMapSizeY(); y++) {
            String line = lines[y];
            for (int x = 0; x < this.getMapSizeX(); x++) {
                Character tile = line.charAt(x);

                try {
                    this.tileHeights[y][x] = Integer.parseInt(String.valueOf(tile));
                    this.tileStates[y][x] = 1;
                } catch (NumberFormatException e) {
                    this.tileHeights[y][x] = 0;
                    this.tileStates[y][x] = 0;
                }

                if (x == this.getDoorX() && y == this.getDoorY()) {
                    this.tileStates[y][x] = 1;
                    this.tileHeights[y][x] = this.getDoorZ();
                }

                temporaryHeightmap.append(tile);
            }

            temporaryHeightmap.append("\r");
        }

        String newHeightMap = temporaryHeightmap.toString();
        this.setHeightmap(newHeightMap);
    }
}
