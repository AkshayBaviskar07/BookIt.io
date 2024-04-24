package com.bookit.Ticket.client;

import com.bookit.Ticket.external.Train;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "TRAIN-SERVICE")
public interface TrainClient {

    @GetMapping("/train/{trainId}")
    Train getTrainById(@PathVariable Long trainId);

    @PutMapping("/train/{trainId}")
    String updateTrainInfo(@PathVariable Long trainId,@RequestBody Train updatedTrain);
}
