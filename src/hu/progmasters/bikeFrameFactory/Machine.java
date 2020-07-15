package hu.progmasters.bikeFrameFactory;

public class Machine {
    private MachineType machineType;

    public Machine(MachineType machineType) {
        this.machineType = machineType;
    }

    public MachineType getMachineType() {
        return machineType;
    }
}
