import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';
import 'package:ticket_bus_reservation_mobile_app/dtos/relation.dart';
import 'package:ticket_bus_reservation_mobile_app/main.dart';

import '../dialogs/dialogs.dart';
import '../dtos/reservation.dart';

class NFCScreen extends StatefulWidget {
  @override
  NFCScreenState createState() => NFCScreenState();
}

class NFCScreenState extends State<NFCScreen> {
  

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Reservations'),
        backgroundColor: Colors.red,
        actions: [
          IconButton(
            icon: Icon(Icons.arrow_back_sharp),
            onPressed: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => MyHomePage(title: 'HomePage')),
              );
            },
          ),
        ],
      ),
      body: Container(
        decoration: BoxDecoration(
          image: DecorationImage(
            image: AssetImage('assets/nfc.png'),
            fit: BoxFit.cover,
          ),
        ),
      ),
    );
  }
}