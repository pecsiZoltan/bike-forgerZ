package hu.progmasters.frame;

public enum FrameType {

    GYB(5, 10, 8, 5, 12, 10),
    FB(8, 16, 12, 5, 20, 15),
    SB(6, 15, 10, 5, 15, 12);

    private final int cutTime;
    private final int bendTime;
    private final int weldTime;
    private final int testTime;
    private final int paintTime;
    private final int packTime;

    FrameType(int cutTime, int bendTime, int weldTime, int testTime, int paintTime, int packTime) {
        this.cutTime = cutTime;
        this.bendTime = bendTime;
        this.weldTime = weldTime;
        this.testTime = testTime;
        this.paintTime = paintTime;
        this.packTime = packTime;
    }

    public int getCutTime() {
        return cutTime;
    }

    public int getBendTime() {
        return bendTime;
    }

    public int getWeldTime() {
        return weldTime;
    }

    public int getTestTime() {
        return testTime;
    }

    public int getPaintTime() {
        return paintTime;
    }

    public int getPackTime() {
        return packTime;
    }
}
