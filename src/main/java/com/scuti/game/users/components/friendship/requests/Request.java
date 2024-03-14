package com.scuti.game.users.components.friendship.requests;

public class Request {
    private int id;
    private final int fromUserId;

    public Request(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getFromUserId() {
        return fromUserId;
    }
}
