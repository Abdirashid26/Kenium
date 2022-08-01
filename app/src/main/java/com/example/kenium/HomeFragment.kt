package com.example.kenium

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.forEach
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kenium.adapters.ArticleAdapter
import com.example.kenium.adapters.BannerAdapter
import com.example.kenium.model.Article
import com.example.kenium.model.Banner
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
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
    private lateinit var cs:Chip
    private lateinit var bs:Chip
    private lateinit var sp:Chip
    private lateinit var ns:Chip
    private val mFirestore  :FirebaseFirestore = FirebaseFirestore.getInstance()
    lateinit var  artcileAdapter: ArticleAdapter


    private var bannerList : ArrayList<Banner> = ArrayList()
    private var contentsList : MutableList<Article> = mutableListOf<Article>()
    private var chipsList : ArrayList<Chip> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val  v =  inflater.inflate(R.layout.fragment_home, container, false)

        filterText = v.findViewById(R.id.filterText)
//        initalize the Recycler view
        banners = v.findViewById(R.id.banners)
        myContent = v.findViewById(R.id.myContent)

        myChips = v.findViewById(R.id.chipGroup)
        cs = v.findViewById(R.id.cs)
        bs = v.findViewById(R.id.bs)
        ns = v.findViewById(R.id.ns)
        sp = v.findViewById(R.id.sp)

        artcileAdapter  = ArticleAdapter(requireContext(),contentsList)

        myContent.adapter = artcileAdapter

        myContent.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)



        chipsList.add(bs)
        chipsList.add(ns)
        chipsList.add(sp)
        chipsList.add(cs)




        chipsList.forEach {
            var text = it.text.toString()
            it.chipBackgroundColor = setChipBackgroundColor(
                checkedColor = Color.BLACK,
                unCheckedColor = Color.GRAY
            )
            it.isChecked = true
            it.setOnClickListener {
                filterText.text = text
                lifecycleScope.launch(Dispatchers.IO){
                    getArtciles(it.id)

                }
            }
        }

        lifecycleScope.launch(Dispatchers.IO){
            getArtciles(R.id.cs)

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


    suspend fun getArtciles(artcileId :Int){
        if(artcileId == R.id.cs){
            mFirestore.collection("csArticles").addSnapshotListener { value, error ->
                if(value != null && error == null){
                    contentsList.clear()
                    var artciles = value.toObjects(Article::class.java)
                    contentsList.addAll(artciles)
                    artcileAdapter.notifyDataSetChanged()
                }
                else{
                    Toast.makeText(requireContext(),"Error : " + error,Toast.LENGTH_SHORT).show()
                }
            }
        }
        else if(artcileId == R.id.bs){
            mFirestore.collection("bsArticles").addSnapshotListener { value, error ->
                if(value != null && error == null){
                    contentsList.clear()
                    var artciles = value.toObjects(Article::class.java)
                    contentsList.addAll(artciles)
                    artcileAdapter.notifyDataSetChanged()
                }
                else{
                    Toast.makeText(requireContext(),"Error : " + error,Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    fun setChipBackgroundColor(
        checkedColor: Int = Color.BLACK,
        unCheckedColor: Int = Color.GRAY
    ): ColorStateList{
        val states = arrayOf(
            intArrayOf(android.R.attr.state_checked),
            intArrayOf(-android.R.attr.state_checked)
        )

        val colors = intArrayOf(
            // chip checked background color
            checkedColor,
            // chip unchecked background color
            unCheckedColor
        )

        return ColorStateList(states, colors)
    }


}