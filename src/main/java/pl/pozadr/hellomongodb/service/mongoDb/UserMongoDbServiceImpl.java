package pl.pozadr.hellomongodb.service.mongoDb;

import org.springframework.stereotype.Service;
import pl.pozadr.hellomongodb.aspect.mongoDb.MongoDbReadTimeMeasure;
import pl.pozadr.hellomongodb.aspect.mongoDb.MongoDbSaveTimeMeasure;
import pl.pozadr.hellomongodb.model.UserMongoDb;
import pl.pozadr.hellomongodb.repository.UserMongoDbRepo;

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
    public void saveAllMongoDb(List<UserMongoDb> users) {
        mongoDbRepo.saveAll(users);
    }

    @Override
    public void deleteAllMongoDb() {
        mongoDbRepo.deleteAll();
    }

}
