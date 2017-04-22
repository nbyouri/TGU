package muga.thegreatuniversity.controllers.fragments;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.controllers.adapters.EventAdapter;
import muga.thegreatuniversity.controllers.tutorial.TutorialFragment;
import muga.thegreatuniversity.lists.enums.FragmentType;
import muga.thegreatuniversity.models.Professor;
import muga.thegreatuniversity.models.Room;
import muga.thegreatuniversity.models.University;
import muga.thegreatuniversity.models.events.Event;
import muga.thegreatuniversity.utils.Tuple;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class StatsFragment extends TutorialFragment implements AdapterView.OnItemClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);
        createTable(view);
        eventList(view);
        return view;
    }

    @Override
    public FragmentType getFragmentType() {
        return FragmentType.STATISTICS;
    }

    private void eventList(View view) {
        ListView events = (ListView) view.findViewById(R.id.event_list);
        EventAdapter eventadapter = new EventAdapter(getActivity().getApplicationContext(),
                University.get().getCurrentEvents());
        events.setAdapter(eventadapter);
        eventadapter.notifyDataSetChanged();
    }

    private void createTable(View view) {

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
        int[] rangeNewStudent = University.get().getFormula().rangeNewStudent();

        Queue<Tuple<String, String>> rowItems = new LinkedList<>();
        rowItems.add(new Tuple<>("Students moral : ", String.valueOf(moral)));
        rowItems.add(new Tuple<>("Classroom number : ", String.valueOf(nbClass)));
        rowItems.add(new Tuple<>("Audience number : ", String.valueOf(nbAudi)));
        rowItems.add(new Tuple<>("Agro labo number : ", String.valueOf(nbAgro)));
        rowItems.add(new Tuple<>("IT labo number : ", String.valueOf(nbIT)));
        rowItems.add(new Tuple<>("Science labo number : ", String.valueOf(nbSci)));
        rowItems.add(new Tuple<>("Professors number : ", String.valueOf(nbProfs)));
        rowItems.add(new Tuple<>("Income/Week : ", String.valueOf(nbIncome)));
        rowItems.add(new Tuple<>("New Student : ", rangeNewStudent[0] + " to " + rangeNewStudent[1]));


        TableLayout table = (TableLayout) view.findViewById(R.id.tableStats);
       

        TableRow row;
        TextView tv1,tv2;
        for(Tuple<String, String> rowItem : rowItems) {
            row = new TableRow(getActivity());

            tv1 = new TextView(getActivity());
            tv1.setText(rowItem.item1);
            tv1.setGravity(Gravity.START);
            tv1.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );


            tv2 = new TextView(getActivity());
            tv2.setText(rowItem.item2);
            tv2.setGravity(Gravity.CENTER);
            tv2.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );


            row.addView(tv1);
            row.addView(tv2);

            table.addView(row);
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Event ev = ((Event) parent.getItemAtPosition(position));

        Bundle bundle = new Bundle();


        try {
            bundle.putString("event", String.valueOf(ev.getAsJSON())); //TODO
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
