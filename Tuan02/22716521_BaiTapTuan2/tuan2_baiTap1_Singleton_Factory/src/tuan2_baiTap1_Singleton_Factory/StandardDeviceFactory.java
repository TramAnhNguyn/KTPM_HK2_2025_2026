package tuan2_baiTap1_SIngleton_Factory;

public class StandardDeviceFactory implements DeviceFactory {
	@Override
    public SmartLight createLight() { return new StandardLight(); }
    @Override
    public SmartFan createFan() { return new StandardFan(); }
}

class StandardLight implements SmartLight {
    public void turnOn() { 
        System.out.println("  [Standard] Đèn dây tóc: Bật sáng (vàng)."); 
    }
}

class StandardFan implements SmartFan {
    public void start() { 
        System.out.println("  [Standard] Quạt cơ: Quay vù vù (ồn)."); 
    }
}


