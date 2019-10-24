package game;

import java.util.ArrayList;


/**
 * Implementation of one strategy using backtracking technique
 */
public class BktStrategy extends Strategy
{
    /**
     * Core method used to backtrack the solution
     * @param current
     * The current state in which the backtrack resides
     * @param path
     * The path to {@current}
     * A list of the states which were encountered until this current state
     * The solution in case the last state is final
     * @return
     * True if one solution has been found, false otherwise
     */
    private boolean backtrack(State current, ArrayList<State> path)
    {
        path.add(current);
        if (path.get(path.size()-1).isFinal())
        {
            return true;
        }

        ArrayList<State> neighbors = game.getNeighbors(current);
        for (State post : path)
        {
            neighbors.remove(post);
        }

        for (State neighbor : neighbors)
        {
            if (backtrack(neighbor, path))
            {
                return true;
            }
        }

        path.remove(path.size()-1);
        return false;
    }

    /**
     * The override of the core method
     * @param initial
     * The initial state of the strategy
     * @return
     * An array representing the solution of the game, null if not solution has been found
     */
    @Override
    public ArrayList<State> run(State initial)
    {
        ArrayList<State> seq = new ArrayList<>();
        if (backtrack(initial, seq))
            return seq;
        return null;
    }

    @Override
    public String toString() {
        return "BKT";
    }
}
