package game;

import util.InternalException;
import java.util.ArrayList;

/**
 * Class responsible for applying a strategy to the mentioned game. It also provides utilities for state chaining.
 */
public class Game
{
    /** The number of missionaries */
    private int missionaries;

    /** The number of cannibals */
    private int cannibals;

    /** The dimension of the boat */
    private int boatDimension;

    /** The strategy used for the game */
    private Strategy strategy;

    /**
     * Constructor used for initializing game's variables
     * @param config
     * The provider for the information
     */
    public Game(Configuration config)
    {
        if (config == null)
            throw new InternalException("Configuration is null, can't start game");

        Input input = config.getInput();
        missionaries = input.getMissionaries();
        cannibals = input.getCannibals();
        boatDimension = input.getBoatDimension();
        strategy = config.getStrategy();
    }

    /**
     * The core method used for solving the game with specified strategy
     * @return
     * A solution to the game
     */
    public ArrayList<State> solve()
    {
        State initial = getInitialState();
        strategy.useGame(this);
        return strategy.run(initial);
    }

    /**
     * Getter for the initial state which has full missionaries and cannibals ont he right side
     * @return
     * The initial state of the game
     */
    private State getInitialState()
    {
        return new State(missionaries, cannibals, State.SIDE.RIGHT);
    }

    /**
     * Getter for a list of states which are accessible from {@state}
     * @param state
     * The state for which the neighbor list should be computed
     * @return
     * The list of neighbors for the specified state
     */
    ArrayList<State> getNeighbors(State state) {

        int currentMis = state.getSide().equals(State.SIDE.RIGHT) ?
                state.getMissionaries() : missionaries - state.getMissionaries();
        int currentCan = state.getSide().equals(State.SIDE.RIGHT) ?
                state.getCannibals() : cannibals - state.getCannibals();
        ArrayList<State> seq = new ArrayList<>();

        for (int i = 0; i <= currentMis; i++)
        {
            for (int j = 0; j <= currentCan; j++)
            {
                if (validate(state, i, j))
                {
                    State neighbor = transition(state, i, j);
                    seq.add(neighbor);
                }
            }
        }
        return seq;
    }

    /**
     * Obtains a neighbor state based on a given current state. For the purpose of the game,
     * one transition should be validated beforehand.
     * @param state
     * The current state
     * @param mis
     * The number of missionaries to join the boat
     * @param can
     * The number of cannibals to join the boat
     * @return
     * A new state obtained from the current one using the transformations specified
     */
    private State transition(State state, int mis, int can)
    {
        if (state.getSide().equals(State.SIDE.RIGHT))
            return new State(state.getMissionaries() - mis,
                    state.getCannibals() - can, state.getOtherSide());
        else
            return new State(state.getMissionaries() + mis,
                    state.getCannibals() + can, state.getOtherSide());
    }

    /**
     * Validation function for one resulting state after applying some transformations to the current one
     * @param state
     * The current state
     * @param mis
     * The number of missionaries to join the boat
     * @param can
     * The number of cannibals to join the boat
     * @return
     * True if the resulting state is valid, false otherwise
     */
    private boolean validate(State state, int mis, int can)
    {
        // Validation against boat restrictions
        if (can + mis > boatDimension || mis < 0 || can < 0 || mis + can == 0)
            return false;

        // Validation against the number o missionaries and cannibals in the boat
        if (can > mis && mis > 0)
            return false;

        int rightMis = state.getMissionaries();
        int rightCan = state.getCannibals();
        int leftMis = missionaries - state.getMissionaries();
        int leftCan = cannibals - state.getCannibals();

        if (state.getSide().equals(State.SIDE.RIGHT))
        {
            rightMis -= mis;
            rightCan -= can;
            leftMis += mis;
            leftCan += can;
        }
        else
        {
            rightMis += mis;
            rightCan += can;
            leftMis -= mis;
            leftCan -= can;
        }

        return (rightMis >= rightCan || rightMis == 0) && (leftMis >= leftCan || leftMis == 0);
    }

    /**
     * Getter for the maximum number of possible states (not necessary valid)
     * @return
     * The number of possible states
     */
    int getMaxStates()
    {
        return missionaries*cannibals*2;
    }
}
