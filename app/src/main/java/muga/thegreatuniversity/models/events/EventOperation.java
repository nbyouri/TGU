package muga.thegreatuniversity.models.events;

import java.lang.reflect.InvocationTargetException;

import muga.thegreatuniversity.utils.Logger;

/**
 * Created on 21-03-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

class EventOperation {
    public static boolean smaller(int val1, int val2) {
        return val1 < val2;
    }

    public static boolean smaller_or_equal(int val1, int val2) {
        return val1 <= val2;
    }

    public static boolean greater(int val1, int val2) {
        return val1 > val2;
    }

    public static boolean greater_or_equal(int val1, int val2) {
        return val1 >= val2;
    }

    public static boolean equal(int val1, int val2) {
        return val1 == val2;
    }

    public static boolean not_equal(int val1, int val2) {
        return val1 != val2;
    }

    public static boolean modulo(int val1, int val2) {
        return val1%val2 == 0;
    }

    public static boolean op(String op, int val1, int val2) {
        java.lang.reflect.Method method;
        boolean ok = false;
        String ops = "";
        switch(op) {
            case "<" : ops = "smaller"; break;
            case "<=" : ops = "smaller_or_equal"; break;
            case ">" : ops = "greater"; break;
            case ">=" : ops = "greater_or_equal"; break;
            case "=" : ops = "equal"; break;
            case "!=" : ops = "not_equal"; break;
            case "%" : ops = "modulo"; break;
        }
        try {
            method = EventOperation.class.getMethod(ops, int.class, int.class);
            ok = (boolean)method.invoke(EventOperation.class, val1, val2);
        } catch (SecurityException e) {
            Logger.error(e.getMessage());
        }
        catch (NoSuchMethodException e) {
            Logger.error("No such method " + ops);
        }
        catch (IllegalArgumentException e) {
            Logger.error("Illegal argument error");
        }
        catch (IllegalAccessException e) {
            Logger.error(e.getCause() + e.getMessage());
        }
        catch (InvocationTargetException e) {
            Logger.error("Method error for " + ops + " " + e.getCause() + " : " + e.getMessage());
        }

        return ok;
    }

    public static boolean op(String op, double val1, double val2) {
        java.lang.reflect.Method method;
        boolean ok = false;
        String ops = "";
        switch(op) {
            case "<" : ops = "smaller"; break;
            case "<=" : ops = "smaller_or_equal"; break;
            case ">" : ops = "greater"; break;
            case ">=" : ops = "greater_or_equal"; break;
            case "=" : ops = "equal"; break;
            case "!=" : ops = "not_equal"; break;
            case "%" : ops = "modulo"; break;
        }
        try {
            method = EventOperation.class.getMethod(ops, int.class, int.class);
            ok = (boolean)method.invoke(EventOperation.class, val1, val2);
        } catch (SecurityException e) {
            Logger.error(e.getMessage());
        }
        catch (NoSuchMethodException e) {
            Logger.error("No such method " + ops);
        }
        catch (IllegalArgumentException e) {
            Logger.error("Illegal argument error");
        }
        catch (IllegalAccessException e) {
            Logger.error(e.getCause() + e.getMessage());
        }
        catch (InvocationTargetException e) {
            Logger.error("Method error for " + ops + " " + e.getCause() + " : " + e.getMessage());
        }

        return ok;
    }
}
