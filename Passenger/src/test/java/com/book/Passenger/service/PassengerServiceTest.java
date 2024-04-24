package com.book.Passenger.service;

import com.book.Passenger.dao.PassengerRepo;
import com.book.Passenger.exception.PassengerNotFoundException;
import com.book.Passenger.model.Passenger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PassengerServiceTest {

    @InjectMocks
    private PassengerService underTest;
    @Mock
    private PassengerRepo passengerRepo;
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
     * Test for registering a passenger.
     */
    @Test
    void test_registerPassenger() {

        // Mocking the save method of passengerRepo to return the passenger
        when(passengerRepo.save(passenger)).thenReturn(passenger);
        // Ensuring passenger is not null
        assertNotNull(passenger);
        // Checking if the registered passenger matches the expected passenger
        assertEquals(passenger, underTest.registerPassenger(passenger));
        // Check whether save method is called only once
        verify(passengerRepo, times(1)).save(passenger);
        // Check whether the registerPassenger method does not throw exception
        assertDoesNotThrow(() -> underTest.registerPassenger(passenger));
    }

    /**
     * Test for registering a passenger which throws PassengerNotFoundException
     */
    @Test
    void test_registerPassengerDoesThrowException() {

        // Mocking the save method of passengerRepo to throw PassengerNotFoundException
        when(passengerRepo.save(passenger)).thenThrow(PassengerNotFoundException.class);
        // Checking if the registerPassenger method throws PassengerNotFoundException
        assertThrows(PassengerNotFoundException.class, () -> underTest.registerPassenger(passenger));
    }

    /**
     * Test for retrieve all passengers from database
    */
    @Test
    void test_getAllPassengers() {

        // Mocking the findAll method of passengerRepo to return the list of passengers
        when(passengerRepo.findAll()).thenReturn(List.of(passenger));
        // Get the list of passengers
        List<Passenger> testPassengers = underTest.getAllPassengers();
        // Checking if the size of records matches the expected list of passengers
        assertEquals(1, testPassengers.size());
    }

    @Test
    void test_getAllPassengersDoesThrowException() {

        // Mocking the findAll method of passengerRepo to throw a PassengerNotFoundException
        when(passengerRepo.findAll()).thenThrow(PassengerNotFoundException.class);
        // Checking if the getAllPassengers throws PassengerNotFoundException
        assertThrows(PassengerNotFoundException.class, () -> underTest.getAllPassengers());
    }

    /**
     * Test to retrieve passenger record by id
     */
    @Test
    void test_getPassengerById() {

        // Mocking the findById method of passengerRepo to return a record by Id
        when(passengerRepo.findById(passenger.getId())).thenReturn(Optional.ofNullable(passenger));
        // Retrieve the passenger by its Id
        Passenger actualPassenger = underTest.getPassengerById(passenger.getId());
        // Ensuring that passenger is not null
        assertNotNull(passenger);
        // Checking if the retrieved passenger is matching with expected record
        assertEquals(passenger, actualPassenger);
    }

    @Test
    void test_getAllPassengerByIdDoesThrowException() {

        // Mocking the findAll method of passengerRepo to throw a PassengerNotFoundException
        when(passengerRepo.findById(passenger.getId())).thenThrow(PassengerNotFoundException.class);
        // Checking if the getAllPassengers throws PassengerNotFoundException
        assertThrows(PassengerNotFoundException.class, () -> underTest.getPassengerById(passenger.getId()));
    }

    /**
     * Test to update existing records
     */
    @Test
    void test_updatePassenger() {

        // Mocking the findById method of passengerRepo to return a record by Id
        when(passengerRepo.findById(passenger.getId())).thenReturn(Optional.ofNullable(passenger));
        // Calling the updatePassenger method with the passenger object and Id
        boolean actualResult = underTest.updatePassenger(passenger, passenger.getId());
        // Asserting that the update was successful
        assertTrue(actualResult);
    }

    @Test
    void test_updatePassengerDoesThrowException() {

        // Mocking the findAll method of passengerRepo to throw a PassengerNotFoundException
        when(passengerRepo.findById(passenger.getId())).thenThrow(PassengerNotFoundException.class);
        // Checking if the getAllPassengers throws PassengerNotFoundException
        assertThrows(PassengerNotFoundException.class, () -> underTest.updatePassenger(passenger, passenger.getId()));
    }

    /**
     * Test to delete existing record by Id
     */
    @Test
    void test_deletePassengerById() {

        // Mocking the findById method of passengerRepo to return a record by Id
        when(passengerRepo.findById(passenger.getId())).thenReturn(Optional.ofNullable(passenger));
        // Calling the deletePassengerById method with the passenger Id
        boolean actualResult = underTest.deletePassengerById(passenger.getId());
        // Asserting that the record deleted
        assertTrue(actualResult);
    }

    @Test
    void test_deletePassengerByIdDoesThrowException() {

        // Mocking the findAll method of passengerRepo to throw a PassengerNotFoundException
        when(passengerRepo.findById(passenger.getId())).thenThrow(PassengerNotFoundException.class);
        // Checking if the getAllPassengers throws PassengerNotFoundException
        assertThrows(PassengerNotFoundException.class, () -> underTest.deletePassengerById(passenger.getId()));
    }
}