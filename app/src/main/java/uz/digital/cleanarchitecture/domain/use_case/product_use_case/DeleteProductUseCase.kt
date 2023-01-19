package uz.digital.cleanarchitecture.domain.use_case.product_use_case

import kotlinx.coroutines.flow.Flow
import uz.digital.cleanarchitecture.domain.model.Product
import uz.digital.cleanarchitecture.domain.repository.ProductRepository
import uz.digital.cleanarchitecture.domain.use_case.base.BaseUseCase
import uz.digital.cleanarchitecture.util.Response
import javax.inject.Inject

typealias DeleteProductBaseUseCase = BaseUseCase<Product, Flow<Response<Boolean>>>

class DeleteProductUseCase @Inject constructor(
    private val repository: ProductRepository
): DeleteProductBaseUseCase {
    override suspend fun invoke(params: Product): Flow<Response<Boolean>> {
        return repository.deleteProduct(params)
    }
}