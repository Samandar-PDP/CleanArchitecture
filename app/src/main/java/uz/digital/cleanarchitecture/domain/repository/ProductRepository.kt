package uz.digital.cleanarchitecture.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.digital.cleanarchitecture.domain.model.Product
import uz.digital.cleanarchitecture.util.Response

interface ProductRepository {
    suspend fun createProduct(product: Product): Flow<Response<Boolean>>
    suspend fun updateProduct(oldProduct: Product, newProduct: Map<String, Any>): Response<Boolean>
    suspend fun deleteProduct(product: Product): Flow<Response<Boolean>>
    suspend fun getAllProducts(): Flow<Response<List<Product>>>
}