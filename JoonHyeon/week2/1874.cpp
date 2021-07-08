#include<iostream>
#include<string>
#include<stack>
#include<vector>

using namespace std;

int main(void) {
	int N;
	vector<int> vec;
	stack<int> stk;
	cin >> N;
	string ans;
	for (int i = 0; i < N; i++) {
        int a;
		cin >> a;
		vec.push_back(a);
	}
    
	int current_max = 0;
	for (int i = 0; i < N; i++) {
		if (vec[i] > current_max) {
			for (int j = current_max + 1; j <= vec[i]; j++) {
				stk.push(j);
				ans+='+';
			}
			current_max = vec[i];
			stk.pop();
			ans+='-';
		}
		else {
			if (vec[i] == stk.top()) {
				stk.pop();
				ans += '-';
			}
			else {
				cout <<"NO\n";
				return 0;
			}
		}
	}
    
    for(auto ch : ans)
        cout << ch << '\n';
}