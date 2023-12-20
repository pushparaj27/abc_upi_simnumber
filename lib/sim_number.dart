import 'dart:async';
import 'dart:convert';

import 'package:abc_upi_simnumber/siminfo.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';
import 'package:flutter/widgets.dart';

class SimNumber {
  static const String channelName = "sim_number";
  static const MethodChannel _channel = MethodChannel(channelName);

  static Future<bool> get hasPhonePermission async {
    final bool hasPermission =
        await _channel.invokeMethod('hasPhonePermission');
    return hasPermission;
  }

  static Future<SimInfo> getSimData() async {
    try {
      dynamic simData = await _channel.invokeMethod('getSimData');
      var data = json.decode(simData);
      SimInfo simCards = SimInfo.fromJson(data);
      return simCards;
    } on PlatformException catch (e) {
      debugPrint('SimDataPlugin failed to retrieve data $e');
      rethrow;
    }
  }

  static Future<void> listenPhonePermission(
      Function(bool isPermissionGranted) subscription) async {
    if (await SimNumber.hasPhonePermission) {
      subscription(true);
    } else {
      subscription(false);
    }
  }
}
