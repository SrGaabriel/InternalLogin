package net.gabriel.internal.login.com.managers;

import net.gabriel.internal.login.com.InternalLogin;
import net.gabriel.internal.login.com.objects.User;

import java.util.HashMap;
import java.util.UUID;

public class UserManager {
    private HashMap<UUID, User> uuidUser = new HashMap<>();

    public UserManager() {}

    public User createUser(UUID arg0) {
        if (existsUser(arg0)) {
            return getUser(arg0);
        } else {
            User user = new User(arg0);
            uuidUser.put(arg0, user);
            return user;
        }
    }

    public User createUser(UUID arg0, String arg1) {
        if (existsUser(arg0)) {
            return getUser(arg0);
        } else {
            User user = new User(arg0, arg1);
            uuidUser.put(arg0, user);
            return user;
        }
    }

    public void deleteUser(User arg0) {
        uuidUser.remove(arg0.getPlayer());
        InternalLogin.getInstance().getSQL().unregister(arg0.getPlayer().toString());
    }

    public boolean existsUser(UUID arg0) {
        return uuidUser.containsKey(arg0);
    }

    public User getUser(UUID arg0) {
        if (existsUser(arg0)) {
            return uuidUser.get(arg0);
        } else {
            return createUser(arg0);
        }
    }

}
