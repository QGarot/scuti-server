package messages.incoming.catalog;

import com.scuti.game.catalog.CatalogItem;
import com.scuti.game.catalog.CatalogManager;
import com.scuti.game.items.ItemBase;
import com.scuti.game.items.ItemManager;
import com.scuti.messages.incoming.MessageEvent;
import com.scuti.messages.outgoing.catalog.PurchaseOKMessageComposer;
import com.scuti.server.netty.connections.NettyConnection;
import com.scuti.server.netty.streams.NettyRequest;

public class PurchaseFromCatalogMessageEvent extends MessageEvent {
    @Override
    public void handle(NettyConnection connection, NettyRequest clientMessage) {
        int pageId = clientMessage.popWiredInt32();
        int id = clientMessage.popWiredInt32();
        String extra = clientMessage.popFixedString();

        CatalogItem catalogItem = CatalogManager.getInstance().getCatalogItemById(id);
        ItemBase itemBase = ItemManager.getInstance().getItemBaseById(catalogItem.getItemId());

        connection.send(new PurchaseOKMessageComposer(itemBase.getId(), itemBase.getItemName(), catalogItem.getCreditsCost(), catalogItem.getPointsCost(), catalogItem.getPointType(), itemBase.getType(), itemBase.getSpriteId(), extra, 1, -1, 0));
    }
}
