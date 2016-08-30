package com.example.cristi.fiborv;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.example.cristi.fiborv.RecyclerView.RecyclerViewInteraction;
import com.example.cristi.fiborv.Toast.ToastMatcher;
import com.example.cristi.fiborv.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    private static final String INPUT_1 = "10";
    private static final String[] DISPLAY_1 = new String[]{"0", "1", "1", "2", "3", "5", "8"};

    private static final String INPUT_2 = "150";
    private static final String[] DISPLAY_2 = new String[]{
            "0", "1", "1", "2", "3", "5", "8", "13", "21", "34", "55", "89", "144"};

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void recyclerView_isDisplayed() {
        onView(withId(R.id.recycler_view))
                .check(matches(isDisplayed()));
    }

    @Test
    public void inputDialog_isDisplayed() {
        // GIVEN
        // WHEN
        onView(withId(R.id.menu_set_n))
                .perform(click());

        // THEN
        onView(withText(R.string.dialog_title))
                .check(matches(isDisplayed()));
    }

    @Test
    public void inputDialog_noInput() {
        // GIVEN
        onView(withId(R.id.menu_set_n))
                .perform(click());

        // WHEN
        onView(withId(android.R.id.button1))
                .perform(click());

        // THEN
        onView(withText(R.string.invalid_input_toast))
                .inRoot(ToastMatcher.isToast())
                .check(matches(isDisplayed()));
    }

    @Test
    public void recyclerView_displayFibonacciSequence_1() {
        checkRecyclerView(INPUT_1, DISPLAY_1);
    }

    @Test
    public void recyclerView_displayFibonacciSequence_2() {
        checkRecyclerView(INPUT_2, DISPLAY_2);
    }

    public void checkRecyclerView(String input, String[] display) {
        // GIVEN
        onView(withId(R.id.menu_set_n))
                .perform(click());
        onView(withId(R.id.user_input))
                .perform(typeText(input));

        // WHEN
        onView(withId(android.R.id.button1))
                .perform(click());

        // THEN
        RecyclerViewInteraction.<String>onRecyclerView(withId(R.id.recycler_view))
                .withItems(Arrays.asList(display))
                .check(new RecyclerViewInteraction.ItemViewAssertion<String>() {
                    @Override
                    public void check(String item, View view, NoMatchingViewException e) {
                        matches(hasDescendant(withText(item))).check(view, e);
                    }
                });
    }
}
