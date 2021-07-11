import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> result = new LinkedList<>();
        int size = scanner.nextInt();
        int K = scanner.nextInt();

        int number = 0;

        for(int i=1; i<=size; i++) {
            queue.offer(i);
        }

        while(result.size() != size) {
            if(((number+1)%K) == 0) {
                result.offer(queue.poll());
            }
            else {
                queue.offer(queue.poll());
            }
            number++;
        }
        System.out.print('<');
        for (int n=0; n < size; n++) {
            if(n == 0) System.out.print(result.poll());
            else System.out.print(", "+result.poll());
        }
        System.out.println('>');
    }
}
