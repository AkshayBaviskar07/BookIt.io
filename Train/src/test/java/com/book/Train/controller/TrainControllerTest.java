package com.book.Train.controller;

import com.book.Train.model.Train;
import com.book.Train.service.TrainService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.converters.Auto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TrainController.class)
class TrainControllerTest {

    @MockBean
    private TrainService trainService;
    @Autowired
    private TrainController underTest;
    @Autowired
    private MockMvc mockMvc;
    Train train = null;

    @BeforeEach
    void setUp() {
        train = new Train(1L, "Demo Train", "Demo Departure ", "Demo Arrival", 1000.00, 10L);
    }

    @AfterEach
    void tearDown() {
        train = null;
    }

    @Test
    void addTrain() throws Exception{
        // Creating Object mapper instance to convert Java object to JSON
        ObjectMapper mapper = new ObjectMapper();
        String response = mapper.writeValueAsString(train);

        // Mocking addTran method of TrainService to return true
        when(trainService.addTrain(train)).thenReturn(true);
        // Performing POST request to "/train" endpoint with request body as JSON expecting status code 201 (CREATED)
        this.mockMvc.perform(post("/train")
                .contentType(MediaType.APPLICATION_JSON).content(response))
                .andExpect(status().isCreated()).andDo(print());
    }

    @Test
    void getAllTrains() throws Exception{
        // Mocking getAllTrains method of TrainService to return list of trains
        when(trainService.getAllTrains()).thenReturn(List.of(train));
        // Performing GET request to "/train" endpoint expecting status code 200 (OK)
        this.mockMvc.perform(get("/train"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getTrainById() throws Exception{
        // Mocking getTrainById method of TrainService to return train object
        when(trainService.getTrainById(train.getTrainId())).thenReturn(train);
        // Performing GET request "/train/{trainId}" endpoint expecting status code 200 (OK)
        this.mockMvc.perform(get("/train/"+train.getTrainId()))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    void getByDepartureAndArrival() throws Exception{
        // Mocking getByDepartureAndArrival method in trainService to return train object
        when(trainService.getByDepartureAndArrival(train.getDeparture(), train.getArrival())).thenReturn(train);
        // Perform GET request "/train/{departure}/to/{arrival}" endpoint expecting status code 200 (OK)
        this.mockMvc.perform(
                get("/train/" +train.getDeparture()+ "/to/" +train.getArrival()))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    void updateTrainInfo() throws Exception{

        // Creating object mapper instance to convert Java object to JSON
        ObjectMapper mapper = new ObjectMapper();
        String response = mapper.writeValueAsString(train);

        // Mocking updateTrainInfo method in trainService to update train info and return true
        when(trainService.updateTrain(train.getTrainId(), train)).thenReturn(true);
        // Perform PUT request to "/train/{trainId}" endpoint with request body as JSON expecting status code 200 (OK)
        mockMvc.perform(put("/train/" +train.getTrainId())
                .contentType(MediaType.APPLICATION_JSON).content(response))
                .andExpect(status().isOk()).andDo(print());
    }
}