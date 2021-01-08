package il.co.expertize.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import il.co.expertize.utils.Globals;

public class TravelsService extends Service {

    public TravelsService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
      new Thread(new Runnable() {
          @Override
          public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        if (Globals.NewTravel == true) {
                            Log.d(Globals.TAG,"Broadcast Travel ");
                            Intent broadcast = new Intent("il.co.expertize.travel1.ACTION_NEW_TRAVEL");
                            broadcast.putExtra("travel",Globals.travel);
                            sendBroadcast(broadcast);
                            Globals.NewTravel = false;
                        }
                    } catch (InterruptedException e) {
                    }
                }
          }
      }).start();
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
