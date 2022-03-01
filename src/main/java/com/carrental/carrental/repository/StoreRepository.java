package com.carrental.carrental.repository;

import com.carrental.carrental.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
