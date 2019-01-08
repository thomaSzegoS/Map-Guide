package com.example.mapguide.mapguide.Activities.MainMenuActivities;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.example.mapguide.mapguide.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

public class SettingsTest {

    @Rule
    public ActivityTestRule<Settings> mActivityTestRule = new ActivityTestRule<>(Settings.class);

    private Settings mActivity = null;


    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunchSettings()
    {
        View view = mActivity.findViewById(R.id.spinnerColor);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}