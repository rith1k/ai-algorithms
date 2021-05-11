import java.io.*;
import java.util.*;

public class NeuralNetwork {
    public static File testingFile = new File("src/cw2DataSet2.csv");// store csv file path name
    public static File trainingFile = new File("src/cw2DataSet1.csv");// store csv file path name
    public static String[] trainingInputArray, testingInputArray;//initialization of variables
    public static double[] trainingRowInput, testingRowInput;
    public static double learning_rate = 0.01, success_rate = 0.0, trainingAccuracy, testingAccuracy; //learning rate
    public static int outputNeurons = 10, hiddenNeurons = 250,  iterations = 500, trainingSuccess, testingSuccess, cycle;//number of neurons and iterations;
    public static Double[][] outputLayerWeights;//array for hidden weights
    public static Double[][] hiddenLayerWeights;//array for output weights
    public static double[] hiddenLayerOutput = new double[hiddenNeurons];//initializing hiddenLayerOutput array
    public static double[] outputLayerOutput = new double[outputNeurons];//initializing outputLayerOutput array
    public static int[] desiredMap = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};//creating a map for desired output for each row
    public static int[] actualMap = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};// creating a map for actual outputs generated from sigmoid and threshold values
    public static int trainingCount = 0, testingCount = 0;

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Step 0: Initializing Neural network....");
        System.out.println("Step 1: Reading Training dataset");
        System.out.println("Step 2: Beginning the FeedForward process for training dataset");
        System.out.println("Step 3: Testing the FeedForward results for training dataset");
        System.out.println("Step 4: Calling BackPropagation for training dataset");
        System.out.println("Step 5: Checking results....");
        int i = 0;
        while (i < iterations) {

            readDataSet();//reading dataset
            trainingAccuracy = (double) trainingSuccess / trainingCount * 100;//calculating accuracy
            if (i == iterations - 1) {//print last values of last iteration
                System.out.println("<<-------------------------------------------------------------->>");
                System.out.println("Results for Training Dataset:\nNumber of iterations= "+iterations+"\nFinal Success = " + trainingSuccess + "\nNumber of rows in the dataset= " + trainingCount + "\nAccuracy= " + trainingAccuracy + "%");//printing success rate
            }

            cycle++;
            success_rate = 0.0;//resetting success rate
            trainingSuccess = 0;//resetting success
            trainingAccuracy = 0;//resetting accuracy
            trainingCount = 0;//resetting count
            i++;
        }
        System.out.println("<<-------------------------------------------------------------->>");
        System.out.println("Step 6: Training Dataset completed!");
        System.out.println("Step 7: Initiating testing dataset...");
        System.out.println("Step 8: Beginning the FeedForward process for testing dataset");
        System.out.println("Step 9: Testing the FeedForward results for testing dataset");
        System.out.println("Step 10: Getting final results....");
        callTesting();
        System.out.println("<<-------------------------------------------------------------->>");

    }

    private static void callTesting() throws IOException, InterruptedException {
        BufferedReader br = new BufferedReader(new FileReader(testingFile));
        String line;
        while ((line = br.readLine()) != null) {
            String[] column = line.split(","); // use comma as separator
            int desired;
            testingInputArray = (Arrays.copyOfRange(column, 0, column.length - 1));//seperating inputs from the desired output
            testingRowInput = Arrays.stream(testingInputArray).mapToDouble(Double::parseDouble).toArray();
            FeedForward.calculateSigmoidOrThreshold(hiddenLayerWeights, hiddenLayerOutput, testingRowInput, 1, 0);//calculating sigmoid for hidden layer
            FeedForward.calculateSigmoidOrThreshold(outputLayerWeights, outputLayerOutput, hiddenLayerOutput, 0, 1);//calculating threshold for output layer
            desired = Integer.parseInt(column[column.length - 1]);//getting desired output for each row
            TrainingAndTesting.mappingArrays(desired, outputLayerOutput, desiredMap, actualMap, testingRowInput, 1);//Verifying if actual output matches with desired output
            testingCount++;
        }
        testingAccuracy = (double) testingSuccess / testingCount * 100;//calculating accuracy
        System.out.println("<<-------------------------------------------------------------->>");
        System.out.println("Results for Testing Dataset:\nTesting Success =" + testingSuccess + "\nDataSet rows= " + testingCount + "\nAccuracy= " + testingAccuracy + "%");//printing success rate
    }

    public static void readDataSet() throws IOException, InterruptedException {

        BufferedReader br = new BufferedReader(new FileReader(trainingFile));//read file
        String line;
        while ((line = br.readLine()) != null) {//so long as dataset as next row
            String[] column = line.split(","); // use comma as separator
            int desired;//variable for storing desired output
            trainingInputArray = (Arrays.copyOfRange(column, 0, column.length - 1));//seperating inputs from the desired output
            trainingRowInput = Arrays.stream(trainingInputArray).mapToDouble(Double::parseDouble).toArray();
            desired = Integer.parseInt(column[column.length - 1]);//getting desired output for each row
            Arrays.fill(trainingInputArray, null);//emptying arrays not in use
            Arrays.fill(column, null);//emptying arrays not in use
            if (cycle == 0 && trainingCount == 0) {
                hiddenLayerWeights = new Double[hiddenNeurons][trainingRowInput.length];//initializing hiddenLayerWeights
                outputLayerWeights = new Double[outputNeurons][hiddenLayerOutput.length];
                FeedForward.randomizeWeights(hiddenLayerWeights);//randomizing weights for hidden layer
                FeedForward.randomizeWeights(outputLayerWeights);//randomizing weights for output layer
            }
            FeedForward.calculateSigmoidOrThreshold(hiddenLayerWeights, hiddenLayerOutput, trainingRowInput, 1, 0);//calculating sigmoid for hidden layer
            FeedForward.calculateSigmoidOrThreshold(outputLayerWeights, outputLayerOutput, hiddenLayerOutput, 0, 1);//calculating threshold for output layer
            TrainingAndTesting.mappingArrays(desired, outputLayerOutput, desiredMap, actualMap, trainingRowInput, 0);//Verifying if actual output matches with desired output
            trainingCount++;//inreasing count
        }
    }
}