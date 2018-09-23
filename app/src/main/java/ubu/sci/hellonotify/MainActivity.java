package ubu.sci.hellonotify;

import android.app.Notification;
import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import static ubu.sci.hellonotify.MainApp.CHANNEL_1_ID;
import static ubu.sci.hellonotify.MainApp.CHANNEL_2_ID;

public class MainActivity extends AppCompatActivity {
    public static final String LOGTAG = "MainActivity";
    TextView tv,title;
    NotificationManagerCompat manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOGTAG,"onCreated");
        manager = NotificationManagerCompat.from(this);
        tv = findViewById(R.id.messageText);
        title = findViewById(R.id.titleMessage);
    }

    public void onNotify1Clicked(View view){
        Log.d(LOGTAG,tv.getText().toString());
        sendOnChannel1();
    }
    public void onNotify2Clicked(View view){
        Log.d(LOGTAG,tv.getText().toString());
        sendOnChannel2();
    }

    private void sendOnChannel1(){
        Notification notification= new NotificationCompat.Builder(this,CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_child)
                .setContentText(tv.getText().toString())
                .setContentTitle(title.getText().toString())
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        manager.notify(1,notification);
    }

    private void sendOnChannel2(){
        Notification notification= new NotificationCompat.Builder(this,CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_baby)
                .setContentText(tv.getText().toString())
                .setContentTitle(title.getText().toString())
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        manager.notify(2,notification);
    }
}
