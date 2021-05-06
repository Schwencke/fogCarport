package business.services;

import business.entities.User;
import business.persistence.Database;
import business.persistence.UserMapper;
import business.exceptions.UserException;

import java.sql.SQLException;

public class UserFacade {
    UserMapper userMapper;

    public UserFacade(Database database) {
        userMapper = new UserMapper(database);
    }

    public User login(String email, String password) throws UserException, SQLException {
        return userMapper.login(email, password);
    }

    public User createUser(String name, String address, int postalCode, String city, String phoneNo, String email, String password) throws UserException {
        User user = new User(name, address, postalCode, city, phoneNo, email, password);
        userMapper.createUser(user);
        return user;
    }
}
