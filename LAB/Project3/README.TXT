PROJECT TITLE: Project 3
PURPOSE OF PROJECT: Move Shipments from Warehouse to Warehouse using Truck objects
VERSION or DATE: 12/15/24
HOW TO START THIS PROJECT: Build Map object and call runSim()
AUTHOR: Sean McLoughlin

Planned implementation:
    I planned on completing the entire project. This included the ability for trucks to properly move from warehouse to warehouse, properly ordering shipments based on location and build time.
    Warehouses should properly manage incoming and outgoing trucks and Shpiments should be able to supply proper and updated information. Map class should be able to properly control the Truck
    objects and increment simulation by the hour.
    
Actual Implementation/Failed Implementation:
    I was able to fully implement all of these features and the simulation runs as expected.
    
Lessons:
    I learned to effectively employ my own data structures and manage variables with diligent control and ordering. I used a finite state machine model to implement the truck's action method.
    This helped massively in simplifying the truck's ability to control itself and how it should be acting at any given moment by splitting it into 4 main possible action types.
