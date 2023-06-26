import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';
import 'package:ticket_bus_reservation_mobile_app/dtos/relation.dart';
import 'package:ticket_bus_reservation_mobile_app/main.dart';
import 'package:ticket_bus_reservation_mobile_app/widgets/nfc-scan-screen.dart';

import '../dialogs/dialogs.dart';
import '../dtos/reservation.dart';

class ReservationListScreen extends StatefulWidget {
  @override
  ReservationListScreenState createState() => ReservationListScreenState();
}

class ReservationListScreenState extends State<ReservationListScreen> {
  List<Reservation> reservations = [];

  @override
  void initState() {
    super.initState();
    fetchReservations();
  }

  Future<void> scanReservation(int reservationId) async {
    final prefs = await SharedPreferences.getInstance();
    final token = prefs.getString('token');
    final url = Uri.parse('http://10.0.2.2:8080/rest/reservation/scan');
    final body = jsonEncode({
      'reservationId': reservationId
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
      // ignore: use_build_context_synchronously
      Navigator.push(context, MaterialPageRoute(builder: (context) => NFCScreen()),);
    } else {
      throw Exception('Failed to scan');
    }
  }

  Future<void> fetchReservations() async {
  
    final prefs = await SharedPreferences.getInstance();
    final token = prefs.getString('token');
    final url = Uri.parse('http://10.0.2.2:8080/rest/reservation/my-reservations');
    final response = await http.get(url, headers: {'Authorization': 'Bearer $token'},);


    if (response.statusCode == 200) {
      final jsonBody = jsonDecode(response.body);
      final reservationGet = (jsonBody as List).map((e) => Reservation.fromJson(e)).toList();
      setState(() {
        reservations = reservationGet;
      });
    } else {
      throw Exception('Failed to fetch reservations');
    }
  }

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
            image: AssetImage('assets/busticket.jpg'),
            fit: BoxFit.cover,
          ),
        ),
        child: reservations.isEmpty
            ? Center(child: CircularProgressIndicator())
            : ListView.separated(
                itemCount: reservations.length,
                separatorBuilder: (context, index) => Divider(
                  color: Colors.black,
                  height: 1,
                ),
                itemBuilder: (context, index) {
                  final reservation = reservations[index];
                  return Container(
                    color: Colors.white.withOpacity(0.7),
                    child: ListTile(
                      title: Text("${reservation.from} - ${reservation.to}"),
                      trailing: Column(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          Text('MKD${reservation.price.toString()}'),
                           ElevatedButton(
                            onPressed: () {
                              scanReservation(reservation.id);
                            },
                            child: Text('Scan'),
                            style: ElevatedButton.styleFrom(primary: Colors.orange),
                          ),
                        ],
                      ),
                    ),
                  );
                },
              ),
      ),
    );
  }
}