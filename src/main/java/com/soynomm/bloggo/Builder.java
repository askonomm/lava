package com.soynomm.bloggo;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Builds a fully usable HashMap of content from
 * content files in a given path, to be consumed by
 * the {@code ContentCompiler}.
 *
 * @author Nomm
 */
public class Builder {

    String rootDir;
    String contentDir;
    List<Map<String, String>> contents;

    public Builder(String rootDir, String contentDir, String siteUrl) {
        this.rootDir = rootDir;
        this.contentDir = contentDir;
        this.contents = this.buildContents(this.buildPaths(contentDir), siteUrl);
    }

    /**
     * Returns the already built {@code this.contents}.
     */
    public List<Map<String, String>> getContents() {
        return this.contents;
    }

    /**
     * Returns a year>posts structure of {@code this.contents}
     * to be used for displaying blog posts by groups of year.
     */
    public List<Map<String, Object>> getPosts() {
        var postsByYear = new HashMap<String, List<Map<String, String>>>();

        for (var entry : this.contents) {
            if (entry.get("date") != null) {
                var dateYear = entry.get("date").split("-")[0];

                if (postsByYear.get(dateYear) != null) {
                    var postsInYear = postsByYear.get(dateYear);
                    postsInYear.add(entry);
                    postsByYear.replace(dateYear, postsInYear);
                } else {
                    var postsInYear = new ArrayList<Map<String, String>>();
                    postsInYear.add(entry);
                    postsByYear.put(dateYear, postsInYear);
                }
            }
        }

        var postsByYearParsed = new ArrayList<Map<String, Object>>();

        for (var year : new TreeMap<>(postsByYear).entrySet()) {
            var postsByYearEntry = new HashMap<String, Object>();
            var entries = postsByYear.get(year.getKey());

            entries.sort((a, b) -> b.get("date").compareTo(a.get("date")));

            postsByYearEntry.put("year", year.getKey());
            postsByYearEntry.put("last_update", year.getValue().get(0).get("date"));
            postsByYearEntry.put("entries", entries);

            postsByYearParsed.add(postsByYearEntry);
        }

        return postsByYearParsed;
    }

    /**
     * Recursively travers a given {@code fromPath} in search for
     * Markdown (.md) files, which it then composes an array of paths of.
     *
     * @param fromPath path to traverse for content files
     */
    private List<String> buildPaths(String fromPath) {
        var paths = new ArrayList<String>();
        var file = new File(fromPath);
        var pathList = file.list();

        if (pathList != null) {
            for (var item : pathList) {
                var completePath = fromPath + "/" + item;
                var relativePath = completePath.replace(this.rootDir, "");
                var path = Paths.get(completePath);

                if (Files.isRegularFile(path) && (item.endsWith(".md")) || item.endsWith(".hbs")) {
                    paths.add(relativePath);
                }

                if (Files.isDirectory(path)) {
                    paths.addAll(this.buildPaths(completePath));
                }
            }
        }

        return paths;
    }

    /**
     * Composes a list of HashMaps containing the content of each content
     * file given in {@code paths}, including its meta and Markdown content
     * generated into ready-to-use HTML. If {@code date} is present as a meta item in
     * the content file, it will also add {@code pretty_date} and {@code pretty_date_without_year}
     * to the HashMap.
     *
     * @param paths array of file paths relative to {@code this.root_dir}
     * @param siteUrl a fully qualified URL of the site (example: https://website.com)
     */
    private List<Map<String, String>> buildContents(List<String> paths, String siteUrl) {
        var contents = new ArrayList<Map<String, String>>();

        for (var item : paths) {
            try {
                var fileContent = new String(Files.readAllBytes(Paths.get(this.rootDir + item)));
                var content = new HashMap<String, String>();

                /* In the case it's a mustache file, we don't actually
                 * want to do any of the magic that we do with a Markdown
                 * file.
                 */
                if (item.endsWith(".hbs")) {
                    content.put("content", fileContent);
                    content.put("path", item.replace(".hbs", ""));

                }

                /* However if it is a markdown file, we parse it normally
                 * and if it has a date meta item, we'll also do date parsing.
                 */
                if (item.endsWith(".md")) {
                    var parser = new Parser(fileContent);
                    var meta = parser.meta();
                    var entry = parser.entry();

                    content.putAll(meta);
                    content.put("entry", Utils.markdownToHtml(entry));
                    content.put("path", item.replace(".md", ""));
                    content.put("url", siteUrl + content.get("path"));
                }

                contents.add(content);
            } catch(java.io.IOException e) {
                System.out.println("File not found.");
            }
        }

        return contents;
    }

}
