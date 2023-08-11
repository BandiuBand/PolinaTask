import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Finder {
    private String allLines = null;
    public Finder (String link)
    {
       if (!downloader(link))
           throw new RuntimeException("Cant connect");
    }
    private boolean downloader(String link)
    {
//        try {
//            URL website = new URL(link);
//            HttpURLConnection connection = (HttpURLConnection) website.openConnection();
//
//            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String inputLine;
//            StringBuilder htmlContent = new StringBuilder();
//
//            while ((inputLine = in.readLine()) != null) {
//                htmlContent.append(inputLine);
//            }
//
//            in.close();
//
//            // Виводимо вміст на консоль (або можна зберегти у файл)
//            allLines = htmlContent.toString();
//            return true;
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            URL url = new URL(link);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            int responseCode = httpURLConnection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK)
            {
                InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder sb=new StringBuilder();
                while (bufferedReader.ready()) {
                    sb.append(bufferedReader.readLine()).append("\n");
                }
                allLines=sb.toString();
                return true;
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return false;
    }

    public String getAllLines() {
        return allLines;
    }
}
