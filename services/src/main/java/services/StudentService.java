package services;

import testMongoDomain.Student;

/**
 * Created by davidevincenzodilecce on 25/11/2018.
 */
public interface StudentService {

    Student getById(String id);

    void save(Student student);
}
