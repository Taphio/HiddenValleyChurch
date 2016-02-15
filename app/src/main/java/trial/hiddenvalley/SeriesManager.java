package trial.hiddenvalley;

import java.util.ArrayList;

/**
 * Gets series from the site.
 * Created by Grant on 2/7/2016.
 */
public class SeriesManager {

    public SeriesManager()
    {

    }

    /**
     * Gets the last num series, or all the series.
     * @param num The number of series to fetch.
     * @return An ArrayList with the Series specified
     */
    public ArrayList<Series> getSeries(int num)
    {

        Thread fetchThread = new Thread(new Runnable() {
            @Override
            public void run() {



            }
        });

        return null;

    }



}
