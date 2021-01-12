package pl.pozadr.hellomongodb.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.pozadr.hellomongodb.model.UserMongoDb;
import pl.pozadr.hellomongodb.model.UserSqlDb;
import pl.pozadr.hellomongodb.service.csv.DataService;
import pl.pozadr.hellomongodb.service.mongoDb.UserMongoDbService;
import pl.pozadr.hellomongodb.service.sqlDb.UserSqlDbService;

import java.util.List;


@Component
public class Main {
    private final UserMongoDbService userMongoDbService;
    private final UserSqlDbService userSqlDbService;
    private final DataService dataService;


    @Autowired
    public Main(UserMongoDbService userMongoDbService, UserSqlDbService userSqlDbService, DataService dataService) {
        this.userMongoDbService = userMongoDbService;
        this.userSqlDbService = userSqlDbService;
        this.dataService = dataService;
    }


    @EventListener(ApplicationReadyEvent.class)
    public void run() {
        List<UserMongoDb> userMongoDbList = dataService.readCsvUserMongoDb();
        List<UserSqlDb> userSqlDbList = dataService.readCsvUserSqlDb();
        userMongoDbService.deleteAllMongoDb();
        userMongoDbService.saveAllMongoDb(userMongoDbList);
        userMongoDbService.findAllMongoDb();


        // application.properties to check: with "spring.jpa.hibernate.ddl-auto=create" deleteAll not required
        userSqlDbService.saveAllFromCsvToSqlDb(userSqlDbList);
        userSqlDbService.findAllSqlDb();
    }


}
