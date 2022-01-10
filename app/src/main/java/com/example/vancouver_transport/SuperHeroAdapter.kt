package com.example.vancouver_transport
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView

class SuperHeroAdapter(private val dataSet: LiveData<ArrayList<SuperHero>>) :
    RecyclerView.Adapter<SuperHeroAdapter.ViewHolder>() {

    /**
      * Provide a reference to the type of views that you are using
      * (custom ViewHolder).
    */
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textView: TextView = view.findViewById(R.id.tv_sh_row)
            /*private var currentRow : String? = null

            init {
                // Define click listener for the ViewHolder's View.
                view.setOnClickListener {
                    Toast.makeText(view.context, "Pouet", Toast.LENGTH_SHORT).show()
                }
            }*/
        }

        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            // Create a new view, which defines the UI of the list item
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.sh_row_item, viewGroup, false)
            return ViewHolder(view)
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

            // Get element from your dataset at this position and replace the
            // contents of the view with that element
            viewHolder.textView.text = dataSet.value?.get(position).toString()
        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = dataSet.value?.size ?: 0

}
