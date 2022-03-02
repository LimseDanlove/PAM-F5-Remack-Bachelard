package com.example.superherodex

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.example.superherodex.model.SuperHero


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var sh: SuperHero? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            sh = it.get("superhero") as SuperHero?
        }
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

        val activity = this.activity as MainActivity
        activity.changeMenu(R.menu.menu_details)
    }

    fun addFavorite() {
        val db = AppDatabase.getInstance(requireContext());
        val shDao = db.shDao()


        shDao.insert(SuperHeroData(sh!!.id, sh!!.name, sh!!.biography.full_name, sh!!.biography.first_appearance, sh!!.biography.publisher, sh!!.biography.alignment,
            sh!!.appearance.height[0]!!, sh!!.appearance.height[1]!!, sh!!.appearance.weight[0]!!, sh!!.appearance.weight[1]!!, sh!!.appearance.eye_color, sh!!.appearance.hair_color, sh!!.appearance.gender, sh!!.appearance.race, sh!!.work.occupation, sh!!.image.url, sh!!.connections.group_affiliation, sh!!.connections.relatives ))
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}