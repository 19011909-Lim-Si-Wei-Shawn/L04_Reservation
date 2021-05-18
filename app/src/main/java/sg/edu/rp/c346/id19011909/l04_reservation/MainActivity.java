package sg.edu.rp.c346.id19011909.l04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    //Creating Variable,
    EditText HNameET;
    EditText HMobileET;
    EditText HGroupET;

    DatePicker DP;
    TimePicker TP;
    CheckBox RTypeCB;

    Button Confirmation;
    Button Reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Linking Variable,
        HNameET = findViewById(R.id.HintNameET);
        HMobileET = findViewById(R.id.HintMobileET);
        HGroupET = findViewById(R.id.HintGroupET);

        DP = findViewById(R.id.datePicker);
        TP = findViewById(R.id.timePicker);
        RTypeCB = findViewById(R.id.RoomTypeCB);

        Confirmation = findViewById(R.id.Confirmation);
        Reset = findViewById(R.id.Resetter);

        //Setting Default Data,
        DP.updateDate(2020, 05, 1);
        TP.setCurrentHour(19);
        TP.setCurrentMinute(30);



        //==============================================================



        //Setting Action,
        //Confirmation,
        Confirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Button Action,

                //Checker,
                String UsrNameCheck = HNameET.getText().toString();
                String UsrMobileCheck = HMobileET.getText().toString();
                String UsrGroupCheck = HGroupET.getText().toString();

                if(TextUtils.isEmpty(UsrNameCheck))
                {
                    Toast.makeText(MainActivity.this, "Please Fill in Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(UsrMobileCheck))
                {
                    Toast.makeText(MainActivity.this, "Please Fill in Mobile No.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(UsrGroupCheck))
                {
                    Toast.makeText(MainActivity.this, "Please Fill in Group Size", Toast.LENGTH_SHORT).show();
                    return;
                }



                //==============================================================



                String Time = "";
                String RoomQ = "";

                //AM OR PM,
                if(TP.getCurrentHour()-12 > 0)
                {
                    int C12 = TP.getCurrentHour()-12;
                    Time = C12 + ":" + TP.getCurrentMinute().toString() + "PM";
                }

                else
                {
                    Time = TP.getCurrentHour().toString() + ":" + TP.getCurrentMinute().toString() + "AM";
                }



                //==============================================================



                //Checking CheckBox,
                if(RTypeCB.isChecked())
                {   RoomQ = "Smoking Room";      }

                else
                {   RoomQ = "Non-Smoking Room";     }



                //==============================================================



                //Loading Message,
                String Data = String.format("Name: %s\n", HNameET.getText().toString());
                Data += String.format("Mobile Number: %s\n", HMobileET.getText().toString());
                Data += String.format("Group Size: %s\n",HGroupET.getText().toString());
                Data += String.format("Date: %s-%s-%s\n", DP.getDayOfMonth(), DP.getMonth(), DP.getYear());
                Data += String.format("Time: %s\n", Time);
                Data += String.format("Room Type: %s", RoomQ);

                //Displaying Message,
                Toast.makeText(MainActivity.this, Data, Toast.LENGTH_SHORT).show();

            }
        });



        //==============================================================



        //Scenario 1 - CHALLENGES,
        TP.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                //Limiting time to 8AM and 8.59PM,
                if(TP.getCurrentHour() < 8)
                {
                    TP.setCurrentHour(8);
                    TP.setCurrentMinute(0);
                    Toast.makeText(MainActivity.this, "Reservation Timing should be between 8AM to 8.59PM", Toast.LENGTH_SHORT).show();
                }

                else if (TP.getCurrentHour() >= 21)
                {
                    TP.setCurrentHour(20);
                    TP.setCurrentMinute(59);
                    Toast.makeText(MainActivity.this, "Reservation Timing should be between 8AM to 8.59PM", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //Scenario 2 - CHALLENGES,
        Calendar calendar = Calendar.getInstance();
        DP.setMinDate((long) (calendar.getTimeInMillis()+86400000));

        //Resetting,
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Button Action,
                HNameET.setText("");
                HGroupET.setText("");
                HMobileET.setText("");
                DP.updateDate(2020, 05, 1);
                TP.setCurrentHour(19);
                TP.setCurrentMinute(30);
                RTypeCB.setChecked(false);

            }
        });




    }
}