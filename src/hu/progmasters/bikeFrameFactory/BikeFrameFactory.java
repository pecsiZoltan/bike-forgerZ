package hu.progmasters.bikeFrameFactory;

import java.util.List;

public class BikeFrameFactory {

    private static final BikeFrameFactory instance = new BikeFrameFactory(new int[]{6, 2, 3, 1, 4, 3});
    private List<Macine> machines;

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
}
