package pl.pozadr.hellomongodb.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.pozadr.hellomongodb.service.mongoDb.UserMongoDbService;
import pl.pozadr.hellomongodb.service.sqlDb.UserSqlDbService;


@Component
public class Main {
    private final UserMongoDbService userMongoDbService;
    private final UserSqlDbService userSqlDbService;

    @Autowired
    public Main(UserMongoDbService userMongoDbService, UserSqlDbService userSqlDbService) {
        this.userMongoDbService = userMongoDbService;
        this.userSqlDbService = userSqlDbService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        userMongoDbService.deleteAllMongoDb();
        userMongoDbService.saveAllFromCsvToMongoDb();
        userMongoDbService.findAllMongoDb();


        // application.properties to check: with "spring.jpa.hibernate.ddl-auto=create" deleteAll not required
        userSqlDbService.saveAllFromCsvToSqlDb();
        userSqlDbService.findAllSqlDb();
    }

}
