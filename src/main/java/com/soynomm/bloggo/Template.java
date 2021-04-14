package com.soynomm.bloggo;

import com.github.jknack.handlebars.Handlebars;
import com.soynomm.bloggo.constants.Feedback;
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
        return Template.compileStringToHandlebars(this.template, data);
    }

    /**
     * Compiles a Mustache string {@code template} with {@code data} into
     * a HTML string, which it then returns.
     *
     * @param template template string to compile
     * @param data data to pass to the Mustache template
     */
    public static String compile(String template, Map<String, Object> data) {
        return Template.compileStringToHandlebars(template, data);
    }

    /**
     * Compiles a Handlebars string {@code template} with {@code data}
     * into a HTML string, which it then returns.
     *
     * @param template template string to compile
     * @param data data to pass to the Handlebars template
     */
    private static String compileStringToHandlebars(String template, Map<String, Object> data) {
        Handlebars handlebars = new Handlebars();
        handlebars.registerHelpers(new TemplateHelpers());

        try {
            return handlebars.compileInline(template).apply(data);
        } catch(Exception e) {
            return "";
        }
    }

}
