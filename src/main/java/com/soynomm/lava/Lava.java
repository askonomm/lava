package com.soynomm.lava;

import com.soynomm.bloggo.constants.Feedback;
import com.soynomm.bloggo.enums.TrimPos;
import io.methvin.watcher.DirectoryWatcher;
import io.methvin.watcher.hashing.FileHasher;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * A blog-oriented static site generator.
 *
 * @author Nomm
 */
class Lava {

    private final String resourcesDir;
    private final String outDir;
    private final boolean watch;
    private final boolean verbose;
    private final boolean help;
    private Config config;
    private Builder contentBuilder;
    private Builder blogBuilder;
    private Map<String, Object> data;

    public Lava(String[] args) {
        var argParser = new ArgParser(args);

        this.resourcesDir = Utils.trimStr(argParser.get("-r", "--resources", "./resources"), TrimPos.RIGHT, "/");
        this.outDir = Utils.trimStr(argParser.get("-o", "--out", "./public"), TrimPos.RIGHT, "/");
        this.watch = argParser.get("-w", "--watch", false);
        this.verbose = argParser.get("-v", "--verbose", false);
        this.help = argParser.get("-h", "--help", false);
    }

    /**
     * Displays when using -h or --help
     */
    private void printHelp() {
        if (this.help) {
            var format = "\t%-40s%s%n";
            System.out.println("Bloggo Static Site Generator");
            System.out.println("\nOptions:");
            System.out.printf(format, "-h, --help", "displays all options");
            System.out.printf(format, "-r <path>, --resources <path>", "path to the resources directory");
            System.out.printf(format, "-o <path>, --out <path>", "path to the compilation directory");
            System.out.printf(format, "-w, --watch", "auto-compile when any change occurs");
            System.out.printf(format, "-v, --verbose", "prints messages of compilation progress");
            System.exit(0);
        }
    }

    /**
     * Makes sure config.json has everything we need
     */
    private void verifyConfiguration() {
        this.config = new Config(this.resourcesDir + "/config.json");

        if (this.config.get("site_url").equals("")) {
            System.out.println(Feedback.CONF_1);
            System.exit(1);
        }

        if (this.config.get("site_title").equals("")) {
            System.out.println(Feedback.CONF_2);
            System.exit(1);
        }

        if (this.config.get("site_description").equals("")) {
            System.out.println(Feedback.CONF_3);
            System.exit(1);
        }
    }

    /**
     * Fetches all the content
     */
    private void initiateBuilders() {
        this.contentBuilder = new Builder(
                this.resourcesDir + "/content",
                this.resourcesDir + "/content",
                Utils.trimStr(config.get("site_url"), TrimPos.RIGHT, "/"));

        this.blogBuilder = new Builder(
                this.resourcesDir + "/content",
                this.resourcesDir + "/content/blog",
                Utils.trimStr(this.config.get("site_url"), TrimPos.RIGHT, "/"));
    }

    /**
     * Constructs a generic dataset to be used within the templates
     */
    private void constructData() {
        var data = config.get();
        var posts = this.blogBuilder.getPosts();

        data.put("posts", posts);

        if (posts.size() > 0) {
            data.put("last_update", posts.get(0).get("last_update"));
        }

        this.data = data;
    }

    /**
     * Removes {@code this.out_dir}
     */
    private void cleanOutDir() {
        try {
            FileUtils.deleteDirectory(new File(this.outDir));
        } catch(java.io.IOException e) {
            System.out.println(Feedback.FILE_4);
            System.exit(1);
        }
    }

    /**
     * Generates each of the content files
     */
    private void generateContent() {
        if (this.verbose) System.out.println(Feedback.VERB_4);

        var generator = new Generator(this.outDir);
        var template = new Template(this.resourcesDir + "/layout.hbs");
        var data = new HashMap<String, Object>();
        data.put("is_home", false);
        data.put("is_post", true);
        data.putAll(this.data);

        for (var item : this.contentBuilder.getContents()) {
            if (this.verbose) System.out.println(Feedback.VERB_5(item.get("path")));

            if (item.get("content") != null) {
                var compiledContent = Template.compile(item.get("content"), data);
                generator.generate(item.get("path").replace(".hbs", ""), compiledContent);
            } else {
                data.put("post", item);
                generator.generate(item.get("path") + "/index.html", template.compile(data));
            }
        }

        if (this.verbose) System.out.println(Feedback.VERB_2);
    }

    /**
     * Generated home page
     */
    private void generateHome() {
        if (this.verbose) System.out.println(Feedback.VERB_6);

        var generator = new Generator(this.outDir);
        var template = new Template(this.resourcesDir + "/layout.hbs");
        var data = new HashMap<String, Object>();
        data.put("is_home", true);
        data.put("is_post", false);
        data.putAll(this.data);

        generator.generate("/index.html", template.compile(data));

        if (this.verbose) System.out.println(Feedback.VERB_2);
    }

    /**
     * Copies assets directory from {@code this.resources_dir} to {@code this.out_dir}
     */
    private void copyAssets() {
        if (this.verbose) System.out.println(Feedback.VERB_7);

        try {
            var from = new File(this.resourcesDir + "/assets");
            var to = new File(this.outDir + "/assets");

            FileUtils.copyDirectory(from, to);
        } catch(java.io.IOException e) {
            System.out.println(Feedback.FILE_6);
            System.exit(1);
        }

        if (this.verbose) System.out.println(Feedback.VERB_2);
    }

    /**
     * Runs the entire generation process.
     */
    private void generate() {
        this.printHelp();
        this.verifyConfiguration();
        this.initiateBuilders();
        this.constructData();
        this.cleanOutDir();
        this.generateContent();
        this.generateHome();
        this.copyAssets();
    }

    /**
     * Runs the generation process, and optionally also watches for any
     * changes to the {@code this.resources_dir}, upon which it runs the
     * generation process again. And again. Until forever.
     */
    public void run() {
        this.generate();

        if (this.watch) {
            try {
                var watcher = DirectoryWatcher.builder()
                    .path(Paths.get(this.resourcesDir))
                    .listener(event -> this.generate())
                    .fileHasher(FileHasher.LAST_MODIFIED_TIME)

                    /* But why not go with native, you ask? Well it's because
                     * I compile Bloggo with GraalVM's native-image, and it doesn't
                     * support JNA (Java Native Access), resulting in everything breaking.
                     * Thus we go with the Java's built-in watch service instead.
                     */
                    .watchService(FileSystems.getDefault().newWatchService())
                    .build();
                watcher.watch();
            } catch(java.io.IOException e) {
                System.out.println(Feedback.FILE_5);
                System.exit(1);
            }
        }
    }

}
