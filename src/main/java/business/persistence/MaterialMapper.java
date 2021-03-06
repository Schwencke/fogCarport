package business.persistence;

import business.entities.Material;
import business.exceptions.UserException;
import business.services.Utility;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MaterialMapper {
    private Database database;

    public MaterialMapper(Database database) {
        this.database = database;
    }

    public HashMap<Integer, String> getAllUnits() throws UserException {
        HashMap<Integer, String> units = new HashMap<>();
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `unit`";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    int unitId = resultSet.getInt("unit_id");
                    String name = resultSet.getString("name");
                    units.put(unitId, name);
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
        return units;
    }

    //<editor-fold desc="getPost">
    public Material getMaterialById(int materialId) throws UserException {
        Material material = new Material();
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `material` WHERE `material_id` = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, materialId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    double price = rs.getDouble("price");
                    int unitId = rs.getInt("unit_id");
                    int width = rs.getInt("width");
                    int length = rs.getInt("length");
                    int height = rs.getInt("height");
                    material.setMaterialID(materialId);
                    material.setName(name);
                    material.setDescription(description);
                    material.setPrice(price);
                    material.setUnitId(unitId);
                    material.setWidth(width);
                    material.setLength(length);
                    material.setHeight(height);
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
        return material;
    }

    //<editor-fold desc="getBeam">
    public List<Material> getMaterialByDescription(String description) throws UserException {
        List<Material> materialList =  new ArrayList<>();
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `material` WHERE `description` = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, description);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int materialId = rs.getInt("material_id");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    int unitId = rs.getInt("unit_id");
                    int width = rs.getInt("width");
                    int length = rs.getInt("length");
                    int height = rs.getInt("height");
                    materialList.add(new Material(materialId, name, description, price, unitId, width, length, height));
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
        return materialList;
    }
}
