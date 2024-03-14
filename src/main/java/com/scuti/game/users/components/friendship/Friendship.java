package com.scuti.game.users.components.friendship;

import com.scuti.game.users.components.friendship.requests.Request;
import com.scuti.game.users.components.friendship.users.Buddy;

import java.util.List;

public class Friendship {
    private List<Buddy> buddies;
    private List<Request> receivedRequests;

    public Friendship(List<Buddy> buddies, List<Request> receivedRequests) {
        this.buddies = buddies;
        this.receivedRequests = receivedRequests;
    }

    public List<Request> getReceivedRequests() {
        return receivedRequests;
    }

    public List<Buddy> getBuddies() {
        return this.buddies;
    }

    public void addBuddy(Buddy buddy) {
        this.getBuddies().add(buddy);
    }

    public void addRequest(Request request) {
        this.getReceivedRequests().add(request);
    }

    public boolean isBuddy(int userId) {
        for (Buddy buddy: this.getBuddies()) {
            if (buddy.getId() == userId) {
                return true;
            }
        }
        return false;
    }

    public void dispose() {
        this.buddies.clear();
        this.receivedRequests.clear();
        this.buddies = null;
        this.receivedRequests = null;
    }
}
