package com.example.superherodex

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.superherodex.model.SuperHero
import com.example.superherodex.service.SHService


class SearchListFragment : Fragment() {

    private var model: SuperHeroViewModel = SuperHeroViewModel()
    private lateinit var shService: SHService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shService = SHService(requireContext())
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
                        navController?.navigate(R.id.action_searchListFragment_to_detailsFragment, bundle)
                    }
                })
            }
        })

        return view
    }

    override fun onStart() {
        super.onStart()

        // Changing menu appearance to display search icon
        val activity = this.activity as MainActivity
        activity.changeMenu(R.menu.menu,false)
    }

    fun querySubmit(query: String?) {
        if (query != null) {
            model.replaceList(shService.getName(query))
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment StopListFragment.
         */
        @JvmStatic
        fun newInstance() =
            SearchListFragment()
    }
}