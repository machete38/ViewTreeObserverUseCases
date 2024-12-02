package com.example.viewtreeobserverusecases.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ScrollView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.viewtreeobserverusecases.R

class ScrollTrackingFragment : Fragment() {

    private lateinit var scrollView: ScrollView
    private lateinit var scrollInfoTextView: TextView

    private val scrollChangedListener = ViewTreeObserver.OnScrollChangedListener {
        updateScrollInfo()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_scroll_tracking, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        scrollView = view.findViewById(R.id.scrollView)
        scrollInfoTextView = view.findViewById(R.id.scrollInfoTextView)

        scrollView.viewTreeObserver.addOnScrollChangedListener(scrollChangedListener)

        // Initial update
        updateScrollInfo()
    }

    private fun updateScrollInfo() {
        val scrollY = scrollView.scrollY
        val scrollViewHeight = scrollView.height
        val scrollViewContentHeight = scrollView.getChildAt(0).height
        val scrollPercentage = (scrollY.toFloat() / (scrollViewContentHeight - scrollViewHeight) * 100).toInt()

        scrollInfoTextView.text = "Scroll position: $scrollY\n" +
                "Scroll percentage: $scrollPercentage%"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        scrollView.viewTreeObserver.removeOnScrollChangedListener(scrollChangedListener)
    }
}