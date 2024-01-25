package com.ece.yallashoot.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Request implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2",strategy="uuid2")
    private String id;


    @Column(nullable = false)
    private String note;

    @Column(nullable = false)
    private boolean accepted;

    @Column(nullable = false)
    private boolean refused;

    @Column(nullable = false)
    private boolean inProgress;

    @OneToOne()
    private User player;

    @OneToOne()
    private Game requestedGame;


    @PrePersist()
    public void setStatus(){
        this.accepted =false;
        this.inProgress = true;
        this.refused=false;
    }








}
