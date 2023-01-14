package uz.digital.cleanarchitecture.domain.use_case.product_use_case

import kotlinx.coroutines.flow.Flow
import uz.digital.cleanarchitecture.domain.model.Product
import uz.digital.cleanarchitecture.domain.repository.ProductRepository
import uz.digital.cleanarchitecture.domain.use_case.base.BaseUseCase
import uz.digital.cleanarchitecture.util.Response
import javax.inject.Inject

typealias GetAllProductBaseUseCase = BaseUseCase<Unit, Flow<Response<List<Product>>>>

class GetAllProductUseCase @Inject constructor(
    private val repository: ProductRepository
) : GetAllProductBaseUseCase {
    override suspend fun invoke(params: Unit): Flow<Response<List<Product>>> {
        return repository.getAllProducts()
    }
}