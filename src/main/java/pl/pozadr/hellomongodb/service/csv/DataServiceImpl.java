package pl.pozadr.hellomongodb.service.csv;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import pl.pozadr.hellomongodb.model.UserMongoDb;
import pl.pozadr.hellomongodb.model.UserSqlDb;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataServiceImpl implements DataService {
    private static final String DATA_PATH = "./data/MOCK_DATA.csv";


    @Override
    public List<UserMongoDb> readCsvUserMongoDb() {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(DATA_PATH));
            CsvToBean<UserMongoDb> csvToBean = new CsvToBeanBuilder<UserMongoDb>(reader)
                    .withType(UserMongoDb.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<UserMongoDb> users = csvToBean.parse();
            reader.close();
            return users;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<UserSqlDb> readCsvUserSqlDb() {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(DATA_PATH));
            CsvToBean<UserSqlDb> csvToBean = new CsvToBeanBuilder<UserSqlDb>(reader)
                    .withType(UserSqlDb.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<UserSqlDb> users = csvToBean.parse();
            reader.close();
            return users;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }
}
