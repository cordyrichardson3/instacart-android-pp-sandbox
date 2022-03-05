package com.instacart.android.challenges.data

import com.instacart.android.challenges.network.DeliveryItem
import com.instacart.android.challenges.network.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class RemoteItemDataSource: ItemDataSource {

    override suspend fun fetchOrders(): List<DeliveryItem> {
       val api = NetworkService().api

        return withContext(Dispatchers.IO) {
            try {
                val orderIds = api.fetchOrdersCoroutine()

                if(orderIds.orders.isEmpty()) {
                    emptyList<DeliveryItem>()
                }

                val order1 = orderIds.orders[0]
                val order = api.fetchOrderByIdCoroutine(order1)
                order.items
            } catch (ex: IOException) {
                emptyList()
            }
        }
    }
}