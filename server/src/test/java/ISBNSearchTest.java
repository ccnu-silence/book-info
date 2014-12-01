import com.pera.entity.Book;
import org.junit.Assert;

import static org.junit.Assert.*;

public class ISBNSearchTest {

    @org.junit.Test
    public void testSearch() throws Exception {
//        Book book = ISBNSearch.search("9787540468798");
        Book book = ISBNSearch.search("9787115281609");
        System.out.println(book);
//        Assert.a

//        String bookUrl = document.select("#result_0 .a-link-normal.a-text-normal").attr("href");
//        title = document.select("#result_0 .a-link-normal.s-access-detail-page.a-text-normal h2").text();
//        System.out.println("url:"+bookUrl+";title:"+title);
    }
}