import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
//import statements

public class TravellingSalesmanProblem {
    static ArrayList<ArrayList<Integer>> dataset = cityInformation.passCityInformation();//receiving information from cityInformation file
    static double[] distanceArray = new double[dataset.size()]; //create an array to store all possible distances
    static int[] xArray = new int[dataset.size() + 1];  //create an array to store all xCoordinates
    static int[] yArray = new int[dataset.size() + 1];//create an array to store all yCoordinates
    static int[] path = new int[dataset.size() + 1];//create an array to store all possible distances
    static double[] sum = new double[dataset.size()]; //create an array to store shortest distance

    public static void main(String[] args) {
        //main method to call all the functions
        splitArrays();//method to split arrayList into separate arrays
        long startTime = System.currentTimeMillis(); //start program timer
        swapArrays();//method swap elements in an array
        findOptimalSolution();//find the optimal result
        long endTime = System.currentTimeMillis();//stop timer
        System.out.println("Output time taken: " + (endTime - startTime) + " milliseconds");//print timer
    }

    public static void swapArrays() {
        double[] shortestDistance = new double[xArray.length - 1];//array to store temporary distances
        for (int y = 0; y < xArray.length - 1; y++) {//nested for loop
            for (int x = 1; x < xArray.length - 1; x++) {
                shortestDistance[y] = returnDistance(xArray[y], yArray[y], xArray[y + 1], yArray[y + 1]);//get initial distance
                if (returnDistance(xArray[y], yArray[y], xArray[x], yArray[x]) < shortestDistance[y] && y < x && y + 1 != x) {//if statement to check the next best city
                    int child = xArray[y + 1];
                    xArray[y + 1] = xArray[x];
                    xArray[x] = child;
                    int child1 = yArray[y + 1];
                    yArray[y + 1] = yArray[x];
                    yArray[x] = child1;
                    int child2 = path[y + 1];
                    path[y + 1] = path[x];
                    path[x] = child2;
                    //swapping array elements and changing city indexes as new best city is found
                }
                if (shortestDistance[shortestDistance.length - 1] == 0) {
                    shortestDistance[shortestDistance.length - 1] = returnDistance(xArray[y], yArray[y], xArray[0], yArray[0]);
                    distanceArray[y] = shortestDistance[shortestDistance.length - 1];
                    //get distance for ending city
                }
                shortestDistance[y] = returnDistance(xArray[y], yArray[y], xArray[y + 1], yArray[y + 1]);//get final distance of all cities
                distanceArray[y] = shortestDistance[y];//store city distance between each city
            }
        }
    }

    private static void splitArrays() {//method splits dataset arraylist into xcoorndinates array,ycoorndinates array, and index array
        for (int y = 0; y < xArray.length - 1; y++) {
            path[y] = dataset.get(y).get(0);
            xArray[y] = dataset.get(y).get(1);
            yArray[y] = dataset.get(y).get(2);
            path[y + 1] = dataset.get(0).get(0);
            xArray[y + 1] = dataset.get(0).get(1);
            yArray[y + 1] = dataset.get(0).get(2);
        }
    }

    public static void swap() {//method swaps the arraylist with all possible combinations
        for (int i = 0; i < dataset.size() - 1; i++) {
            for (int j = 0; j < dataset.size() - 1; j++) {
                Collections.swap(dataset, i, j);
            }
        }
    }

    public static void findOptimalSolution() {//method to find best solution
        double temp;//temp variable holds initial distance
        temp = Arrays.stream(distanceArray).sum();
        for (int i = 0; i < (sum.length); i++) {
            if (sum[i] == 0) {//initial sum array is always 0. if statements runs the program again to find different possibility with new starting city
                swap();//call swapping arraylist function
                splitArrays(); //call splitting arrays function
                swapArrays();//call swapping arrays function
                if (sum[i] < temp) {
                    sum[i] = Arrays.stream(distanceArray).sum();//if new sum has shorter distance, add to array
                }
            } else if (sum[i] == temp) {//if temp variable and sum variable is same,
                swap();//call swapping arraylist function
                splitArrays(); //call splitting arrays function
                swapArrays();//call swapping arrays function
                if (sum[i] < temp) {
                    sum[i] = Arrays.stream(distanceArray).sum();//if new sum has shorter distance, add to array
                }
            } else if (temp < sum[i] && sum[i] != 0) {//if temp is lesser than sum, run the program to check for better possibilities
                swap();//call swapping arraylist function
                splitArrays(); //call splitting arrays function
                swapArrays();//call swapping arrays function
            } else if (sum[i] < temp) {
                sum[i] = Arrays.stream(distanceArray).sum();//if new sum has shorter distance, add to array
            }
        }
        printResults();//print the final results
    }

    public static void printResults() {//method to print the results
        System.out.println("Path covered: " + Arrays.toString(path));//Prints the path covered by the salesman
        Arrays.sort(sum);//sort the sum array in ascending order
        System.out.println("Distance array" + Arrays.toString(distanceArray));//print the distance covered between cities
        System.out.println("Total Distance:" + sum[0]);//print the total optimal distance
    }

    public static double returnDistance(int x1, int y1, int x2, int y2) {//method which takes x and y co-ordinates of 2 cities and calculates Euclidean distance
        return Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2));
        //    return Math.sqrt((y2 - y1) * (y2 - y1)) +( (x2 - x1) * (x2 - x1));//returns the distance
    }
}

