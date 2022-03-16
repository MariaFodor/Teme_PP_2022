package ro.mike.tuiasi
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.io.File
class site_data{
    var list_of_items:List<item> = emptyList();//lista de iteme initializate
    var title_site : String = "";
    var link_site : String = "";
    var description_site :String = "";
    //var pubDate_site : String = "";
    class site_data constructor(title_site:String,list_of_items:List<item>);
}

class item {
    class item constructor(title:String,link:String,descriptio:String,pubdate:String);
    var title : String = "";
    var link : String = "";
    var description :String = "";
    var pub_Date : String = "";
    fun prit_item (){
        println("->Item:")
        println(title);
        println(link);
        println(description);
        println(pub_Date)
        println("");
    }
}
fun main(args: Array<String>) {
    var siteData = site_data();
    var doc: Document = Jsoup.connect("http://rss.cnn.com/rss/edition.rss").get()
    //var newsHeadlines:Elements = doc.select("*")


    var etc : Elements =  doc.getElementsByTag("link")
    siteData.link_site = etc[0].html();
    //println(siteData.link_site);

    var titles:Elements = doc.getElementsByTag("title");
    siteData.title_site = titles[0].data();//am incarcat titlul
    //println(siteData.title_site);

    etc= doc.getElementsByTag("description")
    siteData.description_site = etc[0].data();
    //println(siteData.description_site);

    var newsHeadlines: Elements = doc.getElementsByTag("item")
    //println(newsHeadlines)//am incarcat titlurile de articole

    var k: Int = newsHeadlines.size;
    //var newsHeadlines: String = doc.child(0).child(0).child(16).child(0).data()
    for(i in 14..k-1){//parcurg titlurile
        var aux = item();//variabila cu care vom adauga

        aux.pub_Date = doc.child(0).child(0).child(i).getElementsByTag("pubDate").text()

        aux.title =doc.child(0).child(0).child(i).getElementsByTag("title").text();

        aux.link = doc.child(0).child(0).child(i).getElementsByTag("link").html();

        aux.description = doc.child(0).child(0).child(i).getElementsByTag("description").text();

        aux.prit_item();//cerinta cu afisarea este pusa aiaici daca tot
    }
    var lista : List<String> = listOf(String());

    //println("Hello, World")
}

