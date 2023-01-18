package com.example.proiect.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proiect.databinding.FragmentGalleryBinding
import com.example.proiect.repo.GalleryRepoImpl

class GalleryFragment : Fragment() {
    private lateinit var binding: FragmentGalleryBinding

    private lateinit var adapter: GalleryAdapter

//    private val viewModel by viewModels<GalleryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        observeState()
    }

    private fun setupView() {


//        this.adapter = GalleryAdapter()
//        binding.list.apply {
//            layoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
//            adapter = this@GalleryFragment.adapter
//        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
//            adapter.refreshData(GalleryRepoImpl.getPictureUrls())
//            viewModel.state.collect { state ->
//                adapter.refreshData(state.images)
//                println("daaa")
//            }
        }
    }

    private fun goBack() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }
}