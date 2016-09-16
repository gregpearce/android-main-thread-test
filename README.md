# Sample: How does the Android main thread behave during a configuration change?
-
I was reading through the code of the [Mosby](https://github.com/sockeqwe/mosby) library, an MVP architecture library that uses a pattern of retaining a Presenter class with a weak View reference during configuration changes. 

I've tried to do something similar than this before and found it to be a total mess, since you just don't know if during the configuration change the Presenter will receive input from somewhere, for example the result of a previous network call, and attempt to use the View reference, which doesn't exist, resulting in a null pointer exception.

Mosby outlines this problem in [this section of the documentation](https://github.com/sockeqwe/mosby/blob/58095a3a22cbf39c7f1422ef3b580daee7811547/mvp-common/src/main/java/com/hannesdorfmann/mosby/mvp/MvpBasePresenter.java#L37-L37) and describes that in a "perfect world" where you can be sure that you only attempt to call methods on the View on the main thread, you can avoid this problem. 

It seemed like rxJava should make it easy to create this "perfect world" situation. But I wanted to make sure for myself that during a configuration change the main thread is indeed totally locked up and no external code could attempt to use the thread until the entire Activity lifecycle had taken place.

This project proves that a configuration change does indeed lock the main thread for an entire run of the Activity lifecycle, from the `onPause` method of the old Activity through to the `onResume` method of the new Activity.

A simple MVP architecture is used, in which the Presenter lives in static scope and lives longer than the View (an Activity). 

The Presenter is spamming the View with an update every 1 millisecond whenever it can get time on the main thread, yet during a configuration change (just do an orientation change of the device) the app does not throw a null pointer exception.

Lots of delays are also added at each Activity lifecycle method.
