import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';
import 'package:ticket_bus_reservation_mobile_app/dtos/relation.dart';
import 'package:ticket_bus_reservation_mobile_app/main.dart';

import '../dialogs/dialogs.dart';

class RelationListScreen extends StatefulWidget {
  @override
  RelationListScreenState createState() => RelationListScreenState();
}

class RelationListScreenState extends State<RelationListScreen> {
  List<Relation> relations = [];

  @override
  void initState() {
    super.initState();
    fetchRelations();
  }

  Future<void> fetchRelations() async {
  
    final prefs = await SharedPreferences.getInstance();
    final token = prefs.getString('token');
    final url = Uri.parse('http://10.0.2.2:8080/rest/relation/list-all');
    final response = await http.get(url, headers: {'Authorization': 'Bearer $token'},);


    if (response.statusCode == 200) {
      final jsonBody = jsonDecode(response.body);
      final relationsGet = (jsonBody as List).map((e) => Relation.fromJson(e)).toList();
      setState(() {
        relations = relationsGet;
      });
    } else {
      throw Exception('Failed to fetch relations');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Relations'),
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
        child: relations.isEmpty
            ? Center(child: CircularProgressIndicator())
            : ListView.separated(
                itemCount: relations.length,
                separatorBuilder: (context, index) => Divider(
                  color: Colors.black,
                  height: 1,
                ),
                itemBuilder: (context, index) {
                  final relation = relations[index];
                  return Container(
                    color: Colors.white.withOpacity(0.7),
                    child: ListTile(
                      title: Text("${relation.from} - ${relation.to}"),
                      subtitle: Text("Estimated travel time: ${relation.estimatedTravelTime}"),
                      trailing: Column(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          Text('MKD${relation.price.toString()}'),
                           ElevatedButton(
                            onPressed: () {
                              Dialogs.showCreditCartDialog(relation.id, context);
                            },
                            child: Text('Buy'),
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