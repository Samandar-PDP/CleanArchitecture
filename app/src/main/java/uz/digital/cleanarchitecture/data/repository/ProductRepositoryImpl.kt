package uz.digital.cleanarchitecture.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import uz.digital.cleanarchitecture.domain.model.Product
import uz.digital.cleanarchitecture.domain.repository.ProductRepository
import uz.digital.cleanarchitecture.util.Response
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    auth: FirebaseAuth
) : ProductRepository {

    private var isSuccessful = false
    private val uid = auth.currentUser?.uid

    override suspend fun getAllProducts(): Flow<Response<List<Product>>> = callbackFlow {
        Response.Loading
        val snap = firestore.collection("products")
            .whereEqualTo("userId", uid)
            .addSnapshotListener { value, error ->
                val response = if (value != null) {
                    val productList = value.toObjects(Product::class.java)
                    Response.Success(productList)
                } else {
                    Response.Error(error?.message.toString())
                }
                trySend(response).isSuccess
            }
        awaitClose {
            snap.remove()
        }
    }

    override suspend fun createProduct(product: Product): Flow<Response<Boolean>> = flow {
        isSuccessful = false
        try {
            emit(Response.Loading)
            val productId = firestore.collection("products").document().id
            val newProduct = Product(userId = "$uid", name = product.name, price = product.price)
            firestore.collection("products").document(productId).set(newProduct)
                .addOnSuccessListener {
                    isSuccessful = true
                }.await()
            emit(Response.Success(isSuccessful))
        } catch (e: Exception) {
            emit(Response.Error(e.message.toString()))
        }
    }

    override suspend fun updateProduct(product: Product): Flow<Response<Boolean>> = flow {

    }

    override suspend fun deleteProduct(product: Product): Flow<Response<Boolean>> = flow {

    }
}