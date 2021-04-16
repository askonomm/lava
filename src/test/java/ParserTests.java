import static org.junit.jupiter.api.Assertions.*;
import com.soynomm.bloggo.Parser;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ParserTests {

    @Test
    public void testMeta() throws IOException {
        var expected = new HashMap<String, String>();
        expected.put("title", "Hello, World");
        expected.put("date", "2020-10-10");

        var mock = new String(Files.readAllBytes(Paths.get("src/test/resources/content.md")));
        var parser = new Parser(mock);
        var result = parser.meta();

        assertEquals(expected, result);
    }

    @Test
    public void testNoMeta() throws IOException {
        var expected = new HashMap<String, String>();

        var mock = new String(Files.readAllBytes(Paths.get("src/test/resources/content_no_meta.md")));
        var parser = new Parser(mock);
        var result = parser.meta();

        assertEquals(expected, result);
    }

    @Test
    public void testEntry() throws IOException {
        var expected = "Hi there.";

        var mock = new String(Files.readAllBytes(Paths.get("src/test/resources/content.md")));
        var parser = new Parser(mock);
        var result = parser.entry();

        assertEquals(expected, result);
    }

    @Test
    public void testEntryNoMeta() throws IOException {
        var expected = "Hi there.";

        var mock = new String(Files.readAllBytes(Paths.get("src/test/resources/content_no_meta.md")));
        var parser = new Parser(mock);
        var result = parser.entry();

        assertEquals(expected, result);
    }

}
