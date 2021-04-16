package com.soynomm.bloggo;

import com.soynomm.bloggo.enums.TrimPos;
import org.commonmark.Extension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * A bunch of stand-alone methods that do useful-enough things that
 * it makes sense to extract them here.
 *
 * @author Nomm
 */
public class Utils {

    /**
     * Transform a given {@code input} string containing date into a RFC 822 date string.
     *
     * @param input raw date input (needs to be in yyyy-MM-dd format)
     */
    public static String date(String input) {
        var sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        try {
            var date = sdf.parse(input);
            sdf.applyPattern("EEE, dd MMM yyyy HH:mm:ss Z");
            return sdf.format(date);
        } catch(java.text.ParseException e) {
            return "";
        }
    }

    /**
     * Transforms a given {@code input} string containing date into a desired {@code format}.
     *
     * @param input raw date input (needs to be in yyyy-MM-dd format)
     * @param format SimpleDateFormat date pattern
     */
    public static String date(String input, String format) {
        var sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        try {
            var date = sdf.parse(input);
            sdf.applyPattern(format);
            return sdf.format(date);
        } catch(java.text.ParseException e) {
            return "";
        }
    }

    /**
     * Takes raw {@code markdown} and returns HTML.
     *
     * @param markdown raw Markdown string
     */
    public static String markdownToHtml(String markdown) {
        var extensions = Collections.singletonList(HeadingAnchorExtension.create());
        var parser = Parser.builder().extensions(extensions).build();
        var document = parser.parse(markdown);
        var renderer = HtmlRenderer.builder().extensions(extensions).build();

        return renderer.render(document);
    }

    /**
     * Takes a string {@code input} and trims a {@code trimmedChar} from {@code position}.
     *
     * @param input string to be trimmed
     * @param position either TrimPos.LEFT or TrimPos.RIGHT
     * @param trimmedChar string character to trim
     */
    public static String trimStr(String input, TrimPos position, String trimmedChar) {
        if (position.equals(TrimPos.LEFT)) {
            var firstChar = input.substring(0, 1);

            if (firstChar.equals(trimmedChar)) {
                input = trimStr(input.substring(1), position, trimmedChar);
            }
        }

        if (position.equals(TrimPos.RIGHT)) {
            var lastChar = input.substring(input.length() - 1);

            if (lastChar.equals(trimmedChar)) {
                input = trimStr(input.substring(0, input.length() - 1), position, trimmedChar);
            }
        }

        return input;
    }

}
