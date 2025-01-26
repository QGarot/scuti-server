package services.users;

import services.IService;

import java.util.List;

public interface IUserService extends IService {
    /**
     * Returns all online users.
     * @return a list of logged users
     */
    List<IUser> getOnlineUsers();

    /**
     * Gets user by user id.
     * @param userId: id of searched user
     * @return user
     */
    IUser getUser(int userId);

    /**
     * Gets user by username.
     * @param username: username of searched user
     * @return user
     */
    IUser getUser(String username);

    /**
     * Checks if the SSO ticket is valid.
     * If it is valid, then a IUser instance is created.
     * The user is ready to be connected.
     * @param ticket: sso ticket
     * @return if the connection was successfully established, then the user id is returned. Else, -1 is returned.
     */
    int authentication(String ticket);
}
