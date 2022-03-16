//utilizam File din Java.io pentru a deschide fisierul text
import java.io.File

fun GetUniqueWordCount(all_words : List<String>) : MutableMap<String, Int> {
    //functia pentru calculul cuvintelor unice
    val result = mutableMapOf<String, Int>();
    for(word in all_words)
    {
        if(result.containsKey(word))//daca contine deja cuvantul respectiv
        {
            var aux:Int = result.getValue(word);//scot numarul
            aux = aux + 1;//incrementez nuamrul de aparitii
            result[word] = aux;//actualizam contorul
        }
        else{//trebuie creiat un nou elem
            result.put(word,1);//a fost adaugat noul nod
        }
    }
    return result
}

fun GetUniqueCharCount(all_chars : List<String>) : MutableMap<Char, Int> {
    //functia pentru calculul caracterelor unice
    val result = mutableMapOf<Char, Int>();
    val str = all_chars.joinToString ("");//fara separator
    for(c in str)
    {
        if(result.containsKey(c))//daca contine deja cuvantul respectiv
        {
            var aux:Int = result.getValue(c);//scot numarul
            aux = aux + 1;//incrementez nuamrul de aparitii
            result[c] = aux;//actualizam contorul
        }
        else{//trebuie creiat un nou elem
            result.put(c,1);//a fost adaugat noul nod
        }
    }
    return result
}

fun SortByHitCount(items : MutableMap<Char, Int>, how: Boolean) : MutableMap<Int, Char>{
    //functia de sortare a caracterelor, dupa valoare (frecventa), atat crescator cat si descrescator, in functie de how
    val result = mutableMapOf<Int, Char>()//aici punem ceea ce este sortat
    items.forEach { k, v -> result.put(v,k); }//copiem in ordine inversa elementele
    var ret_map : MutableMap<Int,Char> = mutableMapOf<Int,Char>();//initializare
    if(how == true)//sortare crescatoare
    {
        ret_map = result.toSortedMap(compareBy { it });
    }
    else
    {
        ret_map = result.toSortedMap(compareByDescending { it });//sortare descrescatoare
    }
    return ret_map;
}

//functia main()
fun main(args : Array<String>){
    //citim liniile din fisier
    val lines = File("/home/maria/IdeaProjects/Lab3-P5-GraalVM/src/Fisier.txt").reader().readText()
    //construim un array de cuvinte, seprand prin spatiu
    val words = lines.split(" ")

    //eliminam semnele de punctuatie de pe marginile cuvintelor
    val trim_words = mutableListOf<String>()
    words.forEach {
        val filter:String = it.trim(',','.','"','?', '!')
        val filter_end:String = filter.trimEnd(',','.','"','?', '!');
        trim_words += filter_end.toLowerCase()
        print(filter + " ")
    }
    println("\n")
    print(trim_words);

    //construim o lista cu toate caracterele folosite 'A..Z'
    val chars = mutableListOf<String>()
    trim_words.forEach {
        for (c in it){
            if (c in 'a'..'z' || c in 'A'..'Z') {
                chars += c.toUpperCase().toString()
                print(c.toUpperCase())
            }
        }
    }
    println("\n")

    //Pentru constructia histogramelor, R foloseste un mecanism prin care asociaza caracterelor unice, numarul total de aparitii (frecventa)
    // 1. Construiti in Kotlin acelasi mecanism de masurare a frecventei elementelor unice si afisati cuvintele unice din trim_words
    // 2. Construiti in Kotlin acelasi mecanism de masurare a frecventei elementelor unice si afisati caracterele unice din chars
    // 3. Pentru frecventele caracterelor unice caclulate anterior si
    //      A. Afisati perechile (frecventa -> Caracter) sortate crescator si descrescator
    //      B. afisati graficele variatiei de frecventa sortate anterior crescator si descrescator si concatenati-le intr-un grafic de puncte

    //construim histograma pentru cuvinte
    RHistogram.BuildHistogram(trim_words.toTypedArray(), "Words", true)
    //construim histograma pentru caractere
    RHistogram.BuildHistogram(chars.toTypedArray(), "Chars", false)
    //prima functie
    var mapa_words : MutableMap<String,Int>;
    mapa_words = GetUniqueWordCount(trim_words);
    println("->Cuvintele mapate sunt:")
    mapa_words.forEach { k, v -> println("$k = $v");  }//afisare
    //a 2-a functie
    var mapa_char : MutableMap<Char,Int>;
    mapa_char = GetUniqueCharCount(chars);
    println("->Caracterele mapate sunt:")
    mapa_char.forEach { k, v -> println("$k = $v");  }//afisare*/
    //a 3-a functie
    var my_sotred_map_c = SortByHitCount(mapa_char,true);
    println("->Mapa sorata crescator:")
    my_sotred_map_c.forEach { k, v -> println("$k = $v");  }//afisare crescatoare

    var my_sotred_map_d = SortByHitCount(mapa_char,false);
    println("->Mapa sorata descrescator:")
    my_sotred_map_d.forEach { k, v -> println("$k = $v");  }//afisare DEScrescatoare

    val lista_grafic1 : List<Pair<Int,Char>> = (my_sotred_map_c.toList());//transform prima mapare inn lista
    val lista_grafic2 : List<Pair<Int,Char>> = (my_sotred_map_d.toList());
    val lista_finala = lista_grafic1 + lista_grafic2;
    println(lista_finala);//FINALY!
    val firstList = ArrayList<Int>()
    lista_finala.forEach { firstList.add(it.first) }//facem doar o lista cu int-urile
    RHistogram.BuildHistogram(firstList.toTypedArray(), "Chars2", false);
}