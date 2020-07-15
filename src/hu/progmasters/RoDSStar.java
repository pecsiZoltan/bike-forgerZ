package hu.progmasters;

import hu.progmasters.bikeFrameFactory.BikeFrameFactory;
import hu.progmasters.bikeFrameFactory.MachineType;
import hu.progmasters.order.Order;

import java.util.List;
import java.util.Map;

public class RoDSStar {
    private BikeFrameFactory bikeFrameFactory = BikeFrameFactory.getInstance();
    private List<Order> orders;

    int totalWorkTimeForOrderInMinutes(Order order) {
        return 0;
    }


    MachineType findBottleneckStep(Order order) {
        MachineType bottleneckStep = MachineType.CUTTER;
        Map<MachineType, Double> machinesPerStep = bikeFrameFactory.getMachinesPerStep();
        machinesPerStep.replace(MachineType.CUTTER, order.getFrameType().getCutTime() / machinesPerStep.get(MachineType.CUTTER));
        machinesPerStep.replace(MachineType.BENDER, order.getFrameType().getBendTime() / machinesPerStep.get(MachineType.BENDER));
        machinesPerStep.replace(MachineType.WELDER, order.getFrameType().getWeldTime() / machinesPerStep.get(MachineType.WELDER));
        machinesPerStep.replace(MachineType.TESTER, order.getFrameType().getTestTime() / machinesPerStep.get(MachineType.TESTER));
        machinesPerStep.replace(MachineType.PAINTER, order.getFrameType().getPaintTime() / machinesPerStep.get(MachineType.PAINTER));
        machinesPerStep.replace(MachineType.PACKAGER, order.getFrameType().getPackTime() / machinesPerStep.get(MachineType.PACKAGER));
        double avgTimeOfSlowestStep = machinesPerStep.get(MachineType.CUTTER);
        for (Map.Entry<MachineType, Double> machine : machinesPerStep.entrySet()) {
            if (machine.getValue() > avgTimeOfSlowestStep) {
                bottleneckStep = machine.getKey();
                avgTimeOfSlowestStep = machine.getValue();
            }
        }

        return bottleneckStep;
    }
}
