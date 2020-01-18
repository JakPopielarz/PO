package programowanieobiektowe.lab14;

import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Mean {
    static double[] array;
    static BlockingQueue<Double> results = new ArrayBlockingQueue<Double>(100);
    static void initArray(int size){
        array = new double[size];
        for(int i=0;i<size;i++){
            array[i]= Math.random()*size/(i+1);
        }
    }

    /**
     * Oblicza średnią wartości elementów tablicy array uruchamiając równolegle działające wątki.
     * Wypisuje czasy operacji
     * @param cnt - liczba wątków
     */
    static void parallelMean(int cnt){
        // utwórz tablicę wątków
        MeanCalc threads[]=new MeanCalc[cnt];
        // utwórz wątki, podziel tablice na równe bloki i przekaż indeksy do wątków
        // załóż, że array.length dzieli się przez cnt)
        int delta = array.length / cnt;
        for (int i=0; i<cnt; i++) {
            threads[i] = new MeanCalc(i*delta, (i+1)*delta);
        }
        double t1 = System.nanoTime()/1e6;
        //uruchom wątki
        for (MeanCalc thread : threads)
            thread.start();
        double t2 = System.nanoTime()/1e6;

        double mean = 0;
        for (int i=0; i<cnt; i++) {
            try {
                mean += results.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        mean = mean/cnt;

//        // czekaj na ich zakończenie używając metody ''join''
//        for(MeanCalc mc:threads) {
//            try {
//                mc.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        // oblicz średnią ze średnich
//        double mean = 0;
//        for (MeanCalc thread: threads)
//            mean += thread.mean;
//        mean = mean/cnt;

        double t3 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"size = %d cnt=%d >  t2-t1=%f t3-t1=%f mean=%f\n",
                array.length,
                cnt,
                t2-t1,
                t3-t1,
                mean);
    }

    public static void main(String[] args) {
        initArray(1280000);
        for(int cnt:new int[]{1,2,4,8,16,32,64,128}){
            parallelMean(cnt);
        }
    }
}
