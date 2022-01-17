package com.example.vancouver_transport

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import io.ktor.client.*
import io.ktor.client.call.*

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StopListFragment.newInstance] factory method to
 * create an instance of this fragment..
 */
class SearchListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_stop_list, container, false)

        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewSH);

        val model = SuperHeroViewModel() // Création d'un nouveau model à chaque création de fragment burk

        //model.testName()

        //model.loadList()

        model.getList().observe(viewLifecycleOwner, Observer<List<SuperHero>>{ shs ->
            recyclerView?.adapter = SuperHeroAdapter(model).apply {
                setOnItemClickListener(object : SuperHeroAdapter.ClickListener {
                    override fun onItemClick(position: Int, v: View?) {
                        val navController = findNavController()
                        navController?.navigate(R.id.action_stopListFragment_to_detailsFragment)
                    }
                })
            }

        })


        // Inflate the layout for this fragment
        return view
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StopListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}