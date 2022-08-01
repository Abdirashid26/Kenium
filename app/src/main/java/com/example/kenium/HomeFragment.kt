package com.example.kenium

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kenium.adapters.BannerAdapter
import com.example.kenium.model.Banner
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private lateinit var banners:RecyclerView

    private var bannerList : ArrayList<Banner> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val  v =  inflater.inflate(R.layout.fragment_home, container, false)

//        initalize the banner class
        banners = v.findViewById(R.id.banners)

//        create a banner list
        bannerList.add(Banner("Computer Science","Come and Learn about the New Wave of Technology",R.drawable.cs))
        bannerList.add(Banner("Medicine","Find what's new in the world of medical Science",R.drawable.ns))
        bannerList.add(Banner("Business","The Stock Market is Booming , New Ideas ?",R.drawable.bs))


        lifecycleScope.launch{
            banners.adapter = BannerAdapter(requireContext(),bannerList)
        }

        banners.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)





        return v
    }




}