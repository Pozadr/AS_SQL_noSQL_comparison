package pl.pozadr.hellomongodb.aspect.sqlDb;

import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SqlDbTimeMeasureAspect {
    private final StopWatch readWatch;
    private final StopWatch saveWatch;

    public SqlDbTimeMeasureAspect() {
        this.readWatch = new StopWatch();
        this.saveWatch = new StopWatch();


    }

    @Before("@annotation(pl.pozadr.hellomongodb.aspect.sqlDb.SqlDbReadTimeMeasure)")
    public void startSqlDbReadTime() {
        readWatch.start();
    }

    @After("@annotation(pl.pozadr.hellomongodb.aspect.sqlDb.SqlDbReadTimeMeasure)")
    public void stopSqlDbReadTime() {
        readWatch.stop();
        System.out.println("Sql read time: " + readWatch.getTime() + " ms");
    }

    @Before("@annotation(pl.pozadr.hellomongodb.aspect.sqlDb.SqlDbSaveTimeMeasure)")
    public void startSqlDbSaveTime() {
        saveWatch.start();
    }

    @After("@annotation(pl.pozadr.hellomongodb.aspect.sqlDb.SqlDbSaveTimeMeasure)")
    public void stopSqlDbSaveTime() {
        saveWatch.stop();
        System.out.println("Sql save time: " + saveWatch.getTime() + " ms");
    }
}
