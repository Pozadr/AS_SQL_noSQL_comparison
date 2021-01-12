package pl.pozadr.hellomongodb.service.sqlDb;

import org.springframework.stereotype.Service;
import pl.pozadr.hellomongodb.aspect.sqlDb.SqlDbReadTimeMeasure;
import pl.pozadr.hellomongodb.aspect.sqlDb.SqlDbSaveTimeMeasure;
import pl.pozadr.hellomongodb.model.UserSqlDb;
import pl.pozadr.hellomongodb.repository.UserSqlDbRepo;

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
    public void saveAllFromCsvToSqlDb(List<UserSqlDb> users) {
        sqlDbRepo.saveAll(users);
    }

    @Override
    public void deleteAllSqlDb() {
        sqlDbRepo.deleteAll();
    }

}
