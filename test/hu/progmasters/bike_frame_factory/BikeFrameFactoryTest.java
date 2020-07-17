package hu.progmasters.bike_frame_factory;

import hu.progmasters.constant_values.ConstantValues;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

class BikeFrameFactoryTest {

    @Test
    public void testGetMachinesPerStep() {
        BikeFrameFactory bikeFrameFactory = new BikeFrameFactory(new int[]{
                ConstantValues.NUMBER_OF_CUTTERS,
                ConstantValues.NUMBER_OF_BENDERS,
                ConstantValues.NUMBER_OF_WELDERS,
                ConstantValues.NUMBER_OF_TESTERS,
                ConstantValues.NUMBER_OF_PAINTERS,
                ConstantValues.NUMBER_OF_PACKERS});
        Map<MachineType, Double> machines = bikeFrameFactory.getMachinesPerStep();
        Assertions.assertEquals(6, machines.get(MachineType.CUTTER));
    }
}
