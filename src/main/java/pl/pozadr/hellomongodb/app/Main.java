package pl.pozadr.hellomongodb.app;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.pozadr.hellomongodb.model.UserMongoDb;
import pl.pozadr.hellomongodb.model.UserSqlDb;
import pl.pozadr.hellomongodb.repository.UserMongoDbRepo;


import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


@Component
public class Main {
    private final UserMongoDbRepo mongoDbRepo;

    public Main(UserMongoDbRepo mongoDbRepo) {
        this.mongoDbRepo = mongoDbRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init(){
        mongoDbRepo.deleteAll();
        initMongoDB();
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
            //users.forEach(mongoDbRepo::save);

            // close the reader
            reader.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /*
    public void init() {
        toyRepo.deleteAll();
        Toy teddyBear = new Toy("Teddy", ToyType.TEDDY_BEAR);
        Toy doll = new Toy("Alice", ToyType.DOLL);
        Toy legoBlocks = new Toy("Technics", ToyType.LEGO);

        // CREATE
        System.out.println("CREATE: teddy, doll, lego");
        toyRepo.save(teddyBear);
        toyRepo.save(doll);
        toyRepo.save(legoBlocks);

        // READ
        System.out.println("READ: all");
        List<Toy> toyList = toyRepo.findAll();
        toyList.forEach(System.out::println);
        //UPDATE
        System.out.println("UPDATE: lego");
        Optional<Toy> toyOptional = toyRepo.findById(toyList.get(2).getId());
        if (toyOptional.isPresent()) {
            Toy toy = toyOptional.get();
            toy.setName("Duplo");
            toyRepo.save(toy);
        }
        toyRepo.findAll().forEach(System.out::println);

        //DELETE
        System.out.println("DELETE: doll");
        toyRepo.delete(doll);
        toyRepo.findAll().forEach(System.out::println);
    }
     */

}
