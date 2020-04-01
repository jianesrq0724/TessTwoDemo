package com.carl.mvpdemo.pub.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.carl.mvpdemo.module.home.view.ModifySpActivity;

/**
 * @author Carl
 * version 1.0
 * @since 2018/6/10
 * 接收推送
 */

public class BootBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action_boot = "android.intent.action.BOOT_COMPLETED";
        if (context != null && intent != null && action_boot.equals(intent.getAction())) {
            Intent bootIntent = new Intent(context, ModifySpActivity.class);
            bootIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(bootIntent);
        }
    }
}
