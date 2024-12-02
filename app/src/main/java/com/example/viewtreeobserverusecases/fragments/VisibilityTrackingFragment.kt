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

class VisibilityTrackingFragment : Fragment() {

    private lateinit var scrollView: ScrollView
    private lateinit var targetView: View
    private lateinit var visibilityInfoTextView: TextView

    private val globalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
        checkVisibility()
    }

    private val scrollChangedListener = ViewTreeObserver.OnScrollChangedListener {
        checkVisibility()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_visibility_tracking, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        scrollView = view.findViewById(R.id.scrollView)
        targetView = view.findViewById(R.id.targetView)
        visibilityInfoTextView = view.findViewById(R.id.visibilityInfoTextView)

        scrollView.viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
        scrollView.viewTreeObserver.addOnScrollChangedListener(scrollChangedListener)
    }

    private fun checkVisibility() {
        val rect = android.graphics.Rect()
        val isVisible = targetView.getGlobalVisibleRect(rect)
        val visibleHeight = rect.height()
        val totalHeight = targetView.height

        val visibilityPercentage = (visibleHeight.toFloat() / totalHeight * 100).toInt()

        visibilityInfoTextView.text = when {
            !isVisible -> "Target View is not visible"
            visibilityPercentage == 100 -> "Target View is fully visible"
            else -> "Target View is ${visibilityPercentage}% visible"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        scrollView.viewTreeObserver.removeOnGlobalLayoutListener(globalLayoutListener)
        scrollView.viewTreeObserver.removeOnScrollChangedListener(scrollChangedListener)
    }
}