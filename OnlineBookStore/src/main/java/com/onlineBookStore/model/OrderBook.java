package com.onlineBookStore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OrderBook {

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "quantity")
    private Integer quantity;
}
