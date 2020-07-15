package hu.progmasters.order;


import hu.progmasters.frame.FrameType;

import java.time.LocalDateTime;

public class Order {
    private String orderId;
    private FrameType frameType;
    private int quantity;
    private LocalDateTime deadline;
    private int profitPerPiece;
    private int penaltyPerDay;

    public Order(String orderId, FrameType frameType, int quantity, LocalDateTime deadline, int profitPerPiece, int penaltyPerDay) {
        this.orderId = orderId;
        this.frameType = frameType;
        this.quantity = quantity;
        this.deadline = deadline;
        this.profitPerPiece = profitPerPiece;
        this.penaltyPerDay = penaltyPerDay;
    }


}
