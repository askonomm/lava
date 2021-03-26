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
        Map<String, String> expected = new HashMap<>();
        expected.put("title", "Hello, World");
        expected.put("date", "2020-10-10");

        String mock = new String(Files.readAllBytes(Paths.get("src/test/resources/content.md")));
        Parser parser = new Parser(mock);
        Map<String, String> result = parser.meta();

        assertEquals(expected, result);
    }

    @Test
    public void testNoMeta() throws IOException {
        Map<String, String> expected = new HashMap<>();

        String mock = new String(Files.readAllBytes(Paths.get("src/test/resources/content_no_meta.md")));
        Parser parser = new Parser(mock);
        Map<String, String> result = parser.meta();

        assertEquals(expected, result);
    }

    @Test
    public void testEntry() throws IOException {
        String expected = "Hi there.";

        String mock = new String(Files.readAllBytes(Paths.get("src/test/resources/content.md")));
        Parser parser = new Parser(mock);
        String result = parser.entry();

        assertEquals(expected, result);
    }

    @Test
    public void testEntryNoMeta() throws IOException {
        String expected = "Hi there.";

        String mock = new String(Files.readAllBytes(Paths.get("src/test/resources/content_no_meta.md")));
        Parser parser = new Parser(mock);
        String result = parser.entry();

        assertEquals(expected, result);
    }

}
