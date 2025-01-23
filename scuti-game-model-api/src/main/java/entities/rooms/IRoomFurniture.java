package entities.rooms;

import entities.IEntity;

public interface IRoomFurniture extends IEntity {
    /**
     * Returns the id.
     * @return id
     */
    int getId();

    /**
     * Returns the user id
     * @return user id
     */
    int getUserId();

    /**
     * Sets the user id.
     * @param userId: new user id
     */
    void setUserId(int userId);

    /**
     * Returns the room id
     * @return room id
     */
    int getRoomId();

    /**
     * Sets the room id.
     * @param roomId: new room id
     */
    void setRoomId(int roomId);

    /**
     * Returns the furniture id
     * @return furniture id
     */
    int getFurnitureId();

    /**
     * Sets the furniture id.
     * @param furnitureId: new furniture id
     */
    void setFurnitureId(int furnitureId);

    /**
     * Returns the x location
     * @return x location
     */
    int getX();

    /**
     * Sets the x location.
     * @param x: new x location
     */
    void setX(int x);

    /**
     * Returns the y location
     * @return y location
     */
    int getY();

    /**
     * Sets the y location.
     * @param y: new y location
     */
    void setY(int y);

    /**
     * Returns the z location
     * @return z location
     */
    int getZ();

    /**
     * Sets the z location.
     * @param z: new z location
     */
    void setZ(int z);

    /**
     * Returns the rotation
     * @return rotation
     */
    int getRotation();

    /**
     * Sets the rotation.
     * @param rotation: new rotation
     */
    void setRotation(int rotation);
}
