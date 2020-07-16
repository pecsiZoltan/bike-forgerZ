package hu.progmasters.bike_frame_factory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

class BikeFrameFactoryTest {

    @Test
    public void testGetMachinesPerStep() {
        Map<MachineType, Double> machines = BikeFrameFactory.getInstance().getMachinesPerStep();
        Assertions.assertEquals(6, machines.get(MachineType.CUTTER));
    }
}
