import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class Finder {
    private Document htmlContent = null;
    Elements links = null;
    public Finder (String link)
    {
        htmlContent = downloader(link);
       if (htmlContent == null)
           throw new RuntimeException("Cant connect");
       links = findLink();
    }
    private Document downloader(String link)
    {

        try {
            // Завантаження HTML-коду з веб-сторінки

            Document document = Jsoup.connect(link).get();

            //String htmlContent = document.html();
            return document;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    private Elements findLink()
    {

        Elements images = htmlContent.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
        return images;
    }

    public Document getHtmlContent() {
        return htmlContent;
    }

    public Elements getLinks() {
        return links;
    }
}
