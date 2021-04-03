import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.soynomm.bloggo.Template;
import java.util.HashMap;
import java.util.Map;

public class TemplateTests {

    @Test
    public void testCompilationFromPath() {
        Template template = new Template("src/test/resources/template.mustache");
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("name", "John");
        String expected = "Hi, John";

        assertEquals(expected, template.compile(templateData));
    }

    @Test
    public void testCompilationFromPathNotFound() {
        Template template = new Template("src/test/resources/template_not_found.mustache");
        Map<String, Object> templateData = new HashMap<>();
        String expected = "";

        assertEquals(expected, template.compile(templateData));
    }

    @Test
    public void testCompilationFromString() {
        String template  = "Hi, {{name}}";
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("name", "John");
        String expected = "Hi, John";

        assertEquals(expected, Template.compile(template, templateData));
    }

    @Test
    public void testCompilatInvalidMustache() {
        String template = "Hi, {{#name}}{{.}}{{/is_name}}";
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("name", "John");
        String expected = "";

        assertEquals(expected, Template.compile(template, templateData));
    }

}
