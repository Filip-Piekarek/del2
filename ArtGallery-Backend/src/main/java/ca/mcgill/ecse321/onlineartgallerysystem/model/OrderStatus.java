package ca.mcgill.ecse321.onlineartgallerysystem.model;


import javax.persistence.Entity;

@Entity
public enum OrderStatus{
	
	IN_PROCESS,
	PAID,
	CONFIRMED,
	IN_TRANSIT,
	DELIVERED
}


