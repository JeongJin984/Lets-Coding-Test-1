#include <string>
#include <algorithm>
#include <vector>

using namespace std;

vector<int> solution(int N, vector<int> stages) 
{
    int                     acum        = 0;
    int                     count[501]  = {0,};
    const int               size        = stages.size();
    vector<int>             answer;
    vector<pair<float,int>>   vec;
    
    for(const auto& el : stages)
        ++count[el];
    
    for(int i = 1 ; i <= N;i++){
        vec.push_back({1.0*count[i]/(size-acum),i});
        acum += count[i];
    }
    
    stable_sort(vec.begin(),vec.end(),[](pair<float,int> p1, pair<float,int>p2){
    	return p1.first>p2.first;
    });
    
    for(const auto& el : vec){
        answer.push_back(el.second);
    }
    
    return answer;
}
