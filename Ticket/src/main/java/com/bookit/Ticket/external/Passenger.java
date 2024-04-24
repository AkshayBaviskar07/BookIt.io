package com.bookit.Ticket.external;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Passenger {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private String city;

    private Long ticketId;
}
