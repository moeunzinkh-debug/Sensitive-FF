package com.sensitivepro.app.ui.adapters

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sensitivepro.app.data.Weapon
import com.sensitivepro.app.data.WeaponCategory
import com.sensitivepro.app.databinding.ItemWeaponBinding

class WeaponAdapter : ListAdapter<Weapon, WeaponAdapter.WeaponViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponViewHolder {
        val binding = ItemWeaponBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeaponViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeaponViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class WeaponViewHolder(private val binding: ItemWeaponBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(weapon: Weapon) {
            binding.tvWeaponName.text = weapon.name
            val catText = when (weapon.category) {
                WeaponCategory.SMG -> "SMG • កាំភ្លើងខ្លីបាញ់លឿន"
                WeaponCategory.SHOTGUN -> "Shotgun • កាំភ្លើងបាញ់គ្រាប់ធំ"
                WeaponCategory.RIFLE -> "Rifle • កាំភ្លើងវែង"
                WeaponCategory.MARKSMAN -> "Marksman • កាំភ្លើងបាញ់ចម្ងាយឆ្ងាយ"
            }
            binding.tvWeaponCategory.text = catText
            binding.tvIcon.text = when (weapon.category) {
                WeaponCategory.SMG -> "🔫"
                WeaponCategory.SHOTGUN -> "💥"
                WeaponCategory.RIFLE -> "🎯"
                WeaponCategory.MARKSMAN -> "🔭"
            }
            binding.tvGeneral.text = weapon.sensitivity.general.toString()
            binding.tvRedDot.text = weapon.sensitivity.redDot.toString()
            binding.tv2x.text = weapon.sensitivity.scope2x.toString()
            binding.tv4x.text = weapon.sensitivity.scope4x.toString()
            binding.tvSniper.text = weapon.sensitivity.sniper.toString()
            binding.tvDrag.text = weapon.sensitivity.drag.toString()

            binding.tvDescription.text = weapon.descriptionKh
            binding.tvTips.text = weapon.tipsKh

            binding.btnCopy.setOnClickListener {
                val ctx = binding.root.context
                val text = """
                    ${weapon.name} - ${catText}
                    ទូទៅ: ${weapon.sensitivity.general}
                    Red Dot: ${weapon.sensitivity.redDot}
                    2x Scope: ${weapon.sensitivity.scope2x}
                    4x Scope: ${weapon.sensitivity.scope4x}
                    Sniper: ${weapon.sensitivity.sniper}
                    Free Look: ${weapon.sensitivity.freeLook}
                    អូស Drag: ${weapon.sensitivity.drag}
                    
                    📱 Sensitive Pro - សែនស៊ីធីវ ប្រូ
                """.trimIndent()
                copyToClipboard(ctx, text)
                Toast.makeText(ctx, "✅ បានចម្លង ${weapon.name}!", Toast.LENGTH_SHORT).show()
            }
        }

        private fun copyToClipboard(context: Context, text: String) {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Sensitivity", text)
            clipboard.setPrimaryClip(clip)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Weapon>() {
        override fun areItemsTheSame(oldItem: Weapon, newItem: Weapon): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Weapon, newItem: Weapon): Boolean = oldItem == newItem
    }
}
