package mongodb.impl;

import com.mongodb.*;
import domain.MongoEntity;
import mongodb.MongoDBManager;
import mongodb.utils.MongoUtils;
import org.springframework.stereotype.Repository;

import java.net.UnknownHostException;
import java.util.Optional;

/**
 * Created by davidevincenzodilecce on 24/11/2018.
 */
@Repository
public class MongoDBManagerImpl implements MongoDBManager {

    @Override
    public Optional<? extends MongoEntity> getById(String id, Class<? extends MongoEntity> className) throws UnknownHostException {
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://" + MongoUtils.DB_NAME + ":" + MongoUtils.PORT + ""));
        DB database = mongoClient.getDB(MongoUtils.DB_NAME);
        DBCollection collection = database.getCollection(className.getName());
        DBObject query = new BasicDBObject("_id", id);
        DBCursor cursor = collection.find(query);
        DBObject jo = cursor.one();
        while (cursor.hasNext()) {
            DBObject obj = cursor.next();
            //todo
        }
        return null;
    }
}
