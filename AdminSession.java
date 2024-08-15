package application;

public class AdminSession {
	private static String adminId;
    private static String adminName;

    public static void setAdmin(String id, String name) {
        adminId = id;
        adminName = name;
    }

    public static String getAdminId() {
        return adminId;
    }

    public static String getAdminName() {
        return adminName;
    }

}
