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

package br.com.zup.beagle.automatedTests.cucumber.steps

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import br.com.zup.beagle.automatedTests.activity.MainActivity
import br.com.zup.beagle.automatedTests.cucumber.elements.*
import br.com.zup.beagle.automatedTests.cucumber.robots.ScreenRobot
import br.com.zup.beagle.automatedTests.utils.ActivityFinisher
import br.com.zup.beagle.automatedTests.utils.TestUtils
import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java.en.*
import org.junit.Rule

val LIST_VIEW_SCREEN_BFF_URL = "http://10.0.2.2:8080/listview"

class ListViewScreenSteps {

    @Rule
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    lateinit var listId: String

    @Before("@listview")
    fun setup() {
        TestUtils.startActivity(activityTestRule, LIST_VIEW_SCREEN_BFF_URL)
    }

    @After("@listview")
    fun tearDown() {
        ActivityFinisher.finishOpenActivities()
    }

    @Given("^that I'm on the listview screen$")
    fun checkListViewScreen() {
        ScreenRobot()
            .checkViewContainsText(LISTVIEW_SCREEN_HEADER, true)
    }

    @When("^system navigates to (.*) view$")
    fun checkSimpleListViewScreen(screenTitle: String) {
        ScreenRobot()
            .checkViewContainsText(screenTitle, true)
    }

    @When("^system shows a listView component with id (.*)$")
    fun getListViewId(listId: String) {
        this.listId = listId
    }

    //TODO: jogar para generic steps
    @When("^I scroll listView to position (.*)$")
    fun scrollListViewToPosition(position: Int) {
        ScreenRobot()
            .scrollListToPosition(listId, position)
    }

    //TODO: jogar para generic steps
    @When("^I rotate the screen to landscape$")
    fun rotateScreenLandscape() {
        ScreenRobot()
            .setScreenLandScape()
    }

    //TODO: jogar para generic steps
    @When("^I rotate the screen to portrait$")
    fun rotateScreenPortrait() {
        ScreenRobot()
            .setScreenPortrait()
    }

    //TODO: ver se dá para fazer com generic steps
    @When("^I click on inner list on button with id (.*)$")
    fun clickOnViewWithId(viewId: String) {
        ScreenRobot()
            .clickOnViewWithId(viewId)
    }

    //TODO: possivelmente duplicado com o de baixo. Verificar depois
    @Then("^the button with id (.*) shows text (.*)$")
    fun checkViewRenderText(viewId: String, text: String) {
        ScreenRobot()
            .checkViewWithIdContainsText(viewId, text, true)
    }


    @Then("^listView at (.*) renders view with (.*) and (.*)$")
    fun checkListViewItemRenderText(position: Int, viewId: String, text: String) {
        ScreenRobot()
            .scrollListToPosition(listId, position)
            .checkViewWithIdContainsText(viewId, text)
    }

    @Then("^Outer listView at (.*) shows Inner listView with id (.*) that renders a view with (.*) and (.*) at (.*)$")
    fun checkListViewItemRenderText(outerListPosition: Int, innerListId: String, viewId: String, text: String, innerListPosition: Int) {
        ScreenRobot()
            .scrollListToPosition(listId, outerListPosition)
            .scrollListToPosition(innerListId, innerListPosition)
            .checkViewWithIdContainsText(viewId, text)
    }

    @Then("^an alert containing (.*) message should appear$")
    fun checkOnScrollEndAlertMessage(string: String) {
        ScreenRobot()
            .checkViewContainsText(string, true)
    }

    @Then("^an alert containing (.*) message should NOT appear$")
    fun checkOnScrollEndAlertMessageNotVisible(string: String) {
        ScreenRobot()
            .checkViewDoesNotContainsText(string)
    }


    /*@When("^I have a vertical list configured$")
    fun checkVerticalListText() {
        ScreenRobot()
            .checkViewContainsText(STATIC_LISTVIEW_TEXT_1)
            .sleep(2)
    }

    @Then("^listview screen should render all text attributes correctly$")
    fun checkListViewScreenTexts() {
        /*ScreenRobot()
            .checkViewContainsText(STATIC_LISTVIEW_TEXT_1)
            .checkViewContainsText(STATIC_LISTVIEW_TEXT_2)
            .checkViewContainsText(DYNAMIC_LISTVIEW_TEXT_1)*/
    }

    @Then("^listview screen should perform the scroll action vertically$")
    fun validateVerticalListScroll() {
        ScreenRobot()
            .scrollTo(DYNAMIC_LISTVIEW_TEXT_2)
            .sleep(2)
    }*/


}
