package com.example.mytestapp.presentation.feature.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.R
import com.example.mytestapp.data.model.UserEntity
import kotlinx.android.synthetic.main.item_icon.view.*

class AdapterList :
    RecyclerView.Adapter<AdapterList.FeatureViewHolder>() {

    var onSelectItem: ((List<Int>?) -> Unit)? = null
    val selectedIds: MutableList<Int> = mutableListOf()
    var data: List<UserEntity> = arrayListOf()

    fun loadData(content:List<UserEntity>) {
        this.data = content
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FeatureViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_icon, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: FeatureViewHolder, position: Int) {
        if (holder is ViewHolder) {
            val item = data[holder.bindingAdapterPosition]
            holder.bind(item)
        }
    }

    open class FeatureViewHolder(view: View) : RecyclerView.ViewHolder(view)

    inner class ViewHolder(view: View) : FeatureViewHolder(view) {
        fun bind(data: UserEntity) = with(itemView) {
            tvItem.text = data.name
//            checkbox.isChecked = false
            checkbox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    selectedIds.add(data.id)
                } else {
                    selectedIds.remove(data.id)
                }
                onSelectItem?.invoke(selectedIds.toList())
            }
        }
    }




}

