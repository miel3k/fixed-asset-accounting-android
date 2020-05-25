package com.tp.fixedassetaccounting.feature.asset.presentation.details.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.tp.feature_asset.R
import com.tp.fixedassetaccounting.feature.asset.presentation.details.viewmodel.AssetDetailsViewModel
import kotlinx.android.synthetic.main.fragment_asset_details.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class AssetDetailsFragment : Fragment(), KodeinAware {

    override val kodein by kodein()

    private val viewModel: AssetDetailsViewModel by instance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_asset_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAmortizationBarChart()
    }

    private fun setupAmortizationBarChart() {
        bc_amortization.run {
            description.isEnabled = true
            setDrawGridBackground(false)
            setDrawBarShadow(false)
            data = generateBarData(1, 20000f, 12)
            axisLeft.axisMinimum = 0f
            axisRight.isEnabled = false
            xAxis.isEnabled = false
        }
    }

    private fun generateBarData(dataSets: Int, range: Float, count: Int): BarData? {
        val sets: ArrayList<IBarDataSet> = ArrayList()
        for (i in 0 until dataSets) {
            val entries: ArrayList<BarEntry> = ArrayList()
            for (j in 0 until count) {
                entries.add(BarEntry(j.toFloat(), (Math.random() * range).toFloat() + range / 4))
            }
            val ds = BarDataSet(entries, "Label")
            ds.setColors(*ColorTemplate.VORDIPLOM_COLORS)
            sets.add(ds)
        }
        return BarData(sets)
    }
}
