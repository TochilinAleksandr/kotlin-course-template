import com.sun.org.apache.xml.internal.security.algorithms.implementations.IntegrityHmac
import java.lang.Exception
import javax.swing.text.StyledEditorKit

enum class PhoneType {
    HOME, WORK, PERSONAL
}

enum class MessengerType {
    TELEGRAM, WHATSAPP, VIBER
}

sealed class Contact {
    data class PhoneNumber(
        val number: String,
        val type: PhoneType
    ) : Contact()

    data class Email(val address: String) : Contact() {
        init {
            var atSymbolExist: Boolean = false
            var pointExist: Boolean = false
            for (char in address) {
                if(char == '.' && atSymbolExist) pointExist = true
                if (char == '@')
                    if (!atSymbolExist) atSymbolExist = true
                    else throw Exception("Bad email")
            }
            if(!pointExist) throw Exception("Bad email")
        }
    }

    data class Messenger(
        val id: String,
        val type: MessengerType
    ) : Contact()
}

data class User(
    val firstName: String,
    val lastName: String,
    val age: Int,
    val contactList: MutableList<Contact>
) {

}

fun main() {
    val users: List<User> = listOf(/* TODO: create several users*/)

    println(users)
}

