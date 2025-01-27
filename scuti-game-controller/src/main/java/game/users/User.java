package game.users;

import entities.users.IUserDetails;

public class User implements IUser {

    private IUserDetails details;
    private boolean disposed;

    public User(IUserDetails details) {
        this.details = details;
        this.disposed = false;
    }

    @Override
    public IUserDetails getDetails() {
        return this.details;
    }

    @Override
    public void dispose() {
        this.details = null;
        this.disposed = true;
    }

    @Override
    public boolean isDisposed() {
        return this.disposed;
    }
}
