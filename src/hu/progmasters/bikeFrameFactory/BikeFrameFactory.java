package hu.progmasters.bikeFrameFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BikeFrameFactory {

    private static final BikeFrameFactory instance = new BikeFrameFactory(new int[]{6, 2, 3, 1, 4, 3});
    private List<Macine> machines = new ArrayList<>();
    private int workHours;

    private BikeFrameFactory(int[] numberOfMachinesPerType) {
        for (int i = 0; i < numberOfMachinesPerType.length; i++) {
            for (int j = 0; j < numberOfMachinesPerType[i]; j++) {
                switch (i) {
                    case 0:
                        machines.add(new Macine(MachineType.CUTTER));
                        break;
                    case 1:
                        machines.add(new Macine(MachineType.BENDER));
                        break;
                    case 2:
                        machines.add(new Macine(MachineType.WELDER));
                        break;
                    case 3:
                        machines.add(new Macine(MachineType.TESTER));
                        break;
                    case 4:
                        machines.add(new Macine(MachineType.PAINTER));
                        break;
                    case 5:
                        machines.add(new Macine(MachineType.PACKAGER));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public static BikeFrameFactory getInstance() {
        return instance;
    }

    public Map<MachineType, Double> getMachinesPerStep() {
        Map<MachineType, Double> machinesPerStep = new HashMap<>();
        machinesPerStep.put(MachineType.CUTTER, 0.0);
        machinesPerStep.put(MachineType.BENDER, 0.0);
        machinesPerStep.put(MachineType.WELDER, 0.0);
        machinesPerStep.put(MachineType.TESTER, 0.0);
        machinesPerStep.put(MachineType.PAINTER, 0.0);
        machinesPerStep.put(MachineType.PACKAGER, 0.0);
        for (Macine machine : machines) {
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

    public int getWorkHours() {
        return workHours;
    }
}
