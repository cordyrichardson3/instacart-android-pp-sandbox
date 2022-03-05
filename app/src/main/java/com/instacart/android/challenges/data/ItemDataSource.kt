package com.instacart.android.challenges.data

import com.instacart.android.challenges.network.DeliveryItem

interface ItemDataSource {
    suspend fun fetchOrders(): List<DeliveryItem>
}