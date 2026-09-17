package com.sensitivepro.app.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.sensitivepro.app.R
import com.sensitivepro.app.data.WeaponCategory
import com.sensitivepro.app.data.WeaponRepository
import com.sensitivepro.app.ui.adapters.WeaponAdapter

class WeaponsFragment : Fragment() {

    private lateinit var adapter: WeaponAdapter
    private lateinit var recycler: RecyclerView
    private lateinit var tabLayout: TabLayout
    private lateinit var etSearch: EditText
    private var currentCategory: WeaponCategory? = null
    private var currentQuery: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_weapons, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler = view.findViewById(R.id.recyclerWeapons)
        tabLayout = view.findViewById(R.id.tabLayout)
        etSearch = view.findViewById(R.id.etSearch)

        adapter = WeaponAdapter()
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        // Tabs
        tabLayout.addTab(tabLayout.newTab().setText("ទាំងអស់"))
        tabLayout.addTab(tabLayout.newTab().setText("SMG"))
        tabLayout.addTab(tabLayout.newTab().setText("Shotgun"))
        tabLayout.addTab(tabLayout.newTab().setText("Rifle"))
        tabLayout.addTab(tabLayout.newTab().setText("Marksman"))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                currentCategory = when (tab?.position) {
                    1 -> WeaponCategory.SMG
                    2 -> WeaponCategory.SHOTGUN
                    3 -> WeaponCategory.RIFLE
                    4 -> WeaponCategory.MARKSMAN
                    else -> null
                }
                filterAndUpdate()
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                currentQuery = s.toString()
                filterAndUpdate()
            }
        })

        // Initial load
        filterAndUpdate()
    }

    private fun filterAndUpdate() {
        var list = if (currentQuery.isNotBlank()) {
            WeaponRepository.search(currentQuery)
        } else {
            WeaponRepository.getAllWeapons()
        }

        if (currentCategory != null) {
            list = list.filter { it.category == currentCategory }
        }

        // If searching, show search results; category filter already applied
        adapter.submitList(list)
    }
}
