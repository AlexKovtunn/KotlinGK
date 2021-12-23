import java.util.*

data class Item(
    val Name : String = "None",
    val Price : Double = 0.0
)

data class ItemEntry(
    val ItemDesc : Item,
    val Number : Int = 0
)

sealed class DeliveryMethod {
    object SelfPickup: DeliveryMethod()
    data class CourierDelivery(
        val Adress: String = "None",
        val PriceDelivery: Int = 0
    ) : DeliveryMethod()
}

data class Order(
    val ItemInfo : ItemEntry,
    val Delivery : List<DeliveryMethod> = mutableListOf(),
    val TotalDiscount : Double = 0.0
) {
    fun getTotalPrice(): Double {
        val DeliveryString = Delivery.toString()
        var DeliveryPrice = DeliveryString.substringAfterLast('=').substringBeforeLast(')')
        var Price : Int
        if(DeliveryPrice.length>10) Price = 0
        else Price = DeliveryPrice.toInt()
        var Discount : Double = 1.0
        if(TotalDiscount>0) Discount = TotalDiscount/100
        val total : Double = ((ItemInfo.ItemDesc.Price*ItemInfo.Number)-((ItemInfo.ItemDesc.Price*ItemInfo.Number)*Discount))+Price
        return total // calculate full price here
    }
}



fun main() {
    val Order1 = Order(ItemEntry(Item("Pods",50.0),10), listOf(DeliveryMethod.CourierDelivery("Kurmangazy",100)),20.0)
    val Order2 = Order(ItemEntry(Item("Pods",150.0),10), listOf(DeliveryMethod.SelfPickup),20.0)
    val Order3 = Order(ItemEntry(Item("Pods",200.0),5), listOf(DeliveryMethod.SelfPickup),30.0)
    println(Order1.getTotalPrice())
    print(Order2.getTotalPrice())
    print(Order3.getTotalPrice())
}


