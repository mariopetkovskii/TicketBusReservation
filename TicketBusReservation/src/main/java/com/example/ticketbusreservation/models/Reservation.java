package com.example.ticketbusreservation.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "reservations_table")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @OneToOne
    private Relation relation;
    private Boolean isUsed;

    public Reservation(User user, Relation relation) {
        this.user = user;
        this.relation = relation;
        this.isUsed = false;
    }
}
