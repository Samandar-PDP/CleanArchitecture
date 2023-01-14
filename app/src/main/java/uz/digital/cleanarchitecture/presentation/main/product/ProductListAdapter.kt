package uz.digital.cleanarchitecture.presentation.main.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.digital.cleanarchitecture.databinding.ItemLayoutBinding
import uz.digital.cleanarchitecture.domain.model.Product

class ProductListAdapter :
    ListAdapter<Product, ProductListAdapter.ProductViewHolder>(DiffCallBack()) {
    lateinit var onClick: (Product) -> Unit

    private class DiffCallBack : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ProductViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            with(binding) {
                index.text = adapterPosition.plus(1).toString()
                name.text = product.name
            }
            itemView.setOnClickListener {
                onClick(product)
            }
        }
    }
}