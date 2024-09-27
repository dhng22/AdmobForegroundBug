This example illustrates two severe admob bugs related to app in foreground
(Note that this bug only observed on Xiaomi devices. Tested with Google pixel and it works fine.
So I think this bug relates to battery optimization?)

Step to reproduce:
- Run the app, manually grant notification permission in app info
(The app will load and show an interstitial ad, also start a foreground service). Only when the app
has shown a test notification, move to the next step
- Close and remove the app from recent apps (The app should keep running because of the service)
- Now open the app again, there will be 2 bugs:
    + Pre-cached ads will be error, in this example, interstitial ads will show a transparent
    activity overlay. You have to press back to remove it. App open ads is the same as I tested.
    Rewarded ads will only show a black screen
    + All calls to load the first admob after returning to foreground will take 60 seconds and
    return a Code 2: Network error. After this network error call, you can load ads normally
