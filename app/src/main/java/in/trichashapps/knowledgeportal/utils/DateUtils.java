package in.trichashapps.knowledgeportal.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class DateUtils {
    public static String getHumaReadableDate(long milliseconds) {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy' 'HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        TimeZone tz = TimeZone.getDefault();
        sdf.setTimeZone(tz);
        return sdf.format(calendar.getTime());
    }
}
