package pl.pozadr.hellomongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.pozadr.hellomongodb.model.UserMongoDb;

public interface UserMongoDbRepo extends MongoRepository<UserMongoDb, String> {
}
