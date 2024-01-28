package com.scuti.messages;

import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.incoming.friendlist.*;
import com.scuti.messages.incoming.handshake.InfoRetrieveMessageComposer;
import com.scuti.messages.incoming.handshake.InitCryptoMessageEvent;
import com.scuti.messages.incoming.handshake.SSOTicketMessageEvent;
import com.scuti.messages.incoming.inventory.purse.GetCreditsInfoMessageEvent;
import com.scuti.messages.incoming.navigator.*;
import com.scuti.messages.incoming.register.UpdateFigureDataMessageEvent;
import com.scuti.messages.incoming.rooms.engine.GetFurnitureAliasesMessageEvent;
import com.scuti.messages.incoming.rooms.engine.GetRoomEntryDataMessageEvent;
import com.scuti.messages.incoming.rooms.session.OpenFlatConnectionMessageEvent;
import com.scuti.messages.incoming.tracking.EventLogMessageEvent;
import com.scuti.server.netty.streams.NettyRequest;
import com.scuti.game.users.User;
import com.scuti.util.logger.Logger;

import java.util.HashMap;

public class MessageHandler {
    private HashMap<Integer, MessageEvent> packets;
    private static MessageHandler instance;

    private MessageHandler() {
        this.packets = new HashMap<>();

        this.registerHandshake();
        this.registerTracking();
        this.registerNavigator();
        this.registerInventory();
        this.registerFriendList();
        this.registerRooms();
        this.registerRegister();
    }

    private void registerRegister() {
        this.packets.put(44, new UpdateFigureDataMessageEvent());
    }

    private void registerRooms() {
        this.packets.put(391, new OpenFlatConnectionMessageEvent());
        this.packets.put(215, new GetFurnitureAliasesMessageEvent());
        this.packets.put(390, new GetRoomEntryDataMessageEvent());
    }

    private void registerNavigator() {
        this.packets.put(430, new PopularRoomsSearchMessageEvent());
        this.packets.put(380, new GetOfficialRoomsMessageEvent());
        this.packets.put(434, new MyRoomsSearchMessageEvent());
        this.packets.put(387, new CanCreateRoomMessageEvent());
        this.packets.put(29, new CreateFlatMessageEvent());
        this.packets.put(385, new GetGuestRoomMessageEvent());
    }

    private void registerHandshake() {
        this.packets.put(206, new InitCryptoMessageEvent());
        this.packets.put(415, new SSOTicketMessageEvent());
        this.packets.put(7, new InfoRetrieveMessageComposer());
    }

    public void registerInventory() {
        this.packets.put(8, new GetCreditsInfoMessageEvent());
    }

    private void registerTracking() {
        this.packets.put(482, new EventLogMessageEvent());
    }

    private void registerFriendList() {
        this.packets.put(262, new FollowFriendMessageEvent());
        this.packets.put(12, new MessengerInitMessageEvent());
        this.packets.put(33, new SendMsgMessageEvent());
        this.packets.put(41, new HabboSearchMessageEvent());
        this.packets.put(39, new RequestBuddyMessageEvent());
    }

    public void handle(User user, NettyRequest clientMessage) {
        int header = clientMessage.getHeader();
        if (this.packets.containsKey(header)) {
            Logger.logIncoming(header);
            this.packets.get(header).handle(user, clientMessage);
        } else {
            Logger.logWarning("The packet " + header + " cannot be handled!");
        }
    }

    public static MessageHandler getInstance() {
        if (instance == null) {
            instance = new MessageHandler();
        }

        return instance;
    }
}
