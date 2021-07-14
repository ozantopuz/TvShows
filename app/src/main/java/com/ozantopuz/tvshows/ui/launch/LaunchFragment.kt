package com.ozantopuz.tvshows.ui.launch

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ozantopuz.tvshows.R
import com.ozantopuz.tvshows.databinding.FragmentLaunchBinding
import com.ozantopuz.tvshows.ui.main.MainFragment
import com.ozantopuz.tvshows.util.delegate.viewBinding


class LaunchFragment : Fragment(R.layout.fragment_launch) {

    private val binding: FragmentLaunchBinding by viewBinding()

    companion object {
        fun newInstance() = LaunchFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener { openMainFragment() }
    }

    private fun openMainFragment() {
        activity?.apply {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commit()
        }
    }
}