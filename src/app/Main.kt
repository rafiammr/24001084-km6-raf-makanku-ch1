package app

import datasource.*
import model.menuFnb
import utils.*

class App(var fnbName: String = "Warung Binar") {

    private lateinit var selectedFnb: menuFnb
    private var userWants: Int = -1
    private var userPay: Int = -1
    private var totalPrice: Int = -1
    private var userOrder: Int = -1

    private val fnbData = FnbDataSourceImpl().getFnbList()
    private val takeawayOrder = TakeawayDataSourceImpl().getTakeawayList()
    private val deliveryOrder = DeliveryDataSourceImpl().getDeliveryList()

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
        val selectedIndex = IOUtils.getInputInteger()
        //print selected menu
        selectedIndex?.let {
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
        } ?: run {
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

    private fun PayFnb() { //input money
        try {
            println("......................")
            print("Masukkan Jumlah Uang : ")
            userPay = readln().toInt()
            if (userPay == totalPrice) {
                println("==========================================")
                println("Terimakasih, anda bersahil memesan makanan")
                println("==========================================")
            } else if (userPay > totalPrice){
                val userMoney = (userPay - totalPrice) //calculate the change
                println("==========================================")
                println("Terimakasih, anda bersahil memesan makanan")
                println("Kembalian anda : $userMoney")
                println("==========================================")
            } else{
                println("==========================================")
                println("Maaf, Pembayaran anda gagal!")
                println("==========================================")
                PayFnb()
            }

        } catch (e: NumberFormatException) {
            println("++===========================++")
            println("||  Input menggunakan angka  ||")
            println("++===========================++")
            PayFnb()
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

    private fun orderResult(userOrder: Int): OrderMethod { //check order input
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
        PayFnb()
        printFnbOrder()
        inputFnbOrder()
        val orderMethod = orderResult(userOrder)
        orderMethod.order()
    }

}

fun main() {
    App().run()
}
