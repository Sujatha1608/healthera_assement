package sampleproject.com.my.skeletonApp.feature.display

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_display.*
import sampleproject.com.my.skeletonApp.AppPreference
import sampleproject.com.my.skeletonApp.R
import sampleproject.com.my.skeletonApp.core.BaseActivity
import sampleproject.com.my.skeletonApp.databinding.ActivityDisplayBinding
import sampleproject.com.my.skeletonApp.rest.model.models.RemedyDataModel
import javax.inject.Inject

class DisplayInfoActivity : BaseActivity(), DataResultAdapter.Callbacks {

    @Inject
    lateinit var viewModel: DisplayInfoViewModel

    @Inject
    lateinit var appPreference: AppPreference

    lateinit var mAdapter: DataResultAdapter

    private val datesList: MutableSet<String?> = HashSet()
    private val displayListData: MutableList<RemedyDataModel> = ArrayList()
    private val remediesList: MutableList<RemedyDataModel> = ArrayList()
    private lateinit var binding: ActivityDisplayBinding
    private var selectedPosition = 0
    private val listData: MutableList<MutableList<Any>> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_display)
        binding.viewModel = viewModel
        setupEvent()
    }

    private fun setupEvent() {
        viewModel.callBack = object : DisplayInfoViewModel.ViewModelCallBack {
            override fun updateRecyclerView(update: Boolean) {
                mAdapter.notifyDataSetChanged()
            }

        }
        viewModel.closeEvent.observe(this){
            finish()
        }
        viewModel.leftClickEvent.observe(this){
            previousSelected()
        }
        viewModel.rightClickEvent.observe(this){
            nextSelected()
        }
        viewModel.errorEvent.observe(this) {
            if (it.isNotEmpty()) {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loadingDialogEvent.observe(this) {
            if (isLoadingDialogShown() || !it) {
                dismissLoadingDialog()
            } else showLoadingDialog()
        }
        viewModel.resultDetails().observe(this) {
            //
            remediesList.clear()
            remediesList.addAll(it)
            updateUI()
        }

        viewModel.adherenceListLiveData.observe(this) {
            datesList.clear()
            datesList.addAll(it)
            updateUI()
        }

        recyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        mAdapter = DataResultAdapter(ArrayList(), this)
        recyclerView.adapter = mAdapter
    }

    private fun nextSelected() {
        if (selectedPosition < listData.size) {
            selectedPosition++
            displayUI(selectedPosition)
            when (selectedPosition) {
                listData.size - 1 -> binding.ivNextImage.visibility = View.GONE
                1 -> binding.ivPreviousImage.visibility = View.VISIBLE
                else -> binding.ivNextImage.visibility = View.VISIBLE
            }
        }
    }

    private fun previousSelected() {
        if (selectedPosition <= listData.size) {
            selectedPosition--
            displayUI(selectedPosition)
            when (selectedPosition) {
                0 -> binding.ivPreviousImage.visibility = View.GONE
                3 -> binding.ivNextImage.visibility = View.VISIBLE
                else -> binding.ivPreviousImage.visibility = View.VISIBLE
            }
        }
    }

    private fun updateUI() {
        if (datesList.isNotEmpty() && remediesList.isNotEmpty()) {
            datesList.forEach {
                remediesList.forEach { it1 ->
                    if (it == it1.id) {
                        displayListData.add(it1)
                    }
                }
            }

            val groupedHashMap: LinkedHashMap<String, MutableList<RemedyDataModel>> =
                LinkedHashMap()
            for (datObject in displayListData) {
                val alarmDate = datObject.dateCreatedStr
                if (groupedHashMap.containsKey(alarmDate)) {
                    val listData: MutableList<RemedyDataModel> = groupedHashMap[alarmDate]!!
                    listData.add(datObject)
                } else {
                    val listData: MutableList<RemedyDataModel> = ArrayList()
                    listData.add(datObject)
                    groupedHashMap[alarmDate!!] = listData
                }
            }
            groupedHashMap.forEach { entry ->
                val list: MutableList<Any> = ArrayList()
                list.add(0, entry.key)
                list.add(1, entry.value)
                listData.add(list)
            }
            if (listData.size > 1) {
                binding.ivNextImage.visibility = View.VISIBLE
            }
            displayUI(0)
        }
    }

    private fun displayUI(position: Int) {
        val list = listData[position]
        val date = list[0] as String?
        binding.tvDateMonth.text = date
        clearItems()
        val listData = list[1] as MutableList<RemedyDataModel>
        mAdapter.setData(listData)
    }

    private fun clearItems() {
        mAdapter.setData(ArrayList())
    }

    override fun onItemClick(view: View, item: RemedyDataModel) {

    }
}
