package com.adityabirlacapital.simnumber

import android.os.Build
import android.telephony.TelephonyManager
import android.telephony.SubscriptionInfo
import org.json.JSONException
import org.json.JSONObject

class SimInfo(telephonyManager: TelephonyManager, subscriptionInfo: SubscriptionInfo?) {

    private var carrierName: String? = null
    private var displayName: String? = null
    private var slotIndex = 0
    private var number: String? = null
    private var countryIso: String? = null

    init {
        carrierName = ""
        displayName = ""
        slotIndex = 0
        number = ""
        countryIso = ""
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1 && subscriptionInfo!=null) {
            carrierName = subscriptionInfo.carrierName.toString()
            displayName = subscriptionInfo.displayName.toString()
            slotIndex = subscriptionInfo.simSlotIndex
            number = subscriptionInfo.number
            if (subscriptionInfo.countryIso != null && subscriptionInfo.countryIso.isNotEmpty()) {
                countryIso = subscriptionInfo.countryIso
            } else if (telephonyManager.simCountryIso != null) {
                countryIso = telephonyManager.simCountryIso
            }
        }else {
            if (telephonyManager.simOperator != null) {
                carrierName = telephonyManager.simOperatorName
            }
            if (telephonyManager.simOperator != null) {
                displayName = telephonyManager.simOperatorName
            }
            if (telephonyManager.simCountryIso != null) {
                countryIso = telephonyManager.simCountryIso
            }
            if (telephonyManager.line1Number != null && telephonyManager.line1Number.isNotEmpty()) {
                number = telephonyManager.line1Number
            }
        }
    }

    fun toJSON(): JSONObject {
        val json = JSONObject()
        try {
            json.put("carrierName", carrierName as Any?)
            json.put("displayName", displayName as Any?)
            json.put("slotIndex", slotIndex)
            json.put("number", number as Any?)
            json.put("countryIso", countryIso as Any?)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return json
    }
}