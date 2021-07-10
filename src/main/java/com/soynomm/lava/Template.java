package com.soynomm.lava;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import com.soynomm.lava.constants.Feedback;
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
        var template = "";

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
        var handlebars = new Handlebars();

        // Create a `format_date` helper.
        handlebars.registerHelper("format_date", new Helper<String>() {
            public CharSequence apply(String date, Options options) {
                return new Handlebars.SafeString(Utils.date(date, options.param(0)));
            }
        });

        try {
            return handlebars.compileInline(template).apply(data);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

}
