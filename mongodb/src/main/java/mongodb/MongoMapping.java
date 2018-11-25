package mongodb;

import com.mongodb.DBObject;
import domain.MongoEntity;

import java.util.Optional;

/**
 * Created by davidevincenzodilecce on 25/11/2018.
 */
public interface MongoMapping {

    Optional<? extends MongoEntity> fromMongoToEntity(DBObject dbObject);
    DBObject fromEntityToMongo(Optional<? extends MongoEntity> entity);

}
