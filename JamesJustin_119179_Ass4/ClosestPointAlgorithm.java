import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ClosestPointAlgorithm {
// method to partitition in quicksort algo
int part(List<double[]> p, int initialindex, int finalindex)
{

double pivot = p.get(finalindex)[1];
   int i = (initialindex-1);

   for (int j = initialindex; j < finalindex; j++) {
       if (p.get(j)[1] <= pivot) {
           i++;

           double swap = p.get(i)[1];
           p.get(i)[1] = p.get(j)[1];
           p.get(j)[1] = swap;
       }
   }

   double swap = p.get(i+1)[1];
   p.get(i+1)[1] = p.get(finalindex)[1];
   p.get(finalindex)[1] = swap;

   return i+1;

}
// calculate eucleidean distance
double dist(double[] a, double[] b)
{
return Math.sqrt(( Math.pow(a[0] - b[0],2) +Math.pow(a[1] - b[1],2))  );  
}
//method to generate rando numbers according to size mentioned in main
List<double[]> RandomList(int sz)
{
List<double[]> randList= new ArrayList<double[]>();

for(int i=0;i<sz;i++)
{
double a= ((Math.random() * 900) + 100) / 100.0D;
double b= ((Math.random() * 800) + 100) / 100.0D;
double[]c= {a,b};
randList.add(c);

}
return randList;
}
// quicksort algorithm implementation to sort list
List<double[]> quicksort(List<double[]> p, int leftindex, int rightindex) 
   { 
	if (leftindex < rightindex) 
       { 
           int pa = part(p, leftindex ,rightindex); 
 
           quicksort(p, leftindex, pa-1); 
           quicksort(p, pa+1, rightindex); 
       } 
       
       return p;
   }

// implementation of closest point algorithm
	double CPoint(List<double[]> p)
{
	double d1=1,d2=1;	
	if(p.size()==1)// if list size is 1
{
		return Double.POSITIVE_INFINITY;  
}	

	if(p.size()==2)// if list size is 2, return distance
{
	return dist(p.get(0),p.get(1));  
}

// if list is greater than 3
	int n=p.size()/2;
	System.out.println("\nmid:");
	double[] mid=p.get((p.size()/2) ); // get mid point
	System.out.println(mid[0]);
	System.out.println(mid[1]);	
	List<double[]> p1= new ArrayList<double[]>();
	p1=  p.subList(0, n); // sublist 1

	System.out.println("\np1 sublist:");
for(int i=0; i< p1.size(); i++){
           System.out.print(p1.get(i)[0]+"-"+p1.get(i)[1]+"\n");
       }

List<double[]> p2= new ArrayList<double[]>();
	p2= p.subList(n, p.size()); // sublist 2
	System.out.println("\np2 sublist:");
for(int i=0; i< p2.size(); i++){
          System.out.print(p2.get(i)[0]+"-"+p2.get(i)[1]+"\n");
      }
	d1=d1*CPoint(p1); // recursion
	d2=d2*CPoint( p2); // recursion
double minDistance =(d1 < d2) ? d1 : d2;
//return minDistance;
	List<double[]> strip= new ArrayList<double[]>();
// add points to strip which are in between 2 lists
for(int i=0; i< p.size(); i++){
          if(p.get(i)[0]-mid[0]<minDistance) {
          strip.add(p.get(i));
          }
      }
// sort the strip according to Y- coordinate
	strip=quicksort(strip,0,strip.size()-1);
	
// calculate min distance of strip points to min distance of other points
for(int i=0;i<strip.size()-2;i++)
{
	int k=i+1;
	while(k<strip.size()-1 & (strip.get(k)[1]-strip.get(i)[1])<minDistance)
{
		double minD=dist(strip.get(k),strip.get(i));
		minDistance=(minD < minDistance) ? minD : minDistance;
		k++;

}
}

return minDistance;

}

public static void main(String[] args) {
// TODO Auto-generated method stub
	Scanner scan = new Scanner(System.in); 
ArrayList<double[]> a= new ArrayList<double[]>();
//	double[] a1= new double[] {300,400};    // tried with manual input for the algorithm
//	a.add(a1);
//	double[] a2= new double[] {50,100};
//	a.add(a2);
//	double[] a3= new double[] {100,200};
//	a.add(a3);
//	double[] a4= new double[] {0.4,0.5};
//	a.add(a4);

	ClosestPointAlgorithm c= new ClosestPointAlgorithm();
	System.out.println("Enter the number of points:");
	int l=scan.nextInt();

	a=(ArrayList<double[]>) c.RandomList(l); // first generate random numbers
//double d=c.CPoint(a);
//System.out.println("smallest distance:"+d);

//	List<double[]> s=c.quicksort(a,0,a.size()-1);
	System.out.println("rand array:"); // print the random numbers
	for(int i=0; i< a.size(); i++){
          System.out.print(a.get(i)[0]+"-"+a.get(i)[1]+"\n");
      }

	double d=c.CPoint(a);
	System.out.println("\nsmallest distance:"+d);// call the closest point algorithm
}

}
