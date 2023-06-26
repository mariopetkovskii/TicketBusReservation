import 'dart:io';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:ticket_bus_reservation_mobile_app/widgets/login-screen.dart';
import 'package:ticket_bus_reservation_mobile_app/widgets/register-screen.dart';
import 'package:ticket_bus_reservation_mobile_app/widgets/relations.dart';
import 'package:ticket_bus_reservation_mobile_app/widgets/reservations-screen.dart';
import 'package:ticket_bus_reservation_mobile_app/widgets/token-screen.dart';


void main() {
  runApp(
    MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MediaQuery(
        data: MediaQueryData(),
        child: MyHomePage(title: 'Flutter Demo Home Page'),
      ),
    ),
  );
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  bool _isLoggedIn = false;

  @override
  void initState() {
    super.initState();
    _checkLoginStatus();
  }

  Future<void> _checkLoginStatus() async {
    final prefs = await SharedPreferences.getInstance();
    final token = prefs.getString('token');
    setState(() {
      _isLoggedIn = token != null;
    });
  }

  void _logout() async {
    final prefs = await SharedPreferences.getInstance();
    await prefs.remove('token');
    setState(() {
      _isLoggedIn = false;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
        backgroundColor: Colors.red,
        actions: _isLoggedIn
            ? [
                IconButton(
                  onPressed: _logout,
                  icon: Icon(Icons.logout),
                )
              ]
            : [
                IconButton(
                  onPressed: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(builder: (context) => Container()),
                    );
                  },
                  icon: Icon(Icons.login),
                ),
              ],
      ),
      body: Stack(
        children: [
          Positioned.fill(
            child: Image.asset(
              'assets/busticket.jpg',
              fit: BoxFit.cover,
            ),
          ),Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            SizedBox(height: 20.0),
            if (_isLoggedIn)
              Column(
                children: [
                  ElevatedButton(
                onPressed: () {
                  Navigator.push(
                    context,
                    MaterialPageRoute(builder: (context) => RelationListScreen()),
                  );
                },
                child: Text(
                  'Relations',
                  style: TextStyle(fontSize: 20),
                ),
                style: ElevatedButton.styleFrom(
                  primary: Colors.red,
                  minimumSize: Size(200, 50),
                ),
              ),
              
                  SizedBox(height: 20.0),
                  ElevatedButton(
                    onPressed: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(builder: (context) => ReservationListScreen()),
                      );
                    },
                    child: Text(
                      'My Reservations',
                      style: TextStyle(fontSize: 20),
                    ),
                    style: ElevatedButton.styleFrom(
                      primary: Colors.red,
                      minimumSize: Size(200, 50),
                    ),
                  ),
                ],
              )
            else
              Column(
                children: [
                  ElevatedButton(
                    onPressed: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(builder: (context) => LoginScreen()),
                      );
                    },
                    child: Text(
                      'Login',
                      style: TextStyle(fontSize: 20),
                    ),
                    style: ElevatedButton.styleFrom(
                      primary: Colors.red,
                      minimumSize: Size(200, 50),
                    ),
                  ),
                  SizedBox(height: 20.0),
                  ElevatedButton(
                    onPressed: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(builder: (context) => RegisterScreen()),
                      );
                    },
                    child: Text(
                      'Register',
                      style: TextStyle(fontSize: 20),
                    ),
                    style: ElevatedButton.styleFrom(
                      primary: Colors.red,
                      minimumSize: Size(200, 50),
                    ),
                  ),
                ],
              ),
          ],
        ),
      ),
        ],
      ),
    );
  }
}