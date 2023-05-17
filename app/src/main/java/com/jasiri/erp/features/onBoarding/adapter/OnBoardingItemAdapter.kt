package com.jasiri.erp.features.onBoarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jasiri.erp.databinding.ItemOnboardingContainerBinding
import com.jasiri.erp.features.onBoarding.models.OnBoardingItem

class OnBoardingItemAdapter(private val items: List<OnBoardingItem>) :
    RecyclerView.Adapter<OnBoardingItemAdapter.OnBoardingItemViewHolder>() {

    private lateinit var itemBinding: ItemOnboardingContainerBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingItemViewHolder {
        itemBinding = ItemOnboardingContainerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OnBoardingItemViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = position

    override fun onBindViewHolder(holder: OnBoardingItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, position)
    }

    inner class OnBoardingItemViewHolder(view: ItemOnboardingContainerBinding) :
        RecyclerView.ViewHolder(view.root) {
        private var current: OnBoardingItem? = null
        private var currentPos: Int = 0
        fun bind(item: OnBoardingItem, position: Int) {
            item.let {
                itemBinding.imageOnBoarding.setImageResource(it.image)
                itemBinding.titleOnBoarding.text = it.title
                itemBinding.descriptionOnBoarding.text = it.description
            }
            current = item
            currentPos = position
        }
    }
}