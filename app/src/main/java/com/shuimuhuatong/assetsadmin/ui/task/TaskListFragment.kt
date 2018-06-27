package com.shuimuhuatong.assetsadmin.ui.task


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.shuimuhuatong.assetsadmin.R
import com.shuimuhuatong.assetsadmin.adapter.TestAdapter
import com.shuimuhuatong.assetsadmin.bean.MyCity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TaskListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class TaskListFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
     var mView : View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_task_list, container, false)
        var recyclerView = mView?.findViewById<RecyclerView>(R.id.recyclerView)
        var citys = arrayListOf<MyCity>()
        for (i in 0 ..10){
           var city = MyCity()
            city.name = "wangcong".plus(i)
            citys.add(city)
        }
        var mAdapter = TestAdapter(R.layout.item_city,citys)
        recyclerView?.adapter = mAdapter
        return mView
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                TaskListFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
