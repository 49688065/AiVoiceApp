package com.imooic.aivoiceapp.service;

import android.app.Activity;
import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import java.lang.ref.WeakReference;

public class KeepLiveManager {
    public static KeepLiveManager sInstance = new KeepLiveManager();

    private KeepLiveManager() {

    }

    public static KeepLiveManager getInstance() {
        return sInstance;
    }

    /**
     * 提升Service的优先级为前台Service
     */
    public void setForegroundService(final Service keepLiveService, final Service innerService) {
        final int foregroundPushId = 1;
        Log.d(KeepLiveManager.class.getSimpleName(), "setForegroundService: KeepLiveService->setForegroundService: " + keepLiveService + ", innerService:" + innerService);
        if (keepLiveService != null) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
                keepLiveService.startForeground(foregroundPushId, new Notification());
            } else {
                keepLiveService.startForeground(foregroundPushId, new Notification());
                if (innerService != null) {
                    innerService.startForeground(foregroundPushId, new Notification());
//                    innerService.stopSelf();
                }
            }
        }
    }
}