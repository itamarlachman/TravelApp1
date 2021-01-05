package il.co.expertize;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import il.co.expertize.utils.DialogUtils;
import il.co.expertize.utils.EmailUtils;

public class Main extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        registerViews();
    }

    private void registerViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Email", Snackbar.LENGTH_LONG)
                        .setAction("Send an email to our company", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                new EmailUtils(Main.this).SendEmail("admin@test.com","Test","This is a test message");
                            }
                        }).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about) {
           new DialogUtils(Main.this).showAlert("Travels App | V1.2","All rightrs resever @2021","Close");
        }
        return super.onOptionsItemSelected(item);
    }
}