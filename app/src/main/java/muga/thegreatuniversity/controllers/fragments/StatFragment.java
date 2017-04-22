package muga.thegreatuniversity.controllers.fragments;

import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.controllers.tutorial.TutorialFragment;
import muga.thegreatuniversity.lists.enums.FragmentType;
import muga.thegreatuniversity.models.University;
import muga.thegreatuniversity.utils.Logger;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class StatFragment extends TutorialFragment {

    private ViewGroup statLay;

    private TextView nbStud;
    private TextView nameUniv;
    private TextView nbMaxStud;
    private TextView cash;
    private TextView week;
    private TextView popularity;
    private TextView moral;

    private ImageView iconMoral;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stat, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();
        createViews();


    }

    @Override
    public void onResume(){
        super.onResume();
        updateViews();
    }

    public void updateViews(){
        if (!(this.isVisible() && nbStud != null && nameUniv != null && nbMaxStud != null
                && cash != null && week != null && statLay != null && popularity != null)){
            Logger.error("Impossible to update the Stat view");
        }
        TransitionManager.beginDelayedTransition(statLay);

        nbStud.setText(String.valueOf(University.get().getStudentNb()));
        nameUniv.setText(University.get().getName());
        nbMaxStud.setText(String.valueOf(University.get().getMaxPopulation()));
        cash.setText(String.valueOf(University.get().getMoney()));
        week.setText(String.valueOf(University.get().getWeek()));
        popularity.setText(String.valueOf(University.get().getPopularity()));
        moral.setText(getActivity().getString(R.string.uni_morale, University.get().getMoral()));
        if (University.get().getMoral() >= 50){
            iconMoral.setImageResource(R.mipmap.ic_moral_positive);
        } else {
            iconMoral.setImageResource(R.mipmap.ic_moral_negative);
        }

    }

    private void createViews(){
        nbStud = (TextView) getActivity().findViewById(R.id.txt_nb_student);
        nameUniv = (TextView) getActivity().findViewById(R.id.txt_name_university);
        nbMaxStud = (TextView) getActivity().findViewById(R.id.txt_max_student);
        cash = (TextView) getActivity().findViewById(R.id.txt_cash);
        week = (TextView) getActivity().findViewById(R.id.txt_turn);
        statLay = (ViewGroup) getActivity().findViewById(R.id.layout_stat);
        popularity = (TextView) getActivity().findViewById(R.id.txt_popularity);
        moral = (TextView) getActivity().findViewById(R.id.txt_stat_moral);
        iconMoral = (ImageView) getActivity().findViewById(R.id.icon_stat_moral);
        updateViews();
    }

    @Override
    public FragmentType getFragmentType() {
        return FragmentType.STAT;
    }
}
