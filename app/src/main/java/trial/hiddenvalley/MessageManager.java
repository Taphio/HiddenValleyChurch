package trial.hiddenvalley;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Grant on 2/7/2016.
 */
public class MessageManager {

    private static ArrayList<Message> cacheList = new ArrayList<Message>();

    private MessageManager(){};

    /**
     * Gets the last num messages
     * @param //num The number of messages to get
     */
    public static Message getMessage(int num)
    {

        return cacheList.get(cacheList.size() - num - 1);

    }

    public static void init()
    {
        convertAll();
    }

    public static int getCount()
    {
        return cacheList.size();
    }

    /**
     * Gets all of the items from XML and converts them to Message objects.
     */
    private static void convertAll()
    {


        ArrayList<MediaRushItem> allItems = ItemBlob.getAllItems();

        for(int i = 0; i < allItems.size(); i++)
        {
            MediaRushItem item = allItems.get(i);

            String realTitle = item.title.substring(0, item.title.indexOf("-")).trim();

            Message temp;

            Message message;

            if((temp = FindWithTitle(realTitle)) != null)
            {
                message = temp;
            }
            else {

                message = new Message();

                message.title = realTitle;

                message.description = item.description;

                message.speaker = item.itunesAuthor;

                long date = Date.parse(item.pubDate);

                Calendar cal = new GregorianCalendar();

                cal.setTimeInMillis(date);

                message.year = cal.get(Calendar.YEAR);

                message.month = cal.get(Calendar.MONTH) + 1;

                message.day = cal.get(Calendar.DAY_OF_MONTH);

            }


            if(item.guid.endsWith(".mp4"))
            {
                //This is a video
                message.videoURL = item.guid;
            }
            else if(item.guid.endsWith(".mp3"))
            {
                //This is audio
                message.audioURL = item.guid;
            }
            else if(item.guid.endsWith("pdf"))
            {
                //This is the notes
                message.notesURL = item.guid;
            }

            if(temp == null)
                cacheList.add(message);

        }


    }

    public static Message FindWithTitle(String title)
    {

        for(int i = 0; i < cacheList.size(); i++)
        {
            if(cacheList.get(i).title.equals(title))
                return cacheList.get(i);
        }

        return null;
    }


}
