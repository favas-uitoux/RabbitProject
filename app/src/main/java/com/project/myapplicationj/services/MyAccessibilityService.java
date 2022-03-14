package com.project.myapplicationj.services;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import androidx.core.app.NotificationCompat;

import com.project.myapplicationj.R;
import com.project.myapplicationj.Utils;
import com.project.myapplicationj.activities.TestActivity;
import com.project.myapplicationj.activities.TestBActivity;

public class MyAccessibilityService extends AccessibilityService {

    private  String TAG="MY ACC SERVICE";
    public static final String CHANNEL_ID = "ForegroundServiceChannel";
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input = intent.getStringExtra("inputExtra");
        createNotificationChannel();
        Intent notificationIntent = new Intent(this, TestActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Foreground Service")
                .setContentText(input)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, notification);
        //do heavy work on a background thread
        //stopSelf();
        return START_NOT_STICKY;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {

        String package_name=accessibilityEvent.getPackageName().toString();

        PackageManager packageManager=this.getPackageManager();


        try{
            ApplicationInfo applicationInfo=packageManager.getApplicationInfo(package_name,0);
            CharSequence appLabel=packageManager.getApplicationLabel(applicationInfo);

            Log.e(TAG,"app name is "+appLabel);
        }
        catch (Exception e)
        {

        }

        getChild(getRootInActiveWindow());
      //  getChromeUrl(getRootInActiveWindow());


    }

    public void getChromeUrl(AccessibilityNodeInfo nodeInfo) {
        //Use the node info tree to identify the proper content.
        //For now we'll just log it to logcat.
        Log.d(TAG, toStringHierarchy(nodeInfo, 0));
    }

    private String toStringHierarchy(AccessibilityNodeInfo info, int depth) {
        if (info == null) return "";

        String result = "|";
        for (int i = 0; i < depth; i++) {
            if (result.contains("http")) {
                Log.d(TAG, "Found URL!!!!!!!!!!!!!!" + result);
            }
            result += "  ";
        }

        result += info.toString();

        for (int i = 0; i < info.getChildCount(); i++) {
            result += "\n" + toStringHierarchy(info.getChild(i), depth + 1);
        }

        return result;
    }


    private void getChild(AccessibilityNodeInfo info)
    {
        int i=info.getChildCount();
        for(int p=0;p<i;p++)
        {
            AccessibilityNodeInfo n=info.getChild(p);
            if(n!=null) {
                String strres = n.getViewIdResourceName();
                if (n.getText() != null) {
                    String txt = n.getText().toString();
                    Log.d("Track", strres + "  :  " + txt);
//txt="result"+txt;
                    if(txt !=null)
                    {
                       // Utils.getTestActivityInterfac().showDialog("bds");

                        if(txt.toLowerCase().contains("yahoo"))
                        {
                            Intent dialogIntent = new Intent(this, TestBActivity.class);
                            dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(dialogIntent);
                        }

                    }

                }
                getChild(n);
            }
        }
    }
    @Override
    public void onInterrupt() {

        Log.e(TAG,"acc services iterupted");
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();


AccessibilityServiceInfo info=new AccessibilityServiceInfo();
        // Set the type of events that this service wants to listen to. Others
        // won't be passed to this service.
        info.eventTypes = AccessibilityEvent.TYPE_VIEW_CLICKED |
                AccessibilityEvent.TYPE_VIEW_FOCUSED;

        // If you only want this service to work with specific applications, set their
        // package names here. Otherwise, when the service is activated, it will listen
        // to events from all applications.
        info.packageNames = new String[]
                {"com.android.chrome", "com.example.android.mySecondApp"};

        // Set the type of feedback your service will provide.
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN;

        // Default services are invoked only if no package-specific ones are present
        // for the type of AccessibilityEvent generated. This service *is*
        // application-specific, so the flag isn't necessary. If this was a
        // general-purpose service, it would be worth considering setting the
        // DEFAULT flag.

        // info.flags = AccessibilityServiceInfo.DEFAULT;

        info.notificationTimeout = 100;

        this.setServiceInfo(info);

        Log.e(TAG,"acc services connected");




    }
}
