package com.stream.functional.demo.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item")
@Data
public class Item {
    @Id
    private Long id;

    @Column(name = "color")
    private String color;
    @Column(name = "grade")
    private String grade;
    @Column(name = "name")
    private String name;

}
