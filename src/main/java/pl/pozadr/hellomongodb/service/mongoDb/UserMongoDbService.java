package pl.pozadr.hellomongodb.service.mongoDb;

import pl.pozadr.hellomongodb.model.UserMongoDb;

import java.util.List;

public interface UserMongoDbService {
    void saveAllFromCsvToMongoDb();
    void deleteAllMongoDb();
    List<UserMongoDb> findAllMongoDb();
}
