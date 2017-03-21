package muga.thegreatuniversity.models.events;

import java.util.ArrayList;

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

    public EventConditions(String[] vars, String[] ops, int[] values) throws Exception {
        if (vars.length != values.length) {
            throw new Exception("Incorrect EventConditions usage");
        }
        this.vars = new EventValueType[vars.length];
        for (int i = 0; i < vars.length; i++) {
            this.vars[i] = EventValueType.getEnum(vars[i]);
        }
        this.ops = ops;
        this.values = values;
    }

    public EventValueType[] getVars() {
        return vars;
    }

    public void setVars(EventValueType[] vars) {
        this.vars = vars;
    }

    public int[] getValues() {
        return values;
    }

    public void setValues(int[] values) {
        this.values = values;
    }

    public boolean check() {
        boolean ok = false;
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
            }
        }

        return ok;
    }
}
