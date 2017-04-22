package muga.thegreatuniversity.models.events;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import muga.thegreatuniversity.lists.enums.EventValueType;
import muga.thegreatuniversity.models.University;

/**
 * Created on 21-03-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class EventConditions {
    private EventValueType[] vars;
    private String[] ops;
    private int[] values;

    public EventConditions() {}

    public boolean check() {
        boolean ok = true;
        for (int i = 0; i < vars.length; i++) {

            switch (vars[i]) {
                case MONEY:
                    ok = EventOperation.op(ops[i], University.get().getMoney(), values[i]);
                    break;
                case POPULARITY:
                    ok = EventOperation.op(ops[i], University.get().getPopularity(), values[i]);
                    break;
                case MORAL:
                    ok = EventOperation.op(ops[i], University.get().getMoral(), values[i]);
                    break;
                case STUDENT:
                    ok = EventOperation.op(ops[i], University.get().getStudentNb(), values[i]);
                    break;
                case WEEK:
                    ok = EventOperation.op(ops[i], University.get().getWeek(), values[i]);
                    break;
            }
        }

        return ok;
    }

    public JSONObject getAsJSON() throws JSONException {
        JSONObject obj = new JSONObject();
        JSONArray jsonVars = new JSONArray();

        for(EventValueType var: vars) {
            jsonVars.put(var.getName());
        }
        obj.put("vars", jsonVars);
        JSONArray jsonOps = new JSONArray();
        for(String op: ops) {
            jsonOps.put(op);
        }
        obj.put("ops", jsonOps);
        JSONArray jsonValues = new JSONArray();
        for(int value: values) {
            jsonValues.put(value);
        }
        obj.put("values", jsonValues);
        return obj;
    }

    public void loadJSON(JSONObject json) throws JSONException {
        JSONArray vars = json.getJSONArray("vars");
        this.vars = new EventValueType[vars.length()];
        for (int i = 0; i < vars.length(); i++) {
            this.vars[i] = EventValueType.getEnum(vars.getString(i));
        }

        JSONArray ops = json.getJSONArray("ops");
        this.ops = new String[ops.length()];
        for (int i = 0; i < ops.length(); i++) {
            this.ops[i] = ops.getString(i);
        }

        JSONArray values = json.getJSONArray("values");
        this.values = new int[values.length()];
        for (int i = 0; i < values.length(); i++) {
            this.values[i] = values.getInt(i);
        }
    }

    @Override
    public String toString() {
        return "EventConditions{" +
                "vars=" + Arrays.toString(vars) +
                ", ops=" + Arrays.toString(ops) +
                ", values=" + Arrays.toString(values) +
                '}';
    }
}
