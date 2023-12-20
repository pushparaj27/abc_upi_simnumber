class SimInfo {
  final List<SimCard> cards;
  SimInfo(this.cards);

  static SimInfo fromJson(data) {
    return SimInfo(data != null && data is List
        ? data.map<SimCard>((_card) => SimCard.fromJson(_card)).toList()
        : []);
  }
}

class SimCard {
  String? carrierName;
  String? countryIso;
  String? displayName;
  int? slotIndex;
  String? phoneNumber;

  SimCard(this.carrierName, this.displayName, this.slotIndex, this.countryIso,
      this.phoneNumber);

  static SimCard fromJson(dynamic card) {
    return SimCard(card['carrierName'], card['displayName'], card['slotIndex'],
        card['countryIso'], card['number']);
  }
}
