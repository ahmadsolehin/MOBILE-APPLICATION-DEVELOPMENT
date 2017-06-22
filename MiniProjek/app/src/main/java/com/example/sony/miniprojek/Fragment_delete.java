package com.example.sony.miniprojek;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_delete extends Fragment {

	EditText editid;
	DataBaseHelper myDb;


	public Fragment_delete() {
        // Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {

		myDb = new DataBaseHelper(getActivity());
		View rootView = inflater.inflate(R.layout.fragment_fragment_delete, container, false);

		editid = (EditText)rootView.findViewById(R.id.IduserField);


		showChangeLangDialog();

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

				Integer insert = myDb.deleteRow(userName);


				if (insert > 0) {
					Toast.makeText(getActivity(),"Success Delete..!",Toast.LENGTH_SHORT).show();

				}else{
					Toast.makeText(getActivity(),"Not Success..!",Toast.LENGTH_SHORT).show();

				}
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



}
