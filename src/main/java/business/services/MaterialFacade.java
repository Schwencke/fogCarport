package business.services;

import business.entities.Material;
import business.entities.Order;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.MaterialMapper;

import java.util.HashMap;
import java.util.List;

public class MaterialFacade {
    MaterialMapper materialMapper;

    public MaterialFacade(Database database) {
        materialMapper = new MaterialMapper(database);
    }

    public HashMap<Integer, String> getAllUnits() throws UserException {
        return materialMapper.getAllUnits();
    }

    public Material getMaterialById(int materialId) throws UserException {
        return materialMapper.getMaterialById(materialId);
    }

    public List<Material> getMaterialByDescription(String description) throws UserException {
        return materialMapper.getMaterialByDescription(description);
    }
}
