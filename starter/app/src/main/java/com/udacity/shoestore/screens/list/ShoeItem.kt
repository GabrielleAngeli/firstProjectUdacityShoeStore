package com.udacity.shoestore.screens.list

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout

import androidx.databinding.DataBindingUtil
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.shoe_item.view.*

class ShoeItem: LinearLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val binding: ShoeItemBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context), R.layout.shoe_item, this, false)

    fun loadShoe(shoe: Shoe) {
        binding.apply {
            addView(this.root)
            shoeName.text = shoe.name
            companyName.text = shoe.company
            size.text = shoe.size
            description.text = shoe.description
        }
    }
}