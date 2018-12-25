package com.example.mapguide.mapguide.Activities.MainMenuActivities;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import com.example.mapguide.mapguide.R;


import static org.junit.Assert.*;

public class NetworkStatusTest {

    @Rule
    public ActivityTestRule<NetworkStatus> nActivityTestRule = new ActivityTestRule<NetworkStatus>(NetworkStatus.class);

    private NetworkStatus nActivity = null;


    @Before
    public void setUp() throws Exception {
        nActivity = nActivityTestRule.getActivity();
    }

    @Test

    public void testLaunch()
    {
        View view = nActivity.findViewById(R.id.conStatusTv);

        assertNotNull(view);


    }

    @After
    public void tearDown() throws Exception {
        nActivity = null;
    }



}