package com.pratik.imgurdemo.ui.view

import android.app.AlertDialog
import android.content.res.Configuration
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.pratik.imgurdemo.R
import com.pratik.imgurdemo.databinding.FragmentImageListBinding
import com.pratik.imgurdemo.model.networkAPI.responseDTO.ImageDTO
import com.pratik.imgurdemo.ui.adapter.ImageAdapter
import com.pratik.imgurdemo.ui.viewmodel.ImageServerViewModel
import com.pratik.imgurdemo.utility.isNetworkAvailable
import com.pratik.imgurdemo.utility.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageListFragment : Fragment() {

    private val imageServerViewModel by activityViewModels<ImageServerViewModel>()
    private val mAdapter by lazy { ImageAdapter() }

    private lateinit var binding: FragmentImageListBinding
    private lateinit var builder: AlertDialog.Builder
    private lateinit var mBuilder: MaterialAlertDialogBuilder
    private val imageList: ArrayList<ImageDTO> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_image_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        builder = AlertDialog.Builder(requireContext())
        mBuilder = MaterialAlertDialogBuilder(requireContext(), R.style.CutShapeTheme)

        binding.ivSearch.setOnClickListener {
            imageServerViewModel.setSearchText("")
            openSearchDialog()
        }

        binding.ivMode.setOnClickListener {
            imageServerViewModel.switchViewMode()
        }

        imageServerViewModel.searchText.value?.let {
            if (it.isEmpty()) openSearchDialog()
        }
        attachListener()
    }

    /*
    * function to attach all the listener from the view model
    * */
    private fun attachListener() {
        imageServerViewModel.imageList.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                imageList.clear()
                imageList.addAll(it)
                setupImageData()
            } else
                requireActivity().showToast("No image found...")
        }

        imageServerViewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progress.visibility = if (it) View.VISIBLE else View.GONE
        }

        imageServerViewModel.selected.observe(viewLifecycleOwner) {
            switchViewMode(it)
        }
    }

    /*
    * function to set the image data according to the selected mode LIST or GRID
    * */
    private fun setupImageData() {
        if (imageServerViewModel.selected.value == ImageAdapter.GRID_MODE) {
            val orientation = this.resources.configuration.orientation
            if (orientation == Configuration.ORIENTATION_PORTRAIT)
                binding.rvList.layoutManager = GridLayoutManager(requireContext(), 2)
            else binding.rvList.layoutManager = GridLayoutManager(requireContext(), 3)
        } else
            binding.rvList.layoutManager = LinearLayoutManager(requireContext())
        imageServerViewModel.selected.value?.let {
            mAdapter.setMode(it)
        }
        binding.rvList.adapter = mAdapter
        mAdapter.submitList(imageList)
    }

    /*
    * function to toggle the selected mode and set the image accordingly
    * */
    private fun switchViewMode(selected: Int) {
        if (selected == ImageAdapter.LIST_MODE)
            binding.ivMode.setImageResource(R.drawable.ic_list)
        else
            binding.ivMode.setImageResource(R.drawable.ic_grid)
        setupImageData()
    }


    /*
    * function to create and show the dialog to make a search
    * */
    private fun openSearchDialog() {
//        builder.setTitle(getString(R.string.search_image))
//
//        val input = EditText(requireContext())
//        input.inputType = InputType.TYPE_CLASS_TEXT
//        input.setText(imageServerViewModel.searchText.value)
//        input.doOnTextChanged { text, _, _, _ ->
//            imageServerViewModel.setSearchText(text.toString())
//        }
//        builder.setView(input)
//
//        builder.setPositiveButton(
//            getString(R.string.search)
//        ) { _, _ ->
//            val text = input.text.toString()
//            if (text.isNotEmpty()) {
//                imageServerViewModel.setSearchText(text)
//                if (!requireContext().isNetworkAvailable())
//                    requireContext().showToast(getString(R.string.internet_not_available))
//                imageServerViewModel.getTopImageOfWeek()
//            } else
//                requireActivity().showToast("Fill the value...")
//        }
//        builder.setNegativeButton(
//            getString(R.string.cancel)
//        ) { dialog, _ -> dialog.cancel() }
//
//        builder.show()
        openSearchDialogMaterial()
    }

    /*
    * function to create and show the dialog to make a search
    * */
    private fun openSearchDialogMaterial() {
        mBuilder.setTitle(getString(R.string.search_image))
        val input = EditText(requireContext())
        input.id = R.id.my_dialog_edit_text
        input.inputType = InputType.TYPE_CLASS_TEXT
        input.setText(imageServerViewModel.searchText.value)
        input.doOnTextChanged { text, _, _, _ ->
            imageServerViewModel.setSearchText(text.toString())
        }
        mBuilder.setView(input)
        mBuilder.setPositiveButton(
            getString(R.string.search)
        ) { _, _ ->
            val text = input.text.toString()
            if (text.isNotEmpty()) {
                imageServerViewModel.setSearchText(text)
                if (!requireContext().isNetworkAvailable())
                    requireContext().showToast(getString(R.string.internet_not_available))
                imageServerViewModel.getTopImageOfWeek()
            } else
                requireActivity().showToast("Fill the value...")
        }
        mBuilder.setNegativeButton(
            getString(R.string.cancel)
        ) { dialog, _ -> dialog.cancel() }

        mBuilder.show()
    }

    /*
    * remove all the attached listener
    * */
    override fun onDestroyView() {
        imageServerViewModel.imageList.removeObservers(viewLifecycleOwner)
        imageServerViewModel.isLoading.removeObservers(viewLifecycleOwner)
        super.onDestroyView()
    }
}