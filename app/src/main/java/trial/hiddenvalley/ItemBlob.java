package trial.hiddenvalley;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * This class deals with the "ItemBlob" of shit that we can effectively get from
 * MediaRush. Right now it only gets messages by Brian, because that's the best
 * way we can ever hope of quickly getting content.
 * Created by Grant on 2/7/2016.
 */
public class ItemBlob {

    private static ArrayList<MediaRushItem> cachedList = new ArrayList<MediaRushItem>();

    private ItemBlob(){
    }

    public static ArrayList<MediaRushItem> getAllItems() {
        final SimpleDone done = new SimpleDone();

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {

                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse("http://www.elexioinfinity.com/Admin/Extern/MediaRush/Feeds.aspx?aid=759&tid=19189");
                    //Document doc = dBuilder.parse(fXmlFile);

                    //optional, but recommended
                    //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
                    doc.getDocumentElement().normalize();

                    NodeList nList = doc.getElementsByTagName("item");

                    for (int temp = 0; temp < nList.getLength(); temp++) {

                        Node nNode = nList.item(temp);

                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                            Element eElement = (Element) nNode;

                            MediaRushItem newItem = new MediaRushItem();

                            newItem.title = eElement.getElementsByTagName("title").item(0).getTextContent();

                            newItem.description = eElement.getElementsByTagName("description").item(0).getTextContent();;

                            newItem.guid = eElement.getElementsByTagName("guid").item(0).getTextContent();

                            newItem.itunesAuthor = eElement.getElementsByTagName("itunes:author").item(0).getTextContent();

                            newItem.itunesExplicit = eElement.getElementsByTagName("itunes:explicit").item(0).getTextContent();

                            newItem.link = eElement.getElementsByTagName("link").item(0).getTextContent();;

                            newItem.pubDate = eElement.getElementsByTagName("pubDate").item(0).getTextContent();;

                            cachedList.add(newItem);

                        }
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

                done.done = true;

            }
        });

        thread.start();

        while(!done.done);
        //Just waiting 'n stuff

        return cachedList;

    }

}

