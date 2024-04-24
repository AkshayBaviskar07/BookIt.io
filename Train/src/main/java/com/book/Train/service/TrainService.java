package com.book.Train.service;

import com.book.Train.dao.TrainRepo;
import com.book.Train.exception.TrainNotFoundException;
import com.book.Train.model.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainService {

    @Autowired
    private TrainRepo trainRepo;

    public boolean addTrain(Train train){
       if(train != null) {
           trainRepo.save(train);
           return true;
       } else {
           return false;
       }
    }

    public List<Train> getAllTrains() {
        List<Train> trains = trainRepo.findAll();
        if(!trains.isEmpty()) {
            return trains;
        } else {
            throw new TrainNotFoundException("Train not found!");
        }
    }

    public Train getTrainById(Long id) {
        Optional<Train> trainOptional = trainRepo.findById(id);

        if(trainOptional.isPresent()){
            return trainOptional.get();
        } else{
            throw new TrainNotFoundException("Not Found with id " + id);
        }
    }


    public Train getByDepartureAndArrival(String departure, String arrival) {
        Optional<Train> trainOptional = trainRepo.findByDepartureAndArrival(departure, arrival);

        if(trainOptional.isPresent()){
            return trainOptional.get();
        } else{
            throw new TrainNotFoundException("Not found");
        }
    }

    public boolean updateTrain(Long trainId, Train updatedTrain) {
        Optional<Train> trainOptional = trainRepo.findById(trainId);
        if(trainOptional.isPresent()) {
            Train train = trainOptional.get();

            train.setName(updatedTrain.getName());
            train.setAvailableSeats(updatedTrain.getAvailableSeats());
            train.setTravelAmount(updatedTrain.getTravelAmount());
            train.setDeparture(updatedTrain.getDeparture());
            train.setArrival(updatedTrain.getArrival());
            train.setTickets(updatedTrain.getTickets());

            return true;
        } else {
            throw new TrainNotFoundException("Train not found");
        }
    }

}
