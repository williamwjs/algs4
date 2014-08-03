package WeekTwo;

/**
 * Created by JiashuoWang on 7/22/14.
 */
public class Subset {
    public static void main(String[] args) {
        //int k = 3;
        //int N = 9;
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        //for (int i = 0; i < N; i++) {
        while (!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }
        //for (int i = 0; i < k; i++) {
        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            System.out.println(rq.dequeue());
        }
    }
}
