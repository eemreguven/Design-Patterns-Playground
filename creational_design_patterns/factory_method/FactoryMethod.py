from abc import ABC, abstractmethod

# product interface
class Vehicle(ABC):
    @abstractmethod
    def design(self):
        pass

    @abstractmethod
    def manufacture(self):
        pass

# create concrete products
class Car(Vehicle):
    def design(self):
        print("Designing a Car")

    def manufacture(self):
        print("Manufacturing a Car")

class Bike(Vehicle):
    def design(self):
        print("Designing a Bike")

    def manufacture(self):
        print("Manufacturing a Bike")

# creator abstract class
class VehicleFactory(ABC):
    @abstractmethod
    def create_vehicle(self):
        pass

    def build_vehicle(self):
        vehicle = self.create_vehicle()
        vehicle.design()
        vehicle.manufacture()

# concrete creators
class CarFactory(VehicleFactory):
    def create_vehicle(self):
        return Car()

class BikeFactory(VehicleFactory):
    def create_vehicle(self):
        return Bike()

if __name__ == "__main__":
    car_factory = CarFactory()
    car_factory.build_vehicle()

    bike_factory = BikeFactory()
    bike_factory.build_vehicle()
