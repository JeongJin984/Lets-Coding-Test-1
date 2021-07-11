import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
 
public class Main {
    public static int N;                // 입력받을 수열의 크기
    public static int num = 1;            // 수열을 만들기 위해 1 ~ N가 되는 수
    
    public static int[] arr;            // 입력받을 수열이 저장될 배열
    public static Stack<Integer> stack;    // 수열을 만들기 위해 1 ~ N 까지의 수가 저장될 스택
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        arr = new int[N];
        stack = new Stack<Integer>();
        
        boolean isAble = true;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            
            if(isAble) {
                // num이 arr[i] 보다 작으면 같을때까지 스택에 증가 시키고 num++한다.
                if(num <= arr[i]) {
                    while(num <= arr[i]) {
                        stack.push(num++);
                        sb.append("+ \n");
                    }
                }
                if(stack.isEmpty()) isAble = false;
                else {
                    // 스택의 top이 arr[i]보다 작을때까지 pop 
                    while(stack.peek() >= arr[i]) {
                        stack.pop();
                        sb.append("- \n");
                        if(stack.isEmpty()) {
                            break;
                        }
                    }
                }
            }
        }
        if(isAble) {
            System.out.println(sb.toString());
        }else {
            System.out.println("NO");
        }
    }
}
