# Vehicle Queue System Simulation
## Table of content
* [General info](#general-info)
* [About structure](#about-structue)
* [Simulations](#simulations)
* [Example](#example)
* [TODO](#TODO)

## General info
This project represents simple vehicle queue simulation with examplary management and it is created only for learning purposes.
	
## About structure - most important classes and interfaces
The `QueueSystem` abstract class in 'SysSim' package contains 4 methods that can be use to make a simulation(It is extedned by BorderCrossingSystem class):
* `status()` - should display and return basic system info in String object,
* `step()` - creates step of simulation in time unit, in my implementation it runs controller to manage queue and dispatch ready trucks; Also it updates vehicle map,
* `arrive(Vehicle vehicle)` - it should simulate arrival of new vehicle,
* `vehicleWaitingTime(Integer vehicleId)` - returns truck's timeleft,

The fields of `QueueSystem`:
* `entryGate` - this object represents common entry gate, 
* `elevatorsHashMap` - contains vehicle objects due to have fast vehicle info access(we can create a method to check by id if some vehicle still exists),
* `queues` - contains vehicle queues with exit gates,
* `Controller` - this is a brain of queues management; dispatch ready trucks and runs selected queue management strategy

The `SystemLogger` class give Us a posibility to make logs(basic infos, errors) and save them to file

The `QueueStrategy` interface contains only one method which should manage queues in specified by Us way; it is used in Controller

The `VehicleQueues` class contains all objects implementing VehicleQueue interface and provides simple methods to manage them

The `VehicleQueue` interface provides methods to manage vehicle objects in queue 

## About structure - less important classes and interfaces

The `Gate` class is a simple representation of gates in queues

The `Vehicle` abstract class represents vehicle object with basic methods

The enum `Status` represents vehicle status in this simulation

## Simulations
To run this project, just compile and build; Simulation playground is placed in Simulation class in start() method - you can use all derived methods from QueueSystem

### Example
* borderCrossingSystem.systemStatus();  - print some info about system
* borderCrossingSystem.arrive(truck);   - add new truck(track status is ARRIVING) to entry gate
* borderCrossingSystem.systemStatus();  - print some info (the vehicle shoud be still "invisible" for the system)
* borderCrossingSystem.step();          - make one unit time step (takes arriving vehicle from entry gate,
                                        moves vehicle in queue, puts first vehicle to empty exit gate);
                                        our truck is now in queue on postition 1 with setted "WAITING" status
* borderCrossingSystem.arrive(truck1);  - now entry gate is empty, we can add another truck
* borderCrossingSystem.systemStatus();  - lets print some info
* borderCrossingSystem.step();          - the first truck is now in exit gate with "CHECKING" status, second one should be in queue
* borderCrossingSystem.step();          - the first one leaves exit gates with "LEAVING" status, second one should be in exit gate

## TODO
- Some unit tests,
- more strategies,
- think about class structures
