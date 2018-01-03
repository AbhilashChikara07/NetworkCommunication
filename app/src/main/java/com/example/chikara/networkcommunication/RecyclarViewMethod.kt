package com.example.chikara.retrofitnetworkcommunication

import org.json.JSONObject

/**
 * Created by chikara on 1/2/18.
 */
interface RecyclarViewMethod {

    fun showWait()

    fun removeWait()

    fun onFailure(failureMessage: String)

    fun getSpeciesList(jsonResp: JSONObject)

}