package com.scuti.messages.outgoing.friendlist;

import com.scuti.game.users.components.friendship.users.UserSearched;
import com.scuti.messages.outgoing.MessageComposer;

import java.util.List;

public class HabboSearchMessageComposer extends MessageComposer {
    private List<UserSearched> friends;
    private List<UserSearched> others;
    public HabboSearchMessageComposer(List<UserSearched> friends, List<UserSearched> others) {
        this.getResponse().setHeader(435);
        this.friends = friends;
        this.others = others;
    }

    public List<UserSearched> getFriends() {
        return friends;
    }

    public List<UserSearched> getOthers() {
        return others;
    }

    public void serialize(UserSearched user) {
        this.getResponse().appendInt32(user.getId()); // id
        this.getResponse().appendStringWithBreak(user.getUsername()); // name
        this.getResponse().appendStringWithBreak(user.getMotto()); // motto
        this.getResponse().appendBoolean(user.isOnline()); // online
        if (user.isOnline()) {
            this.getResponse().appendBoolean(user.isFollowingAllowed()); // following allowed
        } else {
            this.getResponse().appendBoolean(false);
        }
        this.getResponse().appendStringWithBreak(""); // ?
        this.getResponse().appendInt32(user.getGender()); // gender
        this.getResponse().appendStringWithBreak(user.getFigure()); // figure
        this.getResponse().appendStringWithBreak(user.getLastLogin()); // last login
        this.getResponse().appendStringWithBreak(""); // real name
    }

    @Override
    public void compose() {
        this.getResponse().appendInt32(this.getFriends().size());
        for (UserSearched user: this.getFriends()) {
            this.serialize(user);
        }

        this.getResponse().appendInt32(this.getOthers().size());
        for (UserSearched user: this.getOthers()) {
            this.serialize(user);
        }
    }
}
