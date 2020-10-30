package com.GadsMobileEdu22.Schoolbox365.admin.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.GadsMobileEdu22.Schoolbox365.admin.R
import com.GadsMobileEdu22.Schoolbox365.admin.databinding.NewsListItemBinding
import com.bumptech.glide.Glide
import om.GadsMobileEdu22.Schoolbox365.core.data.News

class NewsListAdapter : ListAdapter<News, NewsListAdapter.NewsViewHolder>(NewsDiffCallback()) {

    class NewsDiffCallback : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class NewsViewHolder(private val binding: NewsListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(news: News) = with(binding) {
            textTittle.text = news.tittle
            textDescrption.text = news.description
            Glide.with(binding.root).load(news.image).placeholder(R.drawable.ic_notifications).into(imageNews)

        }

        companion object {
            fun from(parent: ViewGroup): NewsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = NewsListItemBinding.inflate(layoutInflater, parent, false)
                return NewsViewHolder(binding)
            }
        }
    }

}
