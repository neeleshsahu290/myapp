package com.learningplatforms.myapplication.ui.subject_details

import android.content.Context
import android.content.Intent.getIntent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.learningplatforms.myapplication.R
import com.learningplatforms.myapplication.databinding.FragmentHomeBinding
import com.learningplatforms.myapplication.databinding.FragmentSubjectDetailsBinding
import com.learningplatforms.myapplication.ui.home.HomeViewModel
import com.learningplatforms.myapplication.ui.home.Itemlist


class SubjectDetailsFragment : Fragment() ,LifecycleObserver{

    private var _binding: FragmentSubjectDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.lifecycleScope?.launchWhenCreated {
        }
    }



    companion object {
        fun newInstance() = SubjectDetailsFragment()
    }

    private lateinit var viewModel: SubjectDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSubjectDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val imp= arguments?.getSerializable("amount")
        Log.d("msggg",imp.toString())
        var ArrayList:ArrayList<Itemlist> = ArrayList()
        ArrayList = imp as ArrayList<Itemlist>

        binding.detailsrecycler.apply {
            adapter = SubjectDetailsAdapter(ArrayList, requireContext())
            layoutManager= LinearLayoutManager(requireContext())
        }





        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[SubjectDetailsViewModel::class.java]

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}