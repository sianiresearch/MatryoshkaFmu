package org.javafmi;

import org.javafmi.exporter.framework.FmiAccess;
import org.javafmi.exporter.framework.Variable;
import org.javafmi.wrapper.generic.Simulation;

public class DoubleVariable implements Variable<Double> {
    private final String name;
    private final Simulation simulation;

    public DoubleVariable(String name, Simulation simulation) {
        this.name = name;
        this.simulation = simulation;
    }

    @Override
    public Double getValue() {
        return simulation.read(name).asDouble();
    }

    @Override
    public FmiAccess.Status setValue(Double aDouble) {
        return FmiAccess.Status.decode(simulation.write(name).with(aDouble).toString());
    }
}
