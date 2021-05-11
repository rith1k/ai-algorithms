public class BackPropagation {
    public static double[] error;//error array for output layer weights
    public static double[] errorPrime;//error array for hidden layer weights
    public static double errorTemp;//summation for calculating error prime

    public static void CalculateError(int[] actualOutputMap, int[] desiredOutputMap, double[] rowInput, int desired) throws InterruptedException {
        error = new double[NeuralNetwork.outputLayerOutput.length];//initializing error array
        for (int i = 0; i < actualOutputMap.length; i++) {
            error[i] = (desiredOutputMap[i] - actualOutputMap[i]);//finding the error: difference between desired output array and actual output array
        }
        calculateNewOutputLayerWeights(error);//call the method to calculate new weights for output layer
        calculateErrorPrime(error, rowInput); //method for calculating error prime
    }

    public static void calculateErrorPrime(double[] error, double[] rowInput) throws InterruptedException {
        errorPrime = new double[NeuralNetwork.hiddenNeurons];
        for (int j = 0; j < NeuralNetwork.hiddenNeurons; j++) {
            errorTemp = 0;
            for (int i = 0; i < NeuralNetwork.outputNeurons; i++) {
                errorTemp += error[i] * NeuralNetwork.outputLayerWeights[i][j];//calculating temp Error
            }
                errorPrime[j] = ((NeuralNetwork.hiddenLayerOutput[j] * (1 - NeuralNetwork.hiddenLayerOutput[j]) * errorTemp));//calculate error prime
        }
        calculateNewHiddenLayerWeights(errorPrime,  rowInput);//method for calculating new adjusted weights for hidden layer
    }

    public static void calculateNewHiddenLayerWeights(double[] errorPrime, double[] rowInput) throws InterruptedException {
        for (int i = 0; i < NeuralNetwork.hiddenNeurons; i++) {
            for (int j = 0; j < NeuralNetwork.trainingRowInput.length; j++) {
                    NeuralNetwork.hiddenLayerWeights[i][j] = NeuralNetwork.hiddenLayerWeights[i][j] + (NeuralNetwork.learning_rate * rowInput[j] * errorPrime[i]);//calculating adjusted hidden weights
            }
        }
    }

    public static void calculateNewOutputLayerWeights(double[] error) {
        for (int i = 0; i < NeuralNetwork.outputLayerOutput.length; i++) {
            for (int j = 0; j < NeuralNetwork.hiddenLayerOutput.length; j++) {
                NeuralNetwork.outputLayerWeights[i][j] = NeuralNetwork.outputLayerWeights[i][j] + NeuralNetwork.learning_rate * NeuralNetwork.hiddenLayerOutput[j] * error[i];//calculating adjusted output weights
            }
        }
    }
}

