package com.imran.currencyapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.imran.currencyapp.R
import com.imran.currencyapp.data.model.CurrencyInfo
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_currency.view.*
import java.text.DecimalFormat

/**
 * Created by Md. Imran Chowdhury on 5/9/2021.
 */
class CurrencyAdapter(): RecyclerView.Adapter<CurrencyViewHolder>() {

    private var mDataList: MutableList<CurrencyInfo>? = mutableListOf()
    private var targetPrice: Double = 1.0
    private var currencyRate: Double = 3.673204

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        return CurrencyViewHolder.createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        mDataList?.get(position)?.let { holder.bind(it, targetPrice, currencyRate) }
    }

    override fun getItemCount(): Int {
        return mDataList?.size ?: 0
    }

    fun setData(list: MutableList<CurrencyInfo>) {
        this.mDataList = list
        notifyDataSetChanged()
    }

    fun setOnPriceChange(price: Double) {
        this.targetPrice = price
        notifyDataSetChanged()
    }

    fun setOnCurrencyTypeChange(currencyType: String) {
        val countryCode = currencyType.substring(currencyType.indexOf("(") + 1, currencyType.indexOf(")"))
        mDataList?.forEach {
            if (it.code == countryCode) {
                this.currencyRate = it.rate
                notifyDataSetChanged()
            }
        }

    }
}


class CurrencyViewHolder(
        override val containerView: View
): RecyclerView.ViewHolder(containerView), LayoutContainer {


    fun bind(model: CurrencyInfo, targetPrice: Double, currencyRate: Double) {
        containerView.tvCountryShortCode.text = model.code
        containerView.tvCountryFullName.text = "(${model.name})"
        val rate = model.rate * targetPrice / currencyRate
        val dec = DecimalFormat("#,###.##")
        containerView.tvCurrency.text = "$ ${dec.format(rate)}"
    }


    companion object {
        fun createViewHolder(
                parent: ViewGroup
        ): CurrencyViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_currency, parent, false)
            return CurrencyViewHolder(itemView)
        }

    }


}