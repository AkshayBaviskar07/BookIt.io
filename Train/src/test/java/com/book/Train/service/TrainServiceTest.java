package com.book.Train.service;

import com.book.Train.dao.TrainRepo;
import com.book.Train.model.Train;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrainServiceTest {

    @Mock
    private TrainRepo trainRepo;
    @InjectMocks
    private TrainService underTest;
    Train train = null;

    @BeforeEach
    void setUp() {
        train = new Train(1L, "Demo Train", "Demo Departure ", "Demo Arrival", 1000.00, 10L);
    }

    @AfterEach
    void tearDown() {
        train = null;
    }

    /**
     * Test for addTrain method
     */
    @Test
    void test_addTrain() {

        // Mocking the repository call to save the train object in the database
        when(trainRepo.save(train)).thenReturn(train);
        // Calling the addTrain method of the TrainService
        boolean actualResponse = underTest.addTrain(train);

        // Checking object should not be null and response should be true
        assertNotNull(train);
        assertTrue(actualResponse);
        // Verifying the save method is called only once
        verify(trainRepo, times(1)).save(train);
    }

    /**
     * Test for getAllTrains method
     */
    @Test
    void test_getAllTrains() {

        List<Train> trains = new ArrayList<>();
        trains.add(train);

        // Mocking the repository call to get all trains from the database
        when(trainRepo.findAll()).thenReturn(trains);
        // Checking list should not be null and size should be 1
        assertNotNull(trains);
        assertEquals(1, underTest.getAllTrains().size());
        // Verifying the findAll method is called only once
        verify(trainRepo, times(1)).findAll();
    }

    /**
     * Test for getTrainById method
     */
    @Test
    void test_getTrainById() {

        // Mocking trainRepo call to get the train from database
        when(trainRepo.findById(train.getTrainId())).thenReturn(Optional.ofNullable(train));
        // Getting actual object from getTrainById method
        Train actualResponse = underTest.getTrainById(train.getTrainId());
        // Checking object should not be null and response should be same as expected train object
        assertNotNull(actualResponse);
        assertEquals(train, actualResponse);
        // Verifying the findById method is called only once
        verify(trainRepo, times(1)).findById(train.getTrainId());
    }

    /**
     * Test for getByDepartureAndArrival method
     */
    @Test
    void test_getByDepartureAndArrival() {

        // Mocking repository call to get the train by departure and arrival from db
        when(trainRepo.findByDepartureAndArrival(train.getDeparture(), train.getArrival()))
                .thenReturn(Optional.of(train));
        // Getting the actual object from getByDepartureAndArrival method
        Train actualResponse = underTest.getByDepartureAndArrival(train.getDeparture(), train.getArrival());
        // Checking object should not be null and response should be same as expected train object
        assertNotNull(actualResponse);
        assertEquals(train, actualResponse);
        //verifying the indByDepartureAndArrival called only once
        verify(trainRepo, times(1))
                .findByDepartureAndArrival(train.getDeparture(), train.getArrival());
    }


    @Test
    void test_updateTrain() {

        // Mocking the repository call to findById method to get train from db
        when(trainRepo.findById(train.getTrainId())).thenReturn(Optional.of(train));
        // Calling the updateTrain method of the TrainService
        boolean actualResponse = underTest.updateTrain(train.getTrainId(), train);
        // Checking response should be true
        assertTrue(actualResponse);
        // Verify the findById method is called only once
        verify(trainRepo, times(1)).findById(train.getTrainId());
    }
}