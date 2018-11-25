package mongodb.impl;

import com.mongodb.*;
import domain.MongoEntity;
import mongodb.MongoDBManager;
import mongodb.utils.MongoUtils;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by davidevincenzodilecce on 24/11/2018.
 */
@Repository
public class MongoDBManagerImpl implements MongoDBManager {

    @Override
    public DBObject getById(String id, Class<? extends MongoEntity> className) {

        DB database = MongoUtils.mongoClient.getDB(MongoUtils.DB_NAME);
        DBCollection collection = database.getCollection(className.getSimpleName().toLowerCase());
        DBObject query = new BasicDBObject("_id", new ObjectId(id));
        DBCursor cursor = collection.find(query);
        DBObject dbObject = cursor.one();

        return dbObject;

    }

    @Override
    public void save(DBObject object, Optional<? extends MongoEntity> entity, Class<? extends MongoEntity> className) {

        if (object == null) {
            return;
        }

        DB database = MongoUtils.mongoClient.getDB(MongoUtils.DB_NAME);
        DBCollection collection = database.getCollection(className.getSimpleName().toLowerCase());

        Object id = object.get("_id");

        if (id == null) {
            collection.insert(object);
            entity.get().setId(object.get("_id").toString());
        } else {
            BasicDBObject searchQuery = new BasicDBObject().append("_id", new ObjectId(String.valueOf(id)));
            collection.update(searchQuery, object);
        }

    }

}
