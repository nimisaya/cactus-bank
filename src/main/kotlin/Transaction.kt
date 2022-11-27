import java.util.Scanner
import kotlin.math.sign

class Transaction(private val account: Account) {
    private val hasSufficientFunds: Boolean = account.balance > 0.0

    fun displayBalance(balance: Double) {
        println("The current balance is $$balance")
        println()
    }

    fun showMenu() {
        for (type in TransactionType.values()) {
            if (type == TransactionType.WITHDRAW && !hasSufficientFunds) {
                continue
            } else {
                println("${type.ordinal}. ${type.transactionName}")
            }
        }
    }

    fun getUserTransactionType() : TransactionType {
        print("Choose a transaction type (")

        for(type in TransactionType.values()) {
            if (type == TransactionType.WITHDRAW && !hasSufficientFunds) {
                continue
            } else {
                print(type.ordinal)
                if (type.ordinal < TransactionType.values().size - 1) {
                    print(", ")
                }
            }
        }
        println(")")

        val userInput = try {
            Scanner(System.`in`).nextInt()
        } catch (e: Throwable) {
            println("That is not a valid selection")
            getUserTransactionType()
        }

        val transactionType = when (userInput) {
            0 -> TransactionType.WITHDRAW
            1 -> TransactionType.DEPOSIT
            2 -> TransactionType.BALANCE
            3 -> TransactionType.NONE
            else -> {
                getUserTransactionType()
            }
        }
        return transactionType
    }

    fun process(transactionType: TransactionType) =
        when (transactionType) {
            TransactionType.WITHDRAW -> withdraw(account.balance)
            TransactionType.BALANCE -> {
                displayBalance(account.balance)
                account.balance
            }
            TransactionType.DEPOSIT -> deposit(account.balance)
            else -> account.balance
        }

    private fun getAmount() : Double {
        val amount = try {
            println("Please enter amount:")
            Scanner(System.`in`).nextDouble()
        } catch (e: Throwable) {
            println("That is not a valid amount")
            getAmount()
        }
        if ( sign(amount)  == -1.0 || amount < 0.0  || amount == -0.0 ) {
            println("That is not a valid amount")
            getAmount()
        } else if (amount > 1_000_000_000_000){
            println("$ $ $ $ Sorry Richie Rich, we can't afford to do business $ $ $ $")
            getAmount()
        }

        return amount
    }

    private fun withdraw(accountBalance: Double) : Double {
        var amount = getAmount()

        if (amount > accountBalance) {
            println("You have insufficient funds in your account")
            displayBalance(accountBalance)
            amount = getAmount()
        }

        val newAccountBalance = accountBalance - amount
        println("$amount has been withdrawn from your account")
        displayBalance(newAccountBalance)
        return newAccountBalance
    }

    private fun deposit(accountBalance: Double) : Double {
        val amount = getAmount()
        val newAccountBalance = accountBalance + amount
        println("$amount has been deposited into your account")
        displayBalance(newAccountBalance)
        return newAccountBalance
    }
}

