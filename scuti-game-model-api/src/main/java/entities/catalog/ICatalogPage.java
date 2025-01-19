package entities.catalog;

import entities.IEntity;

public interface ICatalogPage extends IEntity {
    /**
     * Returns the id.
     * @return id
     */
    int getId();

    /**
     * Returns the parent id.
     * @return parent id
     */
    int getParentId();

    /**
     * Sets the parent id.
     * @param parentId: new parent id
     */
    void setParentId(int parentId);

    /**
     * Returns the caption.
     * @return caption
     */
    String getCaption();

    /**
     * Sets the caption.
     * @param caption: new caption
     */
    void setCaption(String caption);

    /**
     * Returns whether the page is visible.
     * @return visibility
     */
    boolean isVisible();

    /**
     * Sets the visibility.
     * @param visible: new state
     */
    void setVisible(boolean visible);

    /**
     * Returns whether the page is enabled.
     * @return page enabled
     */
    boolean isEnabled();

    /**
     * Sets the availability.
     * @param enabled: new state
     */
    void setEnabled(boolean enabled);

    /**
     * Returns the minimum rank needed to see the page.
     * @return minimum rank
     */
    int getMinRank();

    /**
     * Sets the minimum rank needed to see the page.
     * @param minRank: new minimum rank
     */
    void setMinRank(int minRank);

    /**
     * Returns whether club adhesion is needed to see the page.
     * @return club only
     */
    boolean isClubOnly();

    /**
     * Sets the accessibility.
     * @param clubOnly: whether club only is needed
     */
    void setClubOnly(boolean clubOnly);

    /**
     * Returns the icon color id.
     * @return icon color id
     */
    int getIconColor();

    /**
     * Sets the icon color.
     * @param iconColor: new icon color
     */
    void setIconColor(int iconColor);

    /**
     * Returns the icon image id.
     * @return icon image id
     */
    int getIconImg();

    /**
     * Sets the icon image.
     * @param iconImg: new icon image
     */
    void setIconImg(int iconImg);

    /**
     * Returns the catalog page layout.
     * @return layout
     */
    String getLayout();

    /**
     * Sets the layout.
     * @param layout: new layout
     */
    void setLayout(String layout);

    /**
     * Returns the layout headline.
     * @return layout headline
     */
    String getLayoutHeadline();

    /**
     * Sets the layout headline.
     * @param layoutHeadline: new layout headline
     */
    void setLayoutHeadline(String layoutHeadline);

    /**
     * Returns the layout teaser.
     * @return layout teaser
     */
    String getLayoutTeaser();

    /**
     * Sets the layout teaser.
     * @param layoutTeaser: new layout teaser
     */
    void setLayoutTeaser(String layoutTeaser);

    /**
     * Returns the layout special.
     * @return layout special
     */
    String getLayoutSpecial();

    /**
     * Sets the layout special.
     * @param layoutSpecial: new layout special
     */
    void setLayoutSpecial(String layoutSpecial);

    /**
     * Returns the text 1.
     * @return text 1
     */
    String getText1();

    /**
     * Sets the text 1.
     * @param text1: new text 1
     */
    void setText1(String text1);

    /**
     * Returns the text 2.
     * @return text 2
     */
    String getText2();

    /**
     * Sets the text 2.
     * @param text2: new text 2
     */
    void setText2(String text2);

    /**
     * Returns the text details.
     * @return text details
     */
    String getTextDetails();

    /**
     * Sets the text details.
     * @param textDetails: new details
     */
    void setTextDetails(String textDetails);

    /**
     * Returns the text teaser.
     * @return text teaser
     */
    String getTextTeaser();

    /**
     * Sets the text teaser.
     * @param textTeaser: new teaser
     */
    void setTextTeaser(String textTeaser);

    /**
     * Returns the text description attached to the link.
     * @return link description
     */
    String getTextLinkDesc();

    /**
     * Sets the text link description.
     * @param textLinkDesc: new link description
     */
    void setTextLinkDesc(String textLinkDesc);

    /**
     * Returns the text link.
     * @return text link
     */
    String getTextLinkPage();

    /**
     * Sets the text link page.
     * @param textLinkPage: new link
     */
    void setTextLinkPage(String textLinkPage);
}
