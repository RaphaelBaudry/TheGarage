package manager;

import java.util.ArrayList;
import java.util.List;

import exceptions.VehicleNotFound;

import random.GenerateRandom;
import vehicle.Car;
import vehicle.Motorbike;
import vehicle.Vehicle;
import garage.Garage;
import garage.Level;
import garage.LevelPlace;
import garage.Place;

/**
 * Manager class which is the main class and manage the vehicles
 * @author Raphaël Baudry
 */
public class Manager {
	private List<Vehicle> vehicles_in; // list of all vehicles in the garage
	private List<Vehicle> vehicles_out; // list of all vehicles outside of the garage
	private Garage garage; // the garage used to park vehicles

	/**
	 * Main method used to generate vehicles and launch the trafic
	 * @param args
	 */
	public static void main(String[] args) {
		Manager manager = new Manager();
		manager.generateVehicles(50);
		manager.launchTrafic();
		manager.searchVehicle();
		manager.displayPlacesAvailable();
	}
	
	/**
	 * Constructor of the Manager class
	 */
	public Manager() {
		garage = new Garage();
	}

	/**
	 * Generate a number of new vehicles (alternates motorbike and car)
	 */
	public void generateVehicles(int number_vehicle) {
		vehicles_in = new ArrayList<Vehicle>();
		vehicles_out = new ArrayList<Vehicle>();
		
		for(int i = 1; i < number_vehicle; i++)
			if (i % 2 == 0)
				vehicles_out.add(new Motorbike(i));
			else
				vehicles_out.add(new Car(i));
	}
	
	/**
	 * Launch the trafic randomly in order to insert 
	 * vehicles in the parking and put ones out
	 */
	public void launchTrafic() {
		for(int i = 1; i < GenerateRandom.getRandom(10, vehicles_out.size()); i++)
			if (garage.enterGarage(vehicles_out.get(i)))
				vehicles_in.add(vehicles_out.get(i));

		vehicles_out.removeAll(vehicles_in);

		List<Vehicle> vehicles_to_be_removed = new ArrayList<Vehicle>();
		for(int i = 1; i < GenerateRandom.getRandom(vehicles_in.size()); i++)
			if (garage.exitGarage(vehicles_in.get(i))) {
				vehicles_out.add(vehicles_in.get(i));
				vehicles_to_be_removed.add(vehicles_in.get(i));
			}
		
		vehicles_in.removeAll(vehicles_to_be_removed);
	}
	
	/**
	 * Search a specific vehicle
	 */
	public void searchVehicle() {
		if(vehicles_in.size() != 0) {
			Vehicle vehicle = vehicles_in.
					get(GenerateRandom.getRandom(vehicles_in.size()) - 1);
			try {
				LevelPlace levelPlace = garage.getLevelPlace(vehicle);
				
				if (vehicle instanceof Car)
					System.out.print("The Vehicle car ");
				else
					System.out.print("The Vehicle motorbike ");
				System.out.println("with the licence plate : " + 
						vehicle.getLicencePlace() + " is in the level : " + 
						levelPlace.getLevel().getLevel() + " with the place : " + 
						levelPlace.getPlace().getPos());
			} catch (VehicleNotFound e) {
				System.out.println("Vehicle not found");
			}
		} else
			System.out.println("There is no car inside the parking !");
	}
	
	public void displayPlacesAvailable() {
		int nbPosition = 0;
		
		for(Level level : garage.getLevels())
			for(Place place : level.getPlaces())
				if(place.isEmpty())
					nbPosition++;
		
		System.out.println("Number of free spaces still available : " + nbPosition);
	}
}
