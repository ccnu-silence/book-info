import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by phnix on 11/17/2014.
 */
public class ISBNSearch {
    public static String search(String isbnString) throws IOException {
        String title = null;
        String searchUrl = "http://www.amazon.cn/s/ref=nb_sb_noss?field-keywords=" + isbnString;
        System.out.println(searchUrl);
        Document document = Jsoup.connect(searchUrl).timeout(10000).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2").get();
        String bookUrl = document.select("#result_0 .a-link-normal.a-text-normal").attr("href");
        title = document.select("#result_0 .a-link-normal.s-access-detail-page.a-text-normal h2").text();
        System.out.println("url:"+bookUrl+";title:"+title);
        return title;
    }
}
