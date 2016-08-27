package com.example.cristi.fiborv;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.example.cristi.fiborv.RecyclerView.RecyclerViewInteraction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    private String[] fibonacciSequence;
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Before
    public void setup() {
        fibonacciSequence = new String[] {
                "0", "1" ,"1", "2", "3", "5", "8", "13", "21", "34", "55", "89", "144"};
    }

    @Test
    public void recyclerViewDisplayed() {
        onView(withId(R.id.recycler_view))
                .check(matches(isDisplayed()));
    }

    @Test
    public void fibonacciSequenceDisplayed() {
        RecyclerViewInteraction.
                <String>onRecyclerView(withId(R.id.recycler_view))
                .withItems(Arrays.asList(fibonacciSequence))
                .check(new RecyclerViewInteraction.ItemViewAssertion<String>() {
                    @Override
                    public void check(String item, View view, NoMatchingViewException e) {
                        matches(withText(item))
                                .check(view, e);
                    }
                });
    }
}
