package com.example.superherodex

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.superherodex.model.SuperHero
import androidx.core.os.bundleOf
import androidx.core.view.isEmpty
import com.example.superherodex.service.SHService


/**
 * A simple [Fragment] subclass.
 * Use the [SHListFragment.newInstance] factory method to
 * create an instance of this fragment..
 */
class SHListFragment : Fragment() {

    private lateinit var shService: SHService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shService = SHService(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sh_list, container, false)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewSH);
        val model = SuperHeroViewModel()

        model.replaceList(shService.getAll())

        model.getList().observe(viewLifecycleOwner, Observer<List<SuperHero>>{ shs ->
            recyclerView?.adapter = SuperHeroAdapter(model).apply {
                setOnItemClickListener(object : SuperHeroAdapter.ClickListener {
                    override fun onItemClick(position: Int, v: View?) {
                        val navController = findNavController()
                        val bundle = bundleOf("superhero" to model.getList().value?.get(position))
                        navController?.navigate(R.id.action_shListFragment_to_detailsFragment, bundle)
                    }
                })
            }
        })
        return view
    }

    override fun onStart() {
        super.onStart()

        val activity = this.activity as MainActivity
        activity.changeMenu(R.menu.menu,false)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment StopListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            SHListFragment()
    }
}