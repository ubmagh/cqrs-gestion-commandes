package me.ubmagh.customerservicequery.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    private String id;

    private String nom;
    private String email;
    private String tel;
    private String adresse;


}
