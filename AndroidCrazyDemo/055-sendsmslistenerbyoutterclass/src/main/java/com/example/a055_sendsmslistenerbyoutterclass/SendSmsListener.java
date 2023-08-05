package com.example.a055_sendsmslistenerbyoutterclass;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SendSmsListener implements View.OnLongClickListener {

    private Activity activity;
    private View address;
    private View content;

    public SendSmsListener(Activity activity, View adress, View content) {
        this.activity = activity;
        this.address = adress;
        this.content = content;
    }

    @Override
    public boolean onLongClick(View view) {
        String addressStr = ((EditText) address).getText().toString();
        String contentStr = ((EditText) content).getText().toString();

        SmsManager smsManager = SmsManager.getDefault();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(activity, 0, new Intent(), 0);
        smsManager.sendTextMessage(addressStr, null, contentStr, pendingIntent, null);
        Toast.makeText(activity, "短信发送完成", Toast.LENGTH_SHORT).show();
        return false;
    }
}
