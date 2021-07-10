import static org.junit.jupiter.api.Assertions.*;
import com.soynomm.lava.Builder;
import org.junit.jupiter.api.Test;
import java.util.*;

public class BuilderTests {

    @Test
    public void testContents() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        var rootDir = "src/test/resources/content";
        var contentDir = "src/test/resources/content";
        var siteUrl = "https://example.com";
        var builder = new Builder(rootDir, contentDir, siteUrl);
        var expected = new ArrayList<Map<String, String>>();

        var expectedItem1 = new HashMap<String, String>();
        expectedItem1.put("entry", "<p>Hi there.</p>\n");
        expectedItem1.put("path", "/blog/hello-world");
        expectedItem1.put("date", "2020-10-10");
        expectedItem1.put("title", "Hello, World.");
        expectedItem1.put("url", "https://example.com/blog/hello-world");
        expected.add(expectedItem1);

        var expectedItem2 = new HashMap<String, String>();
        expectedItem2.put("entry", "<p>Bye there.</p>\n");
        expectedItem2.put("path", "/blog/bye-world");
        expectedItem2.put("date", "2020-10-05");
        expectedItem2.put("title", "Bye, World.");
        expectedItem2.put("url", "https://example.com/blog/bye-world");
        expected.add(expectedItem2);

        var expectedItem3 = new HashMap<String, String>();
        expectedItem3.put("entry", "<p>Test page content.</p>\n");
        expectedItem3.put("path", "/test-page");
        expectedItem3.put("title", "Test Page");
        expectedItem3.put("url", "https://example.com/test-page");
        expected.add(expectedItem3);

        assertTrue(expected.containsAll(builder.getContents()) && builder.getContents().containsAll(expected));
    }

    @Test
    public void testContentsNoneFound() {
        var rootDir = "src/test/resources/content";
        var contentDir = "src/test/resources/content/doesnt-exist";
        var siteUrl = "https://example.com";
        var builder = new Builder(rootDir, contentDir, siteUrl);
        var expected = new ArrayList<Map<String, String>>();

        assertEquals(expected, builder.getContents());
    }

    @Test
    public void testPosts() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        var rootDir = "src/test/resources/content";
        var contentDir = "src/test/resources/content/blog";
        var siteUrl = "https://example.com";
        var builder = new Builder(rootDir, contentDir, siteUrl);
        var expected = new ArrayList<Map<String, Object>>();

        var year = new HashMap<String, Object>();
        year.put("year", "2020");
        year.put("last_update", "2020-10-10");

        var entries = new ArrayList<Map<String, String>>();

        var entry1 = new HashMap<String, String>();
        entry1.put("entry", "<p>Hi there.</p>\n");
        entry1.put("path", "/blog/hello-world");
        entry1.put("date", "2020-10-10");
        entry1.put("title", "Hello, World.");
        entry1.put("url", "https://example.com/blog/hello-world");
        entries.add(entry1);

        var entry2 = new HashMap<String, String>();
        entry2.put("entry", "<p>Bye there.</p>\n");
        entry2.put("path", "/blog/bye-world");
        entry2.put("date", "2020-10-05");
        entry2.put("title", "Bye, World.");
        entry2.put("url", "https://example.com/blog/bye-world");
        entries.add(entry2);

        year.put("entries", entries);

        expected.add(year);

        assertTrue(expected.containsAll(builder.getPosts()) && builder.getPosts().containsAll(expected));
    }

}
