package com.example.viewtreeobserverusecases.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.fragment.app.Fragment
import com.example.viewtreeobserverusecases.databinding.FragmentViewMeasurementBinding

class ViewMeasurementFragment : Fragment() {

    private var _binding: FragmentViewMeasurementBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewMeasurementBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        measureView()

        binding.resizeButton.setOnClickListener {
            resizeView()
        }
    }

    private fun measureView() {
        binding.measuredView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                // Remove the listener to prevent multiple calls
                binding.measuredView.viewTreeObserver.removeOnGlobalLayoutListener(this)

                val width = binding.measuredView.width
                val height = binding.measuredView.height

                updateDimensionsText(width, height)
            }
        })
    }

    private fun resizeView() {
        val params = binding.measuredView.layoutParams
        params.width = (100..300).random()
        params.height = (50..200).random()
        binding.measuredView.layoutParams = params

        measureView()
    }

    private fun updateDimensionsText(width: Int, height: Int) {
        binding.dimensionsTextView.text = "Width: ${width}px, Height: ${height}px"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}