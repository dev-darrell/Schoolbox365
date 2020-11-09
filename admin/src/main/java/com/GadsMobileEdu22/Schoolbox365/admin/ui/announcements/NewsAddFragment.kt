package com.GadsMobileEdu22.Schoolbox365.admin.ui.announcements

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build.VERSION
import android.os.Bundle
import android.provider.MediaStore.Images.Media
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.GadsMobileEdu22.Schoolbox365.admin.R
import com.GadsMobileEdu22.Schoolbox365.admin.databinding.FragmentNewsAddBinding
import com.GadsMobileEdu22.Schoolbox365.admin.ui.dashboard.DashboardFragment
import com.bumptech.glide.Glide
import om.GadsMobileEdu22.Schoolbox365.core.data.News

class NewsAddFragment : Fragment() {

    private val viewModel: NewsListViewModel by viewModels()
    private lateinit var binding: FragmentNewsAddBinding
    private lateinit var mBitmap: Bitmap
    private lateinit var imagePath: String

    private val startActivityForResult =  registerForActivityResult(StartActivityForResult()){ result ->
        if (result.resultCode == RESULT_OK){
            val uri: Uri = result.data?.data!!
            Toast.makeText(
                    binding.root.context,
                    "Image Selected",
                    Toast.LENGTH_SHORT
            )
                    .show()
            try {
                imagePath = uri.lastPathSegment!!
                if (VERSION.SDK_INT >= 29) {
                    val source: ImageDecoder.Source =
                            ImageDecoder.createSource(requireActivity().contentResolver, uri)
                    mBitmap = ImageDecoder.decodeBitmap(source)
                    viewModel.setImageBitmap(mBitmap)
                    Glide.with(requireContext()).load(uri).into(binding.imagePreview)
                }
                else {
                    mBitmap = Media.getBitmap(requireActivity().contentResolver, uri)
                    // Load image using Glide

                    viewModel.setImageBitmap(mBitmap)
                    Glide.with(requireContext()).load(mBitmap).into(binding.imagePreview)
                }
            } catch (e: Exception) {
                e.printStackTrace()

            }
        }

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewsAddBinding.inflate(inflater
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSaveNews.setOnClickListener{
            val news = News(tittle = binding.etTittle.text.toString(),
                    description = binding.etDesc.text.toString())

            viewModel.uploadImageAndNewsToFirebase(news)
        }

        binding.imageAdd.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"

            startActivityForResult.launch(intent)
        }

        viewModel.isUploadComplete.observe(viewLifecycleOwner, { complete ->

            if (complete) {

                val name = arguments?.getString("NameString");
                val bundle = bundleOf("Name" to name)
                activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.nav_host_fragment,
                        DashboardFragment::class.java, bundle)?.commit()

                binding.btnSaveNews.visibility = VISIBLE
                binding.progressBar.stop()

            }
            else{
                binding.btnSaveNews.visibility = GONE
                binding.progressBar.start()
            }

        })

    }



}