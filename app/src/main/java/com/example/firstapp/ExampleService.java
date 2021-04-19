package com.example.firstapp;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import static com.example.firstapp.App.CHANNEL_ID;

public class ExampleService extends Service {

    ServerThread thread;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String portNumber = intent.getStringExtra("portNumber");
        System.out.println(portNumber);
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        thread = new ServerThread(Integer.parseInt(portNumber));
        thread.start();

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Server started on port")
                .setContentText(portNumber)
                .setSmallIcon(R.drawable.ic_android)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, notification);

        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        thread.interrupt();
    }

    class ServerThread extends Thread{
        int portNumber;
        ServerThread(int number){
            portNumber = number;
        }
        @Override
        public void run() {
            Server server = new Server(portNumber);
        }
    }
}
