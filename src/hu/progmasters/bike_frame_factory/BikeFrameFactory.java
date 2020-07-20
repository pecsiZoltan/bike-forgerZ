package hu.progmasters.bike_frame_factory;

import hu.progmasters.constant_values.ConstantValues;
import hu.progmasters.frame.Frame;
import hu.progmasters.order.Order;

import java.time.LocalDateTime;
import java.util.*;

public class BikeFrameFactory {

    private static long minuteCounter = 0;

    private static LocalDateTime timer = ConstantValues.productionStartDate;

    private final List<Machine> machines = new ArrayList<>();

    private final Queue<Frame> toCut = new LinkedList<>();
    private final Queue<Frame> toBend = new LinkedList<>();
    private final Queue<Frame> toWeld = new LinkedList<>();
    private final Queue<Frame> toTest = new LinkedList<>();
    private final Queue<Frame> toPaint = new LinkedList<>();
    private final Queue<Frame> toPackage = new LinkedList<>();
    private final Queue<Frame> finished = new LinkedList<>();

    public BikeFrameFactory(int[] numberOfMachinesPerType) {
        for (int i = 0; i < numberOfMachinesPerType.length; i++) {
            for (int j = 0; j < numberOfMachinesPerType[i]; j++) {
                switch (i) {
                    case 0:
                        machines.add(new Machine(MachineType.CUTTER, toCut, toBend));
                        break;
                    case 1:
                        machines.add(new Machine(MachineType.BENDER, toBend, toWeld));
                        break;
                    case 2:
                        machines.add(new Machine(MachineType.WELDER, toWeld, toTest));
                        break;
                    case 3:
                        machines.add(new Machine(MachineType.TESTER, toTest, toPaint));
                        break;
                    case 4:
                        machines.add(new Machine(MachineType.PAINTER, toPaint, toPackage));
                        break;
                    case 5:
                        machines.add(new Machine(MachineType.PACKAGER, toPackage, finished));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void produce(List<Order> orders) {
        createAndSerializeFrames(orders);


    }

    void createAndSerializeFrames(List<Order> orders) {
        for (Order order : orders) {
            for (int i = 0; i < order.getQuantity(); i++) {
                toCut.add(new Frame(order));
            }
        }
    }

    public Map<MachineType, Double> getMachinesPerStep() {
        Map<MachineType, Double> machinesPerStep = new HashMap<>();
        initializeMap(machinesPerStep);
        for (Machine machine : machines) {
            switch (machine.getMachineType()) {
                case CUTTER:
                    machinesPerStep.replace(MachineType.CUTTER, machinesPerStep.get(MachineType.CUTTER) + 1);
                    break;
                case BENDER:
                    machinesPerStep.replace(MachineType.BENDER, machinesPerStep.get(MachineType.BENDER) + 1);
                    break;
                case WELDER:
                    machinesPerStep.replace(MachineType.WELDER, machinesPerStep.get(MachineType.WELDER) + 1);
                    break;
                case TESTER:
                    machinesPerStep.replace(MachineType.TESTER, machinesPerStep.get(MachineType.TESTER) + 1);
                    break;
                case PAINTER:
                    machinesPerStep.replace(MachineType.PAINTER, machinesPerStep.get(MachineType.PAINTER) + 1);
                    break;
                case PACKAGER:
                    machinesPerStep.replace(MachineType.PACKAGER, machinesPerStep.get(MachineType.PACKAGER) + 1);
                    break;
                default:
                    break;
            }
        }
        return machinesPerStep;
    }

    private void initializeMap(Map<MachineType, Double> machinesPerStep) {
        machinesPerStep.put(MachineType.CUTTER, 0.0);
        machinesPerStep.put(MachineType.BENDER, 0.0);
        machinesPerStep.put(MachineType.WELDER, 0.0);
        machinesPerStep.put(MachineType.TESTER, 0.0);
        machinesPerStep.put(MachineType.PAINTER, 0.0);
        machinesPerStep.put(MachineType.PACKAGER, 0.0);
    }

    public int getWorkHours() {
        return ConstantValues.AFTERNOON_SHIFT_ENDS - ConstantValues.MORNING_SHIFT_BEGINS;
    }

    public int getStartHour() {
        return ConstantValues.MORNING_SHIFT_BEGINS;
    }

    public int getFinishHour() {
        return ConstantValues.AFTERNOON_SHIFT_ENDS;
    }
}
