package com.instacart.android.challenges

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.instacart.android.challenges.data.ItemDataSource
import com.instacart.android.challenges.data.RemoteItemDataSource
import kotlinx.coroutines.launch

class MainActivityViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val itemDataSource: ItemDataSource = RemoteItemDataSource()

    interface UpdateListener {
        fun onUpdate(state: ItemListViewState)
    }

//    private var itemListViewState: ItemListViewState = ItemListViewState("", emptyList())
    private var listener: UpdateListener? = null

    init {
 /**       val items = listOf(
            ItemRow("Cabbage"),
            ItemRow("Apple"),
            ItemRow("Bread")
        )
**/
        viewModelScope.launch {
            val items: List<ItemRow> = itemDataSource.fetchOrders().map { ItemRow(name = it.name) }
            val itemListViewState = ItemListViewState("Delivery Items", items)
            listener?.onUpdate(itemListViewState)
        }
    }

    fun setStateUpdateListener(listener: UpdateListener?) {
        this.listener = listener

  //      listener?.onUpdate(itemListViewState)
    }
}
