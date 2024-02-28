package datasource

import model.orderFnb

interface DeliveryDataSource{
    fun getDeliveryList() : Array<orderFnb>
}

class DeliveryDataSourceImpl() : DeliveryDataSource{
    override fun getDeliveryList(): Array<orderFnb> {
        return arrayOf(
            //declare delivery order
            orderFnb(
                orderChat = "Makananmu sedang dimasak (5 detik) ....."
            ),
            orderFnb(
                orderChat = "Makananmu sudah siap! Driver segera menuju tempatmu! (5 detik) ....."
            )
        )
    }
}