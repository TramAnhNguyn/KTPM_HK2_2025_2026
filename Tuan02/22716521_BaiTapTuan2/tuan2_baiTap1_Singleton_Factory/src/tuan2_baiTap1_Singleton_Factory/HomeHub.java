package tuan2_baiTap1_SIngleton_Factory;

import java.util.ArrayList;
import java.util.List;

public class HomeHub {
private static volatile HomeHub instance;
    
    private HomeHub() {
        System.out.println(">> [System] Đang khởi động Smart Home Hub...");
    }
    
    public static HomeHub getInstance() {
        if (instance == null) {
            synchronized (HomeHub.class) {
                if (instance == null) {
                    instance = new HomeHub();
                }
            }
        }
        return instance;
    }
    
    private List<SmartLight> lights = new ArrayList<>();
    private List<SmartFan> fans = new ArrayList<>();

    //Lap dat cac thiet bi
    public void installDevices(DeviceFactory factory) {
        lights.add(factory.createLight());
        fans.add(factory.createFan());
    }

    //Bat chay cac thiet bi da lap dat
    public void turnOnAll() {
        System.out.println("\n--- [HUB] Kích hoạt tất cả thiết bị ---");
        for (SmartLight light : lights) light.turnOn();
        for (SmartFan fan : fans) fan.start();
    }
	

}
