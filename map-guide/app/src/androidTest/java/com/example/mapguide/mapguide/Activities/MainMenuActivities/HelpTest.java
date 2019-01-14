package com.example.mapguide.mapguide.Activities.MainMenuActivities;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.example.mapguide.mapguide.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;


public class HelpTest {

    @Rule
    public ActivityTestRule<Help> mActivityTestRule = new ActivityTestRule<>(Help.class);

    private Help mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }


    @Test
    public void testLaunchHelp()
    {
        View view = mActivity.findViewById(R.id.textHelp);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}