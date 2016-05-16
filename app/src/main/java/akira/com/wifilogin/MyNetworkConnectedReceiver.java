package akira.com.wifilogin;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by AndoridDev on 2016/3/14.
 *
 *
 AndroidManifest.xml

 <uses-permission android:name="android.permission.INTERNET"/>
 <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
 <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />

 <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
 <uses-permission android:name="android.permission.CHANGE_NETWORK_STATE" />

 //通知列 震動,閃燈 權限
 <uses-permission android:name="android.permission.FLASHLIGHT"/>
 <uses-permission android:name="android.permission.VIBRATE"/>


 //intent-filter

 <receiver android:name=".MyNetworkConnectedReceiver">
 <intent-filter>
 <action android:name="android.net.wifi.WIFI_STATE_CHANGED"/>
 <action android:name="android.net.wifi.STATE_CHANGE"/>
 </intent-filter>
 </receiver>


 **/

public class MyNetworkConnectedReceiver extends BroadcastReceiver{

    WifiManager mWiFi;

    int hasShow =0; //flag 僅第一次連入WiFi通知
    final int notifyID = 1;
    String nowSSID;
    String SSID= "Specific SSID";
    String strURL = "https://www.google.com.tw/";

    @Override
    public void onReceive(Context context, Intent intent) {
        mWiFi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        nowSSID = mWiFi.getConnectionInfo().getSSID();

//        ShowNotification(context);

        SSID = context.getApplicationContext().getSharedPreferences(context.getApplicationContext().getPackageName(), 0).getString("SSID", "");

//        if (nowSSID.contains(SSID) & hasShow == 0) {
        if (nowSSID.contains(SSID)) {
            hasShow = 1;
            Log.i("currentNetworkInfo ", "you are now connect to wifi!!");

            Intent intentLogin = new Intent();
            intentLogin.setClass(context, MainActivity.class);
            

            intentLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            context.startActivity(intentLogin);
        }

    }


    public void ShowNotification(Context context){

            /** Notification **/
            final Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION); // 通知音效的URI，在這裡使用系統內建的通知音效
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(R.drawable.wifi)       //圖示
                            .setContentTitle("歡迎使用 VM WiFi ")  //標題
                            .setContentText("點擊獲得更多優惠資訊")  //內容
                            .setAutoCancel(true)                 //點擊後消失
                            .setVibrate(new long[]{0, 1000})     //震動效果new long[] {a,b,c,d} 延遲a毫秒→震動b毫秒→延遲c毫秒→震動d毫秒
                            .setLights(0xff0000ff, 3000, 1000)   //三色燈，閃光顏色/持續毫秒/停頓毫秒
                            .setSound(soundUri);                 //效果音

            // 取得系統的通知服務
            NotificationManager mNotificationManager
                    = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            //設定點擊後跳頁
            Intent resultIntent = new Intent(Intent.ACTION_VIEW);
            resultIntent.setData(Uri.parse(strURL));

            //延遲執行之intent > PendingIntent
            PendingIntent pending = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(pending);

            //發送通知
            mNotificationManager.notify(notifyID, mBuilder.build());

    }

}




