
import org.jsoup.nodes.Element;

public class Task {
    private String link = null;

    private Downloader downloader = null;

    public Task (String path,String name,Element tegWidthLink)
    {

        this.link = getLink(tegWidthLink);

        downloader = new Downloader(link,path,name);
        if (downloader.start()==false)
            System.out.println("The picture " + link +" is not download");
    }
    private String getLink (Element element)
    {
        return element.attr("src");
    }

}
