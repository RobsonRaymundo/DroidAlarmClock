package ray.droid.com.droidalarmclock;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Robson on 28/08/2017.
 */

public class AlarmBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (checkDayOfWeek(intent)) {
            try {
                Log.d("DroidAlarmClock", "AlarmBroadcast - OnReceive");

                Intent i = new Intent(Intent.ACTION_MAIN);
                i.setComponent(new ComponentName(context, FullscreenActivity.class));
                i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP |
                        Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                context.startActivity(i);

                Log.d("DroidAlarmClock", "startActivity");
            } catch (Exception ex) {
                Log.d("DroidAlarmClock", "AlarmBroadcast - OnReceive - Erro: " + ex.getMessage());

            }
        }
    }

    private boolean checkDayOfWeek(Intent intent) {
        ArrayList<Integer> days = intent.getIntegerArrayListExtra("DaysOfWeek");

        Calendar calendar = Calendar.getInstance();
        Integer diasemana = calendar.get(Calendar.DAY_OF_WEEK);

        return days.contains(diasemana);
    }
}
