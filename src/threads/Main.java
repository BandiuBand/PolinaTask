package threads;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

       //threads.Downloader downloader = new threads.Downloader("https://upload.wikimedia.org/wikipedia/commons/thumb/4/47/Nissan_Tiida_C11_003.JPG/300px-Nissan_Tiida_C11_003.JPG","c:\\","1");
       //downloader.start();
       Finder finder = new Finder("https://www.gettyimages.co.uk/photos/travel?assettype=image&license=rf&alloweduse=availableforalluses&embeddable=true&family=creative&phrase=travel&sort=best&page=1");
//        System.out.println(finder.getAllLines());
//        try {
//            FileWriter fileWriter = new FileWriter("C:\\2.txt");
//            fileWriter.write(finder.getAllLines());
//            fileWriter.flush();
//            fileWriter.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        String url = "https://www.gettyimages.co.uk/photos/travel?assettype=image&license=rf&alloweduse=availableforalluses&embeddable=true&family=creative&phrase=travel&sort=best&page=1";
        ParallelExecutionController parallelExecutionController = new ParallelExecutionController(url,"c:\\pictures\\");

    }
}