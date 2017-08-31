package ray.droid.com.droidalarmclock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Robson on 28/08/2017.
 */


public class AlarmBroadcast extends BroadcastReceiver {

    private CheckBox chkSeg;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (checkDayOfWeek(intent)) {
            Intent intentFullScreen = new Intent(context, FullscreenActivity.class);
            intentFullScreen.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intentFullScreen);
        }
    }



    private boolean checkDayOfWeek(Intent intent) {
        ArrayList<Integer> days = intent.getIntegerArrayListExtra("DaysOfWeek");

        Calendar calendar = Calendar.getInstance();
        Integer diasemana = calendar.get(Calendar.DAY_OF_WEEK);

        return days.contains(diasemana);

    }
}
