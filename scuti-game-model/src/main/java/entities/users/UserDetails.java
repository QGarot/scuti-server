package entities.users;

public class UserDetails implements IUserDetails {

    private final int id;
    private String username;
    private String email;
    private String figure;
    private String motto;
    private String sex;
    private String ssoTicket;
    private int rank;
    private int credits;
    private int pixels;
    private int shells;
    private int respect;
    private int dailyRespectPoints;
    private boolean online;

    public UserDetails(int id, String username, String email, String figure, String motto, String sex, String ssoTicket, int rank, int credits, int pixels, int shells, boolean online) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.figure = figure;
        this.motto = motto;
        this.sex = sex;
        this.ssoTicket = ssoTicket;
        this.rank = rank;
        this.credits = credits;
        this.pixels = pixels;
        this.shells = shells;
        this.respect = 0;
        this.dailyRespectPoints = 3;
        this.online = online;
    }

    @Override
    public void setOnline(boolean bool) {
        this.online = bool;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setFigure(String figure) {
        this.figure = figure;
    }

    @Override
    public void setMotto(String motto) {
        this.motto = motto;
    }

    @Override
    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public void setSsoTicket(String ssoTicket) {
        this.ssoTicket = ssoTicket;
    }

    @Override
    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public void addCredits(int credits) {
        this.credits = this.credits + credits;
    }

    @Override
    public void addPixels(int pixels) {
        this.pixels = this.pixels + pixels;
    }

    @Override
    public void addShells(int shells) {
        this.shells = this.shells + shells;
    }

    @Override
    public void setRespect(int respect) {
        this.respect = respect;
    }

    @Override
    public void setDailyRespectPoints(int dailyRespectPoints) {
        this.dailyRespectPoints = dailyRespectPoints;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getFigure() {
        return this.figure;
    }

    @Override
    public String getMotto() {
        return this.motto;
    }

    @Override
    public String getSex() {
        return this.sex;
    }

    @Override
    public String getSsoTicket() {
        return this.ssoTicket;
    }

    @Override
    public int getRank() {
        return this.rank;
    }

    @Override
    public int getCredits() {
        return this.credits;
    }

    @Override
    public int getPixels() {
        return this.pixels;
    }

    @Override
    public int getShells() {
        return this.shells;
    }

    @Override
    public int getRespect() {
        return this.respect;
    }

    @Override
    public int getDailyRespectPoints() {
        return this.dailyRespectPoints;
    }

    @Override
    public boolean isOnline() {
        return this.online;
    }
}
