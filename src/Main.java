import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws Exception {
        List<Character> alphabet = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');

        ArrayList<String> listOfStrings = new ArrayList<>();

        for (int i = 0; i < 100000; i++) {
            String newString = String.valueOf(makeString(alphabet));
            //System.out.println(newString);
            listOfStrings.add(newString);
        }

        long startTimeSeqAlg = System.nanoTime();
        String foundStringS = SequentialAlg.checkLexicographicOrder(listOfStrings, alphabet);
        long stopTimeSeqAlg = System.nanoTime();

        long startTimeParAlg = System.nanoTime();
        String foundString = ParallelAlg.checkLexicographicOrder(listOfStrings, alphabet);
        long stopTimeParAlg = System.nanoTime();

        System.out.println("(Последовательный) Первая строка по лексикографическому порядку - " + foundStringS);
        float endTimeSeqAlg = stopTimeSeqAlg - startTimeSeqAlg;
        System.out.println(endTimeSeqAlg + " нс");
        System.out.println(endTimeSeqAlg / 1000000 + " мс");
        System.out.println(endTimeSeqAlg / 1000000000 + " с");

        System.out.println("(Параллельный) Первая строка по лексикографическому порядку - " + foundString);
        float endTimeParAlg = stopTimeParAlg - startTimeParAlg;
        System.out.println(endTimeParAlg + " нс");
        System.out.println(endTimeParAlg / 1000000 + " мс");
        System.out.println(endTimeParAlg / 1000000000 + " с");

    }

    public static StringBuilder makeString(List<Character> alphabet) {
        Random rand = new Random();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < rand.nextInt(3, 51); i++) {
            str.append(alphabet.get(rand.nextInt(alphabet.size())));
        }
        return str;
    }
}