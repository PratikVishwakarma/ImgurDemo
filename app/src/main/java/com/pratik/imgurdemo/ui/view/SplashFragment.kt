package com.pratik.imgurdemo.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.pratik.imgurdemo.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startRedirection(view)
    }

    /*
    * function to redirect to the image list fragment after 1.5 seconds
    * */
    private fun startRedirection(view: View) {
        lifecycleScope.launch {
            delay(1500)
            withContext(Dispatchers.Main) {
                val action = SplashFragmentDirections.actionSplashFragmentToImageListFragment()
                view.findNavController().navigate(action)
            }
        }
    }
}