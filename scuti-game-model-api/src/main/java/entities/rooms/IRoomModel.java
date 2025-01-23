package entities.rooms;

import entities.IEntity;

public interface IRoomModel extends IEntity {
    /**
     * Sets the name.
     * @param name: new name
     */
    void setName(String name);

    /**
     * Sets the X door location.
     * @param doorX: new X door location
     */
    void setDoorX(int doorX);

    /**
     * Sets the Y door location.
     * @param doorY: new Y door location
     */
    void setDoorY(int doorY);

    /**
     * Sets the Z door location.
     * @param doorZ: new Z door location
     */
    void setDoorZ(int doorZ);

    /**
     * Sets the door rotation.
     * @param doorRotation: new door rotation
     */
    void setDoorRotation(int doorRotation);

    /**
     * Sets the heightmap.
     * @param heightmap: new heightmap
     */
    void setHeightmap(String heightmap);

    /**
     * Sets if this model is only for HC members.
     * @param clubOnly: new boolean
     */
    void setClubOnly(boolean clubOnly);

    /**
     * Returns the name.
     * @return name
     */
    String getName();

    /**
     * Returns the X door location.
     * @return X door location
     */
    int getDoorX();

    /**
     * Returns the Y door location.
     * @return Y door location
     */
    int getDoorY();

    /**
     * Returns the Z door location.
     * @return Z door location
     */
    int getDoorZ();

    /**
     * Returns the door rotation.
     * @return door rotation
     */
    int getDoorRotation();

    /**
     * Returns the heightmap.
     * @return heightmap
     */
    String getHeightmap();

    /**
     * Returns true if this model is only for HC members.
     * @return club only
     */
    boolean isClubOnly();
}
