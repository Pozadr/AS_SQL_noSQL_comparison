package pl.pozadr.hellomongodb.app;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.pozadr.hellomongodb.model.UserMongoDb;
import pl.pozadr.hellomongodb.model.UserSqlDb;
import pl.pozadr.hellomongodb.repository.UserMongoDbRepo;
import pl.pozadr.hellomongodb.repository.UserSqlDbRepo;


import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


@Component
public class Main {
    private final UserMongoDbRepo mongoDbRepo;
    private final UserSqlDbRepo sqlDbRepo;

    @Autowired
    public Main(UserMongoDbRepo mongoDbRepo, UserSqlDbRepo sqlDbRepo) {
        this.mongoDbRepo = mongoDbRepo;
        this.sqlDbRepo = sqlDbRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init(){
        mongoDbRepo.deleteAll();
        initMongoDB();
        // with "spring.jpa.hibernate.ddl-auto=create" deleteAll not required
        initMySqlDB();
    }


    private void initMongoDB() {
        try {
            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get("./data/MOCK_DATA.csv"));

            // create csv bean reader
            CsvToBean<UserMongoDb> csvToBean = new CsvToBeanBuilder<UserMongoDb>(reader)
                    .withType(UserMongoDb.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            // iterate through users
            List<UserMongoDb> users = csvToBean.parse();
            users.forEach(mongoDbRepo::save);

            // close the reader
            reader.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void initMySqlDB() {
        try {
            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get("./data/MOCK_DATA.csv"));

            // create csv bean reader
            CsvToBean<UserSqlDb> csvToBean = new CsvToBeanBuilder<UserSqlDb>(reader)
                    .withType(UserSqlDb.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            // iterate through users
            List<UserSqlDb> users = csvToBean.parse();
            users.forEach(sqlDbRepo::save);

            // close the reader
            reader.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }



}
