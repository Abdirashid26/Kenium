package com.example.kenium

import android.app.Application
import android.opengl.Visibility
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kenium.adapters.ArticleAdapter
import com.example.kenium.adapters.ItemAdapter
import com.example.kenium.model.Article
import com.example.kenium.model.Item
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DownloadsFragment : Fragment() {

    lateinit var sellItems: RecyclerView
    lateinit var sellItems2: RecyclerView
    private var mFirestore = FirebaseFirestore.getInstance()
    lateinit var myItemsList:MutableList<Item>
    lateinit var myPhoones:MutableList<Item>
    lateinit var itemAdapter: ItemAdapter
    lateinit var itemAdapter2: ItemAdapter
    lateinit var progress1 : ProgressBar
    lateinit var progress2 : ProgressBar



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_downloads, container, false)

        myItemsList = mutableListOf()
        myPhoones  = mutableListOf()

        itemAdapter = ItemAdapter(requireContext(),myItemsList)
        itemAdapter2 = ItemAdapter(requireContext(),myPhoones)

        progress1 = v.findViewById(R.id.progress_circular)
        progress2 = v.findViewById(R.id.progress_circular2)

        progress1.visibility = View.VISIBLE
        progress2.visibility  = View.VISIBLE

        sellItems = v.findViewById(R.id.sellitems)
        sellItems2 = v.findViewById(R.id.sellitems2)

        lifecycleScope.launch(Dispatchers.IO){
            getAllItems()
        }

        sellItems.adapter = itemAdapter

        sellItems2.adapter = itemAdapter2

        sellItems.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        sellItems2.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)



        return v
    }


    suspend fun getAllItems(){
        mFirestore.collection("items").addSnapshotListener { value, error ->
            progress1.visibility = View.GONE
            if(value != null && error == null){
                myItemsList.clear()
                val items = value.toObjects(Item::class.java)
                myItemsList.addAll(items)
                itemAdapter.notifyDataSetChanged()
            }
        }
        mFirestore.collection("phones").addSnapshotListener { value, error ->
            progress2.visibility = View.GONE
            if(value != null && error == null){
                myPhoones.clear()
                val items = value.toObjects(Item::class.java)
                myPhoones.addAll(items)
                itemAdapter2.notifyDataSetChanged()
            }
        }
    }


}