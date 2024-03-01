package com.scuti.messages.outgoing.catalog;

import com.scuti.game.catalog.CatalogItem;
import com.scuti.game.catalog.CatalogManager;
import com.scuti.game.catalog.CatalogPage;
import com.scuti.game.items.ItemBase;
import com.scuti.game.items.ItemManager;
import com.scuti.messages.outgoing.MessageComposer;

import java.util.List;

public class CatalogPageMessageComposer extends MessageComposer {
    private final CatalogPage catalogPage;

    public CatalogPageMessageComposer(CatalogPage catalogPage) {
        this.getResponse().setHeader(127);
        this.catalogPage = catalogPage;
    }

    public CatalogPage getCatalogPage() {
        return catalogPage;
    }

    @Override
    public void compose() {
        this.getResponse().appendInt32(this.getCatalogPage().getId());
        String layout = this.getCatalogPage().getLayout();

        if (layout == "frontpage") {
            this.getResponse().appendStringWithBreak("frontpage3");
            this.getResponse().appendInt32(3);
            this.getResponse().appendStringWithBreak(this.getCatalogPage().getLayoutHeadline());
            this.getResponse().appendStringWithBreak(this.getCatalogPage().getLayoutTeaser());
            this.getResponse().appendStringWithBreak("");
            this.getResponse().appendInt32(6);
            this.getResponse().appendStringWithBreak(this.getCatalogPage().getText1());
            this.getResponse().appendStringWithBreak(this.getCatalogPage().getTextLinkDesc());
            this.getResponse().appendStringWithBreak(this.getCatalogPage().getText2());
            this.getResponse().appendStringWithBreak(this.getCatalogPage().getTextDetails());
            this.getResponse().appendStringWithBreak(this.getCatalogPage().getTextTeaser());
            this.getResponse().appendStringWithBreak("Code: ");
        } else if (layout == "club_buy") {
            this.getResponse().appendStringWithBreak(this.getCatalogPage().getLayout());
            this.getResponse().appendInt32(1);
            this.getResponse().appendStringWithBreak("habboclub_2");
            this.getResponse().appendInt32(1);
        } else {
            this.getResponse().appendStringWithBreak(this.getCatalogPage().getLayout());
            this.getResponse().appendInt32(3);
            this.getResponse().appendStringWithBreak(this.getCatalogPage().getLayoutHeadline());
            this.getResponse().appendStringWithBreak(this.getCatalogPage().getLayoutTeaser());
            this.getResponse().appendStringWithBreak(this.getCatalogPage().getLayoutSpecial());
            this.getResponse().appendInt32(3);
            this.getResponse().appendStringWithBreak(this.getCatalogPage().getText1());
            this.getResponse().appendStringWithBreak(this.getCatalogPage().getTextDetails());
            this.getResponse().appendStringWithBreak(this.getCatalogPage().getTextTeaser());
        }

        List<CatalogItem> items = CatalogManager.getInstance().getCatalogItemsOfPage(this.getCatalogPage().getId());
        this.getResponse().appendInt32(items.size());
        for (CatalogItem item: items) {
            this.getResponse().appendInt32(item.getId());
            this.getResponse().appendStringWithBreak(item.getName());
            this.getResponse().appendInt32(item.getCreditsCost());
            // TODO: edit sql structure for activity point type
            this.getResponse().appendInt32(item.getPointsCost()); // price in activity points
            this.getResponse().appendInt32(item.getPointType()); // 0 for pixels, 4 for shells
            this.getResponse().appendInt32(1);

            // furniture
            ItemBase furniture = ItemManager.getInstance().getItemBaseById(item.getItemId());
            this.getResponse().appendStringWithBreak(furniture.getType());
            this.getResponse().appendInt32(furniture.getSpriteId());
            this.getResponse().appendStringWithBreak(""); // extra data
            this.getResponse().appendInt32(item.getAmount());
            this.getResponse().appendInt32(-1);
            this.getResponse().appendInt32(item.getVip());
        }
    }
}
