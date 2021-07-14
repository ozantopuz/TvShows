package com.ozantopuz.tvshows.ui.main

import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ozantopuz.tvshows.R
import com.ozantopuz.tvshows.databinding.FragmentMainBinding
import com.ozantopuz.tvshows.util.delegate.viewBinding
import com.ozantopuz.tvshows.util.extension.toast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels()
    private val binding: FragmentMainBinding by viewBinding()
    private val adapter: TvShowAdapter by lazy { TvShowAdapter() }

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setRecyclerView()

        with(viewModel) {
            fetchTvShows()
            tvShowLiveData.observe(viewLifecycleOwner, { list ->
                adapter.setList(list)
                binding.recyclerView.scrollToPosition(0)
            })
            errorLiveData.observe(viewLifecycleOwner, { message -> context.toast(message) })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_sort -> openSortingDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setRecyclerView() {
        with(binding.recyclerView) {
            adapter = this@MainFragment.adapter
            addItemDecoration(TvShowItemDecoration())
        }
    }

    private fun openSortingDialog(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setItems(resources.getStringArray(R.array.sorting_types)) { dialog, which ->
            viewModel.sortList(which)
            dialog.dismiss()
        }
        builder.show()
    }
}