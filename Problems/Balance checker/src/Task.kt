import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    var initialAmount = scanner.nextInt()

    while (scanner.hasNextInt()) {
        var purchases = scanner.nextInt()
        if (initialAmount >= purchases && initialAmount != 0) {
            initialAmount -= purchases
            if (!scanner.hasNextInt()){
                println("Thank you for choosing us to manage your account! You have $initialAmount money.")
            }
        } else {
            println("Error, insufficient funds for the purchase. You have $initialAmount, but you need $purchases.")
            break
        }
    }
}