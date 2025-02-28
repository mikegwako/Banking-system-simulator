class BankAccount(val accountNumber: String, val owner: String) {
    private var balance: Double = 0.0
    private val transactions = mutableListOf<String>()

    fun deposit(amount: Double) {
        if (amount > 0) {
            balance += amount
            transactions.add("Deposited: $$amount | New Balance: $$balance")
            println("Deposit successful! New Balance: $$balance")
        } else {
            println("Invalid deposit amount.")
        }
    }

    fun withdraw(amount: Double) {
        if (amount > 0 && amount <= balance) {
            balance -= amount
            transactions.add("Withdrawn: $$amount | New Balance: $$balance")
            println("Withdrawal successful! New Balance: $$balance")
        } else {
            println("Invalid withdrawal amount or insufficient funds.")
        }
    }

    fun checkBalance() {
        println("Account Balance: $$balance")
    }

    fun transactionHistory() {
        if (transactions.isEmpty()) {
            println("No transactions available.")
        } else {
            println("Transaction History:")
            transactions.forEach { println(it) }
        }
    }
}

fun main() {
    val accounts = mutableMapOf<String, BankAccount>()

    while (true) {
        println("\n--- Simple Banking System ---")
        println("1. Create Account")
        println("2. Deposit")
        println("3. Withdraw")
        println("4. Check Balance")
        println("5. View Transaction History")
        println("6. Exit")
        print("Select an option: ")

        when (readLine()?.toIntOrNull()) {
            1 -> {
                print("Enter account number: ")
                val accNum = readLine().orEmpty()
                if (accNum in accounts) {
                    println("Account already exists!")
                } else {
                    print("Enter account owner name: ")
                    val owner = readLine().orEmpty()
                    accounts[accNum] = BankAccount(accNum, owner)
                    println("Account created successfully for $owner!")
                }
            }
            2 -> {
                print("Enter account number: ")
                val accNum = readLine().orEmpty()
                val account = accounts[accNum]
                if (account != null) {
                    print("Enter deposit amount: ")
                    val amount = readLine()?.toDoubleOrNull() ?: 0.0
                    account.deposit(amount)
                } else {
                    println("Account not found.")
                }
            }
            3 -> {
                print("Enter account number: ")
                val accNum = readLine().orEmpty()
                val account = accounts[accNum]
                if (account != null) {
                    print("Enter withdrawal amount: ")
                    val amount = readLine()?.toDoubleOrNull() ?: 0.0
                    account.withdraw(amount)
                } else {
                    println("Account not found.")
                }
            }
            4 -> {
                print("Enter account number: ")
                val accNum = readLine().orEmpty()
                accounts[accNum]?.checkBalance() ?: println("Account not found.")
            }
            5 -> {
                print("Enter account number: ")
                val accNum = readLine().orEmpty()
                accounts[accNum]?.transactionHistory() ?: println("Account not found.")
            }
            6 -> {
                println("Exiting... Have a great day!")
                return
            }
            else -> println("Invalid option. Please try again.")
        }
    }
}
