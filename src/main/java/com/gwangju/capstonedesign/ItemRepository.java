package com.gwangju.capstonedesign;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findBytmEqk(String tmEqk);
}
