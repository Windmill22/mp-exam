package project.toti.andrea.vehicle;

public interface VehicleVisitor {

	void visitPetrolVehicle(PetrolVehicle vehicle);

	void visitPlugInHybridVehicle(PlugInHybridVehicle vehicle);

}
