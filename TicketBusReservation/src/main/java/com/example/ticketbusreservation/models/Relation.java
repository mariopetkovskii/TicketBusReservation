package com.example.ticketbusreservation.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "relations_table")
public class Relation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private City relationFrom;
    @ManyToOne
    private City relationTo;
    private Double price;
    private String estimatedTravelTime;
}
