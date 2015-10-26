package org.javafmi;

import org.javafmi.exporter.framework.modeldescription.*;

import static org.javafmi.exporter.framework.modeldescription.ScalarVariableSpec.Causality.INPUT;
import static org.javafmi.exporter.framework.modeldescription.ScalarVariableSpec.Causality.OUTPUT;
import static org.javafmi.exporter.framework.modeldescription.ScalarVariableSpec.Variability.DISCRETE;

public class MatryoshkaModelDescription {
    public static final String MODEL_NAME = "Matryoshka";
    public static final String SEDAN_SPEED = "sedan.speed";
    public static final String COUPE_SPEED = "coupe.speed";
    public static final String SEDAN_DISTANCE = "sedan.distance";
    public static final String COUPE_DISTANCE = "coupe.distance";

    public static ModelDescription build() {
        return ModelDescription
                .from(new ModelDescriptionSpec(new ModelDescriptionAttributes(MODEL_NAME))
                        .withVariablesSpec(variables()));
    }

    private static ModelVariablesSpec variables() {
        return new ModelVariablesSpec()
                .add(sedanSpeed())
                .add(coupeSpeed())
                .add(sedanDistance())
                .add(coupeDistance());
    }

    private static RealVariableSpec sedanSpeed() {
        return new RealVariableSpec(new ScalarVariableSpec(SEDAN_SPEED)
                .causality(INPUT)
                .variability(DISCRETE))
                .start(Matryoshka.INITIAL_SPEED);
    }

    private static RealVariableSpec coupeSpeed() {
        return new RealVariableSpec(new ScalarVariableSpec(COUPE_SPEED)
                .causality(INPUT)
                .variability(DISCRETE))
                .start(Matryoshka.INITIAL_SPEED);
    }

    private static RealVariableSpec sedanDistance() {
        return new RealVariableSpec(new ScalarVariableSpec(SEDAN_DISTANCE)
                .causality(OUTPUT)
                .variability(DISCRETE));
    }

    private static RealVariableSpec coupeDistance() {
        return new RealVariableSpec(new ScalarVariableSpec(COUPE_DISTANCE)
                .causality(OUTPUT)
                .variability(DISCRETE));
    }

}
