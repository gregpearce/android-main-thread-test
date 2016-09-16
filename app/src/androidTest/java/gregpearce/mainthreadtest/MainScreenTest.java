package gregpearce.mainthreadtest;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class) @LargeTest public class MainScreenTest {

  @Rule public ActivityTestRule<MainActivity> mActivityRule =
      new ActivityTestRule<>(MainActivity.class);

  /**
   * Note: this test won't actually pass unless you increase the interval that the Observable is
   * spamming the main thread. It will timeout. At 1 millisecond it's actually spamming the
   * main thread so hard that it breaks Espresso.
   */
  @Test public void doOrientationChanges() {
    for (int i = 0; i < 100; i++) {
      TestUtils.rotateOrientation(mActivityRule);
    }
  }
}
