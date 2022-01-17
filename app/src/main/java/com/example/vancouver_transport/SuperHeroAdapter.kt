package com.example.vancouver_transport
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.vancouver_transport.SuperHeroAdapter.ClickListener
import android.os.Bundle


class SuperHeroAdapter(private val dataSet: LiveData<ArrayList<SuperHero>>) :
    RecyclerView.Adapter<SuperHeroAdapter.ViewHolder>() {

    // Static attribute
    companion object {
        private var clickListener: ClickListener? = null
    }

    /**
      * Provide a reference to the type of views that you are using
      * (custom ViewHolder).
    */
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
            val textView: TextView = view.findViewById(R.id.tv_sh_row)

            init {
                view.setOnClickListener(this)
            }

            override fun onClick(v: View?) {
                clickListener?.onItemClick(adapterPosition, v)
            }


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


        interface ClickListener {
            fun onItemClick(position: Int, v: View?)
        }

        fun setOnItemClickListener(clickListener: ClickListener) {
            Companion.clickListener = clickListener
        }

}


