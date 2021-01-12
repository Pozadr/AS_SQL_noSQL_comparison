package pl.pozadr.hellomongodb.service.sqlDb;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import pl.pozadr.hellomongodb.aspect.mongoDb.MongoDbReadTimeMeasure;
import pl.pozadr.hellomongodb.aspect.mongoDb.MongoDbSaveTimeMeasure;
import pl.pozadr.hellomongodb.aspect.sqlDb.SqlDbReadTimeMeasure;
import pl.pozadr.hellomongodb.aspect.sqlDb.SqlDbSaveTimeMeasure;
import pl.pozadr.hellomongodb.model.UserSqlDb;
import pl.pozadr.hellomongodb.repository.UserSqlDbRepo;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class UserSqlDbServiceImpl implements UserSqlDbService {

    private final UserSqlDbRepo sqlDbRepo;

    public UserSqlDbServiceImpl(UserSqlDbRepo sqlDbRepo) {
        this.sqlDbRepo = sqlDbRepo;
    }

    @Override
    @SqlDbReadTimeMeasure
    public List<UserSqlDb> findAllSqlDb() {
        return sqlDbRepo.findAll();
    }

    @Override
    @SqlDbSaveTimeMeasure
    public void saveAllFromCsvToSqlDb() {
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
            sqlDbRepo.saveAll(users);

            // close the reader
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteAllSqlDb() {
        sqlDbRepo.deleteAll();
    }

}
