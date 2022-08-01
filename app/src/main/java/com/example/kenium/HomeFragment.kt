package com.example.kenium

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.forEach
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kenium.adapters.BannerAdapter
import com.example.kenium.model.Article
import com.example.kenium.model.Banner
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.firebase.ktx.Firebase
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
    private lateinit var myContent:RecyclerView
    private lateinit var myChips:ChipGroup
    private lateinit var filterText:TextView

    private var bannerList : ArrayList<Banner> = ArrayList()
    private var contentsList : ArrayList<Article> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val  v =  inflater.inflate(R.layout.fragment_home, container, false)

        filterText = v.findViewById(R.id.filterText)
//        initalize the Recycler view
        banners = v.findViewById(R.id.banners)
        myContent = v.findViewById(R.id.myContent)

        myChips = v.findViewById(R.id.chipGroup)

        myChips.forEach {
            it.setOnClickListener{

            }
        }


//        create a banner list
        bannerList.add(Banner("Computer Science","Come and Learn about the New Wave of Technology",R.drawable.cs))
        bannerList.add(Banner("Medicine","Find what's new in the world of medical Science",R.drawable.ns))
        bannerList.add(Banner("Business","The Stock Market is Booming , New Ideas ?",R.drawable.bs2))
        bannerList.add(Banner("Sports","Premier League, UEFA and many more sport leagues . GET Lit",R.drawable.sp3))



        lifecycleScope.launch{
            banners.adapter = BannerAdapter(requireContext(),bannerList)

        }

        banners.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)






        return v
    }




}