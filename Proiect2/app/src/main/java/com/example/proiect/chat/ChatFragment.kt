package com.example.proiect.chat

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView.OnEditorActionListener
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proiect.databinding.FragmentChatBinding


class ChatFragment: Fragment() {
    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    val viewModel: ChatViewModel by viewModels()

    private lateinit var messageAdapter: MessageAdapter
    private lateinit var templateAdapter: TemplateAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =  FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        observeState()
    }

    private fun setupView() {
        messageAdapter = MessageAdapter()
        templateAdapter = TemplateAdapter(
            object : TemplateAdapter.ClickListener {
                override fun itemSelected(template: String) {
                    println(template)
                    binding.messageInput.editText?.clearFocus()
                    viewModel.sendMessage(template)
                }
            }
        )
        binding.list.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = this@ChatFragment.messageAdapter
        }
        binding.templates.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = this@ChatFragment.templateAdapter
        }
        binding.messageInput.editText?.doOnTextChanged { text, start, before, count ->
            viewModel.searchTemplate(text.toString())
        }
        binding.messageInput.editText?.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                println("Done pressed "+binding.messageInput.editText?.text.toString())
                viewModel.sendMessage(binding.messageInput.editText?.text.toString())
            }
            false
        })
        binding.sendMessage.setOnClickListener {
            viewModel.sendMessage(binding.messageInput.editText?.text.toString())
        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.viewState.collect { state ->
                println(state.messages.size.toString()+"?????")
                messageAdapter.refreshData(state.messages)
                templateAdapter.refreshData(state.templates)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}