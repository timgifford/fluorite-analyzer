package net.timandersen;

import net.timandersen.model.Activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SessionGrouper {

  public static final long FIVE_MINUTES = 5 * 60 * 1000L;

  public List<Activity> assignGroups(List<Activity> activities) {
    Collections.sort(activities, new ActivityComparator());
    List<Activity> modifiedActivities = new ArrayList<Activity>();
    Long previousMillisecond = 0L;
    Long currentSessionGroupId = 0L;
    for (Activity activity : activities) {
      if (overFiveMinutes(previousMillisecond, activity.getMillisecond())) {
        currentSessionGroupId++;
      }
      activity.setSession(currentSessionGroupId);
      modifiedActivities.add(activity);
      previousMillisecond = activity.getMillisecond();
    }
    return modifiedActivities;
  }

  private boolean overFiveMinutes(Long t1, long t2) {
    return Math.abs(t1 - t2) > FIVE_MINUTES;
  }

}
