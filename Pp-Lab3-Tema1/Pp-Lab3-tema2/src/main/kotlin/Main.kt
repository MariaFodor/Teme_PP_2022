import java.io.File
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.util.StringTokenizer
fun printAllRegexMatches(regex: Regex, searchString: String) {
    for(item in regex.findAll(searchString)) {
        println(item.value)
    }
    println("-".repeat(100))
}
fun main(args: Array<String>) {
    println("Hello World!")
    val pgRegex = Regex("        [1-9]?[0-9]?[0-9]           ");//pattern pentru numarul paginii
    val mutiple_new_line = Regex("\n\n+");//banuim si noi ca mai multe de atat nu intalnim :>
    val multiple_space = Regex("\\s{2,}");//pentru doua sau mai multe spatii
    //val titlu_capitol = Regex("            [-a-zA-Z0-9]{2,256}                  ")
    var text:String = File("/home/maria/IdeaProjects/Pp-Lab3-Tema1/Pp-Lab3-tema2/src/Ebook.txt").readText();

    //find all matches
    for(regex in listOf(pgRegex,multiple_space,mutiple_new_line)){
        printAllRegexMatches(regex, text);
    }
    //eliminarile cerute
    text = pgRegex.replace(text, "");
    text = mutiple_new_line.replace(text,"\n");
    text = multiple_space.replace(text," ");
    //text = titlu_capitol.replace(text,"");
    File("/home/maria/IdeaProjects/Pp-Lab3-Tema1/Pp-Lab3-tema2/src/Ebook.txt").writeText(text);

}