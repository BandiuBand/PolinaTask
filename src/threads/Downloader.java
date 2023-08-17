package threads;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*
This class download img from path
 */
public class Downloader {
    private String urlLink = null;
    private String path = null;
    private String fileName = null;
    private String fullName = null;

    public Downloader (String url,String path,String fileName)
    {
        this.urlLink=url;
        this.path=path;
        this.fileName=fileName;
        this.fullName=path+"\\"+fileName+".jpg";
    }
    public boolean start()
    {
        try {
            URL url = new URL(urlLink);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK)
            {
                byte[] buffer = new byte[2048];
                int byteRead = 0;
                InputStream inputStream = httpURLConnection.getInputStream();
                FileOutputStream fileOutputStream = new FileOutputStream(fullName);
                while ((byteRead = inputStream.read(buffer))!=-1)
                {
                    fileOutputStream.write(buffer,0,byteRead);
                }
                return true;
            }
            else return false;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
        e.printStackTrace();
        }
        return false;
    }

}
