package com.GadsMobileEdu22.Schoolbox365.admin.ui.announcements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.GadsMobileEdu22.Schoolbox365.admin.R
import com.GadsMobileEdu22.Schoolbox365.admin.databinding.NewsListFragmentBinding
import com.GadsMobileEdu22.Schoolbox365.admin.ui.adapters.NewsListAdapter

class NewsListFragment : Fragment() {

    private  val viewModel: NewsListViewModel by viewModels()
    private lateinit var binding : NewsListFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = NewsListFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.newsList.observe(viewLifecycleOwner, { newsList ->
            val adapter = NewsListAdapter()
            adapter.submitList(newsList.reversed())
            binding.recyclerNews.adapter = adapter
        })

        binding.floatingActionButton.setOnClickListener {
            //              TODO: Open News/Announcements Activity or Fragment
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                    NewsAddFragment::class.java, null).commit()
        }

    }

}