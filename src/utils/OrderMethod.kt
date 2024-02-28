package utils

import model.orderFnb

interface OrderMethod {
    fun order()
}

class Takeaway(private val takeawayOrder: Array<orderFnb>) : OrderMethod {
    override fun order() {
        for (chat in takeawayOrder) { //create a looping
            println(chat.orderChat)
            Thread.sleep(5000) //create a delay
        }
        println("Pesanan selesai (3 detik) ...")
        Thread.sleep(3000)
    }
}

class Delivery(private val deliveryOrder: Array<orderFnb>) : OrderMethod {
    override fun order() {
        for (chat in deliveryOrder) {
            println(chat.orderChat)
            Thread.sleep(5000)
        }
        println("Pesanan selesai (3 detik) ...")
        Thread.sleep(3000)
    }
}

class WrongOrderMethod() : OrderMethod {
    override fun order() {
        println("========================================")
        println("Metode order yang anda pilih tidak valid")
        println("Metode order akan otomaatis menjadi Takeaway")
        println("========================================")
    }
}