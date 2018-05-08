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

    double rate = 0.2; 
    for(int i = 1; i<solution.size()-2; i++){
        if(abs(solution[i].id - solution[i+2].id) <= 100 && abs(solution[i+1].id - solution[i-1].id) <= 100){

            if(((double) rand() / (RAND_MAX)) > rate)
                continue;

            node temp = solution[i];
            solution[i] = solution[i+1];
            solution[i+1] = temp; 
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


vector<node> hardCodeSeed(vector<node> graph){
	string s = "608,573,604,613,691,736,806,839,924,994,956,948,849,920,886,812,827,770,805,833,814,766,666,618,650,632,621,690,778,816,905,861,780,777,746,845,810,853,800,879,866,843,767,731,749,834,835,751,656,680,707,723,679,708,799,702,614,585,506,590,572,515,464,409,377,303,214,130,139,80,102,196,212,282,287,228,175,126,188,224,249,327,348,270,236,166,148,51,82,75,101,71,146,65,67,118,121,183,156,133,140,194,260,251,338,374,408,443,368,326,243,144,234,135,103,116,77,11,38,120,44,14,78,136,184,91,189,283,329,428,411,382,298,202,129,90,43,17,16,34,4,5,59,137,168,253,193,105,96,112,177,125,164,205,162,110,114,40,109,207,219,257,333,404,456,376,462,551,468,449,361,373,407,502,537,588,683,720,709,802,725,716,700,774,714,710,665,694,772,838,933,884,826,898,844,829,914,922,962,961,897,904,921,996,903,918,997,1000,902,850,807,888,943,899,979,895,813,788,791,701,769,848,754,757,776,781,828,797,729,637,670,636,671,669,678,681,612,544,499,514,429,395,360,266,217,204,220,316,350,419,396,421,423,366,301,391,471,547,501,434,491,503,533,556,634,620,606,638,603,647,652,699,684,688,738,706,616,692,703,657,627,535,531,446,416,340,379,389,436,346,384,385,397,422,457,555,629,728,747,756,672,693,667,748,832,817,842,761,753,824,908,890,891,889,936,841,804,743,730,722,771,713,741,782,696,623,564,489,562,611,677,654,734,651,605,589,644,583,543,591,513,453,392,337,304,279,240,309,218,151,174,232,246,178,245,185,242,167,263,181,230,195,206,225,294,336,430,494,567,595,505,561,482,445,522,549,472,458,517,586,609,642,640,565,582,509,504,441,424,357,277,254,317,320,314,305,258,213,122,61,79,89,70,165,190,160,161,111,25,76,74,106,145,197,163,117,132,187,284,292,342,405,493,521,568,660,704,712,768,815,876,938,925,873,786,721,659,685,599,566,485,550,466,448,433,519,560,624,686,784,789,737,803,792,752,808,862,919,968,976,915,958,991,973,942,863,875,974,954,964,985,998,911,967,982,990,937,945,935,984,959,877,972,995,966,907,965,947,957,892,910,916,872,946,941,901,930,931,878,923,970,999,993,952,917,953,855,809,794,836,825,727,646,571,574,523,520,602,639,711,635,649,633,622,705,744,811,900,869,856,885,975,880,971,951,896,801,883,977,950,969,912,851,874,949,893,831,847,821,819,796,894,820,871,793,785,689,675,625,554,577,492,490,447,415,352,341,439,370,299,275,182,235,273,221,153,55,2,3,49,10,9,26,8,12,13,81,119,22,88,138,46,54,20,95,150,199,155,57,36,7,19,30,66,45,97,50,84,104,172,238,276,371,271,280,211,149,226,267,241,297,394,417,414,418,400,427,484,510,524,525,601,664,735,658,718,687,764,783,775,760,740,787,755,739,661,615,619,575,626,645,653,733,822,868,934,940,926,989,992,906,859,944,980,978,960,939,983,963,927,870,840,867,779,732,682,717,726,648,570,540,538,587,598,662,579,600,698,630,628,542,546,508,455,399,420,332,323,288,256,310,233,201,223,200,107,31,94,186,229,142,100,21,62,72,73,41,87,123,134,203,171,93,63,1,6,64,23,37,15,56,27,33,86,173,255,291,328,403,363,269,331,306,359,356,452,444,530,559,532,597,563,473,386,286,364,264,313,268,215,222,227,295,198,248,237,322,353,347,349,351,387,465,486,558,578,663,580,593,668,607,594,673,715,762,763,830,913,881,864,860,798,795,765,745,818,837,882,955,909,858,759,674,592,518,576,655,742,773,857,823,724,750,719,643,697,641,552,461,475,463,365,330,272,311,231,170,191,239,315,367,321,281,208,176,180,261,339,375,369,358,383,398,412,488,516,483,442,477,470,500,440,406,345,381,467,469,495,529,548,545,507,496,487,557,476,435,528,584,511,569,481,454,497,539,474,480,478,541,534,438,372,334,343,312,354,413,426,388,355,318,325,335,247,300,290,344,401,410,319,285,378,278,209,169,124,24,35,99,152,192,143,83,42,131,141,210,158,85,113,127,154,128,98,115,179,157,60,92,18,32,29,47,53,28,108,147,58,39,69,48,52,68,159,216,244,250,252,259,262,265,274,289,293,296,302,307,308,324,362,380,390,393,402,425,431,432,437,450,451,459,460,479,498,512,526,527,536,553,581,596,617,676,758,846,854,887,929,981,987,988,986,932,928,865,852,790,695,631,610,608";

	vector<string> split = explode(s,','); 

	vector<node> solution; 

	for(string i: split){
		solution.push_back(graph[stoi(i)-1]); 
	}

	return solution; 
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

    vector<node> solution = hardCodeSeed(graph); 

    double soFar = calcPath(solution);
    double bestAnswer = 8000000.0; 
    int count = 0;

	while(true){
		for(int i = 1; i<solution.size()-1; i++){
			for(int j = 1; j<solution.size()-1; j++){
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

					if(temp < bestAnswer)
						bestAnswer = temp; 
				}
			}
		}

		if(count <= 10){
			solution = shuffle(solution); 
			cout << "Tour was Shuffled " << calcPath(solution) << endl; 
			soFar = calcPath(solution); 
			//shuffle(solution);
		}

		count = 0; 

		cout << "Best Distance Found So Far " << bestAnswer << endl; 

	}


    return 0;
}
