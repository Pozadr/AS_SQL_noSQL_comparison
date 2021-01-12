package pl.pozadr.hellomongodb.service.mongoDb;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import pl.pozadr.hellomongodb.aspect.mongoDb.MongoDbReadTimeMeasure;
import pl.pozadr.hellomongodb.aspect.mongoDb.MongoDbSaveTimeMeasure;
import pl.pozadr.hellomongodb.model.UserMongoDb;
import pl.pozadr.hellomongodb.repository.UserMongoDbRepo;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class UserMongoDbServiceImpl implements UserMongoDbService {

    private final UserMongoDbRepo mongoDbRepo;

    public UserMongoDbServiceImpl(UserMongoDbRepo mongoDbRepo) {
        this.mongoDbRepo = mongoDbRepo;
    }

    @Override
    @MongoDbReadTimeMeasure
    public List<UserMongoDb> findAllMongoDb() {
        return mongoDbRepo.findAll();
    }

    @Override
    @MongoDbSaveTimeMeasure
    public void saveAllFromCsvToMongoDb() {
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

    @Override
    public void deleteAllMongoDb() {
        mongoDbRepo.deleteAll();
    }

}
