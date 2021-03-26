package com.soynomm.bloggo;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Used to read any given json configuration
 * file into a HashMap to be consumed either entirely or
 * by a provided key.
 *
 * @author Nomm
 * @since 1.0
 */
public class Config {

    Map<String, Object> config;

    /**
     * Attempts to read data from a {@code path} and
     * if successful stores it in {@code this.config}.
     * Otherwise it'll store an empty HashMap instead.
     *
     * @param path path to the .json file
     */
    public Config(String path) {
        try {
            String configContent = new String(Files.readAllBytes(Paths.get(path)));
            Type configType = new TypeToken<HashMap<String, Object>>(){}.getType();
            this.config = new Gson().fromJson(configContent, configType);
        } catch(IOException | JsonSyntaxException e) {
            this.config = new HashMap<>();
        }
    }

    /**
     * Returns a value for a given {@code key} in {@code this.config}.
     * If a key is not found then it returns an empty string instead.
     *
     * @param key key of a configuration item
     */
    public String get(String key) {
        if (this.config.get(key) != null) {
            return String.valueOf(this.config.get(key));
        }

        return "";
    }

    /**
     * Returns the entire {@code this.config}.
     */
    public Map<String, Object> get() {
        return this.config;
    }

}
