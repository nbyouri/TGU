package muga.thegreatuniversity.controllers.fragments;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.TypedValue;
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

import java.util.Map;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.controllers.MainActivity;
import muga.thegreatuniversity.controllers.adapters.HireAdapter;
import muga.thegreatuniversity.lists.enums.ProfType;
import muga.thegreatuniversity.models.Course;
import muga.thegreatuniversity.models.Professor;
import muga.thegreatuniversity.models.University;
import muga.thegreatuniversity.utils.Context;
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
            Logger.error("Failed to load json professor" + e.getMessage());
        }

        final Button button = (Button) view.findViewById(R.id.prof_button);
        if (!hire) button.setText(getString(R.string.button_fire));
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

        final TableLayout tl = (TableLayout) view.findViewById(R.id.table_prof);
        int column[] = {R.string.prof_name,
                R.string.prof_type,
                R.string.prof_popularity,
                R.string.prof_age,
                R.string.prof_price};

        String fields[] = {p.getName(),
                p.getType().getName(),
                String.valueOf(p.getPopularity()),
                String.valueOf(p.getAge()),
                String.valueOf(p.getPrice())};


        TableRow row;
        TextView tv1,tv2;

        row = new TableRow(getActivity());
        tv1 = new TextView(getActivity());
        tv1.setText(getActivity().getString(R.string.prof_detail));
        tv1.setGravity(Gravity.LEFT);
        tv1.setLayoutParams(
                new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        row.addView(tv1);
        tl.addView(row);

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
            tv2.setTextSize(getResources().getDimension(R.dimen.text_very_small));
            tv2.setGravity(Gravity.LEFT);
            if (column[i] == R.string.prof_type)
                tv2.setTextColor(p.getType().getColor());
            tv2.setLayoutParams(
                    new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1));

            row.addView(tv2);
            tl.addView(row);
        }

        // courses
        row = new TableRow(getActivity());
        tv1 = new TextView(getActivity());
        tv1.setText(getActivity().getString(R.string.prof_courses));
        tv1.setGravity(Gravity.LEFT);
        tv1.setLayoutParams(
                new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1));

        row.addView(tv1);
        tl.addView(row);

        for (Course course : p.getCourses()) {
            row = new TableRow(getActivity());
            tv1 = new TextView(getActivity());
            tv1.setTextSize(getResources().getDimension(R.dimen.text_very_small));
            tv1.setText(course.toString());
            tv1.setGravity(Gravity.LEFT);
            tv1.setLayoutParams(
                    new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1));

            row.addView(tv1);
            tl.addView(row);
        }

        // Legend
        row = new TableRow(getActivity());
        tv1 = new TextView(getActivity());
        tv1.setText(Context.getString(R.string.color_legend));
        tv1.setGravity(Gravity.CENTER);
        tv1.setLayoutParams(
                new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1));

        row.addView(tv1);
        tl.addView(row);

        for (String key : ProfType.getLookup().keySet()) {
            row = new TableRow(getActivity());
            tv1 = new TextView(getActivity());
            tv1.setTextSize(getResources().getDimension(R.dimen.text_very_small));
            tv1.setText(key);
            tv1.setTextColor(ProfType.getEnum(key).getColor());
            tv1.setGravity(Gravity.CENTER);
            tv1.setLayoutParams(
                    new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1));

            row.addView(tv1);
            tl.addView(row);
        }

        return view;
    }

    private void hireProf(Professor p){
        University.get().addProfessor(p);
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
