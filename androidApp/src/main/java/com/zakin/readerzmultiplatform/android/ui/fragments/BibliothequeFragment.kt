package com.zakin.readerzmultiplatform.android.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zakin.readerzmultiplatform.android.ui.adapters.list.CustomAdapter
import com.zakin.readerzmultiplatform.android.databinding.FragmentBibliothequeBinding
import com.zakin.readerzmultiplatform.android.models.data.MangaViewModel
import com.zakin.readerzmultiplatform.android.ui.adapters.list.MangaAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [BibliothequeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BibliothequeFragment : Fragment() {

    private var binding: FragmentBibliothequeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                Text(text = "Hello world.")
            }
        }

        /*binding = FragmentBibliothequeBinding.inflate(inflater)

        // getting the recyclerview by its id
        val recyclerview: RecyclerView = binding!!.fragmentBibliothequeRecycler
        val textview: TextView = binding!!.fragmentBibliothequeTextView

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(context)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<MangaViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..3) {
            data.add(MangaViewModel("Item " + i, i))
        }

        if(data.isEmpty()) {
            recyclerview.visibility = View.INVISIBLE
        } else {
            textview.visibility = View.INVISIBLE
        }

        // This will pass the ArrayList to our Adapter
        val adapter = MangaAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

        val searchBtn = binding!!.fragmentBibliothequeToolbarSearchBtn
        val backBtn = binding!!.fragmentBibliothequeToolbarBackBtn
        val searchEdit = binding!!.fragmentBibliothequeToolbarSearchEdit
        val toolbarTitle = binding!!.fragmentBibliothequeToolbarTitle

        searchEdit.visibility = View.INVISIBLE
        backBtn.visibility = View.INVISIBLE

        searchBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                searchEdit.visibility = View.VISIBLE
                backBtn.visibility = View.VISIBLE
                searchBtn.visibility = View.INVISIBLE
                toolbarTitle.visibility = View.INVISIBLE
            }
        })

        backBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                searchEdit.visibility = View.INVISIBLE
                backBtn.visibility = View.INVISIBLE
                searchBtn.visibility = View.VISIBLE
                toolbarTitle.visibility = View.VISIBLE
            }
        })

        return binding?.root*/
    }
}