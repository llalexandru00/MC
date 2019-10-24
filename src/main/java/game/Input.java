package game;

import util.ExternalException;

/**
 * A container for the numeric parameters
 */
public class Input implements Comparable<Input>
{
    /** The number of missionaries */
    private final int missionaries;

    /** The number of cannibals */
    private final int cannibals;

    /** The dimension of the boat */
    private final int boatDimension;

    /**
     * Basic constructor used to initialize the input's fields. It also validates the input.
     * @param missionaries
     * The number of missionaries
     * @param cannibals
     * The number of cannibals
     * @param boatDimension
     * The dimension of the boat
     */
    public Input(int missionaries, int cannibals, int boatDimension)
    {
        this.missionaries = missionaries;
        this.cannibals = cannibals;
        this.boatDimension = boatDimension;
        validate();
    }

    /**
     * Input validator
     */
    private void validate()
    {
        if (this.missionaries < 0 || this.cannibals < 0 || this.boatDimension <= 1)
        {
            throw new ExternalException("The numbers inserted should be positive. The boat size should be at least 2.");
        }
        if (this.missionaries < this.cannibals)
        {
            throw new ExternalException("The number of missionaries is lower than the number of cannibals.");
        }
    }

    /**
     * Basic getter for the number of missionaries
     * @return
     * The number of missionaries
     */
    int getMissionaries()
    {
        return missionaries;
    }

    /**
     * Basic getter for the number of cannibals
     * @return
     * The number of cannibals
     */
    int getCannibals()
    {
        return cannibals;
    }

    /**
     * Basic getter for the dimension of the boat
     * @return
     * The dimension of the boat
     */
    int getBoatDimension() {
        return boatDimension;
    }


    /**
     * Basic toString override
     * @return
     * A string describing this input
     */
    public String toString()
    {
        return missionaries + "/" + cannibals + "/" + boatDimension;
    }

    /**
     * Override of the compareTo method imposed by the Comparable interface
     * @param input
     * The operand meant to be used as compare target
     * @return
     * Basic return of a compareTo method
     */
    @Override
    public int compareTo(Input input) {
        if (missionaries == input.missionaries && cannibals == input.cannibals && boatDimension == input.boatDimension)
            return 0;
        if (missionaries < input.missionaries ||
            missionaries == input.missionaries && cannibals < input.cannibals ||
            missionaries == input.missionaries && cannibals == input.cannibals && boatDimension < input.boatDimension)
            return -1;
        return 1;
    }
}
