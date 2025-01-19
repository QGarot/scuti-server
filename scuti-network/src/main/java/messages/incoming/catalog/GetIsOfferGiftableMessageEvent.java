package messages.incoming.catalog;

import com.scuti.game.catalog.CatalogItem;
import com.scuti.game.catalog.CatalogManager;
import com.scuti.game.items.ItemBase;
import com.scuti.game.items.ItemManager;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.outgoing.catalog.IsOfferGiftableMessageComposer;
import com.scuti.server.netty.connections.NettyConnection;
import com.scuti.server.netty.streams.NettyRequest;

public class GetIsOfferGiftableMessageEvent extends MessageEvent {
    @Override
    public void handle(NettyConnection connection, NettyRequest clientMessage) {
        int id = clientMessage.popWiredInt32(); // catalog item id
        CatalogItem catalogItem = CatalogManager.getInstance().getCatalogItemById(id);
        ItemBase furniture = ItemManager.getInstance().getItemBaseById(catalogItem.getItemId());
        if (furniture != null) {
            connection.send(new IsOfferGiftableMessageComposer(id, furniture.allowGift()));
        }
    }
}
