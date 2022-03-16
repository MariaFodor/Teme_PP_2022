package ro.mike.tuiasi

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.File
class HelloTest {
    var doc: Document = Jsoup.connect("http://rss.cnn.com/rss/edition.rss").get()


}
