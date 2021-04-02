package com.dsorcelli.newfeaturesproject.dialogs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dsorcelli.newfeaturesproject.databinding.ErrorBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomErrorDialog : BottomSheetDialogFragment() {

    private var _binding: ErrorBottomSheetBinding? = null
    private val binding get() = _binding!!

    private val args: BottomErrorDialogArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ErrorBottomSheetBinding.inflate(inflater, container, false)

        binding.errorBsTitle.text = getString(args.titleResId)
        binding.errorBsSubtitle.text = getString(args.bodyResId)

        // TODO: set not dismissable
        dialog?.let {
            isCancelable = false
        } ?: run {
            Log.d("ERRORDIALOG", "isCancelable failed")
        }

        binding.btnOk.setOnClickListener {
            findNavController().popBackStack(args.popUpToFragment, false)
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
