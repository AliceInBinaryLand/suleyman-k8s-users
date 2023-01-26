package com.epam.suleymank8susers.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column
    private String username;

    @Column
    private long amountOfPosts;

    public void  increasePostCount(){
        this.amountOfPosts++;
    }
    public void decreasePostCount(){
        this.amountOfPosts--;
    }
}
