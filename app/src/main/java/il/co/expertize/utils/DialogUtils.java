package il.co.expertize.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class DialogUtils {
    private Context _context;

    public DialogUtils(Context _context) {
        this._context = _context;
    }

    public void showAlert(String title,String message,String button) {
        AlertDialog alertDialog = new AlertDialog.Builder(_context).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, button,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void showError(Exception e) {
        AlertDialog alertDialog = new AlertDialog.Builder(_context).create();
        alertDialog.setTitle("Error In Application");
        alertDialog.setMessage(e.getMessage());
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Close",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }


}
