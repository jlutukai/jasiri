package com.jasiri.erp.features.onBoarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.jasiri.erp.R
import com.jasiri.erp.databinding.ActivityOnBoardingBinding
import com.jasiri.erp.features.auth.LoginActivity
import com.jasiri.erp.features.onBoarding.adapter.OnBoardingItemAdapter
import com.jasiri.erp.features.onBoarding.models.OnBoardingItem

class OnBoarding : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingBinding
    private lateinit var onBoardingAdapter: OnBoardingItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUI()
    }

    private fun setUpUI() {
        setOnBoardingItems()
        setUpIndicators()
        setCurrentIndicator(0)
        initListeners()
    }

    private fun initListeners() {
        with(binding){
            btnLogin.setOnClickListener {
                startActivity(Intent(this@OnBoarding, LoginActivity::class.java))
                finish()
            }
        }
    }

    private fun setOnBoardingItems() {
        onBoardingAdapter = OnBoardingItemAdapter(
            listOf(
                OnBoardingItem(
                    image = R.drawable.ic_scheduling,
                    title = getString(R.string.easy_scheduling_and_time_tabling),
                    description = ""
                ),
                OnBoardingItem(
                    image = R.drawable.ic_educator,
                    title = getString(R.string.capturing_all_critical_processes_for_you),
                    description = ""
                ),
                OnBoardingItem(
                    image = R.drawable.ic_support,
                    title = getString(R.string._24_7_support),
                    description = ""
                )
            )
        )
        binding.introViewPager.apply {
            adapter = onBoardingAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    setCurrentIndicator(position)
                }
            })
            (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }
    }

    private fun setUpIndicators() {
        val indicators = arrayOfNulls<ImageView>(onBoardingAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
                it.layoutParams = layoutParams
                binding.indicatorLayout.addView(it)
            }
        }
    }

    private fun setCurrentIndicator(position: Int) {
        for (i in 0 until binding.indicatorLayout.childCount) {
            val imageView = binding.indicatorLayout.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active_background
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
            }
        }
    }
}