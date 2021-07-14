import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// 뱀
// NxN 정사각형 보드
// 맨위 맨 위측 위치(1,1), 처음 오른쪽 방향을 향함
// 뱀은 계속 머리쪽이 늘어남
// 사과 있으면 사과 없어지고 머리 늘려서 꼬리 그대로
// 사과 없으면 머리 늘려서 꼬리가 위치한 칸 비워줌
public class Main {

    static int n; // 보드의 크기 (2~100)
    static int k; // 사과의 갯수 (0~100)
    static int l; // 뱀의 방향 변환 횟수 (1~100)
    static int time; // 게임 시간
    static int[][] board;

    static List<int[]> snake; //뱀의 몸통 위치 (x,y)

    // 처음 시작은 오른쪽 방향을 보고 있음
    // 0:오른쪽   1:아래쪽   2:왼쪽   3:위
    // D(오른쪽)가 다오면 index++
    // L(왼쪽)이 나오면 index--
    static int index = 0;
    static int[] dx = {0, 1, 0, -1}; //세로
    static int[] dy = {1, 0, -1, 0}; //가로

    static Map<Integer, String> dir; // 뱀의 방향 정보

    public static void main(String[] args) throws Exception, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        // 1,1이 맨 위 맨 좌측
        board = new int[n+1][n+1];

        // 사과 위치 입력
        String str;
        int row; // 행
        int col; // 열
        for(int i=0; i<k; i++) {
            str = br.readLine();

            row = Integer.parseInt(str.split(" ")[0]);
            col = Integer.parseInt(str.split(" ")[1]);

            board[row][col] = 1;
        }

        // 뱀 방향 정보 입력
        dir = new HashMap<>();
        l = Integer.parseInt(br.readLine());
        for(int i=0; i<l; i++) {
            str = br.readLine();
            int timeInfo = Integer.parseInt(str.split(" ")[0]);
            String directionInfo = str.split(" ")[1];

            dir.put(timeInfo, directionInfo);
        }

        // 뱀 시작 지점 (1,1) (x,y)
        snake = new LinkedList<>();
        snake.add(new int[]{1,1});

        time = 0;
        int nx, ny; // 다음 움직임
        int cx, cy; // 현재 움직임(1,1)
        cx = 1;
        cy = 1;
        // 뱀 움직임 시작
        while(true) {
            time++;

            // 다음 움직임(머리 데이터)
            nx = cx + dx[index];
            ny = cy + dy[index];

            // 끝나는지 확인
            if(isFinish(nx,ny)) break;

            // 사과 있는지 확인
            // 사과 있으면 사과 없어지고 머리 늘려서 꼬리 그대로
            if(board[nx][ny] == 1) {
                board[nx][ny] = 0;
                snake.add(new int[] {nx,ny}); // 머리 정보 추가
            }
            // 사과 없으면 머리 늘려서 꼬리가 위치한 칸 비워줌
            else {
                snake.add(new int[] {nx,ny}); // 머리 정보 추가
                snake.remove(0); // 꼬리 index는 0
            }

            cx = nx;
            cy = ny;

            // 해당 시간 끝났을 때 다음 방향 정해주기
            if(dir.containsKey(time)) {
                // D(오른쪽)가 다오면 index++
                if(dir.get(time).equals("D")) {
                    index++;
                    if(index == 4)
                        index = 0;
                }
                // L(왼쪽)이 나오면 index--
                if(dir.get(time).equals("L")) {
                    index--;
                    if(index == -1)
                        index = 3;
                }

            }
        }

        System.out.println(time);
    }

    // 게임이 끝나는지 확인
    static boolean isFinish(int nx, int ny){

        // 벽에 부딪히거나
        if(nx<1 || ny<1 || nx>=n+1 || ny>=n+1)
            return true;

        // 자기 몸통에 닿거나
        for(int i=0; i<snake.size(); i++) {
            if(nx == snake.get(i)[0] && ny == snake.get(i)[1])
                return true;
        }

        return false;
    }

}
