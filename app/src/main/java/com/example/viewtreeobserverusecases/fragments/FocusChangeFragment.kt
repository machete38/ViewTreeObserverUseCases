package com.example.viewtreeobserverusecases.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.viewtreeobserverusecases.R

class FocusChangeFragment : Fragment() {

    private lateinit var focusInfoTextView: TextView
    private lateinit var rootView: View

    private val focusChangeListener = ViewTreeObserver.OnGlobalFocusChangeListener { oldFocus, newFocus ->
        val oldFocusName = oldFocus?.contentDescription ?: "null"
        val newFocusName = newFocus?.contentDescription ?: "null"

        focusInfoTextView.append("Focus changed from $oldFocusName to $newFocusName\n")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_focus_change, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rootView = view
        focusInfoTextView = view.findViewById(R.id.focusInfoTextView)

        // Set content descriptions for views
        view.findViewById<View>(R.id.editText1).contentDescription = "Edit Text 1"
        view.findViewById<View>(R.id.editText2).contentDescription = "Edit Text 2"
        view.findViewById<View>(R.id.button1).contentDescription = "Button 1"
        view.findViewById<View>(R.id.button2).contentDescription = "Button 2"

        rootView.viewTreeObserver.addOnGlobalFocusChangeListener(focusChangeListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        rootView.viewTreeObserver.removeOnGlobalFocusChangeListener(focusChangeListener)
    }
}