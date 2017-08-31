package ray.droid.com.droidalarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.provider.SyncStateContract;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private TimePicker timePicker;
    private long segundos = 1000;
    private long minutos = segundos * 5;
    private long horas = minutos * 60;

    private CheckBox chkSeg;
    private CheckBox chkTer;
    private CheckBox chkQua;
    private CheckBox chkQui;
    private CheckBox chkSex;
    private CheckBox chkSab;
    private CheckBox chkDom;
    private Button btnAgendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        timePicker.setCurrentMinute(timePicker.getCurrentMinute()+1);

        context = getBaseContext();

        chkSeg = (CheckBox) findViewById(R.id.chkSeg);
        chkTer = (CheckBox) findViewById(R.id.chkTer);
        chkQua = (CheckBox) findViewById(R.id.chkQua);
        chkQui = (CheckBox) findViewById(R.id.chkQui);
        chkSex = (CheckBox) findViewById(R.id.chkSex);
        chkSab = (CheckBox) findViewById(R.id.chkSab);
        chkDom = (CheckBox) findViewById(R.id.chkDom);

        btnAgendar = (Button) findViewById(R.id.btnAgendar);

        btnAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Others.CancelAlarm(context);
                SetAlarm();
            }
        });


    }

    private ArrayList<Integer> SetDaysOfWeek()
    {
        ArrayList<Integer> days = new ArrayList<>();

        if (chkDom.isChecked()) {
            days.add(1);
        }
        if (chkSeg.isChecked()) {
            days.add(2);
        }
        if (chkTer.isChecked()) {
            days.add(3);
        }
        if (chkQua.isChecked()) {
            days.add(4);
        }
        if (chkQui.isChecked()) {
            days.add(5);
        }
        if (chkSex.isChecked()) {
            days.add(6);
        }
        if (chkSab.isChecked()) {
            days.add(7);
        }
        return days;
    }

    private void SetAlarm()
    {
        try {
            AlarmManager alarmMgr;
            PendingIntent alarmIntent;

            alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(context, AlarmBroadcast.class);
            intent.putIntegerArrayListExtra("DaysOfWeek", SetDaysOfWeek());
            alarmIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());

            alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);
            Toast.makeText(context, "Alarme agendado com sucesso! ", Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex)
        {
            Toast.makeText(context, "Não foi possivel agendar o alarme. " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onDestroy() {
         super.onDestroy();
    }


}
