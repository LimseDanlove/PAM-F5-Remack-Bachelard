package com.example.superherodex

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.superherodex.model.SuperHero
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
 * Use the [SHListFragment.newInstance] factory method to
 * create an instance of this fragment..
 */
class SearchListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var model: SuperHeroViewModel = SuperHeroViewModel()

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
        val view = inflater.inflate(R.layout.fragment_search_list, container, false)

        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewSearch);

        model.getList().observe(viewLifecycleOwner, Observer<List<SuperHero>>{ shs ->
            recyclerView?.adapter = SuperHeroAdapter(model).apply {
                setOnItemClickListener(object : SuperHeroAdapter.ClickListener {
                    override fun onItemClick(position: Int, v: View?) {
                        val navController = findNavController()
                        val bundle = bundleOf("superhero" to model.getList().value?.get(position))
                        navController?.navigate(
                            R.id.action_searchListFragment_to_detailsFragment,
                            bundle
                        )
                    }
                })
            }
        })

        // Inflate the layout for this fragment
        return view
    }

    override fun onStart() {
        super.onStart()

        val activity = this.activity as MainActivity
        activity.changeMenu(R.menu.menu)
    }

    fun querySubmit(query: String?) {
        if (query != null) {
            model.getListName(query)
        }

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