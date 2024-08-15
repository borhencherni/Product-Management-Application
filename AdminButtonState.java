package application;

public class AdminButtonState {
	 private static boolean adminButtonClicked = false;

	    public static boolean isAdminButtonClicked() {
	        return adminButtonClicked;
	    }

	    public static void setAdminButtonClicked(boolean clicked) {
	        adminButtonClicked = clicked;
	    }

}
