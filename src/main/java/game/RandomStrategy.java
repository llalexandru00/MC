package game;

import java.util.ArrayList;
import java.util.Random;

/**
 * An implementation of the strategy abstract class, based on the random technique
 */
public class RandomStrategy extends Strategy
{
    /** The iteration limit for the random generation of the solution*/
    private final static int ITERATION_LIMIT = 1000;

    /** A random number provider */
    private Random rand = new Random();

    /**
     * The main override of the core method
     * @param initial
     * The initial state of the strategy
     * @return
     * A solution to the problem
     */
    @Override
    public ArrayList<State> run(State initial)
    {
        while (true)
        {
            int iterations = 0;
            ArrayList<State> seq = new ArrayList<>();
            State state = initial;
            seq.add(initial);
            while (!state.isFinal() && iterations < ITERATION_LIMIT)
            {
                ArrayList<State> neighbors = game.getNeighbors(state);
                for (State post : seq)
                {
                    neighbors.remove(post);
                }

                if (neighbors.size() == 0)
                {
                    break;
                }

                int index = rand.nextInt(neighbors.size());
                state = neighbors.get(index);

                seq.add(state);
                iterations++;
            }
            if (state.isFinal())
            {
                return seq;
            }
        }
    }
}
