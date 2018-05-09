package madbarsoft.broadcastreceiverwithsmsreceived;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){

            Bundle bundle = intent.getExtras();
            Object messages[] = (Object[]) bundle.get("pdus");
            SmsMessage smsMessage[] = new SmsMessage[messages.length];

            for (int n = 0; n < messages.length; n++) {
                smsMessage[n] = SmsMessage.createFromPdu((byte[]) messages[n]);
            }

            final String numberSms = smsMessage[0].getOriginatingAddress();
            final String messageSms = smsMessage[0].getDisplayMessageBody();
            long dateTimeSms = smsMessage[0].getTimestampMillis();

            Toast.makeText(context, "You Get a SMS ....!\n From: "+numberSms+"\n Message: "+messageSms+"\n Time: "+dateTimeSms, Toast.LENGTH_SHORT).show();

            return;
        }
        Toast.makeText(context, "Other alert ....!", Toast.LENGTH_SHORT).show();

    }
}
