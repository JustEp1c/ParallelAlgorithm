import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ParallelAlg {

    public static String finalString;
    public static String checkLexicographicOrder(ArrayList<String> listOfStrings, List<Character> alphabet) throws InterruptedException {
        finalString = listOfStrings.get(0);
        int threadCount;
        ReentrantLock locker = new ReentrantLock();

        if (listOfStrings.size() < 16) threadCount = listOfStrings.size() / 2;
        else threadCount = 8;

        int fromIndex = 0;
        int listForThreadSize = listOfStrings.size() / threadCount;
        int toIndex = listForThreadSize;
        int mod = listOfStrings.size() % threadCount;

        ArrayList<Thread> listForThreads = new ArrayList<Thread>();
        for (int i = 0; i < threadCount; i++) {

            if (i == 0) toIndex += mod;

            ArrayList<String> listForThread = new ArrayList<>(listOfStrings.subList(fromIndex, toIndex));

            Runnable task = () -> {
                //System.out.println("Поток " + Thread.currentThread().getId() + " запущен");
                String str = SequentialAlg.compareTwoStrings(listForThread.get(0),listForThread.get(1), alphabet);
                if (listForThread.size() > 2) {
                    for (int j = 2; j < listForThread.size(); j++) {
                        str = SequentialAlg.compareTwoStrings(str, listForThread.get(j), alphabet);
                    }
                }
                //System.out.println("Поток " + Thread.currentThread().getId() + " нашел первую строку");
                locker.lock();
                finalString = SequentialAlg.compareTwoStrings(str, finalString, alphabet);
                locker.unlock();
                //System.out.println("Поток " + Thread.currentThread().getId() + " записал первую строку");
            };
            Thread thread = new Thread(task);
            listForThreads.add(thread);
            listForThreads.get(i).start();

            fromIndex = toIndex;
            toIndex += listForThreadSize;
        }
        for (Thread thread : listForThreads) {
            thread.join();
        }
        return finalString;
    }
}
