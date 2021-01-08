package pl.pozadr.hellomongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.pozadr.hellomongodb.model.Toy;

public interface ToyRepo extends MongoRepository<Toy, String> {
}
