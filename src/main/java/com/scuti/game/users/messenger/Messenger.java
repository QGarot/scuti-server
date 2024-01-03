package com.scuti.game.users.messenger;

import com.scuti.game.users.messenger.requests.Request;
import com.scuti.game.users.messenger.users.Buddy;

import java.util.ArrayList;
import java.util.List;

public class Messenger {
    private List<Buddy> buddies;
    private List<Request> receivedRequests;

    public Messenger() {
        this.buddies = new ArrayList<>();
        this.receivedRequests = new ArrayList<>();
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
    }
}
