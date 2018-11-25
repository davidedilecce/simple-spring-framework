package testMongoDomain;

import domain.MongoEntity;

/**
 * Created by davidevincenzodilecce on 25/11/2018.
 */
public class Student extends MongoEntity {

    private String name;
    private String surname;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
