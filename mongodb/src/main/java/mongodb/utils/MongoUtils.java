package mongodb.utils;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import java.net.UnknownHostException;

/**
 * Created by davidevincenzodilecce on 24/11/2018.
 */
public class MongoUtils {

    public static final String HOST = "localhost";
    public static final String PORT = "27017";
    public static final String DB_NAME = "test_mongo";

    public static final MongoClient mongoClient = buildConnection();

    private static final MongoClient buildConnection() {
        try {
            if (mongoClient != null)
                return mongoClient;
            else
                return new MongoClient(new MongoClientURI("mongodb://" + MongoUtils.HOST + ":" + MongoUtils.PORT + ""));
        } catch (UnknownHostException e) {
            throw new RuntimeException();
        }
    }

}
