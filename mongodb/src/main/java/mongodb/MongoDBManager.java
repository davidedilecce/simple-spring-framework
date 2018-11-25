package mongodb;

import com.mongodb.DBObject;
import domain.MongoEntity;

import java.util.Optional;

/**
 * Created by davidevincenzodilecce on 24/11/2018.
 */
public interface MongoDBManager {

    DBObject getById(String id, Class<? extends MongoEntity> className);

    void save(DBObject object, Optional<? extends MongoEntity> entity, Class<? extends MongoEntity> className);

}
