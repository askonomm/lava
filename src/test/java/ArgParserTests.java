import static org.junit.jupiter.api.Assertions.*;
import com.soynomm.bloggo.ArgParser;
import org.junit.jupiter.api.Test;

public class ArgParserTests {

    @Test
    public void testGetSingleArg() {
        String[] args = new String[]{"--arg", "value"};
        ArgParser argParser = new ArgParser(args);
        String result = argParser.get("--arg", "default");

        assertEquals("value", result);
    }

    @Test
    public void testGetSingleArgNoValue() {
        String[] args = new String[]{"--arg"};
        ArgParser argParser = new ArgParser(args);
        String result = argParser.get("--arg", "default");

        assertEquals("default", result);
    }

    @Test
    public void testGetSingleArgNonePresent() {
        String[] args = new String[]{};
        ArgParser argParser = new ArgParser(args);
        String result = argParser.get("--arg", "default");

        assertEquals("default", result);
    }

    @Test
    public void testGetMultipleArg() {
        String[] args = new String[]{"--arg", "value"};
        ArgParser argParser = new ArgParser(args);
        String result = argParser.get("--arg", "-a", "default");

        assertEquals("value", result);
    }

    @Test
    public void testGetMultipleArgNoValues() {
        String[] args = new String[]{"--arg1", "--arg2"};
        ArgParser argParser = new ArgParser(args);
        String result = argParser.get("--arg1", "-a", "default");

        assertEquals("default", result);
    }

    @Test
    public void testGetMultipleArgBothPresent() {
        String[] args = new String[]{"--arg", "value", "-a", "value"};
        ArgParser argParser = new ArgParser(args);
        String result = argParser.get("--arg", "-a", "default");

        assertEquals("value", result);
    }

    @Test
    public void testGetMultipleArgAlternativePresent() {
        String[] args = new String[]{"-a", "value"};
        ArgParser argParser = new ArgParser(args);
        String result = argParser.get("--arg", "-a", "default");

        assertEquals("value", result);
    }

    @Test
    public void testGetMultipleArgNonePresent() {
        String[] args = new String[]{};
        ArgParser argParser = new ArgParser(args);
        String result = argParser.get("--arg", "-a", "default");

        assertEquals("default", result);
    }

    @Test
    public void testGetMultipleArgBoolean() {
        String[] args = new String[]{"--arg"};
        ArgParser argParser = new ArgParser(args);
        boolean result = argParser.get("--arg", "-a", false);

        assertTrue(result);
    }

    @Test
    public void testGetMultipleArgBooleanNonePresentTrue() {
        String[] args = new String[]{};
        ArgParser argParser = new ArgParser(args);
        boolean result = argParser.get("--arg", "-a", true);

        assertTrue(result);
    }

}
