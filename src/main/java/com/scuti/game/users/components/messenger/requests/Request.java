package com.scuti.game.users.components.messenger.requests;

public class Request {
    private int id;
    private int fromUserId;
    private int toUserId;

    public Request() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    public int getId() {
        return id;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public int getToUserId() {
        return toUserId;
    }
}
