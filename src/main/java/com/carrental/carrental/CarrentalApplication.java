package com.carrental.carrental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarrentalApplication {

	/*
	Relationships

	Vehicle:
		Id: One-to-One
		Make: Many-to-Many
		Model: Many-to-Many
		Year: Many-to-Many
		Miles: Many-to-Many

	Customer:
		Id: One-to-One
		Name: Many-to-Many (Multiple accounts can have the same name without conflict)
		IsMember: Many-to-Many
		LicenceNumber: One-to-One

		Vehicle to Customer: One to One
		Customer to Vehicle: One to One
		Mechanic to Repair: One to Many
		Repair to Vehicle: Many to One
	 */

	public static void main(String[] args) {
		SpringApplication.run(CarrentalApplication.class, args);
	}

}
