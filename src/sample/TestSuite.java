package sample;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
public class TestSuite {



    @RunWith(Suite.class)

    @Suite.SuiteClasses({
            UserTest.class

    })

    public class JunitTestSuite {
    }
}
