package com.soynomm.bloggo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Takes a raw input found in the content files
 * and then parses it for the meta-data and entry data
 * that it contains.
 *
 * @author Nomm
 * @since 1.0
 */
public class Parser {

    private final String content;

    public Parser(String content) {
        this.content = content;
    }

    /**
     * Takes an input of lines which it then transform into key, value
     * HashMap collections.
     *
     * @param items array of lines from the meta-data block to parse
     */
    private HashMap<String, String> parseMetaLines(String[] items) {
        List<String> lines = new ArrayList<>();

        /* Removes items that are `---` and trim spaces from around items. */
        for (String item : items) {
            if (!item.equals("---")) {
                lines.add(item.trim());
            }
        }

        /* Create a HashMap of key, value pairs from the lines */
        HashMap<String, String> meta = new HashMap<>();

        for (String line : lines) {
            String key = line.split(":")[0].trim();
            String value = line.split(":")[1].trim();

            meta.put(key, value);
        }

        return meta;
    }

    /**
     * Finds the meta block from the provided {@code this.content},
     * parses it with {@code this.parseMetaLines}. If no meta block was found,
     * it returns an empty HashMap instead, so nothing breaks.
     */
    public HashMap<String, String> meta() {
        Pattern pattern = Pattern.compile("(?s)^---(.*?)---*");
        Matcher matcher = pattern.matcher(this.content);

        if (matcher.find()) {
            return this.parseMetaLines(matcher.group().split("(\r\n|\r|\n)", -1));
        }

        return new HashMap<>();
    }

    /**
     * Finds and returns the entry block from the provided {@code this.content}.
     */
    public String entry() {
        String entry = this.content.replaceFirst("(?s)^---(.*?)---*", "");

        return entry.trim();
    }

}
