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

class DataResultAdapter(
    var statusList: MutableList<RemedyDataModel>,
    private val callbacks: Callbacks? = null
) : RecyclerView.Adapter<DataResultAdapter.ViewHolder>() {
    lateinit var mContext: Context

    fun setData(list: MutableList<RemedyDataModel>) {
        statusList.clear()
        statusList.addAll(list)
        notifyDataSetChanged()
    }

    interface Callbacks {
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
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.model = statusList[position]
        holder.mapTestStatus(statusList[position])
        holder.itemView.visibility = View.VISIBLE

    }

    override fun getItemCount(): Int {
        return statusList.size
    }

    inner class ViewHolder(val binding: ItemInformationActivityBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun mapTestStatus(statusModel: RemedyDataModel): RemedyDataModel {
            return statusModel
        }
    }
}


























