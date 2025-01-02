package factory_method;

// product interface
interface Vehicle {
    void design();
    void manufacture();
}

// create concrete product classes
class Car implements Vehicle {
    @Override
    public void design() {
        System.out.println("Designing a Car");
    }

    @Override
    public void manufacture() {
        System.out.println("Manufacturing a Car");
    }
}

class Bike implements Vehicle {
    @Override
    public void design() {
        System.out.println("Designing a Bike");
    }

    @Override
    public void manufacture() {
        System.out.println("Manufacturing a Bike");
    }
}

// creator abstract class
abstract class VehicleFactory {
    abstract Vehicle createVehicle();

    public void buildVehicle() {
        Vehicle vehicle = createVehicle();
        vehicle.design();
        vehicle.manufacture();
    }
}

// concrete creators
class CarFactory extends VehicleFactory {
    @Override
    Vehicle createVehicle() {
        return new Car();
    }
}

class BikeFactory extends VehicleFactory {
    @Override
    Vehicle createVehicle() {
        return new Bike();
    }
}

public class FactoryMethod {
    public static void main(String[] args) {
        VehicleFactory carFactory = new CarFactory();
        carFactory.buildVehicle();

        VehicleFactory bikeFactory = new BikeFactory();
        bikeFactory.buildVehicle();
    }
}