package gregpearce.mainthreadtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements TimerView {

  private TextView timerView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    timerView = (TextView) findViewById(R.id.timerView);
    sleep();
  }

  @Override protected void onStart() {
    super.onStart();
    sleep();
  }

  @Override protected void onResume() {
    super.onResume();
    sleep();
    MainPresenter.getInstance().attachView(this);
  }

  @Override protected void onPause() {
    super.onPause();
    MainPresenter.getInstance().detachView();
    sleep();
  }

  @Override protected void onStop() {
    super.onStop();
    sleep();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    sleep();
  }

  private void sleep() {
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      throw new RuntimeException();
    }
  }

  @Override public void showTimer(long timeMs) {
    timerView.setText("Timer: " + timeMs);
  }
}
