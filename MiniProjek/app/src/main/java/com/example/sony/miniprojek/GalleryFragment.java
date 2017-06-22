package com.example.sony.miniprojek;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends Fragment {

    EditText et , editName , editDescription , editDate , editTime;
    Button btn1;

    DataBaseHelper myDb;

    public GalleryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
       Bundle savedInstanceState) {

    	myDb = new DataBaseHelper(getActivity());

        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);


        editDate = (EditText)rootView.findViewById(R.id.TaskDate);

        editName = (EditText)rootView.findViewById(R.id.TasknameField);

        editDescription = (EditText)rootView.findViewById(R.id.TaskDescriptionField);

        editTime = (EditText)rootView.findViewById(R.id.TaskTime);

        btn1 = (Button)rootView.findViewById(R.id.submit);


        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //what ever you need to do goes here
                showDatePickerDialog();
            }

        });

                editTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                           Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mcurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    editTime.setText( selectedHour + ":" + selectedMinute);
                }
            }, hour, minute, true);//Yes 24 hour time
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();
            
            }

        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //what ever you need to do goes here
                boolean insert = myDb.insertData(editName.getText().toString(), editDescription.getText().toString(), editDate.getText().toString(), editTime.getText().toString() );
                
                if (insert = true) {
                    Toast.makeText(getActivity(),"Success Add..!",Toast.LENGTH_SHORT).show();

                }else{
                   Toast.makeText(getActivity(),"Not Success Add..!",Toast.LENGTH_SHORT).show();

               }

           }

       });





        return rootView;

        // Inflate the layout for this fragment
    }

    public void AddData(){



    }

    Calendar myCalendar = Calendar.getInstance(); //global

    public void showDatePickerDialog(){

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }



    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            editDate.setText(String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear+1)
                + "-" + String.valueOf(year));
        }

    };

}
