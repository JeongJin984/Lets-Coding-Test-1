#include <iostream>
#include <vector>
#include <set>
#include <algorithm>
#include <deque>

using namespace std;

pair<int, int> ways[4] = { {0,1},{1,0},{0,-1},{-1,0} };

bool is_out(int N, int y, int x) {
    return y > N || y < 1 || x > N || x < 1;
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(0);
    
    deque<pair<int, int>> snakes;
    snakes.push_front({ 1,1 });

    int N;
    cin >> N;

    int K;
    cin >> K;
    set<pair<int, int>> apples;
    for (int i = 0; i < K; i++) {
        int y, x;
        cin >> y >> x;
        apples.insert({ y,x });
    }

    int L;
    cin >> L;
    int moves[10001];
    for (int i = 0; i < L; i++) {
        int sec; char way;
        cin >> sec >> way;
        moves[sec] = way;
    }
    int way = 0;
    for (int i = 0; i < 10200; i++) {
        auto head = snakes[0];
        int new_y = head.first + ways[way].first;
        int new_x = head.second + ways[way].second;
        
        if (is_out(N, new_y, new_x)) {
            cout << i+1;
            return 0;
        }
        if (find(snakes.begin(), snakes.end(), make_pair( new_y,new_x )) != snakes.end()) {
            cout << i+1;
            return 0;
        }

        snakes.push_front({ new_y,new_x });
        if (apples.find(make_pair(new_y, new_x)) == apples.end())
            snakes.pop_back();
        else
            apples.erase(make_pair(new_y, new_x));
        
        if (moves[i+1] == 'D') {
            way = (way + 1) % 4;
        }
        else if (moves[i+1] == 'L') {
            way = (way + 3) % 4;
        }
    }
}
