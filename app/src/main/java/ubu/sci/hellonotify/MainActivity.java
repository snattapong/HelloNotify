package ubu.sci.hellonotify;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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

        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_doggy);

        Intent contentIntent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent =  PendingIntent.getActivity(this,0,contentIntent,0);

        Intent btIntent = new Intent(this,NotificationReceiver.class);
        btIntent.putExtra("toastMessage",tv.getText().toString());
        PendingIntent actionIntent = PendingIntent.getBroadcast(this,0,btIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification= new NotificationCompat.Builder(this,CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_child)
                .setContentText(tv.getText().toString())
                .setLargeIcon(largeIcon)
                .setStyle(
                        new NotificationCompat.BigTextStyle()
                            .setBigContentTitle("Big Title")
                            .bigText("This is ajsklfjskdjf;ajdflksajflds asfjksfjsf new sstylte heree is a very good indeed")
                            .setSummaryText("This is a summaryText")
                )
                .setContentTitle(title.getText().toString())
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(pendingIntent)
                .setOnlyAlertOnce(true)
                .setAutoCancel(true)
                .setColor(Color.BLUE)
                .addAction(R.mipmap.ic_launcher,"Action JJ",actionIntent)
                .build();
        manager.notify(1,notification);
    }

    private void sendOnChannel2(){
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_doggy);
        Notification notification= new NotificationCompat.Builder(this,CHANNEL_2_ID)
                .setLargeIcon(largeIcon)
                .setSmallIcon(R.drawable.ic_baby)
                .setContentText(tv.getText().toString())
                .setStyle( new NotificationCompat.InboxStyle()
                        .addLine("This is a line 1")
                        .setSummaryText("Summary in box")
                        .addLine("Oh yeah ")
                        .addLine("Gooo d goood")
                )
                .setOnlyAlertOnce(true)
                .setAutoCancel(true)
                .setColor(Color.GREEN)
                .setContentTitle(title.getText().toString())
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        manager.notify(2,notification);
    }
}
