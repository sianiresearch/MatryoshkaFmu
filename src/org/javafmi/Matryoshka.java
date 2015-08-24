package org.javafmi;

import org.javafmi.exporter.framework.FmiSimulation;
import org.javafmi.exporter.framework.modeldescription.ModelDescription;
import org.javafmi.wrapper.generic.Simulation;

import static org.javafmi.MatryoshkaModelDescription.*;

public class Matryoshka extends FmiSimulation {

    private static final String CAR_AS_SIMULATION_RESOURCE = "Car_as_simulation_resource.fmu";
    private static final String CAR_AS_FMU_RESOURCE = "Car_as_fmu_resource.fmu";
    private static final String CAR_AS_FMU_RESOURCE_2 = "Car_as_fmu_resource2.fmu";
    private static final String CAR_SPEED = "car.speed";
    private static final String CAR_DISTANCE = "car.distance";
    public static final double INITIAL_SPEED = 20.;

    private Simulation sedan;
    private Simulation coupe;

    @Override
    public Status init() {
        //loadResource(CAR_AS_SIMULATION_RESOURCE);
        //sedan = new Simulation(CAR_AS_SIMULATION_RESOURCE);
        coupe = new Simulation(CAR_AS_FMU_RESOURCE);
        sedan = new Simulation(CAR_AS_FMU_RESOURCE_2);
        sedan.init(0.);
        coupe.init(0.);
        sedan.write(CAR_SPEED).with(INITIAL_SPEED);
        coupe.write(CAR_SPEED).with(INITIAL_SPEED);
        registerVariables();
        return Status.OK;
    }

    private void registerVariables() {
        register(SEDAN_SPEED, new DoubleVariable(CAR_SPEED, sedan));
        register(COUPE_SPEED, new DoubleVariable(CAR_SPEED, coupe));
        register(SEDAN_DISTANCE, new DoubleVariable(CAR_DISTANCE, sedan));
        register(COUPE_DISTANCE, new DoubleVariable(CAR_DISTANCE, coupe));
    }

    @Override
    public Status doStep(double stepSize) {
        sedan.doStep(stepSize);
        coupe.doStep(stepSize);
        return Status.OK;
    }

    @Override
    public Status reset() {
        return Status.OK;
    }

    @Override
    public ModelDescription createModelDescription() {
        return MatryoshkaModelDescription.build();
    }

    @Override
    public Status terminate() {
        sedan.terminate();
        coupe.terminate();
        return Status.OK;
    }
}
