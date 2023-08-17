package threads;

import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParallelExecutionController {

    private int amountThreads = 10;

    private ExecutorService executorService = Executors.newFixedThreadPool(amountThreads);
    private Elements tegs = null;
    private String path = null;
    private volatile int counter = 0;
    private String url = null;
    public ParallelExecutionController(String url,String path)
    {
        this.url =  url;
        this.path = path;
        Finder finder = new Finder(url);
        tegs = finder.getLinks();
        execute(tegs);
    }
    public void execute (Elements elements)
    {
        System.out.println("Start execute");
        for (Element element:elements) {
            if(counter>=200)
            {
                executorService.shutdown();
                break;
                }

            executorService.execute(()-> {
                int name = 0;
                synchronized (ParallelExecutionController.this) {
                    counter++;
                    name = counter;
                }
                if (name<=200) {
                System.out.println("Start download - "+counter +"    link - " + element.attr("src"));
                Task task = new Task(path,Integer.toString(name),element);
                System.out.println("Finish download - " + name);}});

        }
        if (counter <= 200)
        {
            reFind();
        }
        else executorService.shutdown();
    }

    private void reFind()
    {
        System.out.println("Get next page");
        setNextLink();
        Finder finder = new Finder(url);
        Elements nextTegs = finder.getLinks();
        execute(nextTegs);
    }

    private void setNextLink()
    {
        url = addToLastNumber(url);
    }

    private static String addToLastNumber(String input) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);
        String lastNumber = null;

        while (matcher.find()) {
            lastNumber = matcher.group();
        }

        if (lastNumber != null) {
            int newNumber = Integer.parseInt(lastNumber) + 1;
            return input.replace(lastNumber, String.valueOf(newNumber));
        } else {
            return input; // Не знайдено жодного числа
        }
    }
}

