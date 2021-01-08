package pl.pozadr.hellomongodb.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.pozadr.hellomongodb.model.Toy;
import pl.pozadr.hellomongodb.model.ToyType;
import pl.pozadr.hellomongodb.repository.ToyRepo;

import java.util.List;
import java.util.Optional;

@Component
public class Main {
    private final ToyRepo toyRepo;

    @Autowired
    public Main(ToyRepo toyRepo) {
        this.toyRepo = toyRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
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
}
