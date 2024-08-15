# Product-Management-Application
Welcome to the Product Management Application! This project showcases a user-friendly interface for managing and viewing product-related data, including details about customers and administrators and handle authentication securely .

## Overview
This application displays a `TableView` with comprehensive information about products and their associated customers and admins. It allows you to manage various attributes related to products, such as IDs, names, prices, sizes, colors, and quantities, alongside details about the admins and customers managing these products.

## Features
- **Dynamic TableView**: Display and manage product and user data in a tabular format.
- **Column Customization**: Dynamic columns for Admin ID, Admin Name, Product ID, Product Name, Price, Size, Color, and Quantity.
- **Resizable Layout**: The table and window dimensions are adjustable to fit different display requirements.

## Prerequisites
To build and run this project, ensure you have the following:
- **Java JDK 8 or higher**: JavaFX is included with your JDK.

## Getting Started

Installation
*Clone the repository:

bash
Copier le code  
git clone https://github.com/borhencherni/Product-Management-Application.git  
cd your-repo-name

*Install JavaFX:

Follow the instructions on the JavaFX website to download and set up JavaFX.

*Configure Your IDE:

If using IntelliJ IDEA, go to File -> Project Structure -> Modules, and add the JavaFX libraries to your module dependencies.  
If using Eclipse, configure the build path to include JavaFX libraries.

*Build and Run:

Compile and run the application using your IDE or the command line.

bash
Copier le code  
javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -d out src/application/*.java  
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -cp out application.AmAdmin  
Usage

*Build and Run
Run the WelcomePage class to start the application:

bash
Copier le code
src/application/WelcomePage.java
The application will open a window asking for identifying yourself.

## Project Structure

```plaintext
src/
├── application/
│   ├── WelcomePage.java    # Main JavaFX Application Class
│   └── ...
└── ...
```

## Application Components
 **TableView**
 
The TableView is used to display data in a tabular format. It includes columns for:

Admin ID: Identifier for the admin managing the product.  
Admin Name: Name of the admin.  
Product ID: Identifier for the product.  
Product Name: Name of the product.  
Price: Price of the product.  
Size: Size of the product.  
Color: Color of the product.  
Quantity: Quantity of the product in stock.

**Customization**

To customize the table's appearance or data:

Add or Remove Columns: Modify the tableView.getColumns().addAll() method in the CustomerInterface class.  
Adjust Table Size: Configure the width and height of the table by setting tableView.setPrefWidth() and tableView.setPrefHeight() as needed.  

**Data Management**
Data is added manually in the start() method of the CustomerInterface class. You can extend this functionality to load data from external sources or databases.

**Usage**
Viewing Data: The table displays the current data, with the ability to scroll if the content exceeds the visible area.  
Editing Data: To edit or update the data, modify the data entries directly in the code or implement functionality for data input and updates.

## Contributing

We welcome contributions to improve the application. Feel free to:
Open issues for bugs or feature requests.
Submit pull requests with enhancements or fixes.


## Contact

For any inquiries, please contact:

**Email**: borhencher35@gmail.com

**GitHub**: borhencherni

 ## Thank you for using the Product Management Application!
