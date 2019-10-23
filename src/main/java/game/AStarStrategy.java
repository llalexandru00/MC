package game;

import java.util.*;

/**
 * Implementation of the AStar strategy against the game.
 */
public class AStarStrategy extends Strategy
{
    /* Stores a mapping between a state and a number representing the minimum distance from initial to that state */
    private Map<State, Integer> distance = new TreeMap<>();

    /* Stores a map for each state to know where to backtrace the path until the initial state on the minimum path */
    private Map<State, State> from = new TreeMap<>();

    /**
     * The main heuristic function which takes into the account the position of the boat and the number of persons
     * In case of problems with a boat dimension close to the people number, the boat side will count more
     * Otherwise, the number of people still to be transported weight more
     * @param state
     * The state for which the heuristic distance should be computed
     * @return
     * A number representing the heuristic distance
     */
    private int heuristic_distance(State state)
    {
        int persons = state.getCannibals() + state.getMissionaries();
        int left = state.getSide() == State.SIDE.LEFT ? 1 : 0;
        int batch = game.getBoatDimension() - 1;
        int moves = (persons + left) / batch + ((persons + left) % batch == 0 ? 0 : 1);
        moves *= 2;
        moves += left - 1;
        return moves;
    }

    /**
     * The core of the strategy
     * @param initial
     * The initial state of the strategy
     * @return
     * A sequence of states to represent a solution
     */
    @Override
    public ArrayList<State> run(State initial)
    {
        TreeSet<State> heap = new TreeSet<>((state1, state2) ->
        {
            int left = distance.get(state1) + heuristic_distance(state1);
            int right = distance.get(state2) + heuristic_distance(state2);
            if (left == right)
                return state1.compareTo(state2);
            return Integer.compare(left, right);
        });

        from.put(initial, null);
        distance.put(initial, 0);
        heap.add(initial);
        State current = null;
        while (!heap.isEmpty())
        {
            current = heap.pollFirst();
            if (current != null && current.isFinal())
            {
                break;
            }
            int dist = distance.get(current);
            ArrayList<State> neighbors = game.getNeighbors(current);
            State last = from.get(current);
            if (last != null)
            {
                neighbors.remove(last);
            }

            for (State neighbor : neighbors)
            {
                if (!distance.containsKey(neighbor) || distance.get(neighbor) > dist+1)
                {
                    if (distance.containsKey(neighbor))
                    {
                        heap.remove(neighbor);
                    }
                    distance.put(neighbor, dist+1);
                    from.put(neighbor, current);
                    heap.add(neighbor);
                }
            }
        }
        if (current == null || !current.isFinal())
        {
            return null;
        }

        ArrayList<State> seq = new ArrayList<>();
        while (from.get(current) != null)
        {
            seq.add(current);
            current = from.get(current);
        }
        seq.add(initial);
        Collections.reverse(seq);
        return seq;
    }
}