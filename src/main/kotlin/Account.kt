import java.util.Scanner

class Account {
    var balance: Double = 0.0
    var type: AccountType = AccountType.NONE

    fun showMenu() {
        for (type in AccountType.values()) {
            val menuItem = "${type.ordinal}. ${type.accountName}"

            if (type == AccountType.NONE) {
                println(menuItem)
            } else {
                println("$menuItem account")
            }
        }
    }

    fun getUserAccountType() {
        println("Choose an account type (0, 1 or 2)")

        val userInput = try {
            Scanner(System.`in`).nextInt()
        } catch (e: Throwable) {
            println("That is not a valid selection")
            getUserAccountType()
        }

        type = when (userInput) {
            0 -> AccountType.SAVINGS
            1 -> AccountType.CREDIT
            2 -> AccountType.CHECKING
            3 -> AccountType.NONE
            else -> {
                getUserAccountType()
                AccountType.NONE
            }
        }
    }
}