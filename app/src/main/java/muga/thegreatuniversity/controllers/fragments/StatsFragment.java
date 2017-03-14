package muga.thegreatuniversity.controllers.fragments;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.controllers.adapters.BuildAdapter;
import muga.thegreatuniversity.models.Room;
import muga.thegreatuniversity.models.University;
import muga.thegreatuniversity.utils.Logger;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class StatsFragment extends Fragment {

    private TextView nbClassroom;
    private TextView nbAudience;
    private TextView nbScienceLaboratory;
    private TextView nbAgronomyLaboratory;
    private TextView nbITLaboratory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats, container, false);
    }


    @Override
    public void onStart(){
        super.onStart();
        createViews();
    }

    public void updateViews(){
        if (!(this.isVisible() && nbClassroom != null && nbITLaboratory != null && nbAgronomyLaboratory != null
                && nbAudience != null && nbScienceLaboratory != null)){
            Logger.error("Impossible to update the Stat view");
        }

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

        nbClassroom.setText("Classroom number : "+ String.valueOf(nbClass));
        nbAudience.setText("Audience number : "+String.valueOf(nbAudi));
        nbAgronomyLaboratory.setText("Agro labo number : "+String.valueOf(nbAgro));
        nbScienceLaboratory.setText("Science labo number : "+String.valueOf(nbSci));
        nbITLaboratory.setText("IT labo number : "+String.valueOf(nbIT));
    }

    private void createViews(){

        nbClassroom = (TextView) getActivity().findViewById(R.id.nb_classroom);
        nbAudience = (TextView) getActivity().findViewById(R.id.nb_audience);
        nbAgronomyLaboratory = (TextView) getActivity().findViewById(R.id.nb_agrolab);
        nbScienceLaboratory = (TextView) getActivity().findViewById(R.id.nb_sciencelab);
        nbITLaboratory = (TextView) getActivity().findViewById(R.id.nb_itlab);
        updateViews();
    }


}
