package cz.mciesla.ucl.logic.data.mappers.definition;

import cz.mciesla.ucl.logic.app.entities.definition.ICategory;
import cz.mciesla.ucl.logic.data.dao.CategoryDAO;

import java.util.List;

public interface ICategoryMapper {
    ICategory mapFromDAOShallow(CategoryDAO dao);
    CategoryDAO mapToDAOShallow(ICategory entity);

    ICategory mapFromDAODeep(CategoryDAO dao);
    CategoryDAO mapToDAODeep(ICategory entity);

    List<ICategory> mapFromDAOsShallow(List<CategoryDAO> daos);
    List<CategoryDAO> mapToDAOsShallow(List<ICategory> entities);
}
