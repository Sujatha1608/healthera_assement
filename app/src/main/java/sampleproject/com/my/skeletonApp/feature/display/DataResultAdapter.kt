package sampleproject.com.my.skeletonApp.feature.display

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import sampleproject.com.my.skeletonApp.R
import sampleproject.com.my.skeletonApp.databinding.ItemInformationActivityBinding
import sampleproject.com.my.skeletonApp.rest.model.models.RemedyDataModel

class DataResultAdapter(statusList: List<RemedyDataModel>, private val callbacks: Callbacks? = null) : RecyclerView.Adapter<DataResultAdapter.ViewHolder>() {
    var items = listOf<RemedyDataModel>()

    lateinit var mContext: Context

    init {
        items = statusList
    }

    fun setData(list: List<RemedyDataModel>) {
        items = list
    }
    interface Callbacks{
        fun onItemClick(view: View, item: RemedyDataModel)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        mContext = parent.context
        val binding: ItemInformationActivityBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.item_information_activity,
            parent,
            false
        )
        return ViewHolder(binding,viewType)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.model = items[position]
        holder.mapTestStatus(items[position],holder)
        holder.itemView.visibility = View.VISIBLE

    }
    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(val binding: ItemInformationActivityBinding,viewType: Int) : RecyclerView.ViewHolder(binding.root) {

        fun mapTestStatus(statusModel: RemedyDataModel, holder: ViewHolder): RemedyDataModel {
            return statusModel
        }

    }



}


























