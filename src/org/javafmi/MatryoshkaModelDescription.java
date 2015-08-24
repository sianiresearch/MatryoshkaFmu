package org.javafmi;

import org.javafmi.exporter.framework.modeldescription.*;

public class MatryoshkaModelDescription {
    public static final String MODEL_NAME = "Matryoshka";
    public static final String SEDAN_SPEED = "sedan.speed";
    public static final String COUPE_SPEED = "coupe.speed";
    public static final String SEDAN_DISTANCE = "sedan.distance";
    public static final String COUPE_DISTANCE = "coupe.distance";

    public static ModelDescription build() {
        return ModelDescription
                .from(new ModelDescriptionSpec(new ModelDescriptionAttributes(MODEL_NAME))
                        .withVariablesSpec(configureVariables(new ModelVariablesSpec())));
    }

    private static ModelVariablesSpec configureVariables(ModelVariablesSpec variables) {
        variables.add(new RealVariableSpec(new ScalarVariableSpec(SEDAN_SPEED)))
                .add(new RealVariableSpec(new ScalarVariableSpec(COUPE_SPEED)))
                .add(new RealVariableSpec(new ScalarVariableSpec(SEDAN_DISTANCE)))
                .add(new RealVariableSpec(new ScalarVariableSpec(COUPE_DISTANCE)));
        return variables;
    }

}
