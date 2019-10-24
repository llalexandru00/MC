package game;

import java.util.ArrayList;

/**
 * Implementation of a strategy using the IDDFS technique
 */
public class IddfsStrategy extends Strategy
{
    /**
     * Main recursive DFS used by the IDDFS strategy
     * @param current
     * The current state in which the DFS resides
     * @param depth
     * The depth of the DFS (of the recursion)
     * @param path
     * A list of the states which were encountered until this current state
     * The solution in case the last state is final
     * @return
     * True if a solution has been found, false otherwise
     */
    private boolean DFS(State current, int depth, ArrayList<State> path)
    {
        path.add(current);

        if (path.get(path.size()-1).isFinal())
        {
            return true;
        }

        if (path.size() - 1 == depth)
        {
            return false;
        }

        ArrayList<State> neighbors = game.getNeighbors(current);
        for (State post : path)
        {
            neighbors.remove(post);
        }

        for (State neighbor : neighbors)
        {
            if (DFS(neighbor, depth+1, path))
            {
                return true;
            }
        }

        path.remove(path.size()-1);
        return false;
    }

    /**
     * Override of the core method
     * @param initial
     * The initial state of the strategy
     * @return
     * The solution of the game
     */
    @Override
    public ArrayList<State> run(State initial)
    {
        ArrayList<State> seq = new ArrayList<>();
        int maxDepth = game.getMaxStates();
        for (int i = 0; i <= maxDepth; i++)
        {
            seq.clear();
            if (DFS(initial, i, seq))
                return seq;
        }
        return null;
    }

    @Override
    public String toString() {
        return "IDDFS";
    }

}
