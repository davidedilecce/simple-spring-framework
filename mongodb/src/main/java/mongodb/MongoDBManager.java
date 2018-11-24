package mongodb;

import domain.MongoEntity;

import java.net.UnknownHostException;
import java.util.Optional;

/**
 * Created by davidevincenzodilecce on 24/11/2018.
 */
public interface MongoDBManager {

    Optional<? extends MongoEntity> getById(String id, Class<? extends MongoEntity> className) throws UnknownHostException;

}
