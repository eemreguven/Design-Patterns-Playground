package factory_method

// product interface
interface Vehicle {
    fun design()
    fun manufacture()
}

// create concrete product classes
class Car : Vehicle {
    override fun design() {
        println("Designing a Car")
    }

    override fun manufacture() {
        println("Manufacturing a Car")
    }
}

class Bike : Vehicle {
    override fun design() {
        println("Designing a Bike")
    }

    override fun manufacture() {
        println("Manufacturing a Bike")
    }
}

// creator abstract class
abstract class VehicleFactory {
    abstract fun createVehicle(): Vehicle

    fun buildVehicle() {
        val vehicle = createVehicle()
        vehicle.design()
        vehicle.manufacture()
    }
}

// concrete creators
class CarFactory : VehicleFactory() {
    override fun createVehicle(): Vehicle {
        return Car()
    }
}

class BikeFactory : VehicleFactory() {
    override fun createVehicle(): Vehicle {
        return Bike()
    }
}

fun main() {
    val carFactory: VehicleFactory = CarFactory()
    carFactory.buildVehicle()

    val bikeFactory: VehicleFactory = BikeFactory()
    bikeFactory.buildVehicle()
}