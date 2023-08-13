
import org.jsoup.nodes.Element;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Task {
    private String link = null;

    private Downloader downloader = null;
    Element tegWidthLink = null;
    String name = null;
    String path = null;


    public Task (String path,String name,Element tegWidthLink)
    {

        this.link = getLink(tegWidthLink);
        this.tegWidthLink = tegWidthLink;
        this.name = name;
        this.path = path;
        downloader = new Downloader(link,path,name);
        logTeg();
        //System.out.println(tegWidthLink.toString());
        if (downloader.start()==false)
            System.out.println("The picture " + link +" is not download");
    }
    private String getLink (Element element)
    {
        return element.attr("src");
    }
    private void logTeg ()
    {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path+name+".txt"))){
                bufferedWriter.write(tegWidthLink.toString());
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException ex) {
            throw new RuntimeException(ex);

    }
    }
}
