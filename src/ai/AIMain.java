package ai;

import game.component.GameState;
import game.component.entity.Pipe;

import java.util.Random;

/**
 * Created by mccloskeybr on 4/2/16.
 */
public class AIMain {

    private NeuralNetwork[] brains;
    private int[] reward;

    private int indexCurrent;

    public AIMain(){

        brains = new NeuralNetwork[10];
        for (int i = 0; i < brains.length; i++)
            brains[i] = new NeuralNetwork();

        reward = new int[10];

    }

    public void update(){

        GameState game = GameState.getInstance();

        if (game.isGameOver()) {

            reward[indexCurrent] = (int) game.getPlayer().getX() + game.getScore() * 5;

            reproduce();
            GameState.newGame();

        }

        // find closest pipe (will be ind. 0 at start of game, otherwise its 1)
        Pipe pipe;
        if (game.getPipes().get(0).getX() + 100 > game.getPlayer().getX())
            pipe = game.getPipes().get(0);
        else
            pipe = game.getPipes().get(1);

        double[] input = new double[4];
        input[0] = game.getPlayer().getY();
        input[1] = pipe.getX() - game.getPlayer().getX();
        input[2] = pipe.getComponents().get(0).getY() + pipe.getComponents().get(0).getHeight();
        input[3] = pipe.getComponents().get(1).getY();

        double[] output = brains[indexCurrent].forward(input);
        if (output[0] > 0.5)
            game.getPlayer().jump();

    }

    private void reproduce(){

        double mutationRate = 0.05;

        // find parents to toReplace (child)

        int best = 0, secondBest = 1, worst = 2;
        for (int i = 0; i < brains.length; i++){
            if (reward[i] > reward[best] && i != secondBest)
                best = i;
            else if (reward[i] > reward[secondBest] && i != best)
                secondBest = i;
            else if (reward[i] <= reward[worst])
                worst = i;
        }

        if (Math.random() < 0.2) {
            Random r = new Random();
            best = r.nextInt(reward.length);
            secondBest = r.nextInt(reward.length);
        }

        // reproduce

        NeuralNetwork parent1 = brains[best];
        NeuralNetwork parent2 = brains[secondBest];

        double[] childWeights = new double[parent1.getWeights().length];

        int crossOver = new Random().nextInt(parent1.getWeights().length);

        for (int i = 0; i < crossOver; i++) {
            childWeights[i] = parent1.getWeights()[i];

            if (Math.random() < mutationRate)
                childWeights[i] = Math.random() - Math.random();
        }

        for (int i = crossOver; i < childWeights.length; i++) {
            childWeights[i] = parent2.getWeights()[i];

            if (Math.random() < mutationRate)
                childWeights[i] = Math.random() - Math.random();
        }

        brains[worst] = new NeuralNetwork(childWeights);
        indexCurrent = worst;

    }

}
