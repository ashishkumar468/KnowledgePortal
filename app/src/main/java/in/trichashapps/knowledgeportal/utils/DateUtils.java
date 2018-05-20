package in.trichashapps.knowledgeportal.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class DateUtils {
    public static String getHumaReadableDate(long milliseconds) {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy' 'HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        calendar.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
        return sdf.format(calendar.getTime());
    }
}
