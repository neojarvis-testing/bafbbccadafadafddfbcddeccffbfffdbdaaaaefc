package com.examly.apigateway;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class ApigatewayApplicationTests {
    private String usertoken;
    private String admintoken;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper; // To parse JSON responses

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    @Test
    @Order(1)
    void backend_testRegisterAdmin() {
        String requestBody = "{\"userId\": 1,\"email\": \"demoadmin@gmail.com\", \"password\": \"admin@1234\", \"username\": \"admin123\", \"userRole\": \"ADMIN\", \"mobileNumber\": \"9876543210\"}";
        ResponseEntity<String> response = restTemplate.postForEntity("/api/register",
                new HttpEntity<>(requestBody, createHeaders()), String.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @Order(2)
    void backend_testRegisterUser() {
        String requestBody = "{\"userId\": 2,\"email\": \"demouser@gmail.com\", \"password\": \"user@1234\", \"username\": \"user123\", \"userRole\": \"USER\", \"mobileNumber\": \"1122334455\"}";
        ResponseEntity<String> response = restTemplate.postForEntity("/api/register",
                new HttpEntity<>(requestBody, createHeaders()), String.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @Order(3)
    void backend_testLoginAdmin() throws Exception {
        String requestBody = "{\"email\": \"demoadmin@gmail.com\", \"password\": \"admin@1234\"}";

        ResponseEntity<String> response = restTemplate.postForEntity("/api/login",
                new HttpEntity<>(requestBody, createHeaders()), String.class);

    // Check if response body is null
    Assertions.assertNotNull(response.getBody(), "Response body is null!");

        JsonNode responseBody = objectMapper.readTree(response.getBody());
        String token = responseBody.get("token").asText();
        admintoken = token;

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(token);
    }

    @Test
    @Order(4)
    void backend_testLoginUser() throws Exception {
        String requestBody = "{\"email\": \"demouser@gmail.com\", \"password\": \"user@1234\"}";

        ResponseEntity<String> response = restTemplate.postForEntity("/api/login",
                new HttpEntity<>(requestBody, createHeaders()), String.class);

        JsonNode responseBody = objectMapper.readTree(response.getBody());
        String token = responseBody.get("token").asText();
        usertoken = token;

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(token);
    }

@Test
@Order(5)
void backend_testAddTurfWithRoleValidation() throws Exception {
    // Ensure tokens are available
    Assertions.assertNotNull(admintoken, "Admin token should not be null");
    Assertions.assertNotNull(usertoken, "User token should not be null");

    // Turf request body including user reference
    String requestBody = "{"
        + "\"name\": \"Neo Turf Stadium\","
        + "\"location\": \"Coimbatore\","
        + "\"pricePerHour\": 1200.0,"
        + "\"description\": \"Premium turf with night lighting.\","
        + "\"isActive\": true,"
        + "\"photo\": \"sample-base64-image-data==\","
        + "\"user\": {"
        + "    \"userId\": 1"
        + "}"
        + "}";

    // --- Admin tries to add turf (should succeed) ---
    HttpHeaders adminHeaders = createHeaders();
    adminHeaders.set("Authorization", "Bearer " + admintoken);
    HttpEntity<String> adminRequest = new HttpEntity<>(requestBody, adminHeaders);

    ResponseEntity<String> adminResponse = restTemplate.exchange(
        "/api/turf", HttpMethod.POST, adminRequest, String.class);

    JsonNode responseJson = objectMapper.readTree(adminResponse.getBody());

    System.out.println(adminResponse.getStatusCode() + " Status code for Admin adding turf");
    Assertions.assertEquals(HttpStatus.CREATED, adminResponse.getStatusCode());
    Assertions.assertEquals("Neo Turf Stadium", responseJson.get("name").asText());
    Assertions.assertEquals("Coimbatore", responseJson.get("location").asText());
    Assertions.assertEquals(1200.0, responseJson.get("pricePerHour").asDouble());
    Assertions.assertTrue(responseJson.get("isActive").asBoolean());

    // --- User tries to add turf (should be forbidden) ---
    HttpHeaders userHeaders = createHeaders();
    userHeaders.set("Authorization", "Bearer " + usertoken);
    HttpEntity<String> userRequest = new HttpEntity<>(requestBody, userHeaders);

    ResponseEntity<String> userResponse = restTemplate.exchange(
        "/api/turf", HttpMethod.POST, userRequest, String.class);

    System.out.println(userResponse.getStatusCode() + " Status code for User trying to add turf");
    Assertions.assertEquals(HttpStatus.FORBIDDEN, userResponse.getStatusCode());
}

    
@Test
@Order(6)
void backend_testGetTurfByIdWithRoleValidation() throws Exception {
    // Ensure tokens are available
    Assertions.assertNotNull(admintoken, "Admin token should not be null");
    Assertions.assertNotNull(usertoken, "User token should not be null");

    // Turf ID to retrieve
    int turfId = 1; // Use a valid turfId that you already added in your database during the add test.

    // --- Admin tries to get turf by ID (should succeed) ---
    HttpHeaders adminHeaders = createHeaders();
    adminHeaders.set("Authorization", "Bearer " + admintoken);
    HttpEntity<String> adminRequest = new HttpEntity<>(null, adminHeaders);

    ResponseEntity<String> adminResponse = restTemplate.exchange(
        "/api/turf/" + turfId, HttpMethod.GET, adminRequest, String.class);

    JsonNode adminResponseJson = objectMapper.readTree(adminResponse.getBody());

    System.out.println(adminResponse.getStatusCode() + " Status code for Admin retrieving turf by ID");
    Assertions.assertEquals(HttpStatus.OK, adminResponse.getStatusCode());
    Assertions.assertEquals(turfId, adminResponseJson.get("turfId").asInt());
    Assertions.assertEquals("Neo Turf Stadium", adminResponseJson.get("name").asText());
    Assertions.assertEquals("Coimbatore", adminResponseJson.get("location").asText());

    // --- User tries to get turf by ID (should succeed) ---
    HttpHeaders userHeaders = createHeaders();
    userHeaders.set("Authorization", "Bearer " + usertoken);
    HttpEntity<String> userRequest = new HttpEntity<>(null, userHeaders);

    ResponseEntity<String> userResponse = restTemplate.exchange(
        "/api/turf/" + turfId, HttpMethod.GET, userRequest, String.class);

    JsonNode userResponseJson = objectMapper.readTree(userResponse.getBody());

    System.out.println(userResponse.getStatusCode() + " Status code for User retrieving turf by ID");
    Assertions.assertEquals(HttpStatus.OK, userResponse.getStatusCode());
    Assertions.assertEquals(turfId, userResponseJson.get("turfId").asInt());
    Assertions.assertEquals("Neo Turf Stadium", userResponseJson.get("name").asText());
    Assertions.assertEquals("Coimbatore", userResponseJson.get("location").asText());
}


@Test
@Order(7)
void backend_testGetAllTurfsWithRoleValidation() throws Exception {
    // Ensure tokens are available
    Assertions.assertNotNull(admintoken, "Admin token should not be null");
    Assertions.assertNotNull(usertoken, "User token should not be null");

    // --- Admin tries to get all turfs (should succeed) ---
    HttpHeaders adminHeaders = createHeaders();
    adminHeaders.set("Authorization", "Bearer " + admintoken);
    HttpEntity<String> adminRequest = new HttpEntity<>(null, adminHeaders);

    ResponseEntity<String> adminResponse = restTemplate.exchange(
        "/api/turf", HttpMethod.GET, adminRequest, String.class);

    JsonNode adminResponseJson = objectMapper.readTree(adminResponse.getBody());

    System.out.println(adminResponse.getStatusCode() + " Status code for Admin retrieving all turfs");
    Assertions.assertEquals(HttpStatus.OK, adminResponse.getStatusCode());
    Assertions.assertTrue(adminResponseJson.isArray(), "Response body should be an array");
    Assertions.assertTrue(adminResponseJson.size() > 0, "There should be at least one turf in the response");

    // --- User tries to get all turfs (should succeed) ---
    HttpHeaders userHeaders = createHeaders();
    userHeaders.set("Authorization", "Bearer " + usertoken);
    HttpEntity<String> userRequest = new HttpEntity<>(null, userHeaders);

    ResponseEntity<String> userResponse = restTemplate.exchange(
        "/api/turf", HttpMethod.GET, userRequest, String.class);

    JsonNode userResponseJson = objectMapper.readTree(userResponse.getBody());

    System.out.println(userResponse.getStatusCode() + " Status code for User retrieving all turfs");
    Assertions.assertEquals(HttpStatus.OK, userResponse.getStatusCode());
    Assertions.assertTrue(userResponseJson.isArray(), "Response body should be an array");
    Assertions.assertTrue(userResponseJson.size() > 0, "There should be at least one turf in the response");
}


@Test
@Order(8)
void backend_testUpdateTurfWithRoleValidation() throws Exception {
    // Ensure tokens are available
    Assertions.assertNotNull(admintoken, "Admin token should not be null");
    Assertions.assertNotNull(usertoken, "User token should not be null");

    // Turf update request body
    String requestBody = "{"
        + "\"turfId\": 1,"  // Assuming we are updating the turf with ID 1
        + "\"name\": \"Updated Turf Stadium\","  // New name for the turf
        + "\"location\": \"Updated Location\","  // New location
        + "\"pricePerHour\": 1500.0,"  // Updated price per hour
        + "\"description\": \"Updated turf with additional facilities.\","  // Updated description
        + "\"isActive\": true,"  // Turf is active
        + "\"photo\": \"updated-base64-image-data==\","  // Updated photo (base64 data)
        + "\"user\": {"
        + "    \"userId\": 1"  // The admin user reference
        + "}"
        + "}";

    // --- Admin tries to update turf (should succeed) ---
    HttpHeaders adminHeaders = createHeaders();
    adminHeaders.set("Authorization", "Bearer " + admintoken);
    HttpEntity<String> adminRequest = new HttpEntity<>(requestBody, adminHeaders);

    ResponseEntity<String> adminResponse = restTemplate.exchange(
        "/api/turf/1", HttpMethod.PUT, adminRequest, String.class);  // Assuming turf ID 1

    JsonNode adminResponseJson = objectMapper.readTree(adminResponse.getBody());

    System.out.println(adminResponse.getStatusCode() + " Status code for Admin updating turf");
    Assertions.assertEquals(HttpStatus.OK, adminResponse.getStatusCode());
    Assertions.assertEquals("Updated Turf Stadium", adminResponseJson.get("name").asText());
    Assertions.assertEquals("Updated Location", adminResponseJson.get("location").asText());
    Assertions.assertEquals(1500.0, adminResponseJson.get("pricePerHour").asDouble());
    Assertions.assertTrue(adminResponseJson.get("isActive").asBoolean());

    // --- User tries to update turf (should be forbidden) ---
    HttpHeaders userHeaders = createHeaders();
    userHeaders.set("Authorization", "Bearer " + usertoken);
    HttpEntity<String> userRequest = new HttpEntity<>(requestBody, userHeaders);

    ResponseEntity<String> userResponse = restTemplate.exchange(
        "/api/turf/1", HttpMethod.PUT, userRequest, String.class);  // Assuming turf ID 1

    System.out.println(userResponse.getStatusCode() + " Status code for User trying to update turf");
    Assertions.assertEquals(HttpStatus.FORBIDDEN, userResponse.getStatusCode());
}


@Test
@Order(9)
void backend_testCreateBookingWithRoleValidation() throws Exception {
    Assertions.assertNotNull(usertoken, "User token should not be null");
    Assertions.assertNotNull(admintoken, "Admin token should not be null");
    // Booking request body
    String requestBody = "{"
        + "\"bookingForDate\": \"2025-05-10\","
        + "\"startTime\": \"10:00\","
        + "\"endTime\": \"11:00\","
        + "\"status\": \"BOOKED\","
        + "\"user\": { \"userId\": 2 },"
        + "\"turf\": { \"turfId\": 1 }"
        + "}";

    // --- USER should be able to book the turf ---
    HttpHeaders userHeaders = createHeaders();
    userHeaders.set("Authorization", "Bearer " + usertoken);
    HttpEntity<String> userRequest = new HttpEntity<>(requestBody, userHeaders);

    ResponseEntity<String> userResponse = restTemplate.exchange(
        "/api/booking", HttpMethod.POST, userRequest, String.class);

    System.out.println(userResponse.getStatusCode() + " Status code for User booking turf");
    Assertions.assertEquals(HttpStatus.CREATED, userResponse.getStatusCode());

    // --- ADMIN should not be able to book the turf ---
    HttpHeaders adminHeaders = createHeaders();
    adminHeaders.set("Authorization", "Bearer " + admintoken);
    HttpEntity<String> adminRequest = new HttpEntity<>(requestBody, adminHeaders);
    ResponseEntity<String> adminResponse = restTemplate.exchange(
        "/api/booking", HttpMethod.POST, adminRequest, String.class);
    System.out.println(adminResponse.getStatusCode() + " Status code for Admin booking turf");
    Assertions.assertEquals(HttpStatus.FORBIDDEN, adminResponse.getStatusCode());
}

@Test
@Order(10)
void backend_testViewAllBookings_AdminOnlyAccess() throws Exception {
    Assertions.assertNotNull(admintoken, "Admin token should not be null");
    Assertions.assertNotNull(usertoken, "User token should not be null");

    // --- ADMIN tries to get all bookings (Should succeed) ---
    HttpHeaders adminHeaders = createHeaders();
    adminHeaders.set("Authorization", "Bearer " + admintoken);
    HttpEntity<Void> adminRequest = new HttpEntity<>(adminHeaders);

    ResponseEntity<String> adminResponse = restTemplate.exchange(
        "/api/booking", HttpMethod.GET, adminRequest, String.class);

    System.out.println(adminResponse.getStatusCode() + " Status code for Admin viewing all bookings");
    Assertions.assertEquals(HttpStatus.OK, adminResponse.getStatusCode());

    // --- USER tries to get all bookings (Should be forbidden) ---
    HttpHeaders userHeaders = createHeaders();
    userHeaders.set("Authorization", "Bearer " + usertoken);
    HttpEntity<Void> userRequest = new HttpEntity<>(userHeaders);

    ResponseEntity<String> userResponse = restTemplate.exchange(
        "/api/booking", HttpMethod.GET, userRequest, String.class);

    System.out.println(userResponse.getStatusCode() + " Status code for User viewing all bookings");
    Assertions.assertEquals(HttpStatus.FORBIDDEN, userResponse.getStatusCode());
}



@Test
@Order(11)
void backend_testUpdateBooking_AdminOnlyAccess() throws Exception {
    Assertions.assertNotNull(admintoken, "Admin token should not be null");
    Assertions.assertNotNull(usertoken, "User token should not be null");

    // --- Booking update payload including user and turf ---
    String updateBody = "{"
        + "\"bookingId\": 1,"
        + "\"bookingForDate\": \"2025-05-10\","
        + "\"startTime\": \"15:00\","
        + "\"endTime\": \"17:00\","
        + "\"status\": \"BOOKED\","
        + "\"user\": { \"userId\": 2 },"
        + "\"turf\": { \"turfId\": 1 }"
        + "}";

    // --- ADMIN tries to update booking (Should succeed) ---
    HttpHeaders adminHeaders = createHeaders();
    adminHeaders.set("Authorization", "Bearer " + admintoken);
    HttpEntity<String> adminRequest = new HttpEntity<>(updateBody, adminHeaders);

    ResponseEntity<String> adminResponse = restTemplate.exchange(
        "/api/booking/1", HttpMethod.PUT, adminRequest, String.class);

    System.out.println(adminResponse.getStatusCode() + " Status code for Admin updating booking");
    Assertions.assertEquals(HttpStatus.OK, adminResponse.getStatusCode());

    // --- USER tries to update booking (Should be forbidden) ---
    HttpHeaders userHeaders = createHeaders();
    userHeaders.set("Authorization", "Bearer " + usertoken);
    HttpEntity<String> userRequest = new HttpEntity<>(updateBody, userHeaders);

    ResponseEntity<String> userResponse = restTemplate.exchange(
        "/api/booking/1", HttpMethod.PUT, userRequest, String.class);

    System.out.println(userResponse.getStatusCode() + " Status code for User updating booking");
    Assertions.assertEquals(HttpStatus.FORBIDDEN, userResponse.getStatusCode());
}

@Test
@Order(12)
void backend_testAddFeedbackWithRoleValidation() throws Exception {
    // Ensure tokens are available
    Assertions.assertNotNull(admintoken, "Admin token should not be null");
    Assertions.assertNotNull(usertoken, "User token should not be null");

    String requestBody = "{"
    + "\"feedbackText\": \"Great service!\","
    + "\"rating\": 5,"
    + "\"user\": { \"userId\": 2 },"
    + "\"turf\": { \"turfId\": 1 },"
    + "\"date\": \"2024-07-10\""
    + "}";

    // --- USER tries to add feedback (Should succeed) ---
    HttpHeaders userHeaders = createHeaders();
    userHeaders.set("Authorization", "Bearer " + usertoken);
    HttpEntity<String> userRequest = new HttpEntity<>(requestBody, userHeaders);

    ResponseEntity<String> userResponse = restTemplate.exchange("/api/feedback", HttpMethod.POST, userRequest, String.class);
    JsonNode responseBody = objectMapper.readTree(userResponse.getBody());

    System.out.println(userResponse.getStatusCode() + " Status code for User adding feedback");
    Assertions.assertEquals(HttpStatus.CREATED, userResponse.getStatusCode());
    Assertions.assertEquals("Great service!", responseBody.get("message").asText());
    Assertions.assertEquals(5, responseBody.get("rating").asInt());

    // --- ADMIN tries to add feedback (Should be forbidden) ---
    HttpHeaders adminHeaders = createHeaders();
    adminHeaders.set("Authorization", "Bearer " + admintoken);
    HttpEntity<String> adminRequest = new HttpEntity<>(requestBody, adminHeaders);

    ResponseEntity<String> adminResponse = restTemplate.exchange("/api/feedback", HttpMethod.POST, adminRequest, String.class);
    System.out.println(adminResponse.getStatusCode() + " Status code for Admin trying to add feedback");
    Assertions.assertEquals(HttpStatus.FORBIDDEN, adminResponse.getStatusCode());
}


@Test
@Order(13)
void backend_testGetAllFeedbackWithRoleValidation() throws Exception {
    Assertions.assertNotNull(admintoken, "Admin token should not be null");
    Assertions.assertNotNull(usertoken, "User token should not be null");

    // Admin should be able to view all feedback
    HttpHeaders adminHeaders = createHeaders();
    adminHeaders.set("Authorization", "Bearer " + admintoken);
    HttpEntity<String> adminRequest = new HttpEntity<>(adminHeaders);

    ResponseEntity<String> adminResponse = restTemplate.exchange("/api/feedback", HttpMethod.GET, adminRequest, String.class);
    Assertions.assertEquals(HttpStatus.OK, adminResponse.getStatusCode());

    // User should NOT be able to view all feedback
    HttpHeaders userHeaders = createHeaders();
    userHeaders.set("Authorization", "Bearer " + usertoken);
    HttpEntity<String> userRequest = new HttpEntity<>(userHeaders);

    ResponseEntity<String> userResponse = restTemplate.exchange("/api/feedback", HttpMethod.GET, userRequest, String.class);
    Assertions.assertEquals(HttpStatus.FORBIDDEN, userResponse.getStatusCode());
}

@Test
@Order(14)
void backend_testGetFeedbackByUserIdWithRoleValidation() throws Exception {
    Assertions.assertNotNull(admintoken, "Admin token should not be null");
    Assertions.assertNotNull(usertoken, "User token should not be null");

    String url = "/api/feedback/user/2";

    // User should be able to view their own feedback
    HttpHeaders userHeaders = createHeaders();
    userHeaders.set("Authorization", "Bearer " + usertoken);
    HttpEntity<String> userRequest = new HttpEntity<>(userHeaders);

    ResponseEntity<String> userResponse = restTemplate.exchange(url, HttpMethod.GET, userRequest, String.class);
    Assertions.assertEquals(HttpStatus.OK, userResponse.getStatusCode());

    // Admin should also be able to view feedback by user ID
    HttpHeaders adminHeaders = createHeaders();
    adminHeaders.set("Authorization", "Bearer " + admintoken);
    HttpEntity<String> adminRequest = new HttpEntity<>(adminHeaders);

    ResponseEntity<String> adminResponse = restTemplate.exchange(url, HttpMethod.GET, adminRequest, String.class);
    Assertions.assertEquals(HttpStatus.FORBIDDEN, adminResponse.getStatusCode());
}

     }
