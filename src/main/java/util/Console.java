package util;

import game.Input;
import game.Strategy;

import java.util.Scanner;

public class Console implements IOManager
{
    private static Scanner scanner;

    public Console()
    {
        scanner = new Scanner(System.in);
    }

    private int readInt(String message)
    {
        System.out.println(message);
        return scanner.nextInt();
    }

    private String readString(String message)
    {
        System.out.println(message);
        return scanner.next();
    }

    // TODO: use loop read method with lambda
    public Input readInput()
    {
        do {
            int missionaries = readInt("Insert the number of missionaries: ");
            int cannibals = readInt("Insert the number of cannibals: ");
            int boatDimension = readInt("Insert the dimension of the boat: ");
            try
            {
                return new Input(missionaries, cannibals, boatDimension);
            }
            catch (ExternalException e)
            {
                writeErr(e.getMessage());
            }
        } while (true);
    }

    public Strategy readStrategy()
    {
        do {
            String strategy = readString("Insert the strategy (RANDOM/BKT/IDDFS): ");
            try
            {
                return new Strategy(strategy);
            }
            catch (ExternalException e)
            {
                writeErr(e.getMessage());
            }
        } while (true);
    }

    public void writeErr(String message)
    {
        System.err.println("[ERROR]" + message);
    }
}
