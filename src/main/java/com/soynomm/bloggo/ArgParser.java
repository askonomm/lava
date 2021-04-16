package com.soynomm.bloggo;

import java.util.Arrays;

/**
 * ArgParser parses CLI arguments so that the getting of a
 * value for a specific CLI argument would be painless, and
 * that you could also pass a default value for when the CLI
 * argument is not there.
 *
 * @author Nomm
 */
public class ArgParser {

    private final String[] args;

    public ArgParser(String[] args) {
        this.args = args;
    }

    /**
     * Attempts to find out if a given {@code arg} is provided or not.
     *
     * @param arg the CLI argument to search for
     */
    private boolean has(String arg) {
        return Arrays.asList(this.args).contains(arg);
    }

    /**
     * Attempts to find a value for a given {@code arg} and if the
     * search attempt is unsuccessful returns {@code defaultValue} instead.
     *
     * @param arg the CLI argument to search for
     * @param defaultValue the string to return when CLI argument is not found
     */
    public String get(String arg, String defaultValue) {
        if (this.has(arg)) {
            var index = Arrays.asList(args).indexOf(arg);

            try {
                var value = this.args[index + 1];

                if (value.charAt(0) != '-') {
                    return value;
                }
            } catch(java.lang.ArrayIndexOutOfBoundsException e) {
                return defaultValue;
            }
        }

        return defaultValue;
    }

    /**
     * Attempts to find the first value for any given argument in {@code args}
     * and if the search attempt is unsuccessful returns {@code defaultValue} instead.
     *
     * @param arg the CLI arguments to search for
     * @param alternativeArg alternative CLI argument to search for
     * @param defaultValue fallback string for when the CLI arguments were not found
     */
    public String get(String arg, String alternativeArg, String defaultValue) {
        if (!this.get(arg, "").equals("")) {
            return this.get(arg, "");
        }

        if (!this.get(alternativeArg, "").equals("")) {
            return this.get(alternativeArg, "");
        }

        return defaultValue;
    }

    /**
     * Attempts to find the first value for any given argument in {@code args}
     * and if the search attempt is unsuccessful returns {@code defaultValue} instead.
     *
     * @param arg the CLI arguments to search for
     * @param alternativeArg alternative CLI argument to search for
     * @param defaultValue fallback boolean for when the CLI arguments were not found
     */
    public boolean get(String arg, String alternativeArg, boolean defaultValue) {
        if (this.has(arg) || this.has(alternativeArg)) {
            return true;
        }

        return defaultValue;
    }

}
