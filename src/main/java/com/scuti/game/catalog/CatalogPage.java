package com.scuti.game.catalog;

public class CatalogPage {
    private int id;
    private int parentId;
    private String caption;
    private boolean visible;
    private boolean enabled;
    private int minRank;
    private boolean clubOnly;
    private int iconColor;
    private int iconImg;
    private String layout;
    private String layoutHeadline;
    private String layoutTeaser;
    private String layoutSpecial;
    private String text1;
    private String text2;
    private String textDetails;
    private String textTeaser;
    private String textLinkDesc;
    private String textLinkPage;

    public CatalogPage(int id, int parentId, String caption, boolean visible, boolean enabled, int minRank, boolean clubOnly, int iconColor, int iconImg, String layout, String layoutHeadline, String layoutTeaser, String layoutSpecial, String text1, String text2, String textDetails, String textTeaser, String textLinkDesc, String textLinkPage) {
        this.id = id;
        this.parentId = parentId;
        this.caption = caption;
        this.visible = visible;
        this.enabled = enabled;
        this.minRank = minRank;
        this.clubOnly = clubOnly;
        this.iconColor = iconColor;
        this.iconImg = iconImg;
        this.layout = layout;
        this.layoutHeadline = layoutHeadline;
        this.layoutTeaser = layoutTeaser;
        this.layoutSpecial = layoutSpecial;
        this.text1 = text1;
        this.text2 = text2;
        this.textDetails = textDetails;
        this.textTeaser = textTeaser;
        this.textLinkDesc = textLinkDesc;
        this.textLinkPage = textLinkPage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getMinRank() {
        return minRank;
    }

    public void setMinRank(int minRank) {
        this.minRank = minRank;
    }

    public boolean isClubOnly() {
        return clubOnly;
    }

    public void setClubOnly(boolean clubOnly) {
        this.clubOnly = clubOnly;
    }

    public int getIconColor() {
        return iconColor;
    }

    public void setIconColor(int iconColor) {
        this.iconColor = iconColor;
    }

    public int getIconImg() {
        return iconImg;
    }

    public void setIconImg(int iconImg) {
        this.iconImg = iconImg;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getLayoutHeadline() {
        return layoutHeadline;
    }

    public void setLayoutHeadline(String layoutHeadline) {
        this.layoutHeadline = layoutHeadline;
    }

    public String getLayoutTeaser() {
        return layoutTeaser;
    }

    public void setLayoutTeaser(String layoutTeaser) {
        this.layoutTeaser = layoutTeaser;
    }

    public String getLayoutSpecial() {
        return layoutSpecial;
    }

    public void setLayoutSpecial(String layoutSpecial) {
        this.layoutSpecial = layoutSpecial;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getTextDetails() {
        return textDetails;
    }

    public void setTextDetails(String textDetails) {
        this.textDetails = textDetails;
    }

    public String getTextTeaser() {
        return textTeaser;
    }

    public void setTextTeaser(String textTeaser) {
        this.textTeaser = textTeaser;
    }

    public String getTextLinkDesc() {
        return textLinkDesc;
    }

    public void setTextLinkDesc(String textLinkDesc) {
        this.textLinkDesc = textLinkDesc;
    }

    public String getTextLinkPage() {
        return textLinkPage;
    }

    public void setTextLinkPage(String textLinkPage) {
        this.textLinkPage = textLinkPage;
    }
}
