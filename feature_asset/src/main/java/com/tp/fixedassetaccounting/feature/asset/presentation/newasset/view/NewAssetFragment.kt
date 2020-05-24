package com.tp.fixedassetaccounting.feature.asset.presentation.newasset.view

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tp.feature_asset.R
import com.tp.fixedassetaccounting.core.extension.observe
import com.tp.fixedassetaccounting.core.extension.requestPermission
import com.tp.fixedassetaccounting.core.extension.toast
import com.tp.fixedassetaccounting.core.utils.TextWatcherUtils.addCurrencyFilter
import com.tp.fixedassetaccounting.core.utils.TextWatcherUtils.getOnTextChangedListener
import com.tp.fixedassetaccounting.core.utils.setTextSilently
import com.tp.fixedassetaccounting.feature.asset.presentation.newasset.viewmodel.NewAssetViewModel
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import kotlinx.android.synthetic.main.fragment_new_asset.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import java.text.SimpleDateFormat
import java.util.*

class NewAssetFragment : Fragment(), KodeinAware, DatePickerDialog.OnDateSetListener {

    override val kodein by kodein()

    private val viewModel: NewAssetViewModel by instance()

    private val amortizationTypesAdapter by lazy {
        AmortizationTypesAdapter {
            viewModel.selectAmortizationType(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_new_asset, container, false)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == BarcodeActivity.REQUEST_CODE) {
            val barcode = data?.extras?.getString(BarcodeActivity.BARCODE)
            //TODO Handle barcode
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPriceEditText()
        setupPurchaseDateButton()
        setupScanButton()
        setupAddButton()
        setupCategorySpinner()
        setupAmortizationTypesRecyclerView()
        setupCoefficientTextView()
        setupCoefficientSeekBar()
        setupStateObserver()
        setupAmortizationTypesObserver()
        setupCategoriesObserver()
        setupSelectedCategoryIndexObserver()
        setupPurchaseDateObserver()
    }

    private fun setupPriceEditText() {
        et_price.suffix = "PLN"
        et_price.addCurrencyFilter()
        val textWatcher = getOnTextChangedListener { text, _, _, _ ->
            viewModel.setNewPrice(text.toString())
        }
        viewModel.price.observe(viewLifecycleOwner) {
            et_price.setTextSilently(it, textWatcher)
        }
    }

    private fun setupPurchaseDateButton() {
        btn_purchase_date.setOnClickListener {
            val now = Calendar.getInstance()
            val dialog = DatePickerDialog.newInstance(
                this,
                now[Calendar.YEAR],
                now[Calendar.MONTH],
                now[Calendar.DAY_OF_MONTH]
            )
            dialog.show(parentFragmentManager, "PurchaseDateDialog")
        }
    }

    private fun setupScanButton() {
        tv_scan.setOnClickListener {
            activity?.requestPermission(
                Manifest.permission.CAMERA,
                onGranted = ::startBarcodeActivity,
                onDenied = { toast("No camera permission") }
            )
        }
    }

    private fun setupAddButton() {
        btn_add.setOnClickListener {
            val name = et_document_name.text.toString()
            val code = et_code.text.toString()
            val coefficient = 0.1f * sb_coefficient.progress
            when {
                name.isEmpty() -> toast("Empty category")
                code.isEmpty() -> toast("Empty code")
                else -> viewModel.addAsset(name, code, coefficient.toDouble())
            }
        }
    }

    private fun setupCategorySpinner() {
        ms_category.setOnItemSelectedListener { _, _, _, item ->
            viewModel.selectCategory(item as String)
        }
    }

    private fun setupAmortizationTypesRecyclerView() {
        rv_amortization_types.adapter = amortizationTypesAdapter
    }

    private fun setupCoefficientTextView() {
        tv_coefficient.text = 0f.toString()
    }

    private fun setupCoefficientSeekBar() {
        sb_coefficient.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tv_coefficient.text = String.format("%.1f", 0.1f * progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    private fun setupStateObserver() {
        viewModel.state.observe(viewLifecycleOwner) {
            pb_loading.isVisible = it.isLoading
            if (it.isSuccess) {
                findNavController().popBackStack()
            }
            sb_coefficient.isEnabled = it.isCoefficientEditEnabled
        }
    }

    private fun setupAmortizationTypesObserver() {
        viewModel.amortizationTypes.observe(viewLifecycleOwner) {
            amortizationTypesAdapter.updateAmortizationTypes(it)
        }
    }

    private fun setupCategoriesObserver() {
        viewModel.categories.observe(viewLifecycleOwner) { categories ->
            val categoryNames = categories.map { it.name }
            ms_category.setItems(categoryNames)
        }
    }

    private fun setupSelectedCategoryIndexObserver() {
        viewModel.selectedCategoryIndex.observe(viewLifecycleOwner) {
            ms_category.selectedIndex = it
        }
    }

    private fun setupPurchaseDateObserver() {
        viewModel.purchaseDate.observe(viewLifecycleOwner) {
            val date = Date.from(it)
            val formatter = SimpleDateFormat.getDateInstance()
            tv_purchase_date.text = formatter.format(date)
        }
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        viewModel.selectPurchaseDate(year, monthOfYear, dayOfMonth)
    }

    private fun startBarcodeActivity() {
        val intent = Intent(context, BarcodeActivity::class.java)
        startActivityForResult(intent, BarcodeActivity.REQUEST_CODE)
    }
}