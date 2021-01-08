package pl.pozadr.hellomongodb.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.pozadr.hellomongodb.model.Toy;
import pl.pozadr.hellomongodb.model.ToyType;
import pl.pozadr.hellomongodb.repository.ToyRepo;

@Component
public class Main {
    private final ToyRepo toyRepo;

    @Autowired
    public Main(ToyRepo toyRepo) {
        this.toyRepo = toyRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        Toy teddyBear = new Toy("Teddy", ToyType.TEDDY_BEAR);
        toyRepo.save(teddyBear);
        Toy doll = new Toy("Alice", ToyType.DOLL);
        toyRepo.save(doll);
        Toy legoBlocks = new Toy("Technics", ToyType.LEGO);
        toyRepo.save(legoBlocks);
    }
}
