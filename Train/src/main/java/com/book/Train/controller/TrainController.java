package com.book.Train.controller;

import com.book.Train.model.Train;
import com.book.Train.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("train")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @PostMapping
    public ResponseEntity<String> addTrain(@RequestBody Train train){

        boolean isAdded = trainService.addTrain(train);
        if(isAdded)
            return new ResponseEntity<>("Train added successfully", HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping
    public ResponseEntity<List<Train>> getAllTrains(){
        List<Train> trains = trainService.getAllTrains();
        if (!trains.isEmpty())
            return new ResponseEntity<>(trains, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{trainId}")
    public ResponseEntity<Train> getTrainById(@PathVariable Long trainId){
        Train availableTrain = trainService.getTrainById(trainId);
        if (availableTrain != null)
            return new ResponseEntity<>(availableTrain, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/{departure}/to/{arrival}")
    public ResponseEntity<Train> getByDepartureAndArrival(@PathVariable String departure,
                                                          @PathVariable String arrival){

        Train availableTrain = trainService.getByDepartureAndArrival(departure, arrival);
        if (availableTrain != null)
            return new ResponseEntity<>(availableTrain, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{trainId}")
    public ResponseEntity<String> updateTrainInfo(@PathVariable Long trainId,
                                                  @RequestBody Train updatedTrain) {

        boolean isUpdated = trainService.updateTrain(trainId, updatedTrain);
        if(isUpdated)
            return new ResponseEntity<>("Train updated successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
