import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class cityInformation {
    public static ArrayList<ArrayList<Integer>> dataset = new ArrayList<>();//create a 2d Arraylist

    public static ArrayList<ArrayList<Integer>> passCityInformation() {//function to read file and return arraylist
        try {
            // Number of ints per line:
            //File filename = new File("test1tsp.txt");
            String filename = "test4-20.txt";//read file
            FileInputStream fStream = new FileInputStream(filename);//pass file through file input stream
            BufferedReader input = new BufferedReader(new InputStreamReader(fStream));//pass input stream in buffered reader
            Scanner src = new Scanner(input);//scanner object to read every line of file
            while (src.hasNextLine()) {//loop to iterate through all lines in code
                String[] line = src.nextLine().split("\\s+");//split every line
                dataset.add(new ArrayList<Integer>());//make the arraylist 2d
                for (String num : line) {//loop for every line
                    if (num.matches("-?[1-9]\\d*|0"))//matching line info with regex criteria
                        dataset.get(dataset.size() - 1).add(Integer.parseInt(num));//insert city information in arraylist
                }

            }
        } catch (FileNotFoundException fileNotFoundException) {//handle any exceptions
            fileNotFoundException.printStackTrace();
        }
        System.out.println(dataset);
        return dataset;//return the dataset to main program

    }

}


