package com.mad.tayvigilator;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

public class sTimePickerFragment extends DialogFragment {

    private TimePicker timePicker;
    public interface TimeDialogListener {
        void onFinishDialogStart(String time);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_time,null);

        timePicker = (TimePicker) v.findViewById(R.id.dialog_time_picker);
        return new android.support.v7.app.AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.start_time)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int hour = 0;
                                hour = timePicker.getHour();

                                int minute = 0;
                                minute = timePicker.getMinute();

                                TimeDialogListener activity = (TimeDialogListener) getActivity();
                                activity.onFinishDialogStart(updateTime(hour,minute));
                                dismiss();
                            }
                        })
                .create();
    }

    private String updateTime(int hours, int mins) {

        String timeSet = "";
        if (hours > 12) {
            hours -= 12;
            timeSet = "PM";
        } else if (hours == 0) {
            hours += 12;
            timeSet = "AM";
        } else if (hours == 12)
            timeSet = "PM";
        else
            timeSet = "AM";

        String minutes = "";
        if (mins < 10)
            minutes = "0" + mins;
        else
            minutes = String.valueOf(mins);

        String myTime = new StringBuilder().append(hours).append(':')
                .append(minutes).append(" ").append(timeSet).toString();

        return myTime;
    }
}