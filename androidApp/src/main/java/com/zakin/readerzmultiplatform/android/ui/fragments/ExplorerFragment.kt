package com.zakin.readerzmultiplatform.android.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zakin.readerzmultiplatform.android.models.data.ItemsViewModel
import com.zakin.readerzmultiplatform.android.ui.adapters.list.CustomAdapter
import com.zakin.readerzmultiplatform.android.databinding.FragmentExplorerBinding
import com.zakin.readerzmultiplatform.android.models.Site
import com.zakin.readerzmultiplatform.android.models.enums.Sites
import com.zakin.readerzmultiplatform.android.ui.adapters.list.SiteAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [ExplorerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExplorerFragment : Fragment() {
    private var binding: FragmentExplorerBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExplorerBinding.inflate(inflater)

        // getting the recyclerview by its id
        val recyclerview: RecyclerView = binding!!.fragmentExplorerRecycler

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(context)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<Site>()

        val sites = Sites.values()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in sites) {
            data.add(Site(i.siteName, i.url))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = SiteAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
        return binding?.root
    }
}