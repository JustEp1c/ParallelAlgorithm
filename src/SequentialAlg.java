import java.util.ArrayList;
import java.util.List;

public class SequentialAlg {

    public static String checkLexicographicOrder(ArrayList<String> listOfStrings, List<Character> alphabet) {
        String str = compareTwoStrings(listOfStrings.get(0),listOfStrings.get(1), alphabet);
        if (listOfStrings.size() > 2) {
            for (int i = 2; i < listOfStrings.size(); i++) {
                str = compareTwoStrings(str, listOfStrings.get(i), alphabet);
            }
        }
        return str;
    }

    public static String compareTwoStrings(String s1, String s2, List<Character> alphabet) {
        int minLength = Math.min(s1.length(), s2.length());

        for (int i = 0; i < minLength; i++) {
            int indexOf_S_Symbol = alphabet.indexOf(s1.charAt(i));
            int indexOf_P_Symbol = alphabet.indexOf(s2.charAt(i));
            if (indexOf_S_Symbol > indexOf_P_Symbol) {
                return s2;
            }
            if (indexOf_S_Symbol < indexOf_P_Symbol) {
                return s1;
            }
        }
        if (s1.length() == minLength) return s1;
        return s2;
    }
}
