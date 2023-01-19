package uz.digital.cleanarchitecture.presentation.main.add_update

import uz.digital.cleanarchitecture.domain.model.Product

sealed class AddUpdateEvent {
    data class OnCreateProduct(val product: Product) : AddUpdateEvent()
    data class OnUpdateProduct(val oldProduct: Product, val newProduct: Map<String, Any>) :
        AddUpdateEvent()
}