enum class TransactionType(val transactionName: String) {
    WITHDRAW(transactionName = "Withdraw"),
    DEPOSIT(transactionName = "Deposit"),
    BALANCE(transactionName = "Balance"),
    NONE(transactionName = "Exit");
}

