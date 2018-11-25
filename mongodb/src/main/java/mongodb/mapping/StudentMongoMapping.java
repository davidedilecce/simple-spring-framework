package mongodb.mapping;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import domain.MongoEntity;
import mongodb.MongoMapping;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import testMongoDomain.Student;

import java.util.Optional;

/**
 * Created by davidevincenzodilecce on 25/11/2018.
 */
@Component
public class StudentMongoMapping implements MongoMapping {

    @Override
    public Optional<? extends MongoEntity> fromMongoToEntity(DBObject dbObject) {

        if (dbObject != null) {
            Student student = new Student();
            student.setId( dbObject.get("_id").toString());
            student.setName(((String) dbObject.get("name")));
            student.setSurname(((String) dbObject.get("surname")));
            return Optional.of(student);
        }
        return null;

    }

    @Override
    public DBObject fromEntityToMongo(Optional<? extends MongoEntity> entity) {

        if (entity.isPresent()) {

            Student s = (Student) entity.get();

            DBObject dbObject = new BasicDBObject();

            dbObject.put("_id", new ObjectId(s.getId()));
            dbObject.put("name", s.getName());
            dbObject.put("surname", s.getSurname());

            return dbObject;

        } else {
            return null;
        }

    }

}
