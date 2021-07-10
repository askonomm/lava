import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.soynomm.lava.Template;
import java.util.HashMap;

public class TemplateTests {

    @Test
    public void testCompilationFromPath() {
        var template = new Template("src/test/resources/template.hbs");
        var templateData = new HashMap<String, Object>();
        templateData.put("name", "John");
        var expected = "Hi, John";

        assertEquals(expected, template.compile(templateData));
    }

    @Test
    public void testCompilationFromPathNotFound() {
        var template = new Template("src/test/resources/template_not_found.hbs");
        var templateData = new HashMap<String, Object>();
        var expected = "";

        assertEquals(expected, template.compile(templateData));
    }

    @Test
    public void testCompilationFromString() {
        var template  = "Hi, {{name}}";
        var templateData = new HashMap<String, Object>();
        templateData.put("name", "John");
        var expected = "Hi, John";

        assertEquals(expected, Template.compile(template, templateData));
    }

    @Test
    public void testCompilatInvalidHandlebars() {
        var template = "Hi, {{#name}}{{.}}{{/is_name}}";
        var templateData = new HashMap<String, Object>();
        templateData.put("name", "John");
        var expected = "";

        assertEquals(expected, Template.compile(template, templateData));
    }

}
