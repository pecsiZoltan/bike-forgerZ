package hu.progmasters;

import hu.progmasters.bike_frame_factory.BikeFrameFactory;
import hu.progmasters.bike_frame_factory.MachineType;
import hu.progmasters.constant_values.ConstantValues;
import hu.progmasters.frame.FrameType;
import hu.progmasters.order.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Map;

class RoDSStarTest {

    private RoDSStar roDSStar;
    private BikeFrameFactory bikeFrameFactory;

    @BeforeEach
    public void init() {
        roDSStar = new RoDSStar();
        bikeFrameFactory = new BikeFrameFactory(new int[]{
                ConstantValues.NUMBER_OF_CUTTERS,
                ConstantValues.NUMBER_OF_BENDERS,
                ConstantValues.NUMBER_OF_WELDERS,
                ConstantValues.NUMBER_OF_TESTERS,
                ConstantValues.NUMBER_OF_PAINTERS,
                ConstantValues.NUMBER_OF_PACKERS});
    }

    @Test
    public void testCalculateAvgStepTimes() {
        Map<MachineType, Double> machines = bikeFrameFactory.getMachinesPerStep();
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
    public void testCalculateTotalProductionTimeForOrderInMinutes_6000SB() {
        Order order = new Order("ljwhfg",
                FrameType.SB,
                6000,
                LocalDateTime.now().plusDays(5),
                1000,
                10000);
        roDSStar.calculateTotalProductionTimeForOrderInMinutes(order);
        Assertions.assertEquals(45048, order.getTotalProductionTime());
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
                LocalDateTime.of(2020, 7, 31, 22, 0),
                roDSStar.calculateDateFromMinutesPassed(
                        LocalDateTime.of(2020, 7, 20, 6, 0),
                        9600)
        );
    }

    @Test
    public void testCalculateDateFromMinutesPassed_startAtTwo() {
        Assertions.assertEquals(
                LocalDateTime.of(2020, 8, 3, 14, 0),
                roDSStar.calculateDateFromMinutesPassed(
                        LocalDateTime.of(2020, 7, 20, 14, 0),
                        9600)
        );
    }

    @Test
    public void testCalculateProfitForCurrentOrderList() {
        roDSStar.addOrder("MEGR001",
                FrameType.GYB,
                1000,
                LocalDateTime.of(2020, 7, 21, 13, 0),
                1500,
                20000);
        roDSStar.addOrder("MEGR002",
                FrameType.FB,
                1200,
                LocalDateTime.of(2020, 7, 22, 8, 0),
                3500,
                50000);
        roDSStar.addOrder("MEGR003",
                FrameType.SB,
                250,
                LocalDateTime.of(2020, 7, 23, 14, 0),
                2400,
                30000);
        roDSStar.addOrder("MEGR004",
                FrameType.FB,
                1000,
                LocalDateTime.of(2020, 7, 30, 8, 0),
                1200,
                100000);
        roDSStar.addOrder("MEGR005",
                FrameType.SB,
                3000,
                LocalDateTime.of(2020, 7, 25, 16, 0),
                2000,
                10000);
        roDSStar.calculateProductionTimesInMinutes();
        Assertions.assertEquals(8500000, roDSStar.calculateProfitForCurrentOrderList());
    }

    @Test
    public void testSortOrdersForMaximumProfit() {
        roDSStar.addOrder("MEGR001",
                FrameType.GYB,
                1000,
                LocalDateTime.of(2020, 7, 21, 13, 0),
                1500,
                20000);
        roDSStar.addOrder("MEGR002",
                FrameType.FB,
                1200,
                LocalDateTime.of(2020, 7, 22, 8, 0),
                3500,
                50000);
        roDSStar.addOrder("MEGR003",
                FrameType.SB,
                250,
                LocalDateTime.of(2020, 7, 23, 14, 0),
                2400,
                30000);
        roDSStar.addOrder("MEGR004",
                FrameType.FB,
                1000,
                LocalDateTime.of(2020, 7, 30, 8, 0),
                1200,
                100000);
        roDSStar.addOrder("MEGR005",
                FrameType.SB,
                3000,
                LocalDateTime.of(2020, 7, 25, 16, 0),
                2000,
                10000);
        roDSStar.sortOrdersForMaximumProfit();
        Assertions.assertEquals(10360000, roDSStar.calculateProfitForCurrentOrderList());
    }


}
