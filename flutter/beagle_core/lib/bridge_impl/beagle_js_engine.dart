/*
 *
 *  Copyright 2020 ZUP IT SERVICOS EM TECNOLOGIA E INOVACAO SA
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

import 'dart:convert';

import 'package:beagle_core/interface/beagle_view.dart';
import 'package:beagle_core/model/beagle_action.dart';
import 'package:beagle_core/model/beagle_ui_element.dart';
import 'package:beagle_core/model/request.dart';
import 'package:beagle_core/model/response.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';
import 'package:flutter_js/extensions/xhr.dart';
import 'package:flutter_js/flutter_js.dart';

typedef RemoveListener = void Function();
typedef ActionListener = void Function(BeagleAction action);
typedef HttpListener = void Function(String requestId, Request request);

// ignore: avoid_classes_with_only_static_members
class BeagleJSEngine {
  static JavascriptRuntime js;
  static HttpListener httpListener;
  static ActionListener actionListener;
  static Map<String, List<ViewUpdateListener>> viewUpdateListenerMap = {};
  static Map<String, List<ViewErrorListener>> viewErrorListenerMap = {};

  static dynamic _deserializeJSFunctions(dynamic value) {
    if (value.runtimeType.toString() == 'String' &&
        value.toString().startsWith('__beagleFn:')) {
      return ([Map<String, dynamic> argumentsMap]) {
        final args = argumentsMap == null
            ? "'$value'"
            : "'$value', ${json.encode(argumentsMap)}";
        final result = js.evaluate('global.beagle.call($args)');
        debugPrint('dynamic function result: $result');
      };
    }

    if (value.runtimeType.toString() == 'List<dynamic>') {
      // ignore: avoid_as
      return (value as List<dynamic>).map(_deserializeJSFunctions).toList();
    }

    if (value.runtimeType.toString() ==
        '_InternalLinkedHashMap<String, dynamic>') {
      // ignore: avoid_as
      final map = value as Map<String, dynamic>;
      final result = <String, dynamic>{};
      final keys = map.keys;

      // ignore: cascade_invocations, avoid_function_literals_in_foreach_calls
      keys.forEach((key) {
        result[key] = _deserializeJSFunctions(map[key]);
      });
      return result;
    }

    return value;
  }

  static var httpMethodMap = {
    'get': HttpMethod.get,
    'post': HttpMethod.post,
    'put': HttpMethod.put,
    'patch': HttpMethod.patch,
    'delete': HttpMethod.delete,
  };

  static void _setupHttpMessages() {
    js.onMessage('httpClient.request', (dynamic args) {
      if (httpListener == null) {
        debugPrint('ERROR: no http listener found.');
        return;
      }

      debugPrint('Received http request: $args');
      final String id = args['id'];
      final String url = args['url'];
      final String methodStr =
          args.containsKey('method') ? args['method'].toLowerCase() : 'get';

      if (!httpMethodMap.containsKey(methodStr)) {
        // ignore: only_throw_errors
        throw ErrorDescription('Unsupported http method $methodStr');
      }

      final method = httpMethodMap[methodStr];
      final headers = args.containsKey('headers')
          // ignore: avoid_as
          ? (args['headers'] as Map<String, dynamic>).cast<String, String>()
          : null;
      final String body = args['body'];
      final req = Request(url, method, headers, body);
      httpListener(id, req);
    });
  }

  static void _setupActionMessages() {
    js.onMessage('action', (dynamic args) {
      debugPrint('Received action: $args');
      final action = BeagleAction(_deserializeJSFunctions(args));

      if (actionListener == null) {
        debugPrint('ERROR: no listener found for actions.');
        return;
      }

      actionListener(action);
    });
  }

  static void _setupBeagleViewMessages() {
    js.onMessage('beagleView.update', (dynamic args) {
      debugPrint('Received tree from beagle-web-core');
      final viewId = args['id'];

      if (!viewUpdateListenerMap.containsKey(viewId) ||
          viewUpdateListenerMap[viewId].isEmpty) {
        debugPrint('No listener found for updates on view with id $viewId.');
        return;
      }

      final result = BeagleUIElement(_deserializeJSFunctions(args['tree']));
      // ignore: avoid_function_literals_in_foreach_calls
      viewUpdateListenerMap[viewId].forEach((listener) => listener(result));
    });
  }

  static void _setupMessages() {
    _setupHttpMessages();
    _setupActionMessages();
    _setupBeagleViewMessages();
  }

  static Future<void> start() async {
    if (js == null) {
      js = getJavascriptRuntime(forceJavascriptCoreOnAndroid: true, xhr: false);
      _setupMessages();
      final beagleJS = await rootBundle.loadString('assets/js/beagle.js');
      js.evaluate('var window = global = globalThis;');
      final bundleResult = await js.evaluateAsync(beagleJS);
      debugPrint('Initialization result: $bundleResult');
    }
  }

  // todo: increment this to pass more configurations
  static void createBeagleService(String baseUrl, List<String> actionKeys) {
    final actionArray = json.encode(actionKeys);
    final result = js.evaluate("global.beagle.start('$baseUrl', $actionArray)");
    debugPrint('Beagle service result: $result');
  }

  // todo: increment this to pass more configurations
  static String createBeagleView(String route) {
    final result = js.evaluate("global.beagle.createBeagleView('$route')");
    final id = result.stringResult;
    debugPrint('created beagle view with id $id');
    return id;
  }

  // ignore: use_setters_to_change_properties
  static void onAction(ActionListener listener) {
    actionListener = listener;
  }

  // ignore: use_setters_to_change_properties
  static void onHttpRequest(HttpListener listener) {
    httpListener = listener;
  }

  static RemoveListener onViewUpdate(
      String viewId, ViewUpdateListener listener) {
    viewUpdateListenerMap[viewId] = viewUpdateListenerMap[viewId] ?? [];
    viewUpdateListenerMap[viewId].add(listener);
    return () {
      viewUpdateListenerMap[viewId].remove(listener);
    };
  }

  static RemoveListener onViewUpdateError(
      String viewId, ViewErrorListener listener) {
    viewErrorListenerMap[viewId] = viewErrorListenerMap[viewId] ?? [];
    viewErrorListenerMap[viewId].add(listener);
    return () {
      viewErrorListenerMap[viewId].remove(listener);
    };
  }

  static void removeViewListeners(String viewId) {
    viewUpdateListenerMap.remove(viewId);
    viewErrorListenerMap.remove(viewId);
  }

  static void callJsFunction(String functionId,
      [Map<String, dynamic> argumentsMap]) {
    final args = argumentsMap == null
        ? "'$functionId'"
        : "'$functionId', ${json.encode(argumentsMap)}";
    final result = js.evaluate('global.beagle.call($args)');
    debugPrint('dynamic function result: $result');
  }

  static void respondHttpRequest(String id, Response response) {
    final result = js.evaluate(
        'global.beagle.httpClient.respond($id, ${response.toJson()})');
    debugPrint('httpClient.respond result: $result');
  }
}
