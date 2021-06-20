package com.example.shemajamebeli4redo.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel>(
    private val inflate: Inflate<VB>,
    private val baseViewModel: Class<VM>
) : Fragment() {

    protected var binding: VB? = null
    protected val viewModel: VM by lazy {
        ViewModelProvider(this).get(baseViewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflate(inflater, container, false)
        setUp(inflater, container)
        return binding!!.root
    }

    abstract fun setUp(inflater: LayoutInflater, container: ViewGroup?)

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}