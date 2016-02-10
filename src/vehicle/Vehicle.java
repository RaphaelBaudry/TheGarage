package vehicle;

public class Vehicle {
	protected int licencePlace;

	public Vehicle(int licence_place) {
		licencePlace = licence_place;
	}
	
	public int getLicencePlace() {
		return licencePlace;
	}

	public void setLicencePlace(int licencePlace) {
		this.licencePlace = licencePlace;
	}
}
