package muga.thegreatuniversity.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.app.App;
import muga.thegreatuniversity.models.University;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class StatFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stat, container, false);

    }

    @Override
    public void onStart(){
        super.onStart();
        printStat();
    }

    private void printStat(){
        TextView nbStud = (TextView) getActivity().findViewById(R.id.txt_nb_student);
        TextView nameUniv = (TextView) getActivity().findViewById(R.id.txt_name_university);
        TextView nbMaxStud = (TextView) getActivity().findViewById(R.id.txt_max_student);
        TextView cash = (TextView) getActivity().findViewById(R.id.txt_cash);

        nbStud.setText(String.valueOf(University.getInstance().getStudentNb()));
        nameUniv.setText(University.getInstance().getName());
        nbMaxStud.setText(String.valueOf(University.getInstance().getMaxPopulation()));
        cash.setText(String.valueOf(University.getInstance().getMoney()));
    }
}
