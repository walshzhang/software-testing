package iteach.softwaretesting.tools.mockito;

public interface Car {
    boolean needsFuel();    // 是否需要加油
    double getEngineTemperature();     // 获取发动机温度
    void driveTo(String destination);  // 驶往某地
}
