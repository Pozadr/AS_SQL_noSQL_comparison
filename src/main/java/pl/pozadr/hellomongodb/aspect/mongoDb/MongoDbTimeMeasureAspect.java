package pl.pozadr.hellomongodb.aspect.mongoDb;

import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MongoDbTimeMeasureAspect {
    private final StopWatch readWatch;
    private final StopWatch saveWatch;

    public MongoDbTimeMeasureAspect() {
        this.readWatch = new StopWatch();
        this.saveWatch = new StopWatch();
    }

    @Before("@annotation(pl.pozadr.hellomongodb.aspect.mongoDb.MongoDbReadTimeMeasure)")
    public void startMongoDbReadTime() {
        readWatch.start();
    }

    @After("@annotation(pl.pozadr.hellomongodb.aspect.mongoDb.MongoDbReadTimeMeasure)")
    public void stopMongoDbReadTime() {
        readWatch.stop();
        System.out.println("MongoDb read time: " + readWatch.getTime() + " ms");
    }

    @Before("@annotation(pl.pozadr.hellomongodb.aspect.mongoDb.MongoDbSaveTimeMeasure)")
    public void startMongoDbSaveTime() {
        saveWatch.start();
    }

    @After("@annotation(pl.pozadr.hellomongodb.aspect.mongoDb.MongoDbSaveTimeMeasure)")
    public void stopMongoDbSaveTime() {
        saveWatch.stop();
        System.out.println("MongoDb save time: " + saveWatch.getTime() + " ms");
    }
}
