package com.tp.fixedassetaccounting.feature.asset.presentation.details.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.anychart.AnyChart
import com.anychart.enums.Anchor
import com.anychart.enums.HoverMode
import com.anychart.enums.Position
import com.anychart.enums.TooltipPositionMode
import com.tp.feature_asset.R
import com.tp.fixedassetaccounting.core.extension.observe
import com.tp.fixedassetaccounting.feature.asset.presentation.details.viewmodel.AssetDetailsViewModel
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import kotlinx.android.synthetic.main.fragment_asset_details.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.time.ZonedDateTime
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
        viewModel.setup(assetDetailsArgs.assetName)
        setupDateEditText()
        setupStateObserver()
        setupAssetObserver()
        setupAmortizationBarDataObserver()
        setupAmortizationDateObserver()
    }

    private fun setupDateEditText() {
        et_date.setOnClickListener {
            val amortizationDate = requireNotNull(viewModel.asset.value?.entryDate)
            val zonedDateTime = ZonedDateTime.ofInstant(amortizationDate, ZoneId.systemDefault())
            val now = Calendar.getInstance()
            val dialog = DatePickerDialog.newInstance(
                this,
                now[Calendar.YEAR],
                now[Calendar.MONTH],
                now[Calendar.DAY_OF_MONTH]
            ).apply {
                minDate = GregorianCalendar.from(zonedDateTime)
            }
            dialog.show(parentFragmentManager, "AmortizationDateDialog")
        }
    }

    private fun setupStateObserver() {
        viewModel.state.observe(viewLifecycleOwner) {
            pb_loading.isVisible = it.isLoading
        }
    }

    private fun setupAssetObserver() {
        viewModel.asset.observe(viewLifecycleOwner) {
            tv_asset_name.text = it.assetName
            val date = Date.from(it.purchaseDate)
            val formatter = SimpleDateFormat.getDateInstance()
            tv_purchase_date.text = formatter.format(date)
            tv_document_name.text = it.documentName
            val priceText = "${it.purchaseAmount} PLN"
            tv_price.text = priceText
            tv_amortization_type.text = it.amortizationType.name
            tv_amortization_coefficient.text = it.coefficient.toString()
        }
    }

    private fun setupAmortizationBarDataObserver() {
        val chart = AnyChart.column().apply {
            tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0.0)
                .offsetY(5.0)
                .format("PLN {%Value}{groupsSeparator: }")
            animation(true)
            title("Amortization")
            yScale().minimum(0.0)
            yAxis(0).labels().format("PLN {%Value}{groupsSeparator: }")
            tooltip().positionMode(TooltipPositionMode.POINT)
            interactivity().hoverMode(HoverMode.BY_X)
            yAxis(0).title("Amount")
            xAxis(0).staggerMaxLines(10)
            xScroller(true)
        }
        bc_amortization.setChart(chart)
        viewModel.amortizationBarDataEntries.observe(viewLifecycleOwner) {
            chart.yAxis(0).staggerMaxLines(it.size)
            chart.data(it)
        }
    }

    private fun setupAmortizationDateObserver() {
        viewModel.amortizationDate.observe(viewLifecycleOwner) {
            val date = Date.from(it)
            val formatter = SimpleDateFormat.getDateInstance()
            et_date.setText(formatter.format(date))
        }
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        viewModel.selectDate(year, monthOfYear, dayOfMonth, assetDetailsArgs.assetName)
    }
}
