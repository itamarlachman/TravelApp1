package il.co.expertize.utils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public static String getDateTime() {
        Date now = Calendar.getInstance().getTime();
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(now);
        return  date;
    }

    public static Date getNow() {
        return Calendar.getInstance().getTime();
    }
}
