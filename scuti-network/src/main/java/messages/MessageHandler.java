package messages;

import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.incoming.catalog.GetCatalogIndexMessageEvent;
import com.scuti.messages.incoming.catalog.GetCatalogPageMessageEvent;
import com.scuti.messages.incoming.catalog.GetIsOfferGiftableMessageEvent;
import com.scuti.messages.incoming.catalog.PurchaseFromCatalogMessageEvent;
import com.scuti.messages.incoming.friendlist.*;
import com.scuti.messages.incoming.handshake.InfoRetrieveMessageEvent;
import com.scuti.messages.incoming.handshake.InitCryptoMessageEvent;
import com.scuti.messages.incoming.handshake.SSOTicketMessageEvent;
import com.scuti.messages.incoming.inventory.purse.GetCreditsInfoMessageEvent;
import com.scuti.messages.incoming.navigator.*;
import com.scuti.messages.incoming.register.UpdateFigureDataMessageEvent;
import com.scuti.messages.incoming.rooms.chat.ChatMessageEvent;
import com.scuti.messages.incoming.rooms.engine.GetFurnitureAliasesMessageEvent;
import com.scuti.messages.incoming.rooms.engine.GetRoomEntryDataMessageEvent;
import com.scuti.messages.incoming.rooms.session.OpenFlatConnectionMessageEvent;
import com.scuti.messages.incoming.tracking.EventLogMessageEvent;
import com.scuti.server.netty.connections.NettyConnection;
import com.scuti.server.netty.streams.NettyRequest;
import com.scuti.util.logger.Logger;
import logger.Logger;
import messages.incoming.MessageEvent;
import server.connections.IConnection;
import server.streams.IRequest;

import java.util.HashMap;

public class MessageHandler implements IHandler {

    private final HashMap<Integer, MessageEvent> packets;

    private static MessageHandler instance;

    public MessageHandler() {
        this.packets = new HashMap<>();

        this.registerHandshake();
        this.registerTracking();
        this.registerNavigator();
        this.registerInventory();
        this.registerFriendList();
        this.registerRooms();
//        this.registerRegister();
        this.registerCatalog();
    }

    /**
     * Registers all catalog message events.
     */
    private void registerCatalog() {
        this.packets.put(101, new GetCatalogIndexMessageEvent());
        this.packets.put(102, new GetCatalogPageMessageEvent());
        this.packets.put(3030, new GetIsOfferGiftableMessageEvent());
        this.packets.put(100, new PurchaseFromCatalogMessageEvent());
    }

//    private void registerRegister() {
//        this.packets.put(44, new UpdateFigureDataMessageEvent());
//    }

    /**
     * Registers all room message events.
     */
    private void registerRooms() {
        this.packets.put(391, new OpenFlatConnectionMessageEvent());
        this.packets.put(215, new GetFurnitureAliasesMessageEvent());
        this.packets.put(390, new GetRoomEntryDataMessageEvent());
        this.packets.put(52, new ChatMessageEvent());
    }

    /**
     * Registers all navigator message events.
     */
    private void registerNavigator() {
        this.packets.put(430, new PopularRoomsSearchMessageEvent());
        this.packets.put(380, new GetOfficialRoomsMessageEvent());
        this.packets.put(434, new MyRoomsSearchMessageEvent());
        this.packets.put(387, new CanCreateRoomMessageEvent());
        this.packets.put(29, new CreateFlatMessageEvent());
        this.packets.put(385, new GetGuestRoomMessageEvent());
    }

    /**
     * Registers all handshake message events.
     */
    private void registerHandshake() {
        this.packets.put(206, new InitCryptoMessageEvent());
        this.packets.put(415, new SSOTicketMessageEvent());
        this.packets.put(7, new InfoRetrieveMessageEvent());
    }

    /**
     * Registers all inventory message events.
     */
    public void registerInventory() {
        this.packets.put(8, new GetCreditsInfoMessageEvent());
    }

    /**
     * Registers all tracking message events.
     */
    private void registerTracking() {
        this.packets.put(482, new EventLogMessageEvent());
    }

    /**
     * Registers all friend list message events.
     */
    private void registerFriendList() {
        this.packets.put(262, new FollowFriendMessageEvent());
        this.packets.put(12, new MessengerInitMessageEvent());
        this.packets.put(33, new SendMsgMessageEvent());
        this.packets.put(41, new HabboSearchMessageEvent());
        this.packets.put(39, new RequestBuddyMessageEvent());
    }

    @Override
    public void handle(IConnection connection, IRequest clientMessage) {
        int header = clientMessage.getHeader();
        if (this.packets.containsKey(header)) {
            Logger.logIncoming(header);
            this.packets.get(header).handle(connection, clientMessage);
        } else {
            Logger.logWarning("The packet " + header + " cannot be handled!");
        }
    }
}
