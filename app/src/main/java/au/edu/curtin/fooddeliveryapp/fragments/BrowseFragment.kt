package au.edu.curtin.fooddeliveryapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import au.edu.curtin.fooddeliveryapp.R
import au.edu.curtin.fooddeliveryapp.classes.Restaurant


class BrowseFragment(private val data : ArrayList<Restaurant>) : Fragment() {

    private lateinit var adapter: Adapter
    private lateinit var recyclerView: RecyclerView
    private  lateinit var restaurantList: ArrayList<Restaurant>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_browse, container, false)
        val rv = view.findViewById<RecyclerView>(R.id.restaurant_recycler)
        rv.layoutManager = LinearLayoutManager(context)
        rv.adapter = Adapter(data)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.restaurant_recycler)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = Adapter((data))
        recyclerView.adapter = adapter
    }

}