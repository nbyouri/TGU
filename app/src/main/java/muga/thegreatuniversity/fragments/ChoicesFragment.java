package muga.thegreatuniversity.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.views.MainActivity;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class ChoicesFragment extends Fragment {

    Button btn_hire;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choices, container, false);
    }

    public void onStart(){
        super.onStart();
        btn_hire = (Button) getActivity().findViewById(R.id.btn_hire_prof);
        btn_hire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeHire();
            }
        });
    }

    private void changeHire(){
        ((MainActivity)getActivity()).hireProf();
    }

}
