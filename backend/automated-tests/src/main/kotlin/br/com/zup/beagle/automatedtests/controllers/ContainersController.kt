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

package br.com.zup.beagle.automatedtests.controllers

import br.com.zup.beagle.automatedtests.builders.ContainerTestsScreenBuilder
import br.com.zup.beagle.automatedtests.builders.ListViewScreenBuilder
import br.com.zup.beagle.automatedtests.builders.PageViewScreenBuilder
import br.com.zup.beagle.automatedtests.builders.ScrollViewScreenBuilder
import br.com.zup.beagle.automatedtests.builders.TabViewScreenBuilder
import br.com.zup.beagle.automatedtests.constants.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ContainersController {

    @GetMapping(SCREEN_TABVIEW_ENDPOINT)
    fun getTabViewScreen() = TabViewScreenBuilder.build()

    @GetMapping(PAGEVIEW_TABVIEW_ENDPOINT)
    fun getPageViewScreen() = PageViewScreenBuilder.build()

    @GetMapping(LISTVIEW_TABVIEW_ENDPOINT)
    fun getListViewScreen() = ListViewScreenBuilder.buildListViewOptionsSceen()

    @GetMapping(LISTVIEW_SIMPLE_TABVIEW_ENDPOINT)
    fun getListViewSimpleScreen() = ListViewScreenBuilder.buildSimpleListViewScreen()

    @GetMapping(LISTVIEW_SIMPLE_SCROLLEND_ENDPOINT)
    fun getListViewSimpleWithScrollEndScreen() = ListViewScreenBuilder.buildSimpleListViewWithScrollEndScreen()

    @GetMapping(LISTVIEW_EMPTY_SCROLLEND_ENDPOINT)
    fun getListViewEmptyWithScrollEndScreen() = ListViewScreenBuilder.buildListViewEmptyWithScrollEndScreen()

    @GetMapping(LISTVIEW_SHORT_SCROLLEND_ENDPOINT)
    fun getListViewShortWithScrollEndScreen() = ListViewScreenBuilder.buildListViewShortWithScrollEndScreen()

    @GetMapping(LISTVIEW_EMBEDDED_WITH_CONTEXT_TABVIEW_ENDPOINT)
    fun getEmbeddedListViewWithContextScreen() = ListViewScreenBuilder.buildEmbeddedListViewWithContextScreen()

    @GetMapping(LISTVIEW_EMBEDDED_WITH_CONTEXT_PAGE_2_TABVIEW_ENDPOINT)
    fun getListNamesPage2() = ListViewScreenBuilder.simpleValuesListPage2

    @GetMapping(LISTVIEW_NAMES_ENDPOINT)
    fun getListNames() = ListViewScreenBuilder.listNamesPage1

    @GetMapping(LISTVIEW_VALUES_ENDPOINT)
    fun getListValues() = ListViewScreenBuilder.simpleValuesList

    @GetMapping(SCROLLVIEW_TABVIEW_ENDPOINT)
    fun getScrollViewScreen() = ScrollViewScreenBuilder.build()

    @GetMapping(CONTAINER_TEST_ENDPOINT)
    fun getContainerTestScreen() = ContainerTestsScreenBuilder.build()
}