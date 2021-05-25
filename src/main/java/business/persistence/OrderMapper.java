package business.persistence;

import business.entities.BoM;
import business.entities.Material;
import business.entities.Order;
import business.exceptions.UserException;
import business.services.SVG;

import java.sql.*;
import java.util.*;

public class OrderMapper {
    private Database database;

    public OrderMapper(Database database) {
        this.database = database;
    }

    //<editor-fold desc="createOrder">
    public void createOrder(Order order) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO `order` (`user_id`, `carport_length`, `carport_width`,`cladding_id`, `roofing_id`, `shed_width`, `shed_length`) VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, order.getUserId());
                ps.setInt(2, order.getCarportLength());
                ps.setInt(3, order.getCarportWidth());
                ps.setInt(4, order.getCladdingId());
                ps.setInt(5, order.getRoofingId());
                ps.setInt(6, order.getShedWidth());
                ps.setInt(7, order.getShedLength());
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int orderId = ids.getInt(1);
                order.setOrderId(orderId);
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }
    //</editor-fold>

    //<editor-fold desc="getOrderById">
    public Order getOrderById(int orderId) throws UserException {
        Order order = new Order();
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `order` WHERE `order_id` = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
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
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
        return order;
    }
    //</editor-fold>

    //<editor-fold desc="updateCarportMeasurementsById">
    public void updateCarportMeasurementsById(int orderId, int carportLength, int carportWidth) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "UPDATE `order` SET `carport_length`=?, `carport_width`=? WHERE `order_id` = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, carportLength);
                ps.setInt(2, carportWidth);
                ps.setInt(3, orderId);
                ps.executeUpdate();
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    //</editor-fold>

    //<editor-fold desc="updateShedMeasurementsById">
    public void updateShedMeasurementsById(int orderId, int shedLength, int shedWidth) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "UPDATE `order` SET `shed_length`=?, `shed_width`=? WHERE `order_id` = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, shedLength);
                ps.setInt(2, shedWidth);
                ps.setInt(3, orderId);
                ps.executeUpdate();
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }
    //</editor-fold>

    //<editor-fold desc="getAllOrders">
    public List<Order> getAllOrders() throws UserException {
        List<Order> orderList = new ArrayList<>();

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
                    orderList.add(new Order(orderId, userId, statusId, price, timeCreated, timeUpdated, carportLength, carportWidth, claddingId, roofingId, shedWidth, shedLength));
                }
                return orderList;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }
    //</editor-fold>

    //<editor-fold desc="getAllOrdersById">
    public List<Order> getAllOrdersById(int userId) throws UserException {
        List<Order> orderList = new ArrayList<>();

        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `order` WHERE `user_id` = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int orderId = rs.getInt("order_id");
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
                    orderList.add(new Order(orderId, statusId, price, timeCreated, timeUpdated, carportLength, carportWidth, claddingId, roofingId, shedWidth, shedLength));
                }
                return orderList;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }
    //</editor-fold>

    //<editor-fold desc="getPredefined measurements for carport and shed">
    public Map<String, List<Integer>> getPredefined() throws UserException {

        List<Integer> predefinedCarportWidth = new ArrayList<>();
        List<Integer> predefinedCarportLength = new ArrayList<>();
        List<Integer> predefinedShedWidth = new ArrayList<>();
        List<Integer> predefinedShedLength = new ArrayList<>();

        Map<String, List<Integer>> listMap = new HashMap<>();

        try (Connection connection = database.connect()) {
            String sql = "SELECT `width`, `length` FROM `predefined_carport`";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int width = rs.getInt("width");
                    int length = rs.getInt("length");
                    if (width > 0) {
                        predefinedCarportWidth.add(width);
                    }
                    if (length > 0) {
                        predefinedCarportLength.add(length);
                    }

                }
                listMap.put("carportWidth", predefinedCarportWidth);
                listMap.put("carportLength", predefinedCarportLength);

                String sql2 = "SELECT `width`, `length` FROM `predefined_shed`";
                try (PreparedStatement ps2 = connection.prepareStatement(sql2)) {
                    ResultSet rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        int width = rs2.getInt("width");
                        int length = rs2.getInt("length");

                        if (width > 0) {
                            predefinedShedWidth.add(width);
                        }
                        if (length > 0) {
                            predefinedShedLength.add(length);
                        }

                    }
                    listMap.put("shedWidth", predefinedShedWidth);
                    listMap.put("shedLength", predefinedShedLength);
                } catch (SQLException ex) {
                    throw new UserException(ex.getMessage());
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
        return listMap;

    } //</editor-fold>

    //<editor-fold desc="getRoofing">
    public Map<Integer, Integer> getRoofing() throws UserException {
        Map<Integer, Integer> roofMap = new HashMap<>();

        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `roofing`";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int roofId = rs.getInt("roof_id");
                    int materialId = rs.getInt("material_id");
                    roofMap.put(roofId, materialId);
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
        return roofMap;
    }
    //</editor-fold>

    //<editor-fold desc="getCladding">
    public Map<Integer, Integer> getCladding() throws UserException {
        Map<Integer, Integer> claddingMap = new HashMap<>();

        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `cladding`";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int claddingId = rs.getInt("cladding_id");
                    int materialId = rs.getInt("material_id");
                    claddingMap.put(claddingId, materialId);
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
        return claddingMap;
    }
    //</editor-fold>

    //<editor-fold desc="getMaterialNameById">
    public String getMaterialNameById(int materialId) throws UserException {
        String materialName = "";
        try (Connection connection = database.connect()) {
            String sql = "SELECT `name` FROM `material` WHERE `material_id`=?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, materialId);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    materialName = rs.getString("name");
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
        return materialName;
    }
    //</editor-fold>

    //<editor-fold desc="getAllStatus">
    public HashMap<Integer, String> getAllStatus() throws UserException {
        HashMap<Integer, String> status = new HashMap<>();
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `status`";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    int statusId = resultSet.getInt("status_id");
                    String name = resultSet.getString("name");
                    status.put(statusId, name);
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
        return status;
    }
    //</editor-fold>

    //<editor-fold desc="updateStatusById">
    public void updateStatusById(int statusId, int orderId) {
        try (Connection connection = database.connect()) {
            String sql = "UPDATE `order` SET `status_id`=? WHERE `order_id` = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, statusId);
                ps.setInt(2, orderId);
                ps.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //</editor-fold>

    //<editor-fold desc="updateOrderPrice">
    public void updateOrderPrice(int orderId, double orderPrice) {
        try (Connection connection = database.connect()) {
            String sql = "UPDATE `order` SET `price`=? WHERE `order_id` = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setDouble(1, orderPrice);
                ps.setInt(2, orderId);
                ps.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //</editor-fold>
    //<editor-fold desc="createSVG">
    public void createSVG(int orderId, SVG svg) {
        try (Connection connection = database.connect()) {
            String sql = "UPDATE `order` SET `svg`=? WHERE order_id=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, svg.toString());
                ps.setInt(2, orderId);
                ps.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //</editor-fold>

//    //<editor-fold desc="createSVG">
//    public void updateSVG(int orderId, SVG svg) {
//        try (Connection connection = database.connect()) {
//            String sql = "UPDATE `order` SET `svg`=? WHERE `order_id`=?";
//
//            try (PreparedStatement ps = connection.prepareStatement(sql)) {
//                ps.setString(1, svg.toString());
//                ps.setInt(2, orderId);
//                ps.executeUpdate();
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//    //</editor-fold>

    //<editor-fold desc="getSVG">
    public String getSVG(int orderId) {
        String svg ="";
        try (Connection connection = database.connect()) {
            String sql = "SELECT `svg` FROM `order` WHERE `order_id` = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    svg = rs.getString("svg");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return svg;
    }
    //</editor-fold>


    //<editor-fold desc="setBoM">
    public void setBoM(BoM billOfMaterials, int orderId){
       List<Material> list = billOfMaterials.getMaterials();

        StringBuilder sb = new StringBuilder();

        for (Material material : list) {

            sb.append(material.getName()).append(". ");
            sb.append(material.getDescription()).append(". ");
            sb.append(material.getLength()).append("mm ").append(" ");
            sb.append(material.getQuantity()).append("Stk ").append("| ");

        }
        try (Connection connection = database.connect()){
            String sql = "UPDATE `order` SET `bom`=? WHERE `order_id` = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)){

                ps.setString(1, sb.toString());
                ps.setInt(2,orderId);
                ps.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //</editor-fold>

    //<editor-fold desc="getBOM">
    public String[] getBOM(int orderId) {
        String BoM ="";
        try (Connection connection = database.connect()) {
            String sql = "SELECT `bom` FROM `order` WHERE `order_id` = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    BoM = rs.getString("bom");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return BoM.split("\\|");
    }
    //</editor-fold>
}