import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';


class AuthApiService {

  String endpoint = "http://10.0.2.2:8080";

  static Future<void> register(
    String firstName,
    String lastName,
    String email,
    String password,
    String confirmPassword,
  ) async {
    if (password != confirmPassword) {
      throw Exception('Passwords do not match');
    }

    final url = Uri.parse('http://10.0.2.2:8080/rest/user/register');
    final body = jsonEncode({
      'firstName': firstName,
      'lastName': lastName,
      'email': email,
      'password': password,
      'confirmPassword': confirmPassword,
    });

    final response = await http.post(
      url,
      headers: {'Content-Type': 'application/json'},
      body: body,
    );

    if (response.statusCode == 200) {
      // Registration success
    } else {
      throw Exception('Failed to register: ${response.body}');
    }
  }

  static Future<void> login(String email, String password) async {
    final bytes = utf8.encode('$email:$password');
    final base64Str = base64.encode(bytes);

    final url = Uri.parse('http://10.0.2.2:8080/rest/user/login');
    final response = await http.post(
      url,
      headers: {'Authorization': 'Basic $base64Str'},
    );

    if (response.statusCode == 200) {
      final prefs = await SharedPreferences.getInstance();
      await prefs.setString('token', response.body);
    } else {
      throw Exception('Login failed: Invalid email or password');
    }
  }

}