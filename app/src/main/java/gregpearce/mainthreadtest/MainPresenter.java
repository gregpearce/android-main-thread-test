package gregpearce.mainthreadtest;

import android.util.Log;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainPresenter {
  private static MainPresenter instance;

  public static MainPresenter getInstance() {
    if (instance == null) {
      instance = new MainPresenter();
    }
    return instance;
  }

  private TimerView view;
  private boolean started;

  public void attachView(final TimerView view) {
    this.view = view;
    if (!started) {
      started = true;
      Observable.interval(1, TimeUnit.MILLISECONDS, Schedulers.io())
          .onBackpressureDrop()
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(new Subscriber<Long>() {
            @Override public void onCompleted() {

            }

            @Override public void onError(Throwable e) {
              Log.e("MainPresenter", e.getMessage(), e);
            }

            @Override public void onNext(Long aLong) {
              instance.view.showTimer(aLong);
            }
          });
    }
  }

  public void detachView() {
    view = null;
  }
}
