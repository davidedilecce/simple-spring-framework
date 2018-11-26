package mongodb;

import com.mongodb.DBObject;
import domain.MongoEntity;
import utils.query.QueryBuilder;

import java.util.List;
import java.util.Optional;

/**
 * Created by davidevincenzodilecce on 24/11/2018.
 */
public interface MongoDBManager {

    DBObject getById(String id, Class<? extends MongoEntity> className);

    void save(DBObject object, Optional<? extends MongoEntity> entity, Class<? extends MongoEntity> className);

    List<DBObject> find(QueryBuilder queryBuilder, Class<? extends MongoEntity> className);

    void delete(Object id, Class<? extends MongoEntity> className);
}
