package com.ece.yallashoot.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(indexes = {@Index(name = "date_idx",columnList = "date"),
                                @Index(name = "cat_idx",columnList = "category"),
                                @Index(name="loc_idx",columnList = "location_id"),
                                @Index(name="loc_idx",columnList = "founder_id")})
public class Game implements Serializable {


    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2",strategy = "uuid2")
    private String id;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;


    @Column(nullable = false)
    private String description;



    @Column(nullable = false)
    private int playersNumber;

    @Column(nullable = false)
    private int registredPlayers;


    @Column(nullable = false)
    private int requiredPlayers;

    @OneToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.EAGER)
    private Location location;

    @OneToOne()
    private User founder;

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE},fetch = FetchType.EAGER)
    private Set<Request> joinRequests;





    //================================= Pre persist methods ============================================================


    @PrePersist
    public void playersCount(){
        this.requiredPlayers =  playersNumber - registredPlayers;
    }


}
