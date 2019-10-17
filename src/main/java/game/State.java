package game;

/**
 * The description of one single state of the game in a moment of time
 */
public class State
{

    /**
     * A basic enum describing the possible states of the boat
     */
    enum SIDE
    {
        RIGHT, LEFT
    }

    /** The number of missionaries on the right side of the river */
    private int missionaries;

    /** The number of cannibals on the right side of the river*/
    private int cannibals;

    /** The side on which the boat floats */
    private SIDE side;

    /**
     * The basic constructor used to initialize the state's variables
     * @param missionaries
     * The number of missionaries
     * @param cannibals
     * The number of cannibals
     * @param side
     * The side on which the boat floats
     */
    State(int missionaries, int cannibals, SIDE side)
    {
        this.missionaries = missionaries;
        this.cannibals = cannibals;
        this.side = side;
    }

    /**
     * Simple getter for the number of missionaries
     * @return
     * The number of missionaries
     */
    int getMissionaries()
    {
        return missionaries;
    }

    /**
     * Simple getter for the number of cannibals
     * @return
     * The number of cannibals
     */
    int getCannibals()
    {
        return cannibals;
    }

    /**
     * Decide if the state is final
     * @return
     * True if the state is considered to be final, else otherwise
     */
    boolean isFinal()
    {
        return missionaries == 0 && cannibals == 0;
    }

    /**
     * Simple getter for the side
     * @return
     * The side on which the boat floats
     */
    SIDE getSide()
    {
        return side;
    }

    /**
     * Get the other side from the current one
     * @return
     * If the boat floats on the right, return left, otherwise right
     */
    SIDE getOtherSide()
    {
        if (side == SIDE.RIGHT)
            return SIDE.LEFT;
        return SIDE.RIGHT;
    }

    /**
     * Override of the object equals method
     * @param obj
     * To object used as comparator
     * @return
     * True if the states are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof State)
        {
            State state = (State) obj;
            return missionaries == state.missionaries && cannibals == state.cannibals && side.equals(state.side);
        }
        return false;
    }

    /**
     * Override the object toString method
     * @return
     * A string describing the current state
     */
    @Override
    public String toString()
    {
        return "(right-missionaries: " +
                missionaries +
                ", right-cannibals: " +
                cannibals +
                ", boat-side: " +
                side +
                ")";
    }
}
