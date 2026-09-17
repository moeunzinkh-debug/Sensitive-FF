package com.sensitivepro.app.ui.fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.sensitivepro.app.R
import com.sensitivepro.app.data.DeviceDetector

class GeneratorFragment : Fragment() {

    private lateinit var seekScreen: SeekBar
    private lateinit var seekRam: SeekBar
    private lateinit var seekDpi: SeekBar
    private lateinit var tvScreenValue: TextView
    private lateinit var tvRamValue: TextView
    private lateinit var tvDpiValue: TextView
    private lateinit var rgFinger: RadioGroup
    private lateinit var rgStyle: RadioGroup
    private lateinit var cardResult: CardView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_generator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        seekScreen = view.findViewById(R.id.seekScreen)
        seekRam = view.findViewById(R.id.seekRam)
        seekDpi = view.findViewById(R.id.seekDpi)
        tvScreenValue = view.findViewById(R.id.tvScreenValue)
        tvRamValue = view.findViewById(R.id.tvRamValue)
        tvDpiValue = view.findViewById(R.id.tvDpiValue)
        rgFinger = view.findViewById(R.id.rgFinger)
        rgStyle = view.findViewById(R.id.rgStyle)
        cardResult = view.findViewById(R.id.cardResult)

        // Listeners for seekbars
        seekScreen.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(s: SeekBar?, p: Int, fromUser: Boolean) {
                val size = 4.5f + p * 0.05f // 4.5 to 6.5
                tvScreenValue.text = String.format("%.1f អ៊ីញ", size)
            }
            override fun onStartTrackingTouch(s: SeekBar?) {}
            override fun onStopTrackingTouch(s: SeekBar?) {}
        })
        seekRam.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(s: SeekBar?, p: Int, fromUser: Boolean) {
                val ram = 2 + p // 2-14
                tvRamValue.text = "$ram GB"
            }
            override fun onStartTrackingTouch(s: SeekBar?) {}
            override fun onStopTrackingTouch(s: SeekBar?) {}
        })
        seekDpi.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(s: SeekBar?, p: Int, fromUser: Boolean) {
                val dpi = 320 + p * 3 // 320-620
                tvDpiValue.text = "$dpi DPI"
            }
            override fun onStartTrackingTouch(s: SeekBar?) {}
            override fun onStopTrackingTouch(s: SeekBar?) {}
        })

        // Initial values
        tvScreenValue.text = "6.5 អ៊ីញ"
        tvRamValue.text = "8 GB"
        tvDpiValue.text = "440 DPI"
        // Set progress to match
        seekScreen.progress = 40 // 6.5
        seekRam.progress = 6 // 8GB
        seekDpi.progress = 40 // 440

        view.findViewById<Button>(R.id.btnGenerate).setOnClickListener {
            generate(view)
        }

        view.findViewById<Button>(R.id.btnCopyGen).setOnClickListener {
            copyResult(view)
        }
        view.findViewById<Button>(R.id.btnShareGen).setOnClickListener {
            shareResult(view)
        }
    }

    private fun generate(view: View) {
        val screen = 4.5f + seekScreen.progress * 0.05f
        val ram = 2 + seekRam.progress
        val dpi = 320 + seekDpi.progress * 3

        val finger = when (rgFinger.checkedRadioButtonId) {
            R.id.rb3Finger -> 3
            R.id.rb4Finger -> 4
            else -> 2
        }
        val style = when (rgStyle.checkedRadioButtonId) {
            R.id.rbBalanced -> "balanced"
            R.id.rbSniper -> "sniper"
            else -> "rusher"
        }

        val sens = DeviceDetector.generateCustomSensitivity(screen, ram, dpi, finger, style)
        val explain = DeviceDetector.getGeneratorExplain(screen, ram, dpi, finger, style, sens)

        // Update UI
        view.findViewById<TextView>(R.id.tvResGeneral).text = sens.general.toString()
        view.findViewById<TextView>(R.id.tvResRedDot).text = sens.redDot.toString()
        view.findViewById<TextView>(R.id.tvRes2x).text = sens.scope2x.toString()
        view.findViewById<TextView>(R.id.tvRes4x).text = sens.scope4x.toString()
        view.findViewById<TextView>(R.id.tvResSniper).text = sens.sniper.toString()
        view.findViewById<TextView>(R.id.tvResFreeLook).text = sens.freeLook.toString()

        val styleKh = when (style) { "rusher" -> "វាយលុក" ; "sniper" -> "ចម្ងាយឆ្ងាយ" ; else -> "តុល្យភាព" }
        val fingerKh = when (finger) { 4 -> "4 ម្រាម" ; 3 -> "3 ម្រាម" ; else -> "2 ម្រាម" }
        view.findViewById<TextView>(R.id.tvResultSubtitle).text = "អេក្រង់ ${String.format("%.1f", screen)}\" • ${ram}GB • ${dpi} DPI • ${fingerKh} • ${styleKh}"
        view.findViewById<TextView>(R.id.tvExplain).text = explain

        cardResult.visibility = View.VISIBLE
        // Store for copy/share via tag
        cardResult.tag = sens

        Toast.makeText(requireContext(), "⚡ បានបង្កើតសែនស៊ីធីវ!", Toast.LENGTH_SHORT).show()
    }

    private fun copyResult(view: View) {
        val general = view.findViewById<TextView>(R.id.tvResGeneral).text
        val redDot = view.findViewById<TextView>(R.id.tvResRedDot).text
        val x2 = view.findViewById<TextView>(R.id.tvRes2x).text
        val x4 = view.findViewById<TextView>(R.id.tvRes4x).text
        val sniper = view.findViewById<TextView>(R.id.tvResSniper).text
        val freeLook = view.findViewById<TextView>(R.id.tvResFreeLook).text
        val subtitle = view.findViewById<TextView>(R.id.tvResultSubtitle).text

        val text = """
            🎯 Sensitive Pro - ម៉ូដែលសែនស៊ីធីវអូស
            $subtitle
            
            ⚙️ ការកំណត់:
            • ទូទៅ: $general
            • Red Dot: $redDot
            • 2x Scope: $x2
            • 4x Scope: $x4
            • Sniper: $sniper
            • Free Look: $freeLook
            
            📌 Free Fire > Settings > Sensitivity
            🇰🇭 Sensitive Pro
        """.trimIndent()

        val clipboard = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.setPrimaryClip(ClipData.newPlainText("Sensitive", text))
        Toast.makeText(requireContext(), "📋 បានចម្លង!", Toast.LENGTH_SHORT).show()
    }

    private fun shareResult(view: View) {
        val general = view.findViewById<TextView>(R.id.tvResGeneral).text
        val redDot = view.findViewById<TextView>(R.id.tvResRedDot).text
        val x2 = view.findViewById<TextView>(R.id.tvRes2x).text
        val x4 = view.findViewById<TextView>(R.id.tvRes4x).text
        val sniper = view.findViewById<TextView>(R.id.tvResSniper).text
        val freeLook = view.findViewById<TextView>(R.id.tvResFreeLook).text
        val explain = view.findViewById<TextView>(R.id.tvExplain).text

        val shareText = """
            🎯 Sensitive Pro - សែនស៊ីធីវរបស់ខ្ញុំ
            
            ទូទៅ: $general | Red Dot: $redDot | 2x: $x2 | 4x: $x4 | Sniper: $sniper | Free Look: $freeLook
            
            $explain
            
            📱 ទាញយក Sensitive Pro ដើម្បីបង្កើតសែនស៊ីធីវរបស់អ្នក!
        """.trimIndent()

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, shareText)
        }
        startActivity(Intent.createChooser(intent, "ចែករំលែកសែនស៊ីធីវ"))
    }
}
