package hu.progmasters.bike_frame_factory;

import hu.progmasters.frame.Frame;
import hu.progmasters.order.Order;

import java.util.Queue;

public class Machine {
    private final MachineType machineType;
    private Frame currentWorkpiece;
    private int currentWorkpieceTimeLeft;
    private Queue<Frame> fromQueue;
    private Queue<Frame> toQueue;
    private Order currentOrder;

    public Machine(MachineType machineType, Queue<Frame> fromQueue, Queue<Frame> toQueue) {
        this.machineType = machineType;
        this.fromQueue = fromQueue;
        this.toQueue = toQueue;
    }

    public Order work() {

        Order finishedOrder = null;
        // TODO also return new order started... maybe a boolean field in Order: isFinished
        // TODO to notify the BikeFrameFactory of start and end times...
        // TODO we need to track the individual assignments of machines by day, not just overall...
        if (currentWorkpiece == null) {
            getNewWorkPiece();
            if (currentOrder == null) {
                currentOrder = currentWorkpiece.getOrder();
            }
        }

        currentWorkpieceTimeLeft--;

        if (currentWorkpieceTimeLeft == 0) {
            if (currentWorkpiece.isLast()) {
                finishedOrder = currentOrder;
                currentOrder = null;
            }
            passWorkpieceOn();
        }
        return finishedOrder;
    }

    void getNewWorkPiece() {
        currentWorkpiece = fromQueue.poll();
        switch (machineType) {
            case CUTTER:
                currentWorkpieceTimeLeft = currentWorkpiece.getFrameType().getCutTime();
                break;
            case BENDER:
                currentWorkpieceTimeLeft = currentWorkpiece.getFrameType().getBendTime();
                break;
            case WELDER:
                currentWorkpieceTimeLeft = currentWorkpiece.getFrameType().getWeldTime();
                break;
            case TESTER:
                currentWorkpieceTimeLeft = currentWorkpiece.getFrameType().getTestTime();
                break;
            case PAINTER:
                currentWorkpieceTimeLeft = currentWorkpiece.getFrameType().getPaintTime();
                break;
            case PACKAGER:
                currentWorkpieceTimeLeft = currentWorkpiece.getFrameType().getPackTime();
                break;
            default:
                break;
        }
    }

    void passWorkpieceOn() {
        currentWorkpiece.progressState();
        toQueue.add(currentWorkpiece);
        currentWorkpiece = null;
    }

    public MachineType getMachineType() {
        return machineType;
    }
}
