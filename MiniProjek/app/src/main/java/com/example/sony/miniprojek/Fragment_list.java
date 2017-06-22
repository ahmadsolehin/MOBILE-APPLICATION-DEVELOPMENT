package com.example.sony.miniprojek;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_list extends Fragment {

    DataBaseHelper myDB;


    public Fragment_list() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_fragment_list, container, false);


        ListView listView = (ListView)rootView.findViewById(R.id.listView);
        myDB = new DataBaseHelper(getActivity());

        List<String> values = new ArrayList<String>();


        //populate an ArrayList<String> from the database and then view it
        ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.getAllData();
        if(data.getCount() == 0){
            Toast.makeText(getActivity(), "There are no contents in this list!", Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){

                String a = data.getString(1);
                String b = data.getString(2);
                String c = data.getString(3);

             //   theList.add(data.getString(1));
                values.add(a + " - " + b + " - " + c);

                ArrayAdapter listAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,values);
                listView.setAdapter(listAdapter);
            }
        }
        return rootView;

    }

}
