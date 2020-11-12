/*
* Copyright 2020 ZUP IT SERVICOS EM TECNOLOGIA E INOVACAO SA
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package br.com.zup.beagle.automatedTests.cucumber.robots

import android.text.InputType
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import br.com.zup.beagle.android.utils.toAndroidId
import br.com.zup.beagle.automatedTests.R
import br.com.zup.beagle.automatedTests.utils.WaitHelper
import br.com.zup.beagle.automatedTests.utils.action.OrientationChangeAction
import br.com.zup.beagle.automatedTests.utils.matcher.MatcherExtension
import br.com.zup.beagle.widget.core.TextAlignment
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.hamcrest.TypeSafeMatcher


class ScreenRobot {

    fun checkViewContainsText(text: String?, waitForText: Boolean = false): ScreenRobot {
        if (waitForText) {
            WaitHelper.waitForWithElement(onView(withText(text)))
        }

        onView(Matchers.allOf(withText(text))).check(matches(isDisplayed()))
        return this
    }

    fun checkViewTextColor(text: String?, color: String, waitForText: Boolean = false): ScreenRobot {
        if (waitForText) {
            WaitHelper.waitForWithElement(onView(withText(text)))
        }

        onView(Matchers.allOf(withText(text), MatcherExtension.withTextColor(color))).check(matches(isDisplayed()))
        return this
    }

    fun checkViewTextAlignment(text: String?, textAlignment: TextAlignment, waitForText: Boolean = false): ScreenRobot {
        if (waitForText) {
            WaitHelper.waitForWithElement(onView(withText(text)))
        }

        onView(Matchers.allOf(withText(text), MatcherExtension.withTextAlignment(textAlignment))).check(matches(isDisplayed()))
        return this
    }

    fun checkViewDoesNotContainsText(text: String?, waitForText: Boolean = false): ScreenRobot {
        if (waitForText) {
            WaitHelper.waitForWithElement(onView(withText(text)))
        }

        onView(Matchers.allOf(withText(text))).check(doesNotExist())
        return this
    }

    fun checkViewIsNotDisplayed(text: String?): ScreenRobot {
        onView(Matchers.allOf(withText(text))).check(matches(not(isDisplayed())))
        return this
    }

    fun typeText(hint: String, text: String): ScreenRobot {
        onView(withHint(hint)).perform(ViewActions.typeText((text)))
        return this
    }

    fun checkViewContainsHint(hint: String?, waitForText: Boolean = false): ScreenRobot {
        if (waitForText) {
            WaitHelper.waitForWithElement(onView(withHint(hint)))
        }

        onView(Matchers.allOf(withHint(hint))).check(matches(isDisplayed()))
        return this
    }

    fun clickOnText(text: String?): ScreenRobot {
        onView(Matchers.allOf(withText(text), isDisplayed())).perform(ViewActions.click())
        return this
    }

    fun clickOnInputWithHint(hint: String?): ScreenRobot {
        onView(Matchers.allOf(withHint(hint), isDisplayed())).perform(ViewActions.click())
        return this
    }

    fun disabledFieldHint(text: String): ScreenRobot {
        onView(withHint(text)).check(matches(not(isEnabled())))
        return this
    }

    fun disabledFieldText(text: String): ScreenRobot {
        onView(withText(text)).check(matches(not(isEnabled())))
        return this
    }

    fun hintInSecondPlan(text: String): ScreenRobot {
        onView(withHint(text)).perform(pressBack())
        onView(allOf(withHint(text), isDisplayed()))
        return this
    }

    fun checkInputTypeNumber(text: String): ScreenRobot {
        onView(withHint(text)).check(matches(allOf(withInputType(InputType.TYPE_CLASS_NUMBER))))
        return this
    }

    fun typeIntoTextField(position1: Int, position2: Int, text: String?): ScreenRobot {
        onView(childAtPosition(childAtPosition(withClassName(
            Matchers.`is`("br.com.zup.beagle.android.view.custom.BeagleFlexView")), position1), position2)).perform(scrollTo(), ViewActions.replaceText(text))
        Espresso.closeSoftKeyboard()
        return this
    }

    fun scrollViewDown(): ScreenRobot {
        onView(withId(R.id.root_layout)).perform(ViewActions.swipeUp())
        return this
    }

    fun swipeLeftOnView(): ScreenRobot {
        onView(Matchers.allOf(withId(R.id.root_layout))).perform(ViewActions.swipeLeft())
        return this
    }

    fun swipeRightOnView(): ScreenRobot {
        onView(withId(R.id.root_layout)).perform(ViewActions.swipeRight())
        return this
    }

    fun scrollTo(text: String?): ScreenRobot {
        onView(withText(text)).perform(scrollTo()).check(matches(isDisplayed()))
        return this
    }

    fun scrollToWithHint(text: String?): ScreenRobot {
        onView(withHint(text)).perform(scrollTo()).check(matches(isDisplayed()))
        return this
    }

    fun clickOnTouchableImage(): ScreenRobot {
        onView(childAtPosition(childAtPosition(withClassName(Matchers.`is`("br.com.zup.beagle.android.view.custom.BeagleFlexView")), 1), 1)).perform(ViewActions.click())
        return this
    }

    @Throws(InterruptedException::class)
    fun sleep(seconds: Int): ScreenRobot {
        Thread.sleep(seconds * 1000L)
        return this
    }

    fun hideKeyboard() {
        Espresso.closeSoftKeyboard()
    }

    fun scrollListToPosition(listId: String, position: Int): ScreenRobot {

        var isScrolling = true
        onView(withId(listId.toAndroidId()))
            //.perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position))
            .perform(MeuViewAction(position){
                isScrolling = false
            })

        while (isScrolling) {
            //TODO: refatorar
            Thread.sleep(0)
        }
        //recyclerView.getLayoutManager().smoothScrollToPosition(recyclerView, null, recyclerAdapter.getItemCount() - 1);
        return this
    }

    fun clickOnListPosition(listId: String, position: Int): ScreenRobot {
        onView(withId(listId.toAndroidId()))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(position, click()));
        return this
    }

    class MeuViewAction(val position: Int, val onScrollEnd: () -> Unit) : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return allOf(isAssignableFrom(RecyclerView::class.java), isDisplayed())
        }

        override fun getDescription(): String = ""

        override fun perform(uiController: UiController?, view: View?) {
            val recyclerView = view as RecyclerView


            if(canScroll(recyclerView, position)) {
                Log.wtf("list", "canScroll to $position")
                recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        super.onScrollStateChanged(recyclerView, newState)
                        when (newState) {
                            SCROLL_STATE_IDLE -> {
                                onScrollEnd.invoke()
                                Log.wtf("list", "scrollEnd to $position")
                                recyclerView.removeOnScrollListener(this)
                            }
                        }
                    }
                })

                //recyclerView.getLayoutManager()?.smoothScrollToPosition(recyclerView, null, position)
                recyclerView.smoothScrollToPosition(position)

                uiController?.loopMainThreadUntilIdle()
            } else {
                Log.wtf("list", "cant scroll to $position - called scrollEnd")
                onScrollEnd.invoke()
            }
        }

        //TODO: refatorar
        private fun canScroll(recyclerView: RecyclerView, position: Int): Boolean{
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            return if(position == 0){
                layoutManager.findFirstCompletelyVisibleItemPosition() > 0
            } else {
                if(position == layoutManager.itemCount -1) {
                    layoutManager.findLastCompletelyVisibleItemPosition() < layoutManager.itemCount - 1
                } else {
                    layoutManager.findFirstCompletelyVisibleItemPosition() > position || layoutManager.findLastCompletelyVisibleItemPosition() < position
                }
            }


        }

    }

    fun checkListViewItemContainsText(listId: String, position: Int, expectedText: String): ScreenRobot {

        onView(withId(listId.toAndroidId()))
            .check(matches(atPosition(position, hasDescendant(withText(expectedText)))))
        return this
    }

    fun setScreenPortrait() {
        onView(isRoot()).perform(OrientationChangeAction.orientationPortrait())
    }

    fun setScreenLandScape() {
        onView(isRoot()).perform(OrientationChangeAction.orientationLandscape())
    }

    fun clickOnViewWithId(id: String): ScreenRobot {
        onView(withId(id.toAndroidId())).perform(ViewActions.click())
        return this
    }

    companion object {
        private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {
            return object : TypeSafeMatcher<View>() {
                override fun describeTo(description: Description) {
                    description.appendText("Child at position $position in parent ")
                    parentMatcher.describeTo(description)
                }

                public override fun matchesSafely(view: View): Boolean {
                    val parent = view.parent
                    return (parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position))
                }
            }
        }

        fun atPosition(position: Int, @NonNull itemMatcher: Matcher<View?>): Matcher<View?>? {
            return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
                override fun describeTo(description: Description) {
                    description.appendText("has item at position $position: ")
                    itemMatcher.describeTo(description)
                }

                override fun matchesSafely(view: RecyclerView): Boolean {
                    val viewHolder = view.findViewHolderForAdapterPosition(position)
                        ?: // has no item on such position
                        return false
                    return itemMatcher.matches(viewHolder.itemView)
                }
            }
        }
    }
}
