package pl.pozadr.hellomongodb.service.mongoDb;

import pl.pozadr.hellomongodb.model.UserMongoDb;

import java.util.List;

public interface UserMongoDbService {
    void saveAllMongoDb(List<UserMongoDb> users);

    void deleteAllMongoDb();

    List<UserMongoDb> findAllMongoDb();
}
