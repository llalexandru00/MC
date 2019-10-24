package statistic;

import game.Configuration;
import game.Input;
import game.Strategy;
import javafx.util.Pair;
import util.IOManager;

import java.util.ArrayList;
import java.util.TreeMap;

public class StatisticViewer {

    private static final int COL_SIZE = 12;

    private ArrayList<Input> rows;
    private ArrayList<Strategy> cols;

    private TreeMap<Input, TreeMap<Strategy, Pair> > map = new TreeMap<>();

    public StatisticViewer(ArrayList<Input> rows, ArrayList<Strategy> cols)
    {
        this.rows = rows;
        this.cols = cols;

        for (Input row : rows)
            map.put(row, new TreeMap<>());
    }

    public void add(Result result)
    {
        Configuration config = result.getConfiguration();
        Strategy strategy = config.getStrategy();
        Input input = config.getInput();
        long runTime = result.getRunTime();
        if (result.isTimedOut())
            runTime = -1;

        map.get(input).put(strategy, new Pair<>(result.getSolutionSize(), runTime));
    }

    private void displayPadded(IOManager io, String text)
    {
        for (int i=0; i<COL_SIZE-text.length(); i++)
            io.writeRaw(" ");
        io.writeRaw(text);
    }

    private void displayHeader(IOManager io)
    {
        displayPadded(io, "MIS/CAN/BOAT");
        for (Strategy col : cols)
        {
            displayPadded(io, col.toString());
        }
        io.write("");
    }

    public void display(IOManager io) {
        displayHeader(io);
        for (Input row : rows)
        {
            displayPadded(io, row.toString());
            TreeMap<Strategy, Pair> localMap = map.get(row);
            for (Strategy strategy : cols)
            {
                Pair cell = localMap.get(strategy);
                String solution = cell.getKey().toString();
                String time = cell.getValue().toString();
                if (time.equals("-1"))
                {
                    displayPadded(io,  "TIMED-OUT");
                    continue;
                }
                displayPadded(io,  solution + "|" + time);
            }
            io.write("");
        }
    }
}
