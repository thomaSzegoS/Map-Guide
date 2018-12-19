package com.example.mapguide.mapguide.Activities;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.example.mapguide.mapguide.Activities.MainMenuActivities.NetworkStatus;
import com.example.mapguide.mapguide.R;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;


import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class MainActivityTestGoToNetworkStatusButton {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mActivity =null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(NetworkStatus.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {

        mActivity = mainActivityActivityTestRule.getActivity();
    }

    @Test
    public void testLaunchOfSecondActivityOnButtonClick()
    {
        assertNotNull(mActivity.findViewById(R.id.button));

        onView(withId(R.id.button)).perform(click());

        Activity NetworkStatus = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);

        assertNotNull(NetworkStatus);

        NetworkStatus.finish();
    }
    @After
    public void tearDown() throws Exception {

        mActivity = null;
    }
}