package com.emreozcan.tmdbapp.ui.cast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.emreozcan.tmdbapp.R
import com.emreozcan.tmdbapp.databinding.FragmentCastBinding
import com.emreozcan.tmdbapp.ui.detail.DetailFragmentArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class CastFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentCastBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<CastFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCastBinding.inflate(inflater, container, false)
        binding.cast = args.cast

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}