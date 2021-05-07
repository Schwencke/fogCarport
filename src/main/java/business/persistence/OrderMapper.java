package business.persistence;

import business.entities.Order;
import business.exceptions.UserException;
import java.sql.*;


public class OrderMapper {
    private Database database;

    public OrderMapper(Database database) {
        this.database = database;
    }


    //<editor-fold desc="createOrder">
    public void createOrder(Order order) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO `order` (`user_id`, `carport_length`, `carport_width`, `cladding_id`, `roofing_id`, `shed_width`, `shed_length`) VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, order.getUserId());
                ps.setInt(1, order.getCarportLength());
                ps.setInt(1, order.getCarportWidth());
                ps.setInt(1, order.getCladdingId());
                ps.setInt(1, order.getRoofingId());
                ps.setInt(1, order.getShedWidth());
                ps.setInt(1, order.getShedLength());
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int orderId = ids.getInt(1);
                order.setOrderId(orderId);

            }} catch (SQLException throwables) {
            throw new UserException("ordren kunne ikke gennemføres");
        }
    }
    //</editor-fold>

    //<editor-fold desc="getOrder">
    public Order getOrder(int orderNo) {
        Order order = new Order();
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `order`";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int orderId = rs.getInt("order_id");
                    int userId = rs.getInt("user_id");
                    int statusId = rs.getInt("status_id");
                    double price = rs.getDouble("price");
                    Timestamp timeCreated = rs.getTimestamp("time_created");
                    Timestamp timeUpdated = rs.getTimestamp("time_updated");
                    int carportLength = rs.getInt("carport_length");
                    int carportWidth = rs.getInt("carport_width");
                    int claddingId = rs.getInt("cladding_id");
                    int roofingId = rs.getInt("roofing_id");
                    int shedWidth = rs.getInt("shed_width");
                    int shedLength = rs.getInt("shed_length");
                    order.setOrderId(orderId);
                    order.setUserId(userId);
                    order.setStatusId(statusId);
                    order.setPrice(price);
                    order.setTimeCreated(timeCreated);
                    order.setTimeUpdated(timeUpdated);
                    order.setCarportLength(carportLength);
                    order.setCarportWidth(carportWidth);
                    order.setCladdingId(claddingId);
                    order.setRoofingId(roofingId);
                    order.setShedWidth(shedWidth);
                    order.setShedLength(shedLength);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return order;
    }
    //</editor-fold>

}