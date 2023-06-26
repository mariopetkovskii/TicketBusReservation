
import 'dart:ffi';

class Reservation{
  final int id;
  final String from;
  final String to;
  final double price;
  
  Reservation({
    required this.id,
    required this.from,
    required this.to,
    required this.price
  });

  factory Reservation.fromJson(Map<String, dynamic> json){
    return Reservation(id: json['id'], from: json['from'], to: json['to'], price: json['price']);
  }
}