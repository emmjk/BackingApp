package com.parot.mtecgwa_jr.myrecipes;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isFocusable;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.googlecode.eyesfree.utils.LogUtils.TAG;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RecipeInstructionTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void recipeInstructionAndExoPlayerTest() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recycler_view), isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction appCompatTextView = onView(
                allOf(withText("INSTRUCTIONS"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withText("INGRIDIENTS"), isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction appCompatTextView3 = onView(
                allOf(withText("INSTRUCTIONS"), isDisplayed()));
        appCompatTextView3.perform(click());

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.steps_recycler), isDisplayed()));
        recyclerView2.perform(actionOnItemAtPosition(0, click()));


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.video_container)).check(matches(isDisplayed()));

        onView(withId(R.id.exo_pause)).check(matches(isFocusable()));

        ViewInteraction pause = onView(
                allOf(withId(R.id.exo_pause), withContentDescription("Pause"), isFocusable()));

        pause.perform(click());

        ViewInteraction play = onView(withId(R.id.exo_play)).check(matches(isDisplayed()));

        play.perform(click());

        pause.check(matches(isDisplayed()));

        //test forward ..

        ViewInteraction forward = onView(withId(R.id.exo_ffwd));
        forward.perform(click());

        ViewInteraction playPosition = onView(withId(R.id.exo_position));

        playPosition.check(matches(withText("00:11")));


        //test rewind ..

        ViewInteraction rewind = onView(withId(R.id.exo_rew));

        rewind.perform(click());

        playPosition.check(matches(withText("00:06")));

        forward.perform(click());

        playPosition.check(matches(withText("00:11")));

//        Test previous ....

        ViewInteraction back = onView(withId(R.id.exo_prev));

        back.perform(click());

        playPosition.check(matches(withText("00:00")));

//      Test on another video
        ViewInteraction steps_recycler = onView(
                allOf(withId(R.id.master_list_recycler), isDisplayed()));

        steps_recycler.perform(actionOnItemAtPosition(2, click()));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.video_container)).check(matches(isDisplayed()));

        onView(withId(R.id.exo_pause)).check(matches(isFocusable()));

        pause.perform(click());

         play.check(matches(isDisplayed()));

        play.perform(click());

        pause.check(matches(isDisplayed()));


        forward.perform(click());

        playPosition.check(matches(withText("00:15")));

        rewind.perform(click());

        playPosition.check(matches(withText("00:10")));

        rewind.perform(click());

        playPosition.check(matches(withText("00:05")));


    }

}
