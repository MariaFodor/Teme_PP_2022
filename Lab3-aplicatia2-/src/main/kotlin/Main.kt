import java.io.File
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.util.StringTokenizer

fun insert (dictionar:HashMap<String,String>,cuvant_en:String,cuvant_ro:String)//functia de inserare
{
    dictionar.put(cuvant_en,cuvant_ro);//adauga in dictionar
}
fun Init_file(dictionar:HashMap<String,String>)
{
    var list_of_Strings:List<String> = File("/home/maria/IdeaProjects/Lab3-aplicatia2-/src/date.txt").readLines();
    for(i in 0..(list_of_Strings.size-1)) {
        val line:String =  list_of_Strings[i];
        val st = StringTokenizer(line);
        while (st.hasMoreTokens()) {
            insert(dictionar,st.nextToken(),st.nextToken());
        }
    }
}
fun main(args : Array<String>){
    val Dictionar = hashMapOf<String, String>();
    Init_file(Dictionar);
    /*    "Once" to "Odata",
        "upon" to "ca",
        "a" to "",
        "time" to "niciodata",
        "there" to "acolo",
        "was" to "a fost",
        "an" to "o",
        "old" to "batrana",
        "woman" to "femeie",
        "who" to "care",
        "loved" to "iubea",
        "baking" to "sa gateasca",
        "gingerbread" to "turta dulce",
        "She" to "Ea",
        "would" to "ar fi",
        "bake" to "gatit",
        "gingerbread" to "turta dulce",
        "cookies" to "biscuiti",
        "cakes" to "prajituri",
        "houses" to "case",
        "and" to "si",
        "people" to "oameni",
        "all" to "toti",
        "decorated" to "decorati",
        "with" to "cu",
        "chocolate" to "ciocolata",
        "peppermint" to "menta",
        "caramel" to "caramel",
        "candies" to "bomboane",
        "colored" to "colorate",
        "ingredients" to "ingrediente"
    )*/
    insert(Dictionar,"cradels","carucioare");
    val Poveste = "Once upon a time there was an old woman who loved baking" +
            " gingerbread. She would bake gingerbread cookies, cakes, houses and"+
            " gingerbread people, all decorated with chocolate and peppermint, caramel"+
            " candies and colored ingredients."
    var Poveste_Tradusa : String = "";
    val words1 = Poveste.split(" ")
    println("Cuvintele din poveste [${words1.count()}]:")
    for (word in words1)
        print(word + " ")
    val words2 = mutableListOf<String>()
    for (word in words1){
        words2.add(word.trim(',','.'))
    }
    println("\n")
    println("Povestea tradusa ar suna cam asa:")
    for (item in words2) {
        if (Dictionar.contains(item)) {
            print(Dictionar[item]);
            print(" ");
            var str : String? = Dictionar[item];
            Poveste_Tradusa = Poveste_Tradusa + str + " " ;
        } else {
            print("[$item]");
            print(" ");
        }
    }
    insert(Dictionar,"cradels","carucioare");
    Dictionar.forEach { (key, value) -> println("$key = $value") };//print
    //salvare in fisier a povestilor:
    File("/home/maria/IdeaProjects/Lab3-aplicatia2-/src/fisier.txt").writeText(Poveste_Tradusa);
    val Dictionar1 = hashMapOf<String, String>();
    Init_file(Dictionar1);
    Dictionar1.forEach { (key, value) -> println("$key = $value") };//print
}