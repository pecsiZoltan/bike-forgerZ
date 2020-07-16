package hu.progmasters;

import hu.progmasters.bike_frame_factory.BikeFrameFactory;
import hu.progmasters.bike_frame_factory.MachineType;
import hu.progmasters.frame.FrameType;
import hu.progmasters.order.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Map;

class RoDSStarTest {

    private RoDSStar roDSStar;

    @BeforeEach
    public void init() {
        roDSStar = new RoDSStar();
    }

    @Test
    public void testCalculateAvgStepTimes() {
        Map<MachineType, Double> machines = BikeFrameFactory.getInstance().getMachinesPerStep();
        roDSStar.calculateAvgStepTimes(
                new Order("ljwhfg",
                        FrameType.GYB,
                        10,
                        LocalDateTime.now().plusDays(5),
                        1000,
                        10000),
                machines
        );
        Assertions.assertEquals(5, machines.get(MachineType.BENDER));
    }

    @Test
    public void testFindBottleneckStep() {
        Assertions.assertEquals(MachineType.BENDER,
                roDSStar.findBottleneckStep(
                        new Order("ljwhfg",
                                FrameType.FB,
                                10,
                                LocalDateTime.now().plusDays(5),
                                1000,
                                10000)));
    }

    @Test
    public void testCalculateTotalProductionTimeForOrderInMinutes_1000SB() {
        Order order = new Order("ljwhfg",
                FrameType.SB,
                1000,
                LocalDateTime.now().plusDays(5),
                1000,
                10000);
        roDSStar.calculateTotalProductionTimeForOrderInMinutes(order);
        Assertions.assertEquals(7548, order.getTotalProductionTime());
    }

    @Test
    public void testCalculateTotalProductionTimeForOrderInMinutes_1000FB() {
        Order order = new Order("ljwhfg",
                FrameType.FB,
                1000,
                LocalDateTime.now().plusDays(5),
                1000,
                10000);
        roDSStar.calculateTotalProductionTimeForOrderInMinutes(order);
        Assertions.assertEquals(8060, order.getTotalProductionTime());
    }

    @Test
    public void testCalculateDateFromMinutesPassed_startAtSix() {
        Assertions.assertEquals(
                LocalDateTime.of(2020, 07, 31, 22, 0),
                roDSStar.calculateDateFromMinutesPassed(
                        LocalDateTime.of(2020, 07, 20, 6, 0),
                        9600)
        );
    }

    @Test
    public void testCalculateDateFromMinutesPassed_startAtTwo() {
        Assertions.assertEquals(
                LocalDateTime.of(2020, 8, 3, 14, 0),
                roDSStar.calculateDateFromMinutesPassed(
                        LocalDateTime.of(2020, 07, 20, 14, 0),
                        9600)
        );
    }

}
