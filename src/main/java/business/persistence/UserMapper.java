package business.persistence;

import business.entities.User;
import business.exceptions.UserException;

import java.sql.*;
import java.util.HashMap;

public class UserMapper {
    private Database database;

    public UserMapper(Database database) {
        this.database = database;
    }

    public void createUser(User user) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO `user` (`name`, `address`, `postal_code`, `phone_no`, `email`, `password`) VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, user.getName());
                ps.setString(2, user.getAddress());
                ps.setInt(3, user.getPostalCode());
                ps.setString(4, user.getPhoneNo());
                ps.setString(5, user.getEmail());
                ps.setString(6, user.getPassword());
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int userId = ids.getInt(1);
                user.setUserId(userId);
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }

            // Get default user id from database
            String sql2 = "SELECT `role_id` FROM `user` WHERE `user_id`= ?";
            try (PreparedStatement ps2 = connection.prepareStatement(sql2)) {
                ps2.setInt(1, user.getUserId());
                ResultSet rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    int roleId = rs2.getInt("role_id");
                    user.setRoleId(roleId);
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public User login(String email, String password) throws UserException, SQLException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `user` WHERE `email` = ? AND `password` = ?";
            User user = new User();

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int userId = rs.getInt("user_id");
                    int roleId = rs.getInt("role_id");
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    int postalCode = rs.getInt("postal_code");
                    String phoneNo = rs.getString("phone_no");
                    user.setUserId(userId);
                    user.setRoleId(roleId);
                    user.setName(name);
                    user.setAddress(address);
                    user.setPostalCode(postalCode);
                    user.setPhoneNo(phoneNo);
                    user.setEmail(email);
                    user.setPassword(password);
                }
                return user;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public User getUserById(int userId) throws UserException, SQLException{

        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `user` WHERE `user_id` = ?";
            User user = new User();

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int roleId = rs.getInt("role_id");
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    int postalCode = rs.getInt("postal_code");
                    String phoneNo = rs.getString("phone_no");
                    String email = rs.getString("email");
                    user.setUserId(userId);
                    user.setRoleId(roleId);
                    user.setName(name);
                    user.setAddress(address);
                    user.setPostalCode(postalCode);
                    user.setPhoneNo(phoneNo);
                    user.setEmail(email);
                }
                return user;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public HashMap<Integer, String> getAllRoles() throws UserException {
        HashMap<Integer, String> roles = new HashMap<>();
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `role`";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    int roleId = resultSet.getInt("role_id");
                    String name = resultSet.getString("name");
                    roles.put(roleId, name);
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
        return roles;
    }

    public HashMap<Integer, String> getAllCities() throws UserException {
        HashMap<Integer, String> cities = new HashMap<>();
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `postal_code`";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    int postalCode = resultSet.getInt("postal_code");
                    String name = resultSet.getString("name");
                    cities.put(postalCode, name);
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
        return cities;
    }
}
