package com.soynomm.bloggo;

/**
 * Each method in this class can be used by
 * calling `{{method_name ...args}} inside any
 * and all Handlebars templates.
 *
 * @author Nomm
 */
public class TemplateHelpers {

    /**
     * Returns a {@code date} in a desired {@code format}.
     *
     * @param date Raw input date (year-month-day)
     * @param format SimpleDateFormat date pattern
     */
    public String format_date(String date, String format) {
        return Utils.date(date, format);
    }

}