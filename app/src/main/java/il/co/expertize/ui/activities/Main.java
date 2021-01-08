package il.co.expertize.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import il.co.expertize.R;
import il.co.expertize.services.TravelsService;
import il.co.expertize.ui.fragments.List;
import il.co.expertize.ui.viewmodels.MainViewModel;
import il.co.expertize.utils.DialogUtils;
import il.co.expertize.utils.EmailUtils;

public class Main extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        registerViews();
        registerService();
    }

    private void registerService() {
        Intent service = new Intent(Main.this, TravelsService.class);
        startService(service);
    }

    private void unRegisterService() {
        Intent service = new Intent(Main.this, TravelsService.class);
        stopService(service);
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
        switch (item.getItemId()) {
            case R.id.action_about:
                new DialogUtils(Main.this).showAlert("Travels App | V1.2","All rightrs resever @2021","Close");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterService();
    }
}