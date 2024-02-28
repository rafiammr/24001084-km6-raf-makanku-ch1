package datasource

import model.menuFnb

interface FnbDataSource{
    fun getFnbList() : Array<menuFnb>
}

class FnbDataSourceImpl() : FnbDataSource{
    override fun getFnbList() :  Array<menuFnb>{
        return arrayOf(
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
    }
}