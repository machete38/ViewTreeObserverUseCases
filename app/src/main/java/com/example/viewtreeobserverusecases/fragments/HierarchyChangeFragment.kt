package com.example.viewtreeobserverusecases.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.viewtreeobserverusecases.R

class HierarchyChangeFragment : Fragment() {

    private lateinit var containerLayout: LinearLayout
    private lateinit var logTextView: TextView
    private var viewCount = 0

    private val hierarchyChangeListener = ViewTreeObserver.OnGlobalLayoutListener {
        Toast.makeText(context,"Hierarchy changed. View count: ${containerLayout.childCount}\n", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hierarchy_change, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        containerLayout = view.findViewById(R.id.containerLayout)
        logTextView = view.findViewById(R.id.logTextView)

        view.findViewById<Button>(R.id.addViewButton).setOnClickListener {
            addView()
        }

        view.findViewById<Button>(R.id.removeViewButton).setOnClickListener {
            removeView()
        }

        containerLayout.viewTreeObserver.addOnGlobalLayoutListener(hierarchyChangeListener)
    }

    private fun addView() {
        val newView = TextView(requireContext()).apply {
            text = "View ${++viewCount}"
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }
        containerLayout.addView(newView)
    }

    private fun removeView() {
        if (containerLayout.childCount > 0) {
            containerLayout.removeViewAt(containerLayout.childCount - 1)
            viewCount--
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        containerLayout.viewTreeObserver.removeOnGlobalLayoutListener(hierarchyChangeListener)
    }
}