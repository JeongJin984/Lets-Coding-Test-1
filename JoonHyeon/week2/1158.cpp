include <iostream>
#include <deque>

using namespace std;

int main()
{
    int N, K;
    cin >> N >> K;
    deque<int> d1;
    deque<int> d2;
    for(int i = 0; i < N ; i++){
        d1.push_back(i+1);
    }
    for(int i = 0 ; i < N;i++){
        for(int j = 0 ; j < K-1;j++){
            int a = d1.front();
            d1.pop_front();
            d1.push_back(a);
        }
        d2.push_back(d1.front());
        d1.pop_front();
    }
    cout << '<';
    for(int i = 0 ; i < N-1;i++){
        cout << d2[i] << ", ";
    }
    cout << d2[N-1]<<'>';
}