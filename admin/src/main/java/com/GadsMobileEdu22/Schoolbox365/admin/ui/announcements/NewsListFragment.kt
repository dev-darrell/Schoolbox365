package com.GadsMobileEdu22.Schoolbox365.admin.ui.announcements

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.GadsMobileEdu22.Schoolbox365.admin.R
import com.GadsMobileEdu22.Schoolbox365.admin.databinding.NewsListFragmentBinding
import com.GadsMobileEdu22.Schoolbox365.admin.ui.adapters.NewsListAdapter
import com.GadsMobileEdu22.Schoolbox365.admin.ui.adapters.NewsPagerAdapter

class NewsListFragment : Fragment() {

    private  val viewModel: NewsListViewModel by viewModels()
    private lateinit var binding : NewsListFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = NewsListFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.newsList.observe(viewLifecycleOwner,{newsList ->
            val adapter = NewsListAdapter()
            adapter.submitList(newsList)
            binding.recyclerNews.adapter = adapter
        })

    }

}