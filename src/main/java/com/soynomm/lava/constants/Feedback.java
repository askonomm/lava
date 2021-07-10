package com.soynomm.lava.constants;

/**
 * Holds the strings used to communicate,
 * feedback to the user.
 *
 * @author Nomm
 * @since 1.0
 */
public class Feedback {

    /* Verbosity related */
    public static String VERB_1 = "Generating feed ...";
    public static String VERB_2 = "... done";
    public static String VERB_3 = "Generating sitemap ...";
    public static String VERB_4 = "Generating content ...";

    public static String VERB_5(String path) {
        return "Churning " + path;
    }

    public static String VERB_6 = "Generating home page ...";
    public static String VERB_7 = "Copying assets ...";

    /* Filesystem related */
    public static String FILE_1 = "Were not able to create directories. Are there sufficient permissions?";
    public static String FILE_2 = "Were not able to create a file. Are there sufficient permissions?";
    public static String FILE_3 = "Were not able to write to a file. Are there sufficient permissions?";
    public static String FILE_4 = "Were not able to delete a directory. Are there sufficient permissions?";
    public static String FILE_5 = "Could not start watcher. Do the files exist and are there sufficient permissions?";
    public static String FILE_6 = "Could not copy assets directory. Does the directory exists? Are there permissions?";

    /* Configuration related */
    public static String CONF_1 = "site_url is missing from config.json";
    public static String CONF_2 = "site_title is missing from config.json";
    public static String CONF_3 = "site_description is missing from config.json";

    /* Template related */
    public static String TEMP_1(String path) {
        return "Template file can not be found at: " + path;
    }

    public static String TEMP_2(String path, String error) {
        return "Template error: " + error + ", file " + path;
    }

}
