package cz.zcu.fav.pia.jsf.repo;

import cz.zcu.fav.pia.jsf.domain.User;

public interface UserRepo {

    User getUserByName(String name);

    void changeUserPassword(String name, String password);

}
