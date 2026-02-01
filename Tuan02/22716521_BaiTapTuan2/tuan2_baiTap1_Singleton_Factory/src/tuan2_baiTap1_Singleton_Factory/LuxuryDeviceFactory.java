package tuan2_baiTap1_SIngleton_Factory;

public class LuxuryDeviceFactory implements DeviceFactory {
	@Override
    public SmartLight createLight() { return new LuxuryLight(); }
    @Override
    public SmartFan createFan() { return new LuxuryFan(); }
}

class LuxuryLight implements SmartLight {
    public void turnOn() { 
        System.out.println("  [Luxury] Đèn Smart LED: Bật sáng (16 triệu màu)."); 
    }
}

class LuxuryFan implements SmartFan {
    public void start() { 
        System.out.println("  [Luxury] Quạt không cánh: Chạy êm ru (Silent mode)."); 
    }
}
