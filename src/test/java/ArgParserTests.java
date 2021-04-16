import static org.junit.jupiter.api.Assertions.*;
import com.soynomm.bloggo.ArgParser;
import org.junit.jupiter.api.Test;

public class ArgParserTests {

    @Test
    public void testGetSingleArg() {
        var args = new String[]{"--arg", "value"};
        var argParser = new ArgParser(args);
        var result = argParser.get("--arg", "default");

        assertEquals("value", result);
    }

    @Test
    public void testGetSingleArgNoValue() {
        var args = new String[]{"--arg"};
        var argParser = new ArgParser(args);
        var result = argParser.get("--arg", "default");

        assertEquals("default", result);
    }

    @Test
    public void testGetSingleArgNonePresent() {
        var args = new String[]{};
        var argParser = new ArgParser(args);
        var result = argParser.get("--arg", "default");

        assertEquals("default", result);
    }

    @Test
    public void testGetMultipleArg() {
        var args = new String[]{"--arg", "value"};
        var argParser = new ArgParser(args);
        var result = argParser.get("--arg", "-a", "default");

        assertEquals("value", result);
    }

    @Test
    public void testGetMultipleArgNoValues() {
        var args = new String[]{"--arg1", "--arg2"};
        var argParser = new ArgParser(args);
        var result = argParser.get("--arg1", "-a", "default");

        assertEquals("default", result);
    }

    @Test
    public void testGetMultipleArgBothPresent() {
        var args = new String[]{"--arg", "value", "-a", "value"};
        var argParser = new ArgParser(args);
        var result = argParser.get("--arg", "-a", "default");

        assertEquals("value", result);
    }

    @Test
    public void testGetMultipleArgAlternativePresent() {
        var args = new String[]{"-a", "value"};
        var argParser = new ArgParser(args);
        var result = argParser.get("--arg", "-a", "default");

        assertEquals("value", result);
    }

    @Test
    public void testGetMultipleArgNonePresent() {
        var args = new String[]{};
        var argParser = new ArgParser(args);
        var result = argParser.get("--arg", "-a", "default");

        assertEquals("default", result);
    }

    @Test
    public void testGetMultipleArgBoolean() {
        var args = new String[]{"--arg"};
        var argParser = new ArgParser(args);
        var result = argParser.get("--arg", "-a", false);

        assertTrue(result);
    }

    @Test
    public void testGetMultipleArgBooleanNonePresentTrue() {
        var args = new String[]{};
        var argParser = new ArgParser(args);
        var result = argParser.get("--arg", "-a", true);

        assertTrue(result);
    }

}
