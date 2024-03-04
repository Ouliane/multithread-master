import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class ReverseHelloTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    @Timeout(value = 42, unit = TimeUnit.MINUTES)
    public void testReverseHello() throws InterruptedException {
        ReverseHelloMultithreaded.doReverseHello();
        String expectedOutput = "";
        for (int i = 50; i >= 1; i--) {
            expectedOutput += "Hello from thread " + i + System.lineSeparator();
        }
        assertEquals(expectedOutput, systemOutRule.getLog());
    }
 }
