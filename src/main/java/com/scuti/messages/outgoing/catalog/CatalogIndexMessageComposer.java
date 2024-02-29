package com.scuti.messages.outgoing.catalog;

import com.scuti.game.catalog.CatalogPage;
import com.scuti.messages.outgoing.MessageComposer;

import java.util.List;
import java.util.TreeMap;

public class CatalogIndexMessageComposer extends MessageComposer {
    private List<CatalogPage> pages;

    public CatalogIndexMessageComposer(List<CatalogPage> pages) {
        this.getResponse().setHeader(126);
        this.pages = pages;
    }

    @Override
    public void compose() {
        this.getResponse().appendBoolean(true);
        this.getResponse().appendInt32(0);
        this.getResponse().appendInt32(0);
        this.getResponse().appendInt32(-1);
        this.getResponse().appendStringWithBreak("");
        this.getResponse().appendInt32(this.getTreeSize(1, -1));
        this.getResponse().appendBoolean(true);

        for (CatalogPage page: this.getPages()) {
            if (page.getParentId() == -1) {
                this.getResponse().appendInt32(page.getIconColor());
                this.getResponse().appendInt32(page.getIconImg());
                this.getResponse().appendInt32(page.getId());
                this.getResponse().appendStringWithBreak(page.getCaption());
                this.getResponse().appendInt32(this.getTreeSize(1, page.getId()));
                this.getResponse().appendBoolean(true);

                for (CatalogPage p: this.getPages()) {
                    if (p.getParentId() == page.getId()) {
                        this.getResponse().appendInt32(page.getIconColor());
                        this.getResponse().appendInt32(page.getIconImg());
                        this.getResponse().appendInt32(page.getId());
                        this.getResponse().appendStringWithBreak(page.getCaption());
                        this.getResponse().appendInt32(this.getTreeSize(1, page.getId()));
                        this.getResponse().appendBoolean(true);
                    }
                }
            }
        }
    }

    public List<CatalogPage> getPages() {
        return this.pages;
    }

    public int getTreeSize(int rank, int treeId) {
        int num = 0;
        for (CatalogPage page: this.getPages()) {
            if (page.getParentId() == treeId) {
                num = num + 1;
            }
        }
        return num;
    }
}
