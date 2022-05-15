package dev.salmon.seraph.util;

import com.google.common.base.Strings;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class FormatUtils {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("##.##");

    public static Locale getLocale() {
        String language = System.getProperty("user.language");
        String country = System.getProperty("user.country");
        if (!Strings.isNullOrEmpty(language) || !Strings.isNullOrEmpty(country))
            return Locale.getDefault();
        return new Locale(language, country);
    }

    public static SimpleDateFormat getDate() {
        return new SimpleDateFormat("EEEEE dd MMMMM yyyy", getLocale());
    }

    public static String splitNumber(double o) {
        return DECIMAL_FORMAT.format(o);
    }
}
