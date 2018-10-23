package core.data;

import core.User;

public interface UserRepository {

    User save(User user);

    User findByUsername(String username);

    User validateUser(User user);

}
