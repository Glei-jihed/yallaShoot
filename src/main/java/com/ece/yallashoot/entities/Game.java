package com.ece.yallashoot.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "game",indexes = {@Index(name = "date_idx",columnList = "date"),
                                @Index(name = "lat_idx",columnList = "category"),})
public class Game implements Serializable {


    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;


    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER)

    private Location location;


    @Column(nullable = false)
    private int playersNumber;


    @Column(nullable = false)
    private int requiredPlayers;


    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Set<User> players;


    @PrePersist
    public void playersCount(){
        this.requiredPlayers =  playersNumber - players.size();
    }


}
