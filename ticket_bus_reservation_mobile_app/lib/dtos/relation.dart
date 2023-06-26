
import 'dart:ffi';

class Relation{
  final int id;
  final String from;
  final String to;
  final double price;
  final String estimatedTravelTime;
  
  Relation({
    required this.id,
    required this.from,
    required this.to,
    required this.price,
    required this.estimatedTravelTime,
  });

  factory Relation.fromJson(Map<String, dynamic> json){
    return Relation(id: json['id'], from: json['from'], to: json['to'], price: json['price'], estimatedTravelTime: json['estimatedTravelTime']);
  }
}