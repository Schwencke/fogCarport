package business.services;

import business.entities.User;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.UserMapper;

import java.sql.SQLException;
import java.util.HashMap;

public class UserFacade {
    UserMapper userMapper;

    public UserFacade(Database database) {
        userMapper = new UserMapper(database);
    }

    public User login(String email, String password) throws UserException, SQLException {
        return userMapper.login(email, password);
    }

    public User createUser(String name, String address, int postalCode, String phoneNo, String email, String password) throws UserException {
        User user = new User(name, address, postalCode, phoneNo, email, password);
        userMapper.createUser(user);
        return user;
    }

    public User getUserById(int userId) throws UserException {
        return userMapper.getUserById(userId);
    }

    public HashMap<Integer, String> getAllRoles() throws UserException {
        return userMapper.getAllRoles();
    }

    public HashMap<Integer, String> getAllCities() throws UserException {
        return userMapper.getAllCities();
    }
}
