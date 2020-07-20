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

    public static int getSerialnumberCounter() {
        return serialnumberCounter;
    }

    public FrameType getFrameType() {
        return frameType;
    }

    public FrameState getFrameState() {
        return frameState;
    }

    public Order getOrder() {
        return order;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public boolean isLast() {
        return this.serialNumber == order.getEndSerialNumber();
    }

    public void progressState() {
        switch (frameState) {
            case UNCUT:
                frameState = FrameState.CUT;
                break;
            case CUT:
                frameState = FrameState.BENT;
                break;
            case BENT:
                frameState = FrameState.WELDED;
                break;
            case WELDED:
                frameState = FrameState.TESTED;
                break;
            case TESTED:
                frameState = FrameState.PAINTED;
                break;
            case PAINTED:
                frameState = FrameState.PACKAGED;
                break;
            case PACKAGED:
            default:
                break;
        }
    }
}
