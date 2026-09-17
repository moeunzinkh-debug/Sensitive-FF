package com.sensitivepro.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.sensitivepro.app.MainActivity
import com.sensitivepro.app.R

class HomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btnGoDevice)?.setOnClickListener {
            (activity as? MainActivity)?.navigateToDevice()
        }
        view.findViewById<Button>(R.id.btnGoWeapons)?.setOnClickListener {
            (activity as? MainActivity)?.navigateToWeapons()
        }
    }
}
