package com.example.trackerczasu.ui.main;

<<<<<<< Updated upstream
=======
import android.content.Context;
>>>>>>> Stashed changes
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.trackerczasu.R;


public class SettingsFragment extends Fragment {
<<<<<<< Updated upstream
    Button modeDN;
=======
    public Button modeDN;
    private Context context;

>>>>>>> Stashed changes
    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(Context context) {
        SettingsFragment fragment = new SettingsFragment();
        fragment.context = context;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        modeDN = v.findViewById(R.id.mode);
        modeDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
<<<<<<< Updated upstream
                startActivity(new Intent(getActivity(), DayNightMode.class));
               }
=======
                Intent intent = new Intent(context, DayNightActivity.class);
                startActivity(intent);
            }
>>>>>>> Stashed changes
        });
        return v;
        //public void onMyClick(View view) { //onClick
        //
        //        Intent intent = new Intent(this, DayNightMode.class);
        //        startActivity(intent);
        //    }
    }

}

