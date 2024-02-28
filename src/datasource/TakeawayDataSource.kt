package datasource

import model.orderFnb

interface TakeawayDataSource{
    fun getTakeawayList() : Array<orderFnb>
}

class TakeawayDataSourceImpl() : TakeawayDataSource{
    override fun getTakeawayList(): Array<orderFnb> {
        return arrayOf(
            //declare takeaway order
            orderFnb(
                orderChat = "Makananmu sedang dimasak (5 detik) ....."

            ),
            orderFnb(
                orderChat = "Makananmu sudah siap! Silahkan ambil di resto ya! (5 detik) ....."
            )
        )
    }
}