package business.persistence;

import business.exceptions.UserException;
import business.entities.User;

import java.sql.*;

public class UserMapper {
    private Database database;

    public UserMapper(Database database) {
        this.database = database;
    }

    public void createUser(User user) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO `user` (`name`, `address`, `zip_code`, `phone_no`, `email`, `password`) VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, user.getName());
                ps.setString(2, user.getAddress());
                ps.setInt(3, user.getZipCode());
                ps.setString(4, user.getPhoneNo());
                ps.setString(2, user.getEmail());
                ps.setString(2, user.getPassword());
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int userId = ids.getInt(1);
                user.setUserId(userId);
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }

            String sql2 = "SELECT `role_id`, `balance` FROM user WHERE `user_id`= ?";
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
                    int zipCode = rs.getInt("zip_code");
                    String phoneNo = rs.getString("phone_no");
                    user.setUserId(userId);
                    user.setRoleId(roleId);
                    user.setName(name);
                    user.setAddress(address);
                    user.setZipCode(zipCode);
                    user.setPhoneNo(phoneNo);
                    user.setEmail(email);
                    user.setPassword(password);
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }

            String sql2 = "SELECT `name` FROM `role` WHERE `role_id` = ?";
            try (PreparedStatement ps2 = connection.prepareStatement(sql2)) {
                ps2.setInt(1, user.getRoleId());
                ResultSet rs2 = ps2.executeQuery();
                if (rs2.next()) {
                    String name = rs2.getString("name");
                    user.setRole(name);
                    return user;
                } else {
                    throw new UserException("Could not validate user");
                }
            } catch (SQLException ex) {
                throw new UserException("Connection to database could not be established");
            }
        }
    }
}
