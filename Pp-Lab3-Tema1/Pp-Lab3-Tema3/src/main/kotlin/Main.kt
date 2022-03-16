import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException
import java.util.*
import java.io.File

val coada: Queue<TreeNode> = LinkedList<TreeNode>()

class TreeNode(val value: String) {
    private val children: MutableList<TreeNode> = mutableListOf()

    fun add(child: TreeNode) = children.add(child)
    fun size() = children.size

    fun printChildren(str : String) {
        print(str+": ")
        children.forEach{
            print("${it.value}; ")
        }
        println()
    }


    fun serializeTree() {

        coada.add(this)
        var x = coada.poll()
        x.printChildren(x.value)//afisez copiii radacinii

        x.children.forEach {
            coada.add(it)//adaug in coada copiii radacinei
        }
        while(coada.size != 0) {
            var y = coada.poll()//scot pe rand din coada copiii radacinei
            y.printChildren(x.value+y.value)//afisez copiii copiiilor radacinei(frunzele) si transmit ca parametru path-ul pana la frunze
        }
    }


}

fun deserializeTree(fileName : String):  TreeNode{
    val lines: List<String> = File(fileName).readLines()
    val delim1 = "/"
    var delim2 = ":"
    var delim3 = ";"

    var tokens : List<String> = lines[0].split(delim1,delim2,delim3)
    var tokens2 : List<String>
    //index[0] = http, index[1] = index[2] = gol(avem //), index[3] = nume domeniu, index[4] = gol, index[5] = link-uri fii
    var y : TreeNode
    var z : TreeNode
    var x = TreeNode(tokens[3])
    for(index in tokens.indices)
    {
        if(index > 4 && index < tokens.size - 1)//dupa 4 gasesc noduri
        {
            y = TreeNode(tokens[index])
            tokens2 = lines[index-4].split(delim1,delim2,delim3)//ma duc pe linia unde am copiii nodului y
            for(index2 in tokens2.indices)//parcurg copii nodului y
            {
                if(index2 > 4)//acum pe index[4] nu mai este gol pentru ca nu mai avem "/:"
                {
                    z = TreeNode(tokens2[index2])
                    y.add(z)//copiii nodului y in subarborele sau
                }
            }
            x.add(y)
        }
    }

    return x
}


fun main(args: Array<String>) {
    val document: Document
    var document2 : Document
    val url : String = "http://mike.tuiasi.ro/"
    try {
        document = Jsoup.connect(url).get()
        val tree = TreeNode(url)
        val links = document.select("a[href]")
        var node : TreeNode
        var nodechild : TreeNode

        for (link in links) {
            //daca link-ul actual nu ieses de pe pagina atunci il adaug in arbore
            if(link.attr("abs:href").toString().contains(url))
            {
                node = TreeNode(link.text())
                tree.add(node)

                document2 = Jsoup.connect(link.attr("abs:href").toString()).get()
                var links2 = document2.select("a[href]")

                for(link2 in links2)
                {
                    //daca link-ul actual nu iese din pagina il adaug in arbore
                    if(link.attr("abs:href").toString().contains(url))
                    {
                        nodechild = TreeNode(link2.text())
                        node.add(nodechild)
                    }

                }
            }
        }

        tree.serializeTree()
        println("*".repeat(100))
        println("*".repeat(100))
        val fileName = "in.txt"
        var tree2 : TreeNode
        tree2 = deserializeTree(fileName)
        tree2.serializeTree()
    } catch (e: IOException) {
        e.printStackTrace()
    }
}