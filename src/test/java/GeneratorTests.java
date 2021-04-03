import static org.junit.jupiter.api.Assertions.*;
import com.soynomm.bloggo.Generator;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GeneratorTests {

    @Test
    public void testGeneration() throws IOException {
        String outDir = "src/test/resources/public";
        Generator generator = new Generator(outDir);
        String expected = "Hello, World.";

        generator.generate("/index.html", "Hello, World.");

        String generatedContent = new String(Files.readAllBytes(Paths.get(outDir + "/index.html")));

        assertEquals(expected, generatedContent);

        FileUtils.deleteDirectory(new File(outDir));
    }

}
