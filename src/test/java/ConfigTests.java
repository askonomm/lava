import com.soynomm.bloggo.Config;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;

public class ConfigTests {

    @Test
    public void testCorrectConfig() {
        var config = new Config("src/test/resources/config.json");
        var expected = new HashMap<String, String>();
        expected.put("site_url", "https://website.com");
        expected.put("site_title", "Site Title");
        expected.put("site_description", "Site Description");

        assertEquals(expected, config.get());
    }

    @Test
    public void testInvalidConfig() {
        var config = new Config("src/test/resources/config_invalid.json");
        var expected = new HashMap<String, String>();

        assertEquals(expected, config.get());
    }

    @Test
    public void testConfigItem() {
        var config = new Config("src/test/resources/config.json");
        var expected = "https://website.com";

        assertEquals(expected, config.get("site_url"));
    }

    @Test
    public void testConfigItemNoneFound() {
        var config = new Config("src/test/resources/config.json");
        var expected = "";

        assertEquals(expected, config.get("this_does_not_exist"));
    }

}
