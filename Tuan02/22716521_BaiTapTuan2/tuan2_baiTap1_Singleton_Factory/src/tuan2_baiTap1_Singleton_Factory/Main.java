package tuan2_baiTap1_SIngleton_Factory;

public class Main {
	public static void main(String[] args) {
        // 1. Gọi Singleton
        HomeHub myHub = HomeHub.getInstance();

        // 2. Lắp đặt gói Standard cho phòng ngủ
        System.out.println("\n>> Lắp đặt phòng ngủ (Gói tiết kiệm):");
        myHub.installDevices(new StandardDeviceFactory());

        // 3. Lắp đặt gói Luxury cho phòng khách
        System.out.println("\n>> Lắp đặt phòng khách (Gói cao cấp):");
        myHub.installDevices(new LuxuryDeviceFactory());

        // 4. Test hoạt động
        myHub.turnOnAll();
    }

}
