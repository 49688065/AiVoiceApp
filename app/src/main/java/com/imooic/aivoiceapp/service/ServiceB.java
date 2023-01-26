package com.imooic.aivoiceapp.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;
import android.app.Service;

import com.imooic.aivoiceapp.R;

public class ServiceB extends Service {
 
     private static final String TAG = ServiceB.class.getSimpleName();
     private PendingIntent mPendingIntent;
     private MyBinder mBinder;
     private MyServiceConnection mServiceConnection;
 
     @Override
     public IBinder onBind(Intent intent) {
         return mBinder;
     }
 
     @Override
     public void onCreate() {
         Log.e(TAG,"远程服务B onCreate");
         if (mBinder == null) {
             mBinder = new MyBinder();
         }
 
         mServiceConnection = new MyServiceConnection();
     }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"远程服务B onDestroy");

    }

    @Override
     public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG,"远程服务B onStartCommand");
        this.bindService(new Intent(ServiceB.this, AiVoiceService.class), mServiceConnection, Context.BIND_IMPORTANT);
         mPendingIntent = PendingIntent.getService(this, 0, intent, 0);
         Notification.Builder builder = new Notification.Builder(this);

         builder.setTicker("守护服务B启动中")
                 .setContentText("我是来守护服务A的")
                 .setContentTitle("守护服务B")
                 .setSmallIcon(R.mipmap.ic_launcher)
                 .setContentIntent(mPendingIntent)
                 .setWhen(System.currentTimeMillis());
         Notification notification = builder.build();
//         startForeground(startId, notification);//当一个app两前台服务会销毁另一个

         return START_STICKY;
     }
 
     public class MyBinder extends IBridgeInterface.Stub {

         @Override
         public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

         }

         @Override
         public String getName() throws RemoteException {
             return "B_name:"+TAG;
         }
     }
 
     class MyServiceConnection implements ServiceConnection {
 
         @Override
         public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
             String name=null;
             try {
                 name=IBridgeInterface.Stub.asInterface(iBinder).getName();
             } catch (RemoteException e) {
                 e.printStackTrace();
             }
             Toast.makeText(ServiceB.this, "在ServiceB 中 "+name + "连接成功", Toast.LENGTH_SHORT).show();
             Log.e(TAG,"在ServiceB 中 "+name + "连接成功");
         }
 
         @Override
         public void onServiceDisconnected(ComponentName componentName) {
             Toast.makeText(ServiceB.this, "在ServiceB 中 "+TAG + "断开连接", Toast.LENGTH_SHORT).show();
             Log.e(TAG,"在ServiceB 中 "+TAG + "断开连接");
             ServiceB.this.startService(new Intent(ServiceB.this, AiVoiceService.class));
             ServiceB.this.bindService(new Intent(ServiceB.this, AiVoiceService.class), mServiceConnection, Context.BIND_IMPORTANT);
         }
     }
 
 
 }