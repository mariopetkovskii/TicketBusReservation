import 'dart:convert';
import 'dart:ffi';
import 'package:http/http.dart' as http;
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

class Dialogs{

static Future<void> reserveTicket(int relationId) async {
    final prefs = await SharedPreferences.getInstance();
    final token = prefs.getString('token');
    
    final url = Uri.parse('http://10.0.2.2:8080/rest/reservation/reserve');
    final body = jsonEncode({
      'relationId': relationId
    });

    final response = await http.post(
      url,
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer $token',
      },
      body: body,
    );

    if (response.statusCode == 200) {
    } else {
    }
  }

  static Future<void> showCreditCartDialog(int relationId, BuildContext context) async {
  final prefs = await SharedPreferences.getInstance();
  final token = prefs.getString('token');

  String cardNumber = '';
  String cardHolderName = '';
  String expirationDate = '';
  String cvv = '';

  await showDialog(
    context: context,
    builder: (context) => AlertDialog(
      title: Text('Enter credit card details'),
      content: Column(
        mainAxisSize: MainAxisSize.min,
        children: [
          TextField(
            keyboardType: TextInputType.number,
            decoration: InputDecoration(
              labelText: 'Card Number',
            ),
            onChanged: (value) {
              cardNumber = value;
            },
          ),
          TextField(
            decoration: InputDecoration(
              labelText: 'Cardholder Name',
            ),
            onChanged: (value) {
              cardHolderName = value;
            },
          ),
          TextField(
            keyboardType: TextInputType.datetime,
            decoration: InputDecoration(
              labelText: 'Expiration Date',
              hintText: 'MM/YY',
            ),
            onChanged: (value) {
              expirationDate = value;
            },
          ),
          TextField(
            keyboardType: TextInputType.number,
            decoration: InputDecoration(
              labelText: 'CVV',
            ),
            onChanged: (value) {
              cvv = value;
            },
          ),
        ],
      ),
      actions: [
        TextButton(
          onPressed: () {
            Navigator.of(context).pop();
            reserveTicket(relationId);
          },
          child: Text('Reserve'),
        ),
      ],
    ),
  );
}


}