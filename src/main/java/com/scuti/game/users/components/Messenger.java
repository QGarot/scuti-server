package com.scuti.game.users.components;

import com.scuti.game.users.friends.Buddy;

import java.util.ArrayList;
import java.util.List;

public class Messenger {
    private List<Buddy> buddies;
    private List<Integer> requests;

    public Messenger() {
        this.buddies = new ArrayList<>();
        this.requests = new ArrayList<>();
    }

    public List<Integer> getRequests() {
        return requests;
    }

    public List<Buddy> getBuddies() {
        return this.buddies;
    }

    public void addBuddy(Buddy buddy) {
        this.buddies.add(buddy);
    }

    public boolean isBuddy(int userId) {
        for (Buddy buddy: this.getBuddies()) {
            if (buddy.getId() == userId) {
                return true;
            }
        }
        return false;
    }

    public void deleteBuddy(Buddy buddy) {
        this.buddies.remove(buddy);
    }
}
