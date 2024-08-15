package application;

public class currentcustomerUsername {
	private static String currentUser;

    public static String getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(String username) {
        currentUser = username;
    }

}
