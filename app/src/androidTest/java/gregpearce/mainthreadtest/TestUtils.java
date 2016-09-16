package gregpearce.mainthreadtest;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.Toolbar;

public class TestUtils {

  private static void rotateToLandscape(ActivityTestRule<? extends Activity> activityTestRule) {
    activityTestRule.getActivity()
        .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
  }

  private static void rotateToPortrait(ActivityTestRule<? extends Activity> activityTestRule) {
    activityTestRule.getActivity()
        .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
  }

  public static void rotateOrientation(ActivityTestRule<? extends Activity> activityTestRule) {
    int currentOrientation =
        activityTestRule.getActivity().getResources().getConfiguration().orientation;

    switch (currentOrientation) {
      case Configuration.ORIENTATION_LANDSCAPE:
        rotateToPortrait(activityTestRule);
        break;
      case Configuration.ORIENTATION_PORTRAIT:
        rotateToLandscape(activityTestRule);
        break;
      default:
        rotateToLandscape(activityTestRule);
    }
  }

  /**
   * Returns the content description for the navigation button view in the toolbar.
   */
  public static String getToolbarNavigationContentDescription(
      @NonNull Activity activity, @IdRes int toolbar1) {
    Toolbar toolbar = (Toolbar) activity.findViewById(toolbar1);
    if (toolbar != null) {
      return (String) toolbar.getNavigationContentDescription();
    } else {
      throw new RuntimeException("No toolbar found.");
    }
  }
}
