import org.javafmi.builder.ShellFmuBuilder;
import org.javafmi.modeldescription.ScalarVariable;
import org.javafmi.wrapper.generic.Simulation;
import org.junit.Before;
import org.junit.Test;

import static java.lang.System.out;
import static java.util.stream.IntStream.rangeClosed;
import static org.javafmi.MatryoshkaModelDescription.COUPE_DISTANCE;
import static org.javafmi.MatryoshkaModelDescription.SEDAN_DISTANCE;

public class MatryoshkaTest {

    private Simulation simulation;

    @Before
    public void setUp() {
        String args[] = "Matryoshka.jar -p lin64 win64 -i fmu-res/Car_as_fmu_resource.fmu".split(" ");
        new ShellFmuBuilder(args).build();
    }

    @Test
    public void runMatryoshkaSimulation() {
        long startTime = System.currentTimeMillis();
        simulation = new Simulation("Matryoshka.fmu");
        printVariables(simulation.getModelDescription().getModelVariables());
        simulation.init(0.);
        simulation.write("sedan.speed").with(65.);
        simulation.write("coupe.speed").with(80.);
        rangeClosed(0, 3600).forEach(time -> {
            printDistance(time);
            simulation.doStep(1);
        });
        simulation.terminate();
        System.out.println(System.currentTimeMillis() - startTime);
    }

    private void printVariables(ScalarVariable[] modelVariables) {
        for (ScalarVariable scalarVariable : modelVariables) {
            out.println(scalarVariable.getName() + " has value reference " + scalarVariable.getValueReference());
        }
    }

    private void printDistance(int time) {
        out.printf("%d\t%.4f\t%.4f\n",
                time,
                simulation.read(SEDAN_DISTANCE).asDouble(),
                simulation.read(COUPE_DISTANCE).asDouble());
    }
}
