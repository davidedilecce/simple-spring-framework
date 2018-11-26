package services;

import testMongoDomain.Student;
import utils.query.QueryBuilder;

import java.util.List;

/**
 * Created by davidevincenzodilecce on 25/11/2018.
 */
public interface StudentService {

    Student getById(String id);

    void save(Student student);

    List<Student> find(QueryBuilder queryBuilder);

    void delete(Object id);

}
