package application;

public class CustomerButtonState {
	private static boolean customerButtonClicked = false;

    public static boolean isCustomerButtonClicked() {
        return customerButtonClicked;
    }

    public static void setCustomerButtonClicked(boolean clicked) {
        customerButtonClicked = clicked;
    }


}
