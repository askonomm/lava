import static org.junit.jupiter.api.Assertions.*;
import com.soynomm.bloggo.Builder;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuilderTests {

    @Test
    public void testContents() {
        String rootDir = "src/test/resources/content";
        String contentDir = "src/test/resources/content";
        String siteUrl = "https://example.com";
        Builder builder = new Builder(rootDir, contentDir, siteUrl);
        List<Map<String, String>> expected = new ArrayList<>();

        Map<String, String> expectedItem1 = new HashMap<>();
        expectedItem1.put("date", "Sat, 10 Oct 2020 00:00:00 -0300");
        expectedItem1.put("entry", "<p>Hi there.</p>\n");
        expectedItem1.put("path", "/blog/hello-world");
        expectedItem1.put("pretty_date_without_year", "Oct 10");
        expectedItem1.put("pretty_date", "Oct 10, 2020");
        expectedItem1.put("date_unparsed", "2020-10-10");
        expectedItem1.put("title", "Hello, World.");
        expectedItem1.put("url", "https://example.com/blog/hello-world");
        expected.add(expectedItem1);

        Map<String, String> expectedItem2 = new HashMap<>();
        expectedItem2.put("date", "Mon, 05 Oct 2020 00:00:00 -0300");
        expectedItem2.put("entry", "<p>Bye there.</p>\n");
        expectedItem2.put("path", "/blog/bye-world");
        expectedItem2.put("pretty_date_without_year", "Oct 05");
        expectedItem2.put("pretty_date", "Oct 05, 2020");
        expectedItem2.put("date_unparsed", "2020-10-05");
        expectedItem2.put("title", "Bye, World.");
        expectedItem2.put("url", "https://example.com/blog/bye-world");
        expected.add(expectedItem2);

        Map<String, String> expectedItem3 = new HashMap<>();
        expectedItem3.put("entry", "<p>Test page content.</p>\n");
        expectedItem3.put("path", "/test-page");
        expectedItem3.put("title", "Test Page");
        expectedItem3.put("url", "https://example.com/test-page");
        expected.add(expectedItem3);

        assertEquals(expected, builder.getContents());
    }

    @Test
    public void testContentsNoneFound() {
        String rootDir = "src/test/resources/content";
        String contentDir = "src/test/resources/content/doesnt-exist";
        String siteUrl = "https://example.com";
        Builder builder = new Builder(rootDir, contentDir, siteUrl);
        List<Map<String, String>> expected = new ArrayList<>();

        assertEquals(expected, builder.getContents());
    }

    @Test
    public void testPosts() {
        String rootDir = "src/test/resources/content";
        String contentDir = "src/test/resources/content/blog";
        String siteUrl = "https://example.com";
        Builder builder = new Builder(rootDir, contentDir, siteUrl);
        List<Map<String, Object>> expected = new ArrayList<>();

        Map<String, Object> year = new HashMap<>();
        year.put("year", "2020");
        year.put("last_update", "Sat, 10 Oct 2020 00:00:00 -0300");

        List<Map<String, String>> entries = new ArrayList<>();

        Map<String, String> entry1 = new HashMap<>();
        entry1.put("date", "Sat, 10 Oct 2020 00:00:00 -0300");
        entry1.put("entry", "<p>Hi there.</p>\n");
        entry1.put("path", "/blog/hello-world");
        entry1.put("pretty_date_without_year", "Oct 10");
        entry1.put("pretty_date", "Oct 10, 2020");
        entry1.put("date_unparsed", "2020-10-10");
        entry1.put("title", "Hello, World.");
        entry1.put("url", "https://example.com/blog/hello-world");
        entries.add(entry1);

        Map<String, String> entry2 = new HashMap<>();
        entry2.put("date", "Mon, 05 Oct 2020 00:00:00 -0300");
        entry2.put("entry", "<p>Bye there.</p>\n");
        entry2.put("path", "/blog/bye-world");
        entry2.put("pretty_date_without_year", "Oct 05");
        entry2.put("pretty_date", "Oct 05, 2020");
        entry2.put("date_unparsed", "2020-10-05");
        entry2.put("title", "Bye, World.");
        entry2.put("url", "https://example.com/blog/bye-world");
        entries.add(entry2);

        year.put("entries", entries);

        expected.add(year);

        assertEquals(expected, builder.getPosts());
    }
}
