package il.co.expertize.utils;

import android.content.Context;
import android.content.Intent;

public class EmailUtils {
    Context _context;

    public EmailUtils(Context _context) {
        this._context = _context;
    }

    public void SendEmail(String to, String subject,String message) {
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, message);
        email.setType("message/rfc822");
        _context.startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }
}
