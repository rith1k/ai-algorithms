public class FeedForward {
    public static Double summedInput = 0.0;

    public static void randomizeWeights(Double[][] weights) {
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights[0].length; j++) {
                weights[i][j] = Math.random() * (0.5 - (-0.5)) + (-0.5);//randomizing weights into 2d array

            }
        }
    }

    public static void calculateSigmoidOrThreshold(Double[][] weights, double[] output, double[] dataset, int sig, int threshold) throws InterruptedException {
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights[0].length; j++) {
                summedInput += (((dataset[j])) * weights[i][j]);//calculating summation of weights multiplied by dataset inputs
            }
            if (sig == 1) {
                double sigmoid = (((((1d / (1 + Math.exp(-(summedInput))))))));//calculating sigmoid
                output[i] = (sigmoid);//storing sigmoid values into output array
            } else if (threshold == 1) {//calculating threshold
                if (summedInput > 0) {
                    output[i] = 1;//storing threshold value as 1 if summation is greater than 1
                } else
                    output[i] = 0;//storing threshold value as 0 if summation is lesser than 1
            }
            summedInput = 0.0;//resetting sum
        }

    }
}
