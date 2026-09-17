package com.sensitivepro.app.ui.fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.sensitivepro.app.R
import com.sensitivepro.app.data.DeviceDetector

class DeviceFragment : Fragment() {

    private var isScanned = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_device, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnScan = view.findViewById<Button>(R.id.btnScan)
        val cardInfo = view.findViewById<CardView>(R.id.cardDeviceInfo)
        val cardRecommend = view.findViewById<CardView>(R.id.cardRecommend)

        btnScan.setOnClickListener {
            btnScan.text = "⏳ កំពុងស្គេន..."
            btnScan.isEnabled = false
            // Simulate scanning delay for UX
            Handler(Looper.getMainLooper()).postDelayed({
                scanDevice(view)
                btnScan.text = "✅ បានស្គេនរួចរាល់ - ស្គេនម្តងទៀត"
                btnScan.isEnabled = true
                cardInfo.visibility = View.VISIBLE
                cardRecommend.visibility = View.VISIBLE
                isScanned = true
            }, 1200)
        }

        view.findViewById<Button>(R.id.btnCopy)?.setOnClickListener {
            copySensitivity(view)
        }

        // Auto scan on first load after short delay for better UX? No, require manual
    }

    private fun scanDevice(view: View) {
        val info = DeviceDetector.getDeviceInfo(requireContext())
        val sens = DeviceDetector.getRecommendedSensitivity(info.performanceLevel)
        val tip = DeviceDetector.getTipForLevel(info.performanceLevel)

        view.findViewById<TextView>(R.id.tvModel).text = info.model
        view.findViewById<TextView>(R.id.tvManufacturer).text = info.manufacturer
        view.findViewById<TextView>(R.id.tvAndroid).text = info.androidVersion
        view.findViewById<TextView>(R.id.tvRam).text = "${info.ramGB} GB"
        view.findViewById<TextView>(R.id.tvCpu).text = info.cpuInfo
        view.findViewById<TextView>(R.id.tvScreen).text = info.screenSize
        view.findViewById<TextView>(R.id.tvRefresh).text = "${info.refreshRate.toInt()} Hz"
        view.findViewById<TextView>(R.id.tvDpi).text = "${info.dpi} DPI"

        view.findViewById<TextView>(R.id.tvPerformance).text = info.performanceLevel.kh
        view.findViewById<TextView>(R.id.tvPerformanceExplain).text = info.performanceLevel.explainKh

        view.findViewById<TextView>(R.id.tvGeneral).text = sens.general.toString()
        view.findViewById<TextView>(R.id.tvRedDot).text = sens.redDot.toString()
        view.findViewById<TextView>(R.id.tv2x).text = sens.scope2x.toString()
        view.findViewById<TextView>(R.id.tv4x).text = sens.scope4x.toString()
        view.findViewById<TextView>(R.id.tvSniper).text = sens.sniper.toString()
        view.findViewById<TextView>(R.id.tvFreeLook).text = sens.freeLook.toString()
        view.findViewById<TextView>(R.id.tvTip).text = tip

        // Animate cards? just toast
        Toast.makeText(requireContext(), "✅ ស្គេនបានជោគជ័យ! ${info.manufacturer} ${info.model}", Toast.LENGTH_SHORT).show()
    }

    private fun copySensitivity(view: View) {
        val general = view.findViewById<TextView>(R.id.tvGeneral).text
        val redDot = view.findViewById<TextView>(R.id.tvRedDot).text
        val x2 = view.findViewById<TextView>(R.id.tv2x).text
        val x4 = view.findViewById<TextView>(R.id.tv4x).text
        val sniper = view.findViewById<TextView>(R.id.tvSniper).text
        val freeLook = view.findViewById<TextView>(R.id.tvFreeLook).text
        val model = view.findViewById<TextView>(R.id.tvModel).text
        val level = view.findViewById<TextView>(R.id.tvPerformance).text

        val text = """
            🎯 Sensitive Pro - សែនស៊ីធីវ ប្រូ
            📱 ទូរស័ព្ទ: $model ($level)
            
            ⚙️ ការកំណត់សែនស៊ីធីវ:
            • ទូទៅ: $general
            • Red Dot: $redDot
            • 2x Scope: $x2
            • 4x Scope: $x4
            • Sniper Scope: $sniper
            • Free Look: $freeLook
            
            📌 ចម្លងទៅ Free Fire > Settings > Sensitivity
            🇰🇭 Sensitive Pro - សម្រាប់សហគមន៍ខ្មែរ
        """.trimIndent()

        val clipboard = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.setPrimaryClip(ClipData.newPlainText("Sensitive", text))
        Toast.makeText(requireContext(), "📋 បានចម្លងការកំណត់!", Toast.LENGTH_SHORT).show()
    }
}
