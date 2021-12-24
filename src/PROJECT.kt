import java.util.*

data class Item(
    val name : String,
    val price : Double
)

data class ItemEntry(
    val itemDesc : Item,
    val number : Int
)

sealed class DeliveryMethod {
    object SelfPickup: DeliveryMethod()
    data class CourierDelivery(
        val adress: String,
        val priceDelivery: Int
    ) : DeliveryMethod()
}

data class Order(
    val itemInfo : ItemEntry,
    val delivery : DeliveryMethod,
    val totalDiscount : Double = 0.0
) {
    fun getTotalPrice(): Double {
        val deliveryString = delivery.toString()
        val deliveryPrice = if(delivery is DeliveryMethod.CourierDelivery) delivery.priceDelivery
        else 0
        var discount : Double = 1.0
        if(totalDiscount>0) discount = totalDiscount/100
        val total : Double = ((itemInfo.itemDesc.price*itemInfo.number)-((itemInfo.itemDesc.price*itemInfo.number)*discount))+deliveryPrice
        return total // calculate full price here
    }
}



fun main() {
    val Order1 = Order(ItemEntry(Item("Pods",50.0),10),DeliveryMethod.CourierDelivery("Kurman",10),20.0)
    val Order2 = Order(ItemEntry(Item("Pods",150.0),10),DeliveryMethod.SelfPickup,20.0)
    println(Order1.getTotalPrice())
    println(Order2.getTotalPrice())
}


