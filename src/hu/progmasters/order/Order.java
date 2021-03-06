package hu.progmasters.order;


import hu.progmasters.frame.FrameType;

import java.time.LocalDateTime;

public class Order implements Comparable<Order> {
    private final String orderId;
    private final FrameType frameType;
    private final int quantity;
    private final LocalDateTime deadline;
    private final int profitPerPiece;
    private final int penaltyPerDay;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
    private int startSerialNumber;
    private int endSerialNumber;

    private long totalProductionTime;
    private int productionTimeBeforeBottleneck;


    public Order(String orderId, FrameType frameType, int quantity, LocalDateTime deadline, int profitPerPiece, int penaltyPerDay) {
        this.orderId = orderId;
        this.frameType = frameType;
        this.quantity = quantity;
        this.deadline = deadline;
        this.profitPerPiece = profitPerPiece;
        this.penaltyPerDay = penaltyPerDay;
    }

    public String getOrderId() {
        return orderId;
    }

    public FrameType getFrameType() {
        return frameType;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public int getProfitPerPiece() {
        return profitPerPiece;
    }

    public int getPenaltyPerDay() {
        return penaltyPerDay;
    }

    public long getTotalProductionTime() {
        return totalProductionTime;
    }

    public void setTotalProductionTime(long totalProductionTime) {
        this.totalProductionTime = totalProductionTime;
    }

    public int getProductionTimeBeforeBottleneck() {
        return productionTimeBeforeBottleneck;
    }

    public void setProductionTimeBeforeBottleneck(int productionTimeBeforeBottleneck) {
        this.productionTimeBeforeBottleneck = productionTimeBeforeBottleneck;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public int getEndSerialNumber() {
        return endSerialNumber;
    }

    @Override
    public int compareTo(Order o) {
        return this.getDeadline().compareTo(o.getDeadline());
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }

    public void setStartSerialNumber(int startSerialNumber) {
        this.startSerialNumber = startSerialNumber;
    }

    public void setEndSerialNumber(int endSerialNumber) {
        this.endSerialNumber = endSerialNumber;
    }
}
