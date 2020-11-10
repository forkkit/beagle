#
# Copyright 2020 ZUP IT SERVICOS EM TECNOLOGIA E INOVACAO SA
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

@listview @regression
Feature: ListView Component Validation

    As a Beagle developer/user
    I'd like to make sure my listView component works as expected
    In order to guarantee that my application never fails

    Background:
        Given that I'm on the listview screen

#    Scenario Outline: 01 - simple listView renders all items correctly
#        When I click on button SIMPLE LIST
#        And system navigates to SIMPLE LIST view
#        And system shows a listView component with id simpleList
#        Then listView at <position> renders view with <id> and <text>
#
#        Examples:
#            | position | id      | text       |
#            | 0        | text:0  | 1 OUTSIDE  |
#            | 1        | text:1  | 2 OUTSIDE  |
#            | 2        | text:2  | 3 OUTSIDE  |
#            | 3        | text:3  | 4 OUTSIDE  |
#            | 4        | text:4  | 5 OUTSIDE  |
#            | 5        | text:5  | 6 OUTSIDE  |
#            | 6        | text:6  | 7 OUTSIDE  |
#            | 7        | text:7  | 8 OUTSIDE  |
#            | 8        | text:8  | 9 OUTSIDE  |
#            | 9        | text:9  | 10 OUTSIDE |
#            | 10       | text:10 | 11 OUTSIDE |
#            | 11       | text:11 | 12 OUTSIDE |
#            | 12       | text:12 | 13 OUTSIDE |
#            | 13       | text:13 | 14 OUTSIDE |
#            | 14       | text:14 | 15 OUTSIDE |

#    Scenario Outline: 02 - simple listView renders all items correctly after screen rotation
#        When I click on button SIMPLE LIST
#        And system navigates to SIMPLE LIST view
#        And system shows a listView component with id simpleList
#        And I scroll listView to position 14
#        And I rotate the screen to landscape
#        And I scroll listView to position 0
#        And I rotate the screen to portrait
#        Then listView at <position> renders view with <id> and <text>
#
#        Examples:
#            | position | id      | text       |
#            | 0        | text:0  | 1 OUTSIDE  |
#            | 1        | text:1  | 2 OUTSIDE  |
#            | 2        | text:2  | 3 OUTSIDE  |
#            | 3        | text:3  | 4 OUTSIDE  |
#            | 4        | text:4  | 5 OUTSIDE  |
#            | 5        | text:5  | 6 OUTSIDE  |
#            | 6        | text:6  | 7 OUTSIDE  |
#            | 7        | text:7  | 8 OUTSIDE  |
#            | 8        | text:8  | 9 OUTSIDE  |
#            | 9        | text:9  | 10 OUTSIDE |
#            | 10       | text:10 | 11 OUTSIDE |
#            | 11       | text:11 | 12 OUTSIDE |
#            | 12       | text:12 | 13 OUTSIDE |
#            | 13       | text:13 | 14 OUTSIDE |
#            | 14       | text:14 | 15 OUTSIDE |

#    Scenario: 03 - simple listView executes onScrollEnd action
#        When I click on button SIMPLE LIST WITH SCROLL END
#        And system navigates to SIMPLE LIST view
#        And system shows a listView component with id simpleList
#        And I scroll listView to position 14
#        Then an alert containing OnScrollEnd message should appear


#    Scenario: 04 - listView executes onScrollEnd action only one time
#        When I click on button SIMPLE LIST WITH SCROLL END
#        And system navigates to SIMPLE LIST view
#        And system shows a listView component with id simpleList
#        And I scroll listView to position 14
#        And an alert containing OnScrollEnd message should appear
#        And I click on OK button
#        And I scroll listView to position 0
#        And I scroll listView to position 14
#        Then an alert containing OnScrollEnd message should NOT appear

#    Scenario: 05 - empty listView executes onScrollEnd action
#        When I click on button EMPTY LIST WITH SCROLL END
#        Then an alert containing OnScrollEnd message should appear
#


#    Scenario: 06 - short listView executes onScrollEnd action
#        When I click on button SHORT LIST WITH SCROLL END
#        Then an alert containing OnScrollEnd message should appear

#    Scenario Outline: 07 - simple listView evaluates context correctly
#        When I click on button SIMPLE LIST WITH CONTEXT
#        And system navigates to SIMPLE LIST view
#        And system shows a listView component with id innerList
#        And I click on John - 0.0 button
#        Then listView at <position> renders view with <id> and <text>
#
#        Examples:
#            | position | id        | text                  |
#            | 0        | button:0  | Updated John - 0.0    |
#            | 1        | button:1  | Carter - 1.0          |
#            | 2        | button:2  | Josie - 2.0           |
#            | 3        | button:3  | Dimitri - 3.0         |
#            | 4        | button:4  | Maria - 4.0           |
#            | 5        | button:5  | Max - 5.0             |
#            | 6        | button:6  | Kane - 6.0            |
#            | 7        | button:7  | Amelia - 7.0          |
#            | 8        | button:8  | Jose - 8.0            |
#            | 9        | button:9  | Percy - 9.0           |
#            | 10       | button:10 | Karen - 10.0          |
#            | 11       | button:11 | Sol - 11.0            |
#            | 12       | button:12 | Jacques - 12.0        |
#            | 13       | button:13 | Stephen - 13.0        |
#            | 14       | button:14 | Sullivan - 14.0       |

    Scenario Outline: 08 - simple listView evaluates context correctly after screen rotation
        When I click on button SIMPLE LIST WITH CONTEXT
        And system navigates to SIMPLE LIST view
        And system shows a listView component with id innerList
        And I scroll listView to position 14
        And I rotate the screen to landscape
        And I scroll listView to position 0
        And I rotate the screen to portrait
        #And I click on John - 0.0 button
        Then listView at <position> renders view with <id> and <text>

        Examples:
            | position | id        | text                  |
            | 0        | button:0  | Updated John - 0.0    |
            | 1        | button:1  | Carter - 1.0          |
            | 2        | button:2  | Josie - 2.0           |
            | 3        | button:3  | Dimitri - 3.0         |
            | 4        | button:4  | Maria - 4.0           |
            | 5        | button:5  | Max - 5.0             |
            | 6        | button:6  | Kane - 6.0            |
            | 7        | button:7  | Amelia - 7.0          |
            | 8        | button:8  | Jose - 8.0            |
            | 9        | button:9  | Percy - 9.0           |
            | 10       | button:10 | Karen - 10.0          |
            | 11       | button:11 | Sol - 11.0            |
            | 12       | button:12 | Jacques - 12.0        |
            | 13       | button:13 | Stephen - 13.0        |
            | 14       | button:14 | Sullivan - 14.0       |

#    Scenario Outline: Embedded ListView 01 - listView renders all items correctly
#        When I click on button EMBEDDED LIST WITH CONTEXT
#        And system navigates to EMBEDDED LIST WITH CONTEXT view
#        And system shows a listView component with id outerList
#        Then Outer listView at <outerListPosition> shows Inner listView with id <innerListId> that renders a view with <id> and <text> at <innerListPosition>
#
#        Examples:
#            | outerListPosition | innerListId  | innerListPosition | id           | text            |
#            | 0                 | innerList:0  | 0                 | button:0:0   | John - 0.0      |
#            | 1                 | innerList:1  | 1                 | button:1:1   | Carter - 1.0    |
#            | 2                 | innerList:2  | 2                 | button:2:2   | Josie - 2.0     |
#            | 3                 | innerList:3  | 3                 | button:3:3   | Dimitri - 3.0   |
#            | 4                 | innerList:4  | 4                 | button:4:4   | Maria - 4.0     |
#            | 5                 | innerList:5  | 5                 | button:5:5   | Max - 5.0       |
#            | 6                 | innerList:6  | 6                 | button:6:6   | Kane - 6.0      |
#            | 7                 | innerList:7  | 7                 | button:7:7   | Amelia - 7.0    |
#            | 8                 | innerList:8  | 8                 | button:8:8   | Jose - 8.0      |
#            | 9                 | innerList:9  | 9                 | button:9:9   | Percy - 9.0     |
#            | 10                | innerList:10 | 10                | button:10:10 | Karen - 10.0    |
#            | 11                | innerList:11 | 11                | button:11:11 | Sol - 11.0      |
#            | 12                | innerList:12 | 12                | button:12:12 | Jacques - 12.0  |
#            | 13                | innerList:13 | 13                | button:13:13 | Stephen - 13.0  |
#            | 14                | innerList:14 | 14                | button:14:14 | Sullivan - 14.0 |
#            | 14                | innerList:14 | 15                | button:14:15 | Zoe - 15.0      |


#    Scenario Outline: Embedded ListView 02 - listView renders all items correctly after screen rotation
#        When I click on button EMBEDDED LIST WITH CONTEXT
#        And system navigates to EMBEDDED LIST WITH CONTEXT view
#        And system shows a listView component with id outerList
#        And I scroll listView to position 14
#        And I rotate the screen to landscape
#        And I rotate the screen to portrait
#        Then Outer listView at <outerListPosition> shows Inner listView with id <innerListId> that renders a view with <id> and <text> at <innerListPosition>
#
#        Examples:
#            | outerListPosition | innerListId  | innerListPosition | id           | text            |
#            | 0                 | innerList:0  | 0                 | button:0:0   | John - 0.0      |
#            | 1                 | innerList:1  | 1                 | button:1:1   | Carter - 1.0    |
#            | 2                 | innerList:2  | 2                 | button:2:2   | Josie - 2.0     |
#            | 3                 | innerList:3  | 3                 | button:3:3   | Dimitri - 3.0   |
#            | 4                 | innerList:4  | 4                 | button:4:4   | Maria - 4.0     |
#            | 5                 | innerList:5  | 5                 | button:5:5   | Max - 5.0       |
#            | 6                 | innerList:6  | 6                 | button:6:6   | Kane - 6.0      |
#            | 7                 | innerList:7  | 7                 | button:7:7   | Amelia - 7.0    |
#            | 8                 | innerList:8  | 8                 | button:8:8   | Jose - 8.0      |
#            | 9                 | innerList:9  | 9                 | button:9:9   | Percy - 9.0     |
#            | 10                | innerList:10 | 10                | button:10:10 | Karen - 10.0    |
#            | 11                | innerList:11 | 11                | button:11:11 | Sol - 11.0      |
#            | 12                | innerList:12 | 12                | button:12:12 | Jacques - 12.0  |
#            | 13                | innerList:13 | 13                | button:13:13 | Stephen - 13.0  |
#            | 14                | innerList:14 | 14                | button:14:14 | Sullivan - 14.0 |
#            | 14                | innerList:14 | 15                | button:14:15 | Zoe - 15.0      |

    # Esperando Merge do PR 1124
#    Scenario Outline: Embedded ListView 03 - listView evaluate context correctly
#        When I click on button EMBEDDED LIST WITH CONTEXT
#        And system navigates to EMBEDDED LIST WITH CONTEXT view
#        And system shows a listView component with id outerList
#        And I click on inner list on button with id button:0:0
#        Then Outer listView at <outerListPosition> shows Inner listView with id <innerListId> that renders a view with <id> and <text> at <innerListPosition>
#
#        Examples:
#            | outerListPosition | innerListId  | innerListPosition | id           | text            |
#            | 0                 | innerList:0  | 0                 | button:0:0   | Updated John    |
#            | 1                 | innerList:1  | 0                 | button:1:0   | John - 0.0      |
#            | 2                 | innerList:2  | 0                 | button:2:0   | John - 0.0      |
#            | 3                 | innerList:3  | 0                 | button:3:0   | John - 0.0      |
#            | 4                 | innerList:4  | 0                 | button:4:0   | John - 0.0      |
#            | 5                 | innerList:5  | 0                 | button:5:0   | John - 0.0      |
#            | 6                 | innerList:6  | 0                 | button:6:0   | John - 0.0      |
#            | 7                 | innerList:7  | 0                 | button:7:0   | John - 0.0      |
#            | 8                 | innerList:8  | 0                 | button:8:0   | John - 0.0      |
#            | 9                 | innerList:9  | 0                 | button:9:0   | John - 0.0      |
#            | 10                | innerList:10 | 0                 | button:10:0  | John - 0.0      |
#            | 11                | innerList:11 | 0                 | button:11:0  | John - 0.0      |
#            | 12                | innerList:12 | 0                 | button:12:0  | John - 0.0      |
#            | 13                | innerList:13 | 0                 | button:13:0  | John - 0.0      |
#            | 14                | innerList:14 | 0                 | button:14:0  | John - 0.0      |

    # Esperando Merge do PR 1124
#    Scenario Outline: Embedded ListView 04 - listView evaluate context correctly after screen rotation
#        When I click on button EMBEDDED LIST WITH CONTEXT
#        And system navigates to EMBEDDED LIST WITH CONTEXT view
#        And system shows a listView component with id outerList
#        And I scroll listView to position 14
#        And I rotate the screen to landscape
#        And I rotate the screen to portrait
#        And I scroll listView to position 0
#        And I click on inner list on button with id button:0:0
#        Then Outer listView at <outerListPosition> shows Inner listView with id <innerListId> that renders a view with <id> and <text> at <innerListPosition>
#
#        Examples:
#            | outerListPosition | innerListId  | innerListPosition | id           | text            |
#            | 0                 | innerList:0  | 0                 | button:0:0   | Updated John    |
#            | 1                 | innerList:1  | 0                 | button:1:0   | John - 0.0      |
#            | 2                 | innerList:2  | 0                 | button:2:0   | John - 0.0      |
#            | 3                 | innerList:3  | 0                 | button:3:0   | John - 0.0      |
#            | 4                 | innerList:4  | 0                 | button:4:0   | John - 0.0      |
#            | 5                 | innerList:5  | 0                 | button:5:0   | John - 0.0      |
#            | 6                 | innerList:6  | 0                 | button:6:0   | John - 0.0      |
#            | 7                 | innerList:7  | 0                 | button:7:0   | John - 0.0      |
#            | 8                 | innerList:8  | 0                 | button:8:0   | John - 0.0      |
#            | 9                 | innerList:9  | 0                 | button:9:0   | John - 0.0      |
#            | 10                | innerList:10 | 0                 | button:10:0  | John - 0.0      |
#            | 11                | innerList:11 | 0                 | button:11:0  | John - 0.0      |
#            | 12                | innerList:12 | 0                 | button:12:0  | John - 0.0      |
#            | 13                | innerList:13 | 0                 | button:13:0  | John - 0.0      |
#            | 14                | innerList:14 | 0                 | button:14:0  | John - 0.0      |