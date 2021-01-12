package pl.pozadr.hellomongodb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pozadr.hellomongodb.model.UserSqlDb;

public interface UserSqlDbRepo extends JpaRepository<UserSqlDb, Long> {
}
