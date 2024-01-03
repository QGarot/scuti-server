package com.scuti.game.users.messenger.users;

public class Buddy extends ConsoleAvatar {
    private int categoryId;
    private String facebookId;

    public Buddy(int id, String username, int gender, boolean online, boolean followingAllowed, String figure, int categoryId, String motto, String lastLogin, String facebookId) {
        super(id, username, gender, online, followingAllowed, figure, motto, lastLogin);
        this.categoryId = categoryId;
        this.facebookId = facebookId;
    }

    public int getCategoryId() {
        if (this.categoryId > 0) {
            return this.categoryId;
        } else {
            return this.isOnline() ? 0 : -1;
        }
    }

    public String getFacebookId() {
        return facebookId;
    }
}
