package com.example.viewapptest

import android.util.Log
import androidx.activity.ComponentActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlin.time.Duration.Companion.minutes

object Ads {
    object Inter
}

var interAd: InterstitialAd? = null
fun Ads.Inter.load(unitId: String, activity: ComponentActivity) {
    fun loadAd(onLoaded: (InterstitialAd?) -> Unit) {
        interAd.let {
            if (it != null) {
                onLoaded(it)
            } else {
                InterstitialAd.load(applicationContexts,
                                    unitId,
                                    AdRequest.Builder().build(),
                                    object : InterstitialAdLoadCallback() {
                                        override fun onAdLoaded(p0: InterstitialAd) {
                                            interAd = p0
                                            onLoaded(p0)
                                        }

                                        override fun onAdFailedToLoad(p0: LoadAdError) {
                                            Log.e("AndroidRuntime", p0.toString())
                                            onLoaded(null)
                                        }
                                    })
            }
        }
    }


    loadAd {
        it?.setImmersiveMode(true)
        it?.show(activity)
        interAd = null
        loadAd { }
    }
}
