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
    val user1 = User("Александр", "Точилин", 21, mutableListOf(
        Contact.PhoneNumber("+79213425612", PhoneType.PERSONAL),
        Contact.Email("TochilinA@mail.ru"),
        Contact.Messenger("374127213", MessengerType.TELEGRAM)
    ))
    val user2 = User("Валерий", "Кузнецов", 23, mutableListOf(
        Contact.PhoneNumber("+79511628735", PhoneType.WORK),
        Contact.Email("Valera1998@bk.ru"),
        Contact.Messenger("111333222", MessengerType.WHATSAPP)
    ))
    val user3 = User("Даниил", "Квят", 27, mutableListOf(
        Contact.PhoneNumber("4761631", PhoneType.HOME),
        Contact.Email("Kvyat@gmail.com"),
        Contact.Messenger("12312", MessengerType.VIBER),
        Contact.Messenger("9784351", MessengerType.TELEGRAM)
    ))
    val users: List<User> = listOf(user1, user2, user3)

    println(users)
}

