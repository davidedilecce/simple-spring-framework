package mongodb.impl;

import com.mongodb.*;
import domain.MongoEntity;
import mongodb.MongoDBManager;
import mongodb.utils.MongoUtils;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;
import utils.query.Conjunction;
import utils.query.Disjunction;
import utils.query.Filter;
import utils.query.QueryBuilder;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

    @Override
    public List<DBObject> find(QueryBuilder queryBuilder, Class<? extends MongoEntity> className) {

        DB database = MongoUtils.mongoClient.getDB(MongoUtils.DB_NAME);
        DBCollection collection = database.getCollection(className.getSimpleName().toLowerCase());

        BasicDBObject basicDBObject = new BasicDBObject();

        if (queryBuilder != null) {

            queryBuilder.getFilters().removeIf(filter -> filter.getValue() == null);

            for (Filter filter : queryBuilder.getFilters()) {
                manageFilters(basicDBObject, filter);
            }
        }

        DBCursor cursor = collection.find(basicDBObject);

        return cursor.toArray();

    }

    @Override
    public void delete(Object id, Class<? extends MongoEntity> className) {

        DB database = MongoUtils.mongoClient.getDB(MongoUtils.DB_NAME);
        DBCollection collection = database.getCollection(className.getSimpleName().toLowerCase());
        collection.remove(new BasicDBObject("_id", new ObjectId(String.valueOf(id))));

    }

    private void manageFilters(BasicDBObject basicDBObject, Filter filter) {

        switch (filter.getType()) {

            case Filter.LIKE_ANYWHERE:
                basicDBObject.put(filter.getProperty(), Pattern.compile(String.format("%s", String.valueOf(filter.getValue())), Pattern.CASE_INSENSITIVE));
                break;
            case Filter.LIKE_START:
                basicDBObject.put(filter.getProperty(), new BasicDBObject("$regex", Pattern.compile("^(?i)"+Pattern.quote(String.valueOf(filter.getValue())))));
                break;
            case Filter.LIKE_END:
                basicDBObject.put(filter.getProperty(), new BasicDBObject("$regex", Pattern.compile(Pattern.quote(String.valueOf(filter.getValue())) + "$")));
                break;
            case Filter.EQUALS:
                basicDBObject.put(filter.getProperty(), filter.getValue());
                break;
            case Filter.DISJUNCTION:

                basicDBObject.put("$or", ((Disjunction) filter.getValue()).getFilters().stream().map((f) -> {
                    BasicDBObject bs = new BasicDBObject();
                    manageFilters(bs, f);
                    return bs;
                }).collect(Collectors.toList()));

                break;
            case Filter.CONJUNCTION:

                basicDBObject.put("$and", ((Conjunction) filter.getValue()).getFilters().stream().map((f) -> {
                    BasicDBObject bs = new BasicDBObject();
                    manageFilters(bs, f);
                    return bs;
                }).collect(Collectors.toList()));

                break;

        }

    }


}
