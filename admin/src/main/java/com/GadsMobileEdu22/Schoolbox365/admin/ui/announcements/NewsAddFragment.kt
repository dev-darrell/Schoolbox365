package com.GadsMobileEdu22.Schoolbox365.admin.ui.announcements

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.GadsMobileEdu22.Schoolbox365.admin.R
import com.GadsMobileEdu22.Schoolbox365.admin.databinding.FragmentNewsAddBinding
import om.GadsMobileEdu22.Schoolbox365.core.data.News

class NewsAddFragment : Fragment() {

    private val viewModel: NewsListViewModel by viewModels()
    private lateinit var binding: FragmentNewsAddBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewsAddBinding.inflate(inflater
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val news = News(tittle = binding.etTittle.text.toString(),
                description = binding.etDesc.text.toString())

        viewModel.uploadNews(news)

    }


}