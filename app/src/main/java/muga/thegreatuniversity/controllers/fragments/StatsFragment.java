package muga.thegreatuniversity.controllers.fragments;

import android.app.Fragment;
import android.app.ListFragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.controllers.adapters.BuildAdapter;
import muga.thegreatuniversity.models.Professor;
import muga.thegreatuniversity.models.Room;
import muga.thegreatuniversity.models.University;
import muga.thegreatuniversity.utils.Logger;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class StatsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stats, container, false);
    }


    @Override
    public void onStart(){
        super.onStart();
        createTable();
    }

    public void createTable() {

        int nbClass = 0, nbAudi = 0, nbAgro = 0, nbSci = 0, nbIT = 0;
        ArrayList<Room> l = University.get().getRooms();
        for(int i=0;i<l.size();i++) {
            switch (l.get(i).getType()) {
                case CLASS:
                    nbClass++;
                    break;
                case AUDIT:
                    nbAudi++;
                    break;
                case LAB_AG:
                    nbAgro++;
                    break;
                case LAB_SC:
                    nbIT++;
                    break;
                case LAB_IT:
                    nbSci++;
                    break;
            }
        }

        ArrayList<Professor> p = University.get().getProfessors();
        int nbProfs = p.size();

        int nbIncome = University.get().getIncome();

        int moral = University.get().getMoral();

        final String [] col1 = {"Students moral : ","Classroom number : ","Audience number : ","Agro labo number : ","Science labo number : ",
                                "IT labo number : ","Professors number : ","Income/Week : "};
        final String [] col2 = {String.valueOf(moral),String.valueOf(nbClass),String.valueOf(nbAudi),String.valueOf(nbAgro),
                                String.valueOf(nbSci),String.valueOf(nbIT),String.valueOf(nbProfs),String.valueOf(nbIncome)};

        TableLayout table = (TableLayout) getActivity().findViewById(R.id.tableStats);
       

        TableRow row;
        TextView tv1,tv2;
        for(int i=0;i<col1.length;i++) {
            row = new TableRow(getActivity());

            tv1 = new TextView(getActivity());
            tv1.setText(col1[i]);
            tv1.setGravity(Gravity.LEFT);
            tv1.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );


            tv2 = new TextView(getActivity());
            tv2.setText(col2[i]);
            tv2.setGravity(Gravity.CENTER);
            tv2.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );


            row.addView(tv1);
            row.addView(tv2);

            table.addView(row);
        }
    }


}
