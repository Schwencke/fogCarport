package business.services;

import business.entities.Material;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.MaterialMapper;

public class MaterialFacade {
    MaterialMapper materialMapper;

    public MaterialFacade(Database database) {
        materialMapper = new MaterialMapper(database);
    }

    public Material getPost(int materialId) throws UserException {
        //return materialMapper.getPost(materialId);
        return new Material(1, "p", "q", 1.0, 1, 100, 100, 100);
    }
}
