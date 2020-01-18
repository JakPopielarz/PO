package programowanieobiektowe.lab14;

public class MeanCalc extends Thread {
    private final int start;
    private final int end;
    double mean = 0;

    MeanCalc(int start, int end){
        this.start = start;
        this.end=end;
    }
    public void run(){
        for(int i=start; i<end; i++) {
            mean += Mean.array[i];
        }
        mean = mean / (end-start);
        try {
            Mean.results.put(mean);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.printf(Locale.US,"%d-%d mean=%f\n",start,end,mean);
    }
}
