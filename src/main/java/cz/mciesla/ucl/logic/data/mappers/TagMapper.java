package cz.mciesla.ucl.logic.data.mappers;

import cz.mciesla.ucl.logic.app.entities.definition.ITag;
import cz.mciesla.ucl.logic.data.dao.TagDAO;
import cz.mciesla.ucl.logic.data.mappers.definition.ITagMapper;

import java.util.ArrayList;
import java.util.List;

public class TagMapper implements ITagMapper {
    MapperFactory factory;

    public TagMapper(MapperFactory factory) {
        this.factory = factory;
    }

    @Override
    public ITag mapFromDAOShallow(TagDAO dao) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TagDAO mapToDAOShallow(ITag entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ITag mapFromDAODeep(TagDAO dao) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TagDAO mapToDAODeep(ITag entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ITag> mapFromDAOsShallow(List<TagDAO> daos) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<TagDAO> mapToDAOsShallow(List<ITag> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    // TODO
}
