package entities.users;

import entities.IEntity;

public interface IUserDetails extends IEntity {
    /**
     * Sets the online status.
     * @param bool: new online status
     */
    void setOnline(boolean bool);

    /**
     * Sets the username.
     * @param username: new username
     */
    void setUsername(String username);

    /**
     * Sets the email.
     * @param email: new email
     */
    void setEmail(String email);

    /**
     * Sets the figure.
     * @param figure: new figure
     */
    void setFigure(String figure);

    /**
     * Sets the motto.
     * @param motto: new motto
     */
    void setMotto(String motto);

    /**
     * Sets the sex.
     * @param sex: new gender
     */
    void setSex(String sex);

    /**
     * Sets the sso ticket.
     * @param ssoTicket: new sso ticket
     */
    void setSsoTicket(String ssoTicket);

    /**
     * Sets the rank.
     * @param rank: new rank
     */
    void setRank(int rank);

    /**
     * Adds some credits.
     * @param credits: credits amount
     */
    void addCredits(int credits);

    /**
     * Adds some pixels.
     * @param pixels: credits amount
     */
    void addPixels(int pixels);

    /**
     * Adds some shells.
     * @param shells: shells amount
     */
    void addShells(int shells);

    /**
     * Sets the respect.
     * @param respect: new respect
     */
    void setRespect(int respect);

    /**
     * Sets the daily respect points.
     * @param dailyRespectPoints: new daily respect points
     */
    void setDailyRespectPoints(int dailyRespectPoints);

    /**
     * Returns the id.
     * @return id
     */
    int getId();

    /**
     * Returns the username.
     * @return username
     */
    String getUsername();

    /**
     * Returns the email.
     * @return email
     */
    String getEmail();

    /**
     * Returns the figure.
     * @return figure
     */
    String getFigure();

    /**
     * Returns the motto.
     * @return motto
     */
    String getMotto();

    /**
     * Returns the sex.
     * @return sex
     */
    String getSex();

    /**
     * Returns the sso ticket.
     * @return sso
     */
    String getSsoTicket();

    /**
     * Returns the rank.
     * @return rank
     */
    int getRank();

    /**
     * Returns the credits amount.
     * @return credits amount
     */
    int getCredits();

    /**
     * Returns the pixels amount.
     * @return pixels amount
     */
    int getPixels();

    /**
     * Returns the shells amount.
     * @return shells amount
     */
    int getShells();

    /**
     * Returns the respect.
     * @return respect
     */
    int getRespect();

    /**
     * Returns the daily respect points.
     * @return daily respect points
     */
    int getDailyRespectPoints();

    /**
     * Returns whether the user is online.
     * @return online status
     */
    boolean isOnline();
}
