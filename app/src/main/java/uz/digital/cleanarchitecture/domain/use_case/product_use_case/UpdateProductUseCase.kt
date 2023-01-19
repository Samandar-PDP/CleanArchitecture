package uz.digital.cleanarchitecture.domain.use_case.product_use_case

import uz.digital.cleanarchitecture.domain.model.Product
import uz.digital.cleanarchitecture.domain.repository.ProductRepository
import uz.digital.cleanarchitecture.util.Response
import javax.inject.Inject

class UpdateProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(oldProduct: Product, newProduct: Map<String, Any>): Response<Boolean> {
        return repository.updateProduct(oldProduct, newProduct)
    }
}