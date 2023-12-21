package api.test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    public Logger logger;
    @BeforeClass
    public void setUp(){
//  logs
        logger = LogManager.getLogger(this.getClass());
    }
}
