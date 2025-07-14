package ghazimoradi.soheil.jalali_date_converter;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateConverterHelper {
    private final int[] daysOfMonths = new int[]{31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 29};

    public long getDateMilliseconds(String givenDate, String format) {
        try {
            if (format == null) {
                format = "yyyy/MM/dd";
            }
            SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
            if (givenDate == null) {
                return 0;
            }
            Date mDate = sdf.parse(givenDate);
            if (mDate == null) {
                return 0;
            }
            return mDate.getTime();
        } catch (ParseException e) {
            Log.e("parseDateError", e.getMessage(), e);
        }
        return 0;
    }

    private String getJalaliFromMs(long ms) {
        //1970/01/01
        //1348/10/11
        //leap year 1347
        long DAY_MS = 24 * 60 * 60 * 1000;
        long days = ms / DAY_MS;
        int monthIndex = 10;
        days -= 18;
        int year = 1348;
        int leapYear = 1347;

        while (days > daysOfMonths[monthIndex]) {
            days -= daysOfMonths[monthIndex];
            monthIndex++;
            if (monthIndex == 12) {
                if (((year - leapYear) % 4) == 0) {
                    days -= 1;
                }
                year++;
                monthIndex = 0;
            }
        }
        return year + "/" + (monthIndex + 1) + "/" + days;
    }

    @SuppressWarnings("unused")
    public String g2j(String date, String format) {
        if (date == null) {
            return "date is null!";
        }
        return getJalaliFromMs(getDateMilliseconds(date, format));
    }

    public String g2j(String date) {
        if (date == null) {
            return "date is null!";
        }
        return getJalaliFromMs(getDateMilliseconds(date, null));
    }
}