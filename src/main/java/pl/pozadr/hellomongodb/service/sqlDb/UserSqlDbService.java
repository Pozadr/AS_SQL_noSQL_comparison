package pl.pozadr.hellomongodb.service.sqlDb;

import pl.pozadr.hellomongodb.model.UserMongoDb;
import pl.pozadr.hellomongodb.model.UserSqlDb;

import java.util.List;

public interface UserSqlDbService {
    void saveAllFromCsvToSqlDb();
    void deleteAllSqlDb();
    List<UserSqlDb> findAllSqlDb();
}
