package com.imran.currencyapp.view.fragments


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.imran.currencyapp.R
import com.imran.currencyapp.data.api.network.Resource
import com.imran.currencyapp.data.api.network.Status
import com.imran.currencyapp.data.model.CurrencyInfo
import com.imran.currencyapp.view.adapters.CurrencyAdapter
import com.imran.currencyapp.viewmodel.implimentation.ICurrencyViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mViewModel: ICurrencyViewModel

    private var mAdapter: CurrencyAdapter = CurrencyAdapter()
    private var spinnerAdapter: ArrayAdapter<String>? = null

    private val currencyDataObserver: Observer<Resource<List<CurrencyInfo>>> = Observer { data ->
        data?.let {resource->
            when(resource.status) {
                Status.SUCCESS -> {
                    resource.data?.toMutableList()?.let {
                        mAdapter.setData(it)
                    }
                    val currencyTypes = resource.data?.map { "(${it.code}) ${it.name}" }
                    spinnerAdapter?.clear()
                    spinnerAdapter?.addAll(currencyTypes)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        mViewModel = ViewModelProvider(this, viewModelFactory).get(ICurrencyViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        mViewModel.getCurrencyList().observe(viewLifecycleOwner, currencyDataObserver)

    }

    private fun initView() {
        spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item)
        spinnerAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCurrencyType.adapter = spinnerAdapter
        spinnerCurrencyType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (parent != null) {
                    mAdapter.setOnCurrencyTypeChange(parent.getItemAtPosition(position) as String)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
        etCurrencyInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val getString = s?.toString() ?: "1"
                val getNumber: Double = parseInputPrice(getString)
                mAdapter.setOnPriceChange(getNumber)
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
        rvCurrencyRates.layoutManager = GridLayoutManager(requireContext(), 3)
        rvCurrencyRates.adapter = mAdapter
    }


    private fun parseInputPrice(text: String): Double {
        val parseVal: Double =
                if (text.isEmpty()) 1.0
                else text.toDoubleOrNull() ?: 1.0
        return if (parseVal > 0.0) parseVal else 1.0
    }
}
