package com.example.mockreskill.model.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_product")
public class Product extends AuditingEntity{
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name= "thumbnail")
    private String thumbNail;
    @Column(name = "price")
    private BigDecimal price;

}
