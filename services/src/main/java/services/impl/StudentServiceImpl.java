package services.impl;

import com.mongodb.DBObject;
import domain.MongoEntity;
import mongodb.MongoDBManager;
import mongodb.mapping.StudentMongoMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.StudentService;
import testMongoDomain.Student;

import java.util.Optional;

/**
 * Created by davidevincenzodilecce on 25/11/2018.
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private MongoDBManager mongoDBManager;

    @Autowired
    private StudentMongoMapping mapping;

    @Override
    public Student getById(String id) {
        DBObject dbObject = mongoDBManager.getById(id, Student.class);
        MongoEntity mongoEntity = mapping.fromMongoToEntity(dbObject).orElse(null);
        if (mongoEntity != null) {
            return ((Student) mongoEntity);
        }
        return null;
    }

    @Override
    public void save(Student student) {
        mongoDBManager.save(mapping.fromEntityToMongo(Optional.of(student)), Optional.of(student), Student.class);
    }

}
