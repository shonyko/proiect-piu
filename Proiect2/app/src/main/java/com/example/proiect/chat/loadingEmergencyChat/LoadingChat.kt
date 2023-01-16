package com.example.proiect.chat.loadingEmergencyChat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.proiect.databinding.FragmentLoadingBinding
class LoadingChat:Fragment() {
    private var _binding: FragmentLoadingBinding? = null
    private val binding get() = _binding!!

    val viewModel: LoadingEmergencyChatViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoadingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var newChat = viewModel.createChat()
        val direction = LoadingChatDirections.actionGoToChat(newChat.id)
        findNavController().navigate(direction)
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}