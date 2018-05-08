#include <bits/stdc++.h>
#include <cmath>
using namespace std;
typedef long long ll;
#define rep(i,a,b,c) for(int i = a; i<b; i+=c)
#define reg(i,a,b,c) for(int i = a; i>b; i-=c)


const vector<string> explode(const string& s, const char& c){
	string buff{""};
	vector<string> v;
	
	for(auto n:s)
	{
		if(n != c) buff+=n; else
		if(n == c && buff != "") { v.push_back(buff); buff = ""; }
	}
	if(buff != "") v.push_back(buff);
	
	return v;
}

struct node{
    double x, y; 
    int id; 

    node(int _id, double _x, double _y){
        id = _id; 
        x = _x; 
        y = _y; 
    }

    double dist(node n){
        return haversine(x,y,n.x,n.y); 
    }

    double haversine(double lat1, double lon1, double lat2, double lon2) {
        double convert = M_PI/180; 
        double lat = convert*(lat2 - lat1);
        double lon = convert*(lon2 - lon1);
        lat1 = convert*(lat1);
        lat2 = convert*(lat2);
        double x = pow(sin(lat/2),2)+pow(sin(lon/2),2)*cos(lat1)*cos(lat2);
        return 6371*(2 * asin(sqrt(x)));
    }
};


vector<node> nigelMethod(vector<node> graph){
    vector<node> solution;

    rep(i,607,1000,2){
        solution.push_back(graph[i]); 
    }

    reg(i,998,-1,2){
        solution.push_back(graph[i]); 
    }

    rep(i,1,608,2){
        solution.push_back(graph[i]); 
    }

    return solution; 
}

void displaytour(vector<node> solution){
    for(node n: solution){
        cout << n.id << endl; 
    }
}


bool check(node n1,node n2,node n3){
    if(abs(n1.id-n2.id) > 100 || abs(n2.id-n3.id) > 100){
        return false;
    }
    return true; 
}


double calcPath(vector<node> tour){
    double total = 0; 

    for(int i = 0; i<tour.size()-1; i++){
        total += tour[i].dist(tour[i+1]); 
    }
    return total; 
}

vector<node> shuffle(vector<node> solution){

    double rate = 0.5; 
    for(int i = 1; i<solution.size()-2; i++){
        if(abs(solution[i].id - solution[i+2].id) <= 100 && abs(solution[i+1].id - solution[i-1].id) <= 100){

            if(((double) rand() / (RAND_MAX)) > rate)
                continue;

            node temp = solution[i];
            solution[i] = solution[i+1];
            solution[i+1] = temp; 
            /*
            double before = calcPath(solution); 
            double x = solution[i].x; 
            double y = solution.[i].y; 
            int id = solution[i].id; 
            solution.get(i).resetAttributes(solution.get(i+1).x, solution.get(i+1).y,solution.get(i+1).id,solution.get(i+1).name); 
            solution.get(i+1).resetAttributes(x,y,id,name);
            */

        }
    }

    return solution; 
}


bool checkRanks(vector<node> solution){
    int i;
    rep(i,0,solution.size()-1,1){
        if(abs(solution[i].id - solution[i+1].id) > 100)
            return false;
    }
    return true; 
}

int main(){

    int i,j,k;
    vector<node> graph; 
    
    ifstream reader("Book1.csv"); 
    string line; 

    getline(reader,line); 



    while(!reader.eof()){
        getline(reader, line); 

        vector<string> v = explode(line, ','); 

        int id;
        id = stoi(v[0])-1; 

       // cout << id << endl;

        double x = stod(v[v.size()-2]); 
        double y = stod(v[v.size()-1]); 

        graph.push_back(node(id,x,y)); 
    }

    vector<node> solution = nigelMethod(graph);

    double soFar = calcPath(solution);
    int count = 0;

	while(true){
		for(int i = 1; i<solution.size()-1; i++){
			for(int j = 1; j<solution.size()-1; j++){
				//if(abs(solution[i].id - solution[j].id) <= 100){
					if(check(solution[i-1], solution[j], solution[i+1]) && check(solution[j-1], solution[i], solution[j+1])){ //if swapping is ok


						node t = solution[i];
						solution[i] = solution[j];
						solution[j] = t; 

						double temp = calcPath(solution); 

						if(soFar > temp){
							cout << temp << endl; 
							soFar = temp; 
							count++; 
						}

						else{
							node t = solution[i];
							solution[i] = solution[j];
							solution[j] = t;
						}
					}
				//}
			}
		}


		if(count <= 10){
			solution = shuffle(solution); 
			cout << "Tour was Shuffled " << calcPath(solution) << endl; 
			//shuffle(solution);
		}

		count = 0; 
	}


    return 0;
}