package app

import model.menuFnb
import model.orderFnb

interface orderMethod {
    fun order()
}

class Takeaway(private val takeawayOrder: Array<orderFnb>) : orderMethod {
    override fun order() {
        for (chat in takeawayOrder) { //create a looping
            println(chat.orderChat)
            Thread.sleep(5000) //create a delay
        }
        println("Pesanan selesai (3 detik) ...")
        Thread.sleep(3000)
    }
}

class Delivery(private val deliveryOrder: Array<orderFnb>) : orderMethod {
    override fun order() {
        for (chat in deliveryOrder) {
            println(chat.orderChat)
            Thread.sleep(5000)
        }
        println("Pesanan selesai (3 detik) ...")
        Thread.sleep(3000)
    }
}

class WrongOrderMethod() : orderMethod {
    override fun order() {
        println("========================================")
        println("Metode order yang anda pilih tidak valid")
        println("Metode order akan otomaatis menjadi Takeaway")
        println("========================================")
    }
}

class App(var fnbName: String = "Warung Binar") {

    private lateinit var selectedFnb: menuFnb
    private var userWants: Int = -1
    private var userPay: Int = -1
    private var totalPrice: Int = -1
    private var userOrder: Int = -1

    private val fnbData = arrayOf(
        //declare menu
        menuFnb(
            menu = "Ayam Bakar",
            menuPrice = 50000,
        ),
        menuFnb(
            menu = "Ayam Goreng",
            menuPrice = 40000,
        ),
        menuFnb(
            menu = "Ayam Geprek",
            menuPrice = 40000,
        ),
        menuFnb(
            menu = "Kulit Ayam Crispy",
            menuPrice = 15000,
        ),
        menuFnb(
            menu = "Sate Usus Ayam",
            menuPrice = 5000,
        )
    )

    private val takeawayOrder = arrayOf(
        //declare takeaway order
        orderFnb(
            orderChat = "Makananmu sedang dimasak (5 detik) ....."

        ),
        orderFnb(
            orderChat = "Makananmu sudah siap! Silahkan ambil di resto ya! (5 detik) ....."
        )
    )

    private val deliveryOrder = arrayOf(
        //declare delivery order
        orderFnb(
            orderChat = "Makananmu sedang dimasak (5 detik) ....."
        ),
        orderFnb(
            orderChat = "Makananmu sudah siap! Driver segera menuju tempatmu! (5 detik) ....."
        )
    )

    private fun printHeader() { //display menu
        println("++==================================++")
        println("||Food & Beverages by : $fnbName||")
        println("++==================================++")
        println("||        List Menu Makanan         ||")
        println("++==================================++")
        fnbData.forEachIndexed { index, data ->
            println("${index + 1}. ${data.menu} = Rp ${data.menuPrice}/porsi")
        }
        println("++==================================++")
    }

    private fun getSelectedFnb() {
        print("Whay do you want to buy ? 1/2/3/4/5 ")
        try {
            val selectedIndex = readln().toInt() //print selected menu
            if (selectedIndex in 1..fnbData.size) {
                selectedFnb = fnbData[selectedIndex - 1]
                println("Kamu memilih menu $selectedIndex")
                println("Menu\t: ${selectedFnb.menu}")
                println("Harga\t: ${selectedFnb.menuPrice}/porsi")
            } else {
                println("++==================================++")
                println("Pilihan anda salah, silahkan coba lagi")
                println("++==================================++")
                getSelectedFnb()
            }

        } catch (e: NumberFormatException) { //if input doesn't match the given format
            println("++===========================++")
            println("||  Input menggunakan angka  ||")
            println("++===========================++")
            getSelectedFnb()
        }
    }

    private fun resultPrice(userWants: Int) { //calculate total price
        totalPrice = ("${selectedFnb.menuPrice * userWants}").toInt()
    }

    private fun manyFnb() { //input a lot FnB want to buy
        println("......................")
        print("How many ${selectedFnb.menu} you wanna buy ? ")
        try {
            userWants = readln().toInt()
            //make sure input is more than 0
            if (userWants > 0) {
                resultPrice(userWants)
                println("Total Price = $totalPrice")
            } else {
                println("++===========================++")
                println("||     Inputan anda salah    ||")
                println("++===========================++")
                manyFnb()
            }
        } catch (e: NumberFormatException) {
            println("++===========================++")
            println("||  Input menggunakan angka  ||")
            println("++===========================++")
            manyFnb()
        }

    }

    private fun inputPayFnb() { //input money
        try {
            println("......................")
            print("Masukkan Jumlah Uang : ")
            userPay = readln().toInt()

        } catch (e: NumberFormatException) {
            println("++===========================++")
            println("||  Input menggunakan angka  ||")
            println("++===========================++")
            inputPayFnb()
        }
    }

    private fun payResult(userPay: Int, totalPrice: Int): String { //check amount of money
        return if (userPay == totalPrice) {
            """ 
            ==========================================
            Terimakasih, anda bersahil memesan makanan
            ==========================================
        """.trimIndent()
        } else if (userPay > totalPrice) {
            val userMoney = (userPay - totalPrice) //calculate the change
            """
            Terimakasih, anda bersahil memesan makanan
            Kembalian anda : $userMoney
            """.trimIndent()
        } else {
            """
            ==========================================
            Maaf, Pembayaran anda gagal!
            ==========================================
        """.trimIndent()
        }
    }

    private fun printFnbOrder() { //display order method
        println("++===========================++")
        println("|| Metode pengiriman makanan ||")
        println("++===========================++")
        println("|| 1. Take Away              ||")
        println("|| 2. Delivery               ||")
        println("++===========================++")
    }

    private fun inputFnbOrder() { //input order method
        print("Masukkan pilihan anda : ")
        try {
            userOrder = readln().toInt()
            println("\n")
        } catch (e: NumberFormatException) {
            println("++===========================++")
            println("||  Input menggunakan angka  ||")
            println("++===========================++")
            inputFnbOrder()
        }
    }

    private fun orderResult(userOrder: Int): orderMethod { //check order input
        return when (userOrder) {
            1 -> {
                Takeaway(takeawayOrder) //call class
            }

            2 -> {
                Delivery(deliveryOrder)
            }

            else -> {
                WrongOrderMethod().order()
                Delivery(takeawayOrder)
            }
        }
    }

    fun run() {
        printHeader()
        getSelectedFnb()
        manyFnb()
        inputPayFnb()
        println(payResult(userPay, totalPrice))
        printFnbOrder()
        inputFnbOrder()
        val orderMethod = orderResult(userOrder)
        orderMethod.order()
    }

}

fun main() {
    App().run()
}
