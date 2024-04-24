package com.book.Passenger.controller;

import com.book.Passenger.model.Passenger;
import com.book.Passenger.service.PassengerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PassengerController.class)
class PassengerControllerTest {

    @Autowired
    private PassengerController underTest;
    @MockBean
    private PassengerService passengerService;
    @Autowired
    private MockMvc mockMvc;
    Passenger passenger = null;

    @BeforeEach
    void setUp() {
        passenger = new Passenger(1L, "Rohan", "rohan@gmail.com", "987456321", "Nashik");
    }

    @AfterEach
    void tearDown() {
        passenger = null;
    }

    /**
     * Test for register passenger controller
    */
    @Test
    void test_registerPassenger() {

        // Mocking the registerPassenger method to return passenger
        when(passengerService.registerPassenger(passenger)).thenReturn(passenger);
        // Calling the method in under test class and stored the response
        ResponseEntity<Passenger> actualResponse = underTest.registerPassenger(passenger);
        // Verifying that the registerPassenger method called once with correct parameter
        verify(passengerService, times(1)).registerPassenger(passenger);
        // Checking if the status code in the response matches with expected status code
        assertEquals(HttpStatus.CREATED, actualResponse.getStatusCode());
    }

    /**
     * Test case for the getPassengers method.
     * @throws Exception if an error occurs during the test.
     */
    @Test
    void test_getPassengers() throws Exception {
        // Mocking the getAllPassengers method to return list of passengers
        when(passengerService.getAllPassengers()).thenReturn(List.of(passenger));
        // Perform GET request to "/passenger" and expect status code 200
        this.mockMvc.perform(get("/passenger"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void test_getPassengersEmptyList() throws Exception {
       when(passengerService.getAllPassengers()).thenReturn(new ArrayList<>());
       this.mockMvc.perform(get("/passenger"))
               .andExpect(status().isNotFound()).andDo(print());
    }

    /**
     * Test case for the getPassengerById method.
     * @throws Exception if an error occurs during the test
     */
    @Test
    void test_getPassengerById() throws Exception{
        // Mocking the getPassengerById method to return passenger
        when(passengerService.getPassengerById(passenger.getId())).thenReturn(passenger);
        // Perform GET request to "/passenger/{passengerId}" and expect status code 200
        this.mockMvc.perform(get("/passenger/" + passenger.getId()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    // Test case to verify behavior when getting a non-existing passenger by ID
    @Test
    void test_getPassengersByIdNotFound() throws Exception {

        // Mocking the behavior of the passengerService to return null when getting passenger by ID 2
        when(passengerService.getPassengerById(2L)).thenReturn(null);
        // Performing a GET request to "/passenger/2" and expecting a status of 404 (Not Found)
        this.mockMvc.perform(get("/passenger/2"))
                .andExpect(status().isNotFound()).andDo(print());
    }

    /**
     * Test case for the updatePassenger method.
     * @throws Exception if an error occurs during the test
     */
    @Test
    void test_updatePassenger() throws Exception{
        // Create the object mapper instance
        ObjectMapper mapper = new ObjectMapper();
        // Convert the passenger object to a JSON string
        String response = mapper.writeValueAsString(passenger);

        // Mocking the updatePassenger method to update record
        when(passengerService.updatePassenger(passenger, passenger.getId())).thenReturn(true);
        // Perform PUT request to "/passenger/{passengerId}" and expect status code 200
        this.mockMvc.perform(put("/passenger/"+passenger.getId())
                        .content(response)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    void test_updatePassengerNotFound() throws Exception {

        // Create the object mapper instance
        ObjectMapper mapper = new ObjectMapper();
        // Convert the passenger object to a JSON string
        String response = mapper.writeValueAsString(passenger);

        // Mock the behavior of the passenger service
        when(passengerService.updatePassenger(passenger, 2L)).thenReturn(false);
        // Perform a GET request on the "/passenger/2" endpoint with the JSON content
        this.mockMvc.perform(get("/passenger/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(response))
                // Assert that the response status is NOT FOUND
                .andExpect(status().isNotFound())
                // Print the response for debugging purposes
                .andDo(print());
    }

    /**
     * Test case for the deletePassenger method.
     * @throws Exception if an error occurs during the test
     */
    @Test
    void test_deletePassenger() throws Exception{
        // Mock the deletePassengerById method to delete passenger by Id
        when(passengerService.deletePassengerById(passenger.getId())).thenReturn(true);
        // Performing a DELETE request to "/passenger/{passengerId}" and expecting a status of 200 (OK)
        this.mockMvc.perform(delete("/passenger/"+passenger.getId()))
                .andExpect(status().isOk()).andDo(print());
    }

    // Test case to verify behavior when deleting a non-existing passenger by ID
    @Test
    void test_deletePassengerNotFound() throws Exception {

        // Mocking the behavior of the passengerService to return false when deleting passenger by ID 2
        when(passengerService.deletePassengerById(2L)).thenReturn(false);
        // Performing a DELETE request to "/passenger/2" and expecting a status of 404 (Not Found)
        this.mockMvc.perform(delete("/passenger/2"))
                .andExpect(status().isNotFound()).andDo(print());
    }
}