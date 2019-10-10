package game;

import util.ExternalException;

public class Input
{

    private int missionaries, cannibals, boatDimension;

    public Input(int missionaries, int cannibals, int boatDimension)
    {
        this.missionaries = missionaries;
        this.cannibals = cannibals;
        this.boatDimension = boatDimension;
        validate();
    }

    private void validate()
    {
        if (this.missionaries < 0 || this.cannibals < 0 || this.boatDimension <= 0)
            throw new ExternalException("The numbers inserted should be positive.");
        if (this.missionaries < this.cannibals)
            throw new ExternalException("The number of missionaries is lower than the number of cannibals.");
    }
}
