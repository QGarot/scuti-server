package services.navigator.thumbnail;

public interface IThumbnail {
    /**
     * Returns the background image id.
     * @return background image id
     */
    int getBackgroundImage();

    /**
     * Returns the foreground image id.
     * @return foreground image id
     */
    int getForegroundImage();

    /**
     * TODO
     * @return
     */
    int getItemCount();

    /**
     * Returns the item position.
     * @return item position
     */
    int getItemPosition();

    /**
     * Returns the item image id.
     * @return item image id
     */
    int getItemImage();
}
