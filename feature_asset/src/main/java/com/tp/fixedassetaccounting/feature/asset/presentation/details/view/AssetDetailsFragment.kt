package com.tp.fixedassetaccounting.feature.asset.presentation.details.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.tp.feature_asset.R
import com.tp.fixedassetaccounting.core.extension.observe
import com.tp.fixedassetaccounting.feature.asset.presentation.details.viewmodel.AssetDetailsViewModel
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import kotlinx.android.synthetic.main.fragment_asset_details.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import java.text.SimpleDateFormat
import java.util.*

class AssetDetailsFragment : Fragment(), KodeinAware, DatePickerDialog.OnDateSetListener {

    override val kodein by kodein()

    private val viewModel: AssetDetailsViewModel by instance()
    private val assetDetailsArgs: AssetDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_asset_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDateEditText()
        setupStateObserver()
        setupAmortizationBarDataObserver()
        setupAmortizationDateObserver()
    }

    private fun setupDateEditText() {
        et_date.setOnClickListener {
            val now = Calendar.getInstance()
            val dialog = DatePickerDialog.newInstance(
                this,
                now[Calendar.YEAR],
                now[Calendar.MONTH],
                now[Calendar.DAY_OF_MONTH]
            )
            dialog.show(parentFragmentManager, "AmortizationDateDialog")
        }
    }

    private fun setupStateObserver() {
        viewModel.state.observe(viewLifecycleOwner) {
            pb_loading.isVisible = it.isLoading
        }
    }

    private fun setupAmortizationBarDataObserver() {
        viewModel.amortizationBarData.observe(viewLifecycleOwner) {
            bc_amortization.run {
                description.isEnabled = true
                setDrawGridBackground(false)
                setDrawBarShadow(false)
                data = it
                axisLeft.axisMinimum = 0f
                axisRight.isEnabled = false
                xAxis.isEnabled = false
                invalidate()
            }
        }
    }

    private fun setupAmortizationDateObserver() {
        viewModel.amortizationDate.observe(viewLifecycleOwner) {
            val date = Date.from(it)
            val formatter = SimpleDateFormat.getDateInstance()
            tv_purchase_date.text = formatter.format(date)
        }
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        viewModel.selectDate(year, monthOfYear, dayOfMonth, assetDetailsArgs.assetName)
    }
}
