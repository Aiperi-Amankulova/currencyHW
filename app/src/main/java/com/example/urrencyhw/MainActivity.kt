package com.example.urrencyhw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.core.widget.doAfterTextChanged
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {

    private val values = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupListeners()
        setupNetwork()
    }

    private fun setupNetwork() {
        fetchCurencies()
    }

    private fun setupListeners() {
        etOne.doAfterTextChanged {
            calculate(it.toString())
        }
    }

    private fun calculate(value: String) {
        if (value.isNotEmpty()) {
            val result = value.toDouble() * values[spTwo.selectedItemPosition].toDouble()
            etTwo.setText(result.toString())
        } else etTwo.setText("")
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(txt: Editable?) {
            etTwo.setText(txt.toString())
        }
    }

    private fun fetchCurencies() {
        RetrofitBuilder.getService()?.getCurrencies(API_KEY)
            ?.enqueue(object : Callback<CurrencyModel> {
                override fun onResponse(
                    call: Call<CurrencyModel>,
                    response: Response<CurrencyModel>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        val data = response.body()
                        workWithData(data)
                    } else {
                        Log.e("NETWORK", "NO DATA")
                    }
                }

                override fun onFailure(call: Call<CurrencyModel>, t: Throwable) {
                    Log.e("NETWORK", t.localizedMessage)
                }
            })
    }

    private fun workWithData(data: CurrencyModel?) {
        val keys = data?.rates?.keySet()?.toList()


        if (keys != null) {
            for (item in keys) {
                values.add(data.rates.get(item).toString())
            }
        }

        val adapter = CurrencySpinnerAdapter(applicationContext, R.layout.item_spinner, keys!!)

        spOne.adapter = adapter
        spTwo.adapter = adapter

        spOne.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {}
        }

        spTwo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                calculate(etTwo.text.toString())
            }
        }
    }
}