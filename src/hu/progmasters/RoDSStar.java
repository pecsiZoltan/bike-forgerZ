package hu.progmasters;

import hu.progmasters.bikeFrameFactory.BikeFrameFactory;
import hu.progmasters.bikeFrameFactory.MachineType;
import hu.progmasters.order.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RoDSStar {

    private final BikeFrameFactory bikeFrameFactory = BikeFrameFactory.getInstance();
    private final List<Order> orders = new ArrayList<>();

    long calculateTotalWorkTimeForOrderInMinutes(Order order) {
        long totalTime = 0;
        MachineType bottleneckStep = findBottleneckStep(order);
        int bottleneckStepTime = findBottleneckStepTimeByMachineTypeAndOrder(bottleneckStep, order);
        double numberOfMachinesInBottleneck = bikeFrameFactory.getMachinesPerStep().get(bottleneckStep);
        totalTime += calculateTimeBeforeBottleneck(bottleneckStep, order);
        totalTime +=
                (long) Math.ceil((double) (bottleneckStepTime * order.getQuantity())
                        / numberOfMachinesInBottleneck);
        totalTime += calculateTimeAfterBottleneck(bottleneckStep, order);
        return totalTime;
    }

    MachineType findBottleneckStep(Order order) {
        MachineType bottleneckStep = MachineType.CUTTER;
        Map<MachineType, Double> machinesPerStep = bikeFrameFactory.getMachinesPerStep();
        calculateAvgStepTimes(order, machinesPerStep);
        double avgTimeOfSlowestStep = machinesPerStep.get(MachineType.CUTTER);
        for (Map.Entry<MachineType, Double> machine : machinesPerStep.entrySet()) {
            if (machine.getValue() > avgTimeOfSlowestStep) {
                bottleneckStep = machine.getKey();
                avgTimeOfSlowestStep = machine.getValue();
            }
        }
        return bottleneckStep;
    }

    int findBottleneckStepTimeByMachineTypeAndOrder(MachineType machineType, Order order) {
        switch (machineType) {
            case BENDER:
                return order.getFrameType().getBendTime();
            case WELDER:
                return order.getFrameType().getWeldTime();
            case TESTER:
                return order.getFrameType().getTestTime();
            case PAINTER:
                return order.getFrameType().getPaintTime();
            case PACKAGER:
                return order.getFrameType().getPackTime();
            default:
                return order.getFrameType().getCutTime();
        }
    }

    private int calculateTimeBeforeBottleneck(MachineType bottleneckStep, Order order) {
        switch (bottleneckStep) {
            case BENDER:
                return order.getFrameType().getCutTime();
            case WELDER:
                return order.getFrameType().getCutTime()
                        + order.getFrameType().getBendTime();
            case TESTER:
                return order.getFrameType().getCutTime()
                        + order.getFrameType().getBendTime()
                        + order.getFrameType().getWeldTime();
            case PAINTER:
                return order.getFrameType().getCutTime()
                        + order.getFrameType().getBendTime()
                        + order.getFrameType().getWeldTime()
                        + order.getFrameType().getTestTime();
            case PACKAGER:
                return order.getFrameType().getCutTime()
                        + order.getFrameType().getBendTime()
                        + order.getFrameType().getWeldTime()
                        + order.getFrameType().getTestTime()
                        + order.getFrameType().getPaintTime();
            default:
                return 0;
        }
    }

    private int calculateTimeAfterBottleneck(MachineType bottleneckStep, Order order) {
        switch (bottleneckStep) {
            case CUTTER:
                return order.getFrameType().getBendTime()
                        + order.getFrameType().getWeldTime()
                        + order.getFrameType().getTestTime()
                        + order.getFrameType().getPaintTime()
                        + order.getFrameType().getPackTime();
            case BENDER:
                return order.getFrameType().getWeldTime()
                        + order.getFrameType().getTestTime()
                        + order.getFrameType().getPaintTime()
                        + order.getFrameType().getPackTime();
            case WELDER:
                return order.getFrameType().getTestTime()
                        + order.getFrameType().getPaintTime()
                        + order.getFrameType().getPackTime();
            case TESTER:
                return order.getFrameType().getPaintTime()
                        + order.getFrameType().getPackTime();
            case PAINTER:
                return order.getFrameType().getPackTime();
            default:
                return 0;
        }
    }

    private void calculateAvgStepTimes(Order order, Map<MachineType, Double> machinesPerStep) {
        machinesPerStep.replace(MachineType.CUTTER, order.getFrameType().getCutTime() / machinesPerStep.get(MachineType.CUTTER));
        machinesPerStep.replace(MachineType.BENDER, order.getFrameType().getBendTime() / machinesPerStep.get(MachineType.BENDER));
        machinesPerStep.replace(MachineType.WELDER, order.getFrameType().getWeldTime() / machinesPerStep.get(MachineType.WELDER));
        machinesPerStep.replace(MachineType.TESTER, order.getFrameType().getTestTime() / machinesPerStep.get(MachineType.TESTER));
        machinesPerStep.replace(MachineType.PAINTER, order.getFrameType().getPaintTime() / machinesPerStep.get(MachineType.PAINTER));
        machinesPerStep.replace(MachineType.PACKAGER, order.getFrameType().getPackTime() / machinesPerStep.get(MachineType.PACKAGER));
    }
}
