package com.soynomm.bloggo;

import com.soynomm.bloggo.constants.Feedback;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Responsible for generating the HTML that powers
 * the end-result static website, as well as generating the XML blog feed
 * and a sitemap.xml for crawlers.
 *
 * @author Nomm
 */
public class Generator {

    String out_dir;

    public Generator(String out_dir) {
        this.out_dir = out_dir;
    }

    /**
     * Takes a location and contents, then creates a {@code index.html} file with
     * that information.
     *
     * @param path path inside the {@code this.out_dir} to generate into
     * @param contents contents to add into the generated file
     */
    public void generate(String path, String contents) {
        var pathToFile = Paths.get(this.out_dir + path);

        try {
            Files.createDirectories(pathToFile.getParent());
        } catch(java.io.IOException e) {
            System.out.println(Feedback.FILE_1);
            System.exit(1);
        }

        try {
            Files.createFile(pathToFile);
        } catch(java.io.IOException e) {
            System.out.println(Feedback.FILE_2);
            System.exit(1);
        }

        try {
            var file = new File(this.out_dir + path);
            var fw = new FileWriter(file.getAbsoluteFile());
            var bw = new BufferedWriter(fw);
            bw.write(contents);
            bw.close();
        } catch(java.io.IOException e) {
            System.out.println(Feedback.FILE_3);
            System.exit(1);
        }
    }

}
