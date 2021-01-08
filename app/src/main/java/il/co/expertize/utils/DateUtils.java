package il.co.expertize.utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public static String getDate(Date date) {
        return  new SimpleDateFormat("dd-MM-yyyy").format(date);
    }

    public static Date getDate(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.parse(date);
    }

    public static String getDate(Date date,String format) {
        return  new SimpleDateFormat(format).format(date);
    }

    public static String getDateTime() {
        Date now = Calendar.getInstance().getTime();
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(now);
        return  date;
    }

    public static Date getNow() {
        return Calendar.getInstance().getTime();
    }
}
