package au.edu.curtin.fooddeliveryapp.fragments.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import au.edu.curtin.fooddeliveryapp.R
import com.bumptech.glide.Glide

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var image = view.findViewById<ImageView>(R.id.home_image)

        Glide.with(this).load("https://images.pexels.com/photos/3686790/pexels-photo-3686790.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1").into(image)
    }
}