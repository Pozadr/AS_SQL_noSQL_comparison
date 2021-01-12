package pl.pozadr.hellomongodb.service.csv;

import pl.pozadr.hellomongodb.model.UserMongoDb;
import pl.pozadr.hellomongodb.model.UserSqlDb;

import java.util.List;

public interface DataService {

    List<UserMongoDb> readCsvUserMongoDb();
    List<UserSqlDb> readCsvUserSqlDb();
}
