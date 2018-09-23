package ubu.sci.hellonotify;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;

public class MainApp extends Application {
    public static final String CHANNEL_1_ID = "channel_1";
    public static final String CHANNEL_2_ID = "channel_2";
    public void onCreate(){
        super.onCreate();
        createNotificationChanels();
    }
    private void createNotificationChanels()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ){
            Log.d("MainApp ","NotificationChannels Created");
            NotificationChannel channel1 = new NotificationChannel(
              CHANNEL_1_ID,"Channel 1", NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is a channel1");

            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_2_ID,"Channel 2", NotificationManager.IMPORTANCE_LOW
            );
            channel2.setDescription("This is a channel2");

            //minimum api version 23
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);

        }

    }
}
