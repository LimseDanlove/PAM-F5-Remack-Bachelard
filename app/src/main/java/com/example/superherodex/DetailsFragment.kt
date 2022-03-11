package com.example.superherodex

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import coil.load
import com.example.superherodex.model.SuperHero
import com.example.superherodex.service.SHService


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_SH = "superhero"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : Fragment() {
    private var sh: SuperHero? = null
    private lateinit var shService: SHService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            sh = it.get(ARG_SH) as SuperHero?
        }
        shService = SHService(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        // General infos
        val textName = view.findViewById<TextView>(R.id.name)
        val textFullName = view.findViewById<TextView>(R.id.full_name)
        val textGender = view.findViewById<TextView>(R.id.gender)
        val textRace = view.findViewById<TextView>(R.id.race)
        val textOccupation = view.findViewById<TextView>(R.id.occupation)
        val image = view.findViewById<ImageView>(R.id.imageView)

        textName.text = sh?.name
        textFullName.text = sh?.biography?.full_name
        textGender.text = sh?.appearance?.gender
        textRace.text = sh?.appearance?.race
        textOccupation.text = sh?.work?.occupation
        image.load(sh?.image?.url) {
            placeholder(R.drawable.ic_not_loaded)
        }


        // Physical appearance
        val textWeight = view.findViewById<TextView>(R.id.weight)
        val textHeight = view.findViewById<TextView>(R.id.height)
        val textEyeColor = view.findViewById<TextView>(R.id.eye_color)
        val textHairColor = view.findViewById<TextView>(R.id.hair_color)

        textWeight.text = sh?.appearance?.weight?.get(1)
        textHeight.text = sh?.appearance?.height?.get(1)
        textEyeColor.text = sh?.appearance?.eye_color
        textHairColor.text = sh?.appearance?.hair_color


        // Life in comics
        val textPublisher = view.findViewById<TextView>(R.id.publisher)
        val textFirstAppearance = view.findViewById<TextView>(R.id.first_appearance)
        val textAlignment = view.findViewById<TextView>(R.id.alignement)

        textPublisher.text = sh?.biography?.publisher
        textFirstAppearance.text = sh?.biography?.first_appearance
        textAlignment.text = sh?.biography?.alignment


        // Connections
        val textGroup = view.findViewById<TextView>(R.id.group)
        val textFamily = view.findViewById<TextView>(R.id.family)

        textGroup.text = sh?.connections?.group_affiliation
        textFamily.text = sh?.connections?.relatives


        return view
    }

    override fun onStart() {
        super.onStart()

        var found = false
        if (sh != null) {
            var foundSH = shService.get(sh!!)
            if(foundSH != null){
                found = true
            }
        }
        val activity = this.activity as MainActivity
        activity.changeMenu(R.menu.menu_details,found)
    }

    fun addFavorite() {
        if(sh != null) {
            var foundSH = shService.get(sh!!)
            if(foundSH == null) {
                shService.add(sh!!)
            }
        }
    }

    fun deleteFavorite(){
        if(sh != null) {
            var foundSH = shService.get(sh!!)
            if(foundSH != null) {
                shService.delete(sh!!)
            }
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param sh Superhero.
         * @return A new instance of fragment MainFragment.
         */
        @JvmStatic
        fun newInstance(sh: SuperHero) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    bundleOf(ARG_SH to sh)
                }
            }
    }
}