import com.soynomm.bloggo.Config;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;

public class ConfigTests {

    @Test
    public void testCorrectConfig() {
        Config config = new Config("src/test/resources/config.json");
        HashMap<String, String> expected = new HashMap<>();

        expected.put("site_url", "https://website.com");
        expected.put("site_title", "Site Title");
        expected.put("site_description", "Site Description");

        assertEquals(expected, config.get());
    }

    @Test
    public void testInvalidConfig() {
        Config config = new Config("src/test/resources/config_invalid.json");
        HashMap<String, String> expected = new HashMap<>();

        assertEquals(expected, config.get());
    }

    @Test
    public void testConfigItem() {
        Config config = new Config("src/test/resources/config.json");
        String expected = "https://website.com";

        assertEquals(expected, config.get("site_url"));
    }

    @Test
    public void testConfigItemNoneFound() {
        Config config = new Config("src/test/resources/config.json");
        String expected = "";

        assertEquals(expected, config.get("this_does_not_exist"));
    }

}
