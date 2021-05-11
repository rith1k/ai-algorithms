import java.util.*;

public class TrainingAndTesting {
    public static void mappingArrays(int desired, double[] outputLayerOutput, int[] desiredOutputMap, int[] actualOutputMap, double[] rowInput, int testing) throws InterruptedException {
        desiredOutputMap[desired] = 1;//setting desired output variable as 1 for the corresponding element in desiredOutputMap
        for (int i = 0; i < NeuralNetwork.actualMap.length; i++) {
            if (i != desired) {
                desiredOutputMap[i] = 0;//mapping desired Output map
            }
            actualOutputMap[i] = (int) outputLayerOutput[i];//converting output layer to actual Output map
        }
        if (testing == 0) {
            if (Arrays.equals(desiredOutputMap, actualOutputMap)) {//if the desired output map doesn't match the actual output map
                NeuralNetwork.trainingSuccess++;//increase success
            } else {
                BackPropagation.CalculateError(actualOutputMap, desiredOutputMap, rowInput, desired);//do backpropagation

            }
        } else if (testing == 1) {
            if (Arrays.equals(desiredOutputMap, actualOutputMap)) {//if the desired output map doesn't match the actual output map
                NeuralNetwork.testingSuccess++;//increase success
            }
        }


    }
}
