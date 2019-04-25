package com.papps.freddy_lazo.redvet.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateHelper {

        /**
         * This function is used to convert an string into a timestamp as long. It is used
         * when using input from forms in the app.
         *
         * @param date: The string representation of the given date.
         * @return This function return a long to be interpreted as time.
         */
        public static Long createTimeStamp(String date) throws ParseException {
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            Date d = f.parse(date);
            return d.getTime();
        }

        public static String convertToDateSpanish(String date) throws ParseException {
            SimpleDateFormat month_date = new SimpleDateFormat("dd MMMM yyyy", new Locale("es", "ES"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date d = sdf.parse(date);
            return month_date.format(d);
        }
}
