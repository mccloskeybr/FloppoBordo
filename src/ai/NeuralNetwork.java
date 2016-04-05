package ai;

/**
 * Created by mccloskeybr on 4/2/16.
 */
public class NeuralNetwork {

    private double[] weights;
    private double[] output;

    private static final int NUM_INPUT = 4;
    private static final int NUM_HIDDEN = 5;
    private static final int NUM_OUTPUT = 1;

    public NeuralNetwork(){

        weights = new double[NUM_INPUT * NUM_HIDDEN + NUM_HIDDEN * NUM_HIDDEN + NUM_HIDDEN * NUM_OUTPUT];
        for (int i = 0; i < weights.length; i++)
            weights[i] = Math.random() - Math.random();

        output = new double[NUM_OUTPUT];

    }

    public NeuralNetwork(double[] weights){
        this.weights = weights;
        output = new double[NUM_OUTPUT];
    }

    public double[] getWeights(){
        return weights;
    }

    public double[] forward(double[] input){

        if (input.length == NUM_INPUT) {

            // populate hidden_1
            double[] hidden_1 = new double[NUM_HIDDEN];
            for (int i = 0; i < NUM_INPUT; i++)
                for (int j = 0; j < NUM_HIDDEN; j++)
                    hidden_1[j] += weights[i * NUM_HIDDEN + j] * input[i];

            // activate hidden_1
            for (int i = 0; i < hidden_1.length; i++)
                hidden_1[i] = sigmoid(hidden_1[i]);

            // populate hidden_2
            double[] hidden_2 = new double[NUM_HIDDEN];
            for (int i = 0; i < NUM_HIDDEN; i++)
                for (int j = 0; j < NUM_HIDDEN; j++)
                    hidden_2[j] += weights[(NUM_INPUT * NUM_HIDDEN) + i * NUM_HIDDEN + j] * hidden_1[i];

            // activate hidden_2
            for (int i = 0; i < hidden_2.length; i++)
                hidden_2[i] = sigmoid(hidden_2[i]);

            // populate output
            for (int i = 0; i < NUM_HIDDEN; i++)
                for (int j = 0; j < NUM_OUTPUT; j++)
                    output[j] += weights[(NUM_INPUT * NUM_HIDDEN) + (NUM_HIDDEN * NUM_HIDDEN) + i * NUM_OUTPUT + j] * hidden_2[i];

            // activate output
            for (int i = 0; i < output.length; i++)
                output[i] = sigmoid(output[i]);

            return output;
        }

        System.out.println("ERR: NUM INPUT (" + input.length + ") IS NOT REQ. SIZE (" + NUM_INPUT + ").");
        return null;

    }

    private double sigmoid(double x){
        return (1 / (1 + Math.pow(Math.E, -4.9 * x)));
    }

}
