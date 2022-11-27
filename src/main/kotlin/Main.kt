import kotlin.system.exitProcess

fun main() {
    greetUser()

    // Request user PIN
    simulateUserEnterPin()

    // Create account and set account type and balance based on user input
    val account = Account()
    account.showMenu()

    account.getUserAccountType()

    // Exit program if user selects "Exit"
    if (account.type == AccountType.NONE) endProgram() else println("You have created a ${account.type.accountName} account")

    while(true){
        // Perform transaction based on user input
        val transaction = Transaction(account = account)
        transaction.displayBalance(account.balance)

        transaction.showMenu()
        val transactionType = transaction.getUserTransactionType()

        if (transactionType == TransactionType.NONE) endProgram() // End program if user selects "Exit"

        account.balance = transaction.process(transactionType = transactionType)

        // Check if user wants to perform another transaction
        println("Would you like to perform another transaction? (Press N to exit or any key to continue)")
        when(readLine()) {
            "N", "n" -> endProgram()
            else -> continue
        }
    }
}
private fun greetUser() {
    // Print CACTUS all pretty like
    println(
        " ██████  █████   ██████ ████████ ██    ██ ███████ \n" +
                "██      ██   ██ ██         ██    ██    ██ ██      \n" +
                "██      ███████ ██         ██    ██    ██ ███████ \n" +
                "██      ██   ██ ██         ██    ██    ██      ██ \n" +
                " ██████ ██   ██  ██████    ██     ██████  ███████ \n" +
                "                                                  \n"
    )
    println("           Welcome to Cactus Bank!")
    println()
}

private fun simulateUserEnterPin() {
    println("Enter your PIN:")
    Thread.sleep(1_000)
    print("*")
    Thread.sleep(1_000)
    print("*")
    Thread.sleep(1_250)
    print("*")
    Thread.sleep(1_000)
    println("*")
    Thread.sleep(2_000)
    println()
}

private fun endProgram() {
    println("Thank you for banking with us!")
    exitProcess(status = 0)
}


