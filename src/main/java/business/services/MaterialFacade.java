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

    public void getPost(int materialId) throws UserException {
        materialMapper.getPost(materialId);
    }
}
