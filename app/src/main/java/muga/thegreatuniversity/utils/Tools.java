package muga.thegreatuniversity.utils;

/**
 * Created on 10/03/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class Tools {
    public static String Capitalize(String s) {
        StringBuilder sb = new StringBuilder(s.toLowerCase());
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return sb.toString();
    }
}
