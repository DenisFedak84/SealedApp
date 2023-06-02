package com.example.sealedapp.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.sealedapp.databinding.CartViewBinding

class CartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyle, defStyleRes) {

    private var _binding: CartViewBinding? = null
    val binding get() = requireNotNull(_binding)

    init {
        _binding = CartViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun updateQuantity(quantity: Int) {
        binding.tvQuantity.text = quantity.toString()
    }

}