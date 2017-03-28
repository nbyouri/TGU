package muga.thegreatuniversity.controllers.fragments;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONObject;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.controllers.MainActivity;
import muga.thegreatuniversity.controllers.adapters.HireAdapter;
import muga.thegreatuniversity.models.Professor;
import muga.thegreatuniversity.models.University;
import muga.thegreatuniversity.utils.Logger;
import muga.thegreatuniversity.utils.Tools;

/**
 * Created on 28/03/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class ProfFragment extends Fragment {
    private Professor p;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prof, container, false);

        String json =  getArguments().getString("prof");
        final Boolean hire = getArguments().getBoolean("hire");
        p = new Professor();
        try {
            p.loadJSON(new JSONObject(json));
        } catch (Exception e) {
            Logger.error("Failed to loadjson professor" + e.getMessage());
        }

        final Button button = (Button) view.findViewById(R.id.prof_button);
        if (!hire) button.setText("Fire");
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (hire) {
                    hireProf(p);
                    int idx = University.get().getAvailableHires().indexOf(p);
                    University.get().getAvailableHires().remove(idx);
                } else {

                    University.get().getProfessors().indexOf(p);
                    University.get().getProfessors().remove(p);
                }
                getFragmentManager().popBackStack();

            }
        });

        Logger.info("ProfFragment!!!");

        return view;
    }

    private void hireProf(Professor p){
        University.get().addProfessor(p);
    }

    @Override
    public void onStart(){
        super.onStart();

        TableLayout table = (TableLayout) getActivity().findViewById(R.id.table_prof);
        int column[] = {R.string.prof_name,
                R.string.prof_type,
                R.string.prof_popularity,
                /* courses */
                R.string.prof_age,
                R.string.prof_price};

        String fields[] = {p.getName(),
                p.getType().getName(),
                String.valueOf(p.getPopularity()),
                /* courses */
                String.valueOf(p.getAge()),
                String.valueOf(p.getPrice())};


        TableRow row;
        TextView tv1,tv2;

        for (int i = 0; i < column.length; i++) {
            row = new TableRow(getActivity());

            tv1 = new TextView(getActivity());
            tv1.setText(getActivity().getString(column[i]));
            tv1.setGravity(Gravity.LEFT);
            tv1.setLayoutParams(
                    new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1));

            row.addView(tv1);

            tv2 = new TextView(getActivity());
            tv2.setText(fields[i]);
            tv2.setGravity(Gravity.LEFT);
            tv2.setLayoutParams(
                    new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1));

            row.addView(tv2);
            table.addView(row);
        }
    }
}
