package com.example.sony.miniprojek;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
public class UpdateFragment extends Fragment {

	EditText et , editName , editDescription , editDate , editTime, editid;
	Button btn1, view;

	DataBaseHelper myDb;


	public UpdateFragment() {
        // Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {

		myDb = new DataBaseHelper(getActivity());


		View rootView = inflater.inflate(R.layout.fragment_update, container, false);

		showChangeLangDialog();

		editDate = (EditText)rootView.findViewById(R.id.TaskDate);

		editName = (EditText)rootView.findViewById(R.id.TasknameField);

		editDescription = (EditText)rootView.findViewById(R.id.TaskDescriptionField);

		editTime = (EditText)rootView.findViewById(R.id.TaskTime);

		editid = (EditText)rootView.findViewById(R.id.IduserField);


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

				boolean insert = myDb.updateData(editid.getText().toString(), editName.getText().toString(), editDescription.getText().toString(), editDate.getText().toString(), editTime.getText().toString() );

				if (insert = true) {
					Toast.makeText(getActivity(),"Success Update..!",Toast.LENGTH_SHORT).show();

				}else{
					Toast.makeText(getActivity(),"Not Success Update..!",Toast.LENGTH_SHORT).show();

				}			}

			});




		return rootView;

	}

	public void showChangeLangDialog() {

		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = this.getLayoutInflater(null);
		final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
		dialogBuilder.setView(dialogView);

		final EditText edt = (EditText) dialogView.findViewById(R.id.edit1);

		dialogBuilder.setTitle("");
		dialogBuilder.setMessage("");
		dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
            //do something with edt.getText().toString();
				String userName=edt.getText().toString();
				huhu(userName);
			}
		});
		dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
            //pass
			}
		});
		AlertDialog b = dialogBuilder.create();
		b.show();
	}


	public void huhu(String name)
	{
		try{
			Cursor c=myDb.getoneData(name);
	        if(c.getCount()<1) // UserName Not Exist
	        {
	        	c.close();
	        }
	        c.moveToFirst();
	        String id=c.getString(c.getColumnIndex("ID"));
	        String date=c.getString(c.getColumnIndex("NAME"));
	        String time =c.getString(c.getColumnIndex("DESCRIPTION"));
	        String tok=c.getString(c.getColumnIndex("DATE"));
	        String remarks=c.getString(c.getColumnIndex("TIME"));

	        editName.setText(date);
	        editDescription.setText(time);
	        editDate.setText(tok);
	        editTime.setText(remarks);
	        editid.setText(id);

	    }catch(Throwable e){
	    	e.printStackTrace();
	    }


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
