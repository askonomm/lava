package com.soynomm.bloggo;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheException;
import com.github.mustachejava.MustacheFactory;
import com.soynomm.bloggo.constants.Feedback;
import org.apache.commons.io.output.StringBuilderWriter;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

/**
 * Takes a Mustache template, a HashMap of data
 * and then blends those two together with a fiery
 * power of fusion to create the beloved HTML that
 * powers the site.
 *
 * @author Nomm
 * @since 1.0
 */
public class Template {
    String template;
    String templatePath;

    public Template(String path) {
        String template = "";

        try {
            template = new String(Files.readAllBytes(Paths.get(path)));
        } catch(java.io.IOException e) {
            System.out.println(Feedback.TEMP_1(path));
        }

        this.template = template;
        this.templatePath = path;
    }

    /**
     * Compiles a Mustache {@code this.template} with {@code data} into
     * a HTML string, which it then returns.
     *
     * @param data data to pass to the Mustache template
     */
    public String compile(Map<String, Object> data) {
        return Template.compileStringToMustache(this.template, data);
    }

    /**
     * Compiles a Mustache string {@code template} with {@code data} into
     * a HTML string, which it then returns.
     *
     * @param template template string to compile
     * @param data data to pass to the Mustache template
     */
    public static String compile(String template, Map<String, Object> data) {
        return Template.compileStringToMustache(template, data);
    }

    /**
     * Compiles a Mustache string {@code template} with {@code data}
     * into a HTML string, which it then returns.
     *
     * @param template template string to compile
     * @param data data to pass to the Mustache template
     */
    private static String compileStringToMustache(String template, Map<String, Object> data) {
        StringBuilderWriter writer = new StringBuilderWriter();
        MustacheFactory mf = new DefaultMustacheFactory();

        try {
            Mustache mustache = mf.compile(new StringReader(template), "");
            mustache.execute(writer, data);

            return writer.toString();
        } catch(MustacheException e) {
            System.out.println(Feedback.TEMP_2("", e.getMessage()));

            return "";
        }
    }

}
