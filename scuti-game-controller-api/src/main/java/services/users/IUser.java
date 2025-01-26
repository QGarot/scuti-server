package services.users;

import disposable.Disposable;
import entities.users.IUserDetails;

public interface IUser extends Disposable {
    /**
     * Returns user details.
     * @return user details
     */
    IUserDetails getDetails();
}
