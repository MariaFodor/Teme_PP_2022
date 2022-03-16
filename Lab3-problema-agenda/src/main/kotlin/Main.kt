
import java.awt.Container
import java.util.Scanner
class Birth(val year: Int, val Month: Int, val Day: Int){
    override fun toString() : String{
        return "($Day.$Month.$year)"
    }
}
class Contact(val Name: String, val Phone: String, val BirthDate: Birth){
    fun Print() {
        println("Name: $Name, Mobile: $Phone, Date: $BirthDate")
    }
    fun copy(Name: String = this.Name, Phone: String= this.Phone,BirthDate: Birth= this.BirthDate) = Contact(Name,Phone,BirthDate);
}
fun search(l:  MutableList<Contact>): Contact?{
    val my_Contact:Contact?;
    print("\n->1 = cautare dupa nume\n->2 = cautare dupa numar de telefon");
    var valid: Boolean = false;
    var optiune: Int = 0;
    val reader = Scanner(System.`in`);
    while (valid == false) {
        optiune = reader.nextInt();
        println();
        print("\n->S-a introdus optiunea: $optiune");
        if (optiune == 1 || optiune == 2)//daca datele introduse sunt valide{
            valid = true;
    }
    if (optiune == 1) {
        println("->Introduceti NUMELE cautat:");
        val str: String = readLine()!!;//??
        val my_Contact:Contact? = l.find { it.Name == str };//imi teturneaza contactul
        if (my_Contact != null) {
            println("->A forst gasit contactul!");
            my_Contact.Print();
            return my_Contact;
        } else {
            println("->Nu a putut fi gasit contactul");
        }
    }
    else
    {
        if (optiune == 2)
        {
            println("->Introduceti NUMARUL cautat:");
            val str: String = readLine()!!;
            val my_Contact:Contact? = l.find {it.Phone == str};
            if (my_Contact != null)
            {
                println("->A forst gasit contactul!");
                my_Contact.Print();
                return my_Contact;
            }
            else
            {
                println("->Nu a putut fi gasit contactul");
            }
        }
    }
    val return_not_found:Contact? = null;
    return return_not_found;
}
fun actualizare_numar (my_Contact: Contact?,l: MutableList<Contact>)//returnat de functia de cautare
{
    println("->Introduceti noul numar de telefon:");
    var str : String = readLine()!!;
    var copie_contact : Contact = my_Contact!!.copy(Phone = str);
    var index:Int = l.indexOf(my_Contact);//returneaza index-ul
    l.set(index,copie_contact);
}

fun main(args : Array<String>){
    val agenda = mutableListOf<Contact>()
    agenda.add(Contact("Mihai", "0744321987", Birth(1900, 11, 25)))
    agenda += Contact("George", "0761332100", Birth(2002, 3, 14))
    agenda += Contact("Liviu" , "0231450211", Birth(1999, 7, 30))
    agenda += Contact("Popescu", "0211342787", Birth(1955, 5, 12))
    for (persoana in agenda){
        persoana.Print()
    }
    println("Agenda dupa eliminare contact [George]:")
    agenda.removeAt(1)
    for (persoana in agenda){
        persoana.Print()
    }
    agenda.remove(Contact("Liviu" , "0231450211", Birth(1999, 7, 30)))
    println("Agenda dupa eliminare contact [Liviu]:")
    agenda.removeAt(1)
    for (persoana in agenda){
        persoana.Print()
    }
    search(agenda);
    println("");
    println("->A se introduce obtiunea 2 pt verificare modificare");
    actualizare_numar(search(agenda),agenda);//actualizez numarul dupa ce il caut
    for (persoana in agenda){
        persoana.Print()
    }//printez pt verificare
}