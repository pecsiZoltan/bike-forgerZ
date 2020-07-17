package hu.progmasters.frame;

import hu.progmasters.order.Order;

public class Frame {

    private static int serialnumberCounter = 1;

    private FrameType frameType;
    private FrameState frameState;
    private Order order;
    private int serialNumber;

    public Frame(Order order) {
        this.frameType = order.getFrameType();
        this.frameState = FrameState.UNCUT;
        this.order = order;
        this.serialNumber = serialnumberCounter++;
    }

    public boolean isLast() {
        return this.serialNumber == order.getEndSerialNumber();
    }
}
