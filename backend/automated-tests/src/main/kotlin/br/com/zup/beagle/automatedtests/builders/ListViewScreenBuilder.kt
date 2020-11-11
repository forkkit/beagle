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

package br.com.zup.beagle.automatedtests.builders

import br.com.zup.beagle.automatedtests.constants.*
import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.*
import br.com.zup.beagle.widget.action.*
import br.com.zup.beagle.widget.context.ContextData
import br.com.zup.beagle.widget.context.expressionOf
import br.com.zup.beagle.widget.core.*
import br.com.zup.beagle.widget.layout.Container
import br.com.zup.beagle.widget.layout.NavigationBar
import br.com.zup.beagle.widget.layout.NavigationBarItem
import br.com.zup.beagle.widget.layout.Screen
import br.com.zup.beagle.widget.ui.Button
import br.com.zup.beagle.widget.ui.ImagePath.Local
import br.com.zup.beagle.widget.ui.ListView
import br.com.zup.beagle.widget.ui.Text

object ListViewScreenBuilder {
    fun buildListViewOptionsSceen() = buildScreen("Beagle ListView", buildOptionsScreen())

    fun buildSimpleListViewScreen() = buildScreen("SIMPLE LIST", buildSimpleListView(simpleValuesList, null))

    fun buildSimpleListViewWithScrollEndScreen() = buildScreen("SIMPLE LIST", buildSimpleListView(simpleValuesList, listOf(Alert(message = "OnScrollEnd"))))

    fun buildEmbeddedListViewWithContextScreen() = buildScreen("EMBEDDED LIST WITH CONTEXT",
        buildEmbeddedListViewWithContext())

    fun buildListViewEmptyWithScrollEndScreen() = buildScreen("EMPTY LIST",
        buildSimpleListView(emptyList(), listOf(Alert(message = "OnScrollEnd"))))

    fun buildListViewShortWithScrollEndScreen() = buildScreen("SHORT LIST",
        buildSimpleListView(shortSimpleValuesList, listOf(Alert(message = "OnScrollEnd"))))

    fun buildListViewSimpleWithContextScreen() = buildScreen("SIMPLE LIST",
        Container(children = listOf(personlist)))

    private fun buildScreen(title: String, child: Container) = Screen(
        navigationBar = NavigationBar(
            title = title,
            showBackButton = true,
            navigationBarItems = listOf(
                NavigationBarItem(
                    text = "",
                    image = Local.justMobile("informationImage"),
                    action = Alert(
                        title = "ListView",
                        message = "Is a Layout component that will define a list of views natively. " +
                            "These views could be any Server Driven Component.",
                        labelOk = "OK"
                    )
                )
            )
        ),
        child = child
    )

    private fun buildOptionsScreen() = Container(
        children = listOf(
            Button(
                text = "SIMPLE LIST",
                onPress = listOf(
                    Navigate.PushView(
                        route = Route.Remote(url = LISTVIEW_SIMPLE_TABVIEW_ENDPOINT)
                    )
                )
            ),
            Button(
                text = "SIMPLE LIST WITH CONTEXT",
                onPress = listOf(
                    Navigate.PushView(
                        route = Route.Remote(url = LISTVIEW_SIMPLE_WITH_CONTEXT_ENDPOINT)
                    )
                )
            ),
            Button(
                text = "EMPTY LIST WITH SCROLL END",
                onPress = listOf(
                    Navigate.PushView(
                        route = Route.Remote(url = LISTVIEW_EMPTY_SCROLLEND_ENDPOINT)
                    )
                )
            ),
            Button(
                text = "SHORT LIST WITH SCROLL END",
                onPress = listOf(
                    Navigate.PushView(
                        route = Route.Remote(url = LISTVIEW_SHORT_SCROLLEND_ENDPOINT)
                    )
                )
            ),
            Button(
                text = "SIMPLE LIST WITH SCROLL END",
                onPress = listOf(
                    Navigate.PushView(
                        route = Route.Remote(url = LISTVIEW_SIMPLE_SCROLLEND_ENDPOINT)
                    )
                )
            ),
            Button(
                text = "EMBEDDED LIST WITH CONTEXT",
                onPress = listOf(
                    Navigate.PushView(
                        route = Route.Remote(url = LISTVIEW_EMBEDDED_WITH_CONTEXT_TABVIEW_ENDPOINT)
                    )
                )
            ),
            Button(
                text = "EMBEDDED LIST TWO"
            )
        )
    ).applyFlex(
        flex = Flex(
            alignItems = AlignItems.CENTER,
            flexDirection = FlexDirection.COLUMN
        )
    )

    val simpleValuesList = listOf("1 OUTSIDE", "2 OUTSIDE", "3 OUTSIDE", "4 OUTSIDE", "5 OUTSIDE",
        "6 OUTSIDE", "7 OUTSIDE", "8 OUTSIDE", "9 OUTSIDE", "10 OUTSIDE", "11 OUTSIDE", "12 OUTSIDE",
        "13 OUTSIDE", "14 OUTSIDE", "15 OUTSIDE")

    private val shortSimpleValuesList = listOf("1 OUTSIDE")

    val simpleValuesListPage2 = listOf("16 OUTSIDE", "17 OUTSIDE", "18 OUTSIDE", "19 OUTSIDE", "20 OUTSIDE")

    val listNamesPage1 = listOf(
        Person(
            "John",
            0
        ),
        Person(
            "Carter",
            1
        ),
        Person(
            "Josie",
            2
        ),
        Person(
            "Dimitri",
            3
        ),
        Person(
            "Maria",
            4
        ),
        Person(
            "Max",
            5
        ),
        Person(
            "Kane",
            6
        ),
        Person(
            "Amelia",
            7
        ),
        Person(
            "Jose",
            8
        ),
        Person(
            "Percy",
            9
        ),
        Person(
            "Karen",
            10
        ),
        Person(
            "Sol",
            11
        ),
        Person(
            "Jacques",
            12
        ),
        Person(
            "Stephen",
            13
        ),
        Person(
            "Sullivan",
            14
        ),
        Person(
            "Zoe",
            15
        )
    )

    private fun buildSimpleListView(
        items: List<String>,
        onScrollEnd: List<Action>?
    ) = Container(
        children = listOf(
            ListView(
                context = ContextData(
                    id = "outsideContext",
                    value = items
                ),
                dataSource = expressionOf("@{outsideContext}"),
                direction = ListDirection.VERTICAL,
                onScrollEnd = onScrollEnd,
                template = Container(
                    children = listOf(
                        Text(
                            text = expressionOf("@{item}")
                        ).setId(id = "text")
                    )
                ).applyStyle(
                    Style(
                        size = Size(width = 500.unitReal(), height = 300.unitReal())
                    )
                )
            ).setId(
                id = "simpleList"
            )
        )
    )

    private fun buildEmbeddedListViewWithContext() = Container(
        children = listOf(
            ListView(
                context = ContextData(
                    id = "outsideContext",
                    value = emptyList<String>()
                ),
                onInit = listOf(
                    SendRequest(
                        url = "http://10.0.2.2:8080/listview-values",
                        onSuccess = listOf(
                            SetContext(
                                contextId = "outsideContext",
                                value = "@{onSuccess.data}"
                            )
                        )
                    )
                ),
                dataSource = expressionOf("@{outsideContext}"),
                direction = ListDirection.VERTICAL,
                template = Container(
                    children = listOf(
                        Text(
                            text = expressionOf("@{item}")
                        ).setId(id = "text"),
                        personlist
                    )
                ).applyStyle(
                    Style(
                        size = Size(width = 500.unitReal(), height = 300.unitReal())
                    )
                )
            ).setId(id = "outerList")
        )
    )

    data class Person(
        val name: String,
        val cpf: Int
    )

    private val personlist = ListView(
        context = ContextData(
            id = "insideContext",
            //value = emptyList<Person>()
        value = listNamesPage1
        ),
        /*onInit = listOf(
            SendRequest(
                url =  "http://10.0.2.2:8080/listview-names",
                onSuccess = listOf(
                    SetContext(
                        contextId = "insideContext",
                        value = "@{onSuccess.data}"
                    )
                )
            )
        ),*/
        key = "cpf",
        dataSource = expressionOf("@{insideContext}"),
        direction = ListDirection.HORIZONTAL,
        template = Container(
            children = listOf(
                Button(
                    text = expressionOf("@{item.name} - @{item.cpf}"),
                    onPress = listOf(
                        SetContext(
                            contextId = "insideContext",
                            path = "[0].name",
                            value = "Updated John"
                        )
                    )
                ).applyStyle(
                    Style(
                        size = Size(width = 300.unitReal(), height = 80.unitReal())
                    )
                ).setId("button")
            )
        ).setId("container")
    ).applyStyle(
        Style(
            backgroundColor = "#CCC"
        )
    ).setId(id = "innerList")
}
