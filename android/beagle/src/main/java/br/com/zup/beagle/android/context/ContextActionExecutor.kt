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

package br.com.zup.beagle.android.context

import android.view.View
import br.com.zup.beagle.analytics2.AnalyticsHandleEvent
import br.com.zup.beagle.android.action.Action
import br.com.zup.beagle.android.action.ActionAnalytics
import br.com.zup.beagle.android.action.AsyncAction
import br.com.zup.beagle.android.utils.generateViewModelInstance
import br.com.zup.beagle.android.view.viewmodel.AnalyticsViewModel
import br.com.zup.beagle.android.view.viewmodel.AsyncActionViewModel
import br.com.zup.beagle.android.view.viewmodel.ScreenContextViewModel
import br.com.zup.beagle.android.widget.RootView

internal object ContextActionExecutor {

    @Suppress("LongParameterList")
    fun executeActions(
        rootView: RootView,
        origin: View,
        sender: Any,
        actions: List<Action>,
        context: ContextData? = null,
        analyticsHandleEvent: AnalyticsHandleEvent? = null
    ) {
        if (context != null) {
            createImplicitContextForActions(rootView, sender, context, actions)
        }

        executeActions(rootView, origin, actions, analyticsHandleEvent)
    }

    private fun createImplicitContextForActions(
        rootView: RootView,
        sender: Any,
        context: ContextData,
        actions: List<Action>
    ) {
        val viewModel = rootView.generateViewModelInstance<ScreenContextViewModel>()
        viewModel.addImplicitContext(context.normalize(), sender, actions)
    }

    private fun executeActions(
        rootView: RootView,
        origin: View,
        actions: List<Action>?,
        analyticsHandleEvent: AnalyticsHandleEvent?
    ) {
        actions?.forEach { action ->
            if (action is AsyncAction) {
                val viewModel = rootView.generateViewModelInstance<AsyncActionViewModel>()
                viewModel.onAsyncActionExecuted(AsyncActionData(origin, action))
                action.onActionStarted()
            }
            executeAction(action, rootView, origin, analyticsHandleEvent)
        }
    }

    private fun executeAction(
        action: Action,
        rootView: RootView,
        origin: View,
        analyticsHandleEvent: AnalyticsHandleEvent? = null
    ) {
        if (action is ActionAnalytics) {
            reportActionAnalytics(
                action,
                rootView,
                origin,
                analyticsHandleEvent
            )
        } else {
            action.execute(rootView, origin)
        }
    }

    private fun reportActionAnalytics(
        action: ActionAnalytics,
        rootView: RootView,
        origin: View,
        analyticsHandleEvent: AnalyticsHandleEvent?
    ) {
        action.execute(rootView, origin, analyticsHandleEvent?.originComponent)
        rootView.generateViewModelInstance<AnalyticsViewModel>().createActionReport(
            rootView,
            origin,
            action,
            analyticsHandleEvent
        )
    }
}

internal data class AsyncActionData(
    val origin: View,
    val asyncAction: AsyncAction
)
