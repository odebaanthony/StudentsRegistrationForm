package com.student;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class StudentRegistrationForm extends Application {

    private TextField firstNameField;
    private TextField lastNameField;
    private TextField emailField;
    private TextField confirmEmailField;
    private PasswordField passwordField;
    private PasswordField confirmPasswordField;
    private ComboBox<String> dayCombo;
    private ComboBox<String> monthCombo;
    private ComboBox<String> yearCombo;
    private RadioButton maleRadio;
    private RadioButton femaleRadio;
    private RadioButton civilRadio;
    private RadioButton computerRadio;
    private RadioButton electricalRadio;
    private RadioButton electronicRadio;
    private RadioButton mechanicalRadio;
    private TextArea outputArea;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("New Student Registration Form");

        // Main container
        VBox mainContainer = new VBox(15);
        mainContainer.setPadding(new Insets(20));
        mainContainer.setStyle("-fx-background-color: #E0E0E0;");

        // Create form fields
        GridPane formGrid = createFormGrid();

        // Create output area
        Label outputLabel = new Label("Your Data is Below:");
        outputLabel.setStyle("-fx-font-weight: bold;");
        
        outputArea = new TextArea();
        outputArea.setPrefRowCount(8);
        outputArea.setEditable(false);
        outputArea.setStyle("-fx-control-inner-background: white;");

        VBox outputBox = new VBox(5, outputLabel, outputArea);
        outputBox.setPadding(new Insets(10));
        outputBox.setStyle("-fx-background-color: white; -fx-border-color: #CCCCCC;");

        // Add all components to main container
        mainContainer.getChildren().addAll(formGrid, outputBox);

        // Create scene
        Scene scene = new Scene(mainContainer, 650, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createFormGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(12);
        grid.setPadding(new Insets(15));
        grid.setStyle("-fx-background-color: #D0D8E8;");

        int currentRow = 0;

        // Student First Name
        grid.add(new Label("Student First Name:"), 0, currentRow);
        firstNameField = new TextField();
        firstNameField.setPrefWidth(200);
        grid.add(firstNameField, 1, currentRow);

        currentRow++;

        // Student Last Name
        grid.add(new Label("Student Last Name:"), 0, currentRow);
        lastNameField = new TextField();
        lastNameField.setPrefWidth(200);
        grid.add(lastNameField, 1, currentRow);

        currentRow++;

        // Email Address
        grid.add(new Label("Email Address:"), 0, currentRow);
        emailField = new TextField();
        emailField.setPrefWidth(200);
        grid.add(emailField, 1, currentRow);

        currentRow++;

        // Confirm Email Address
        grid.add(new Label("Confirm Email Address:"), 0, currentRow);
        confirmEmailField = new TextField();
        confirmEmailField.setPrefWidth(200);
        grid.add(confirmEmailField, 1, currentRow);

        currentRow++;

        // Password
        grid.add(new Label("Password:"), 0, currentRow);
        passwordField = new PasswordField();
        passwordField.setPrefWidth(200);
        grid.add(passwordField, 1, currentRow);

        currentRow++;

        // Confirm Password
        grid.add(new Label("Confirm Password:"), 0, currentRow);
        confirmPasswordField = new PasswordField();
        confirmPasswordField.setPrefWidth(200);
        grid.add(confirmPasswordField, 1, currentRow);

        currentRow++;

        // Date of Birth
        grid.add(new Label("Date of Birth:"), 0, currentRow);
        HBox dateBox = createDateSelector();
        grid.add(dateBox, 1, currentRow);

        currentRow++;

        // Gender
        grid.add(new Label("Gender:"), 0, currentRow);
        HBox genderBox = createGenderSelector();
        grid.add(genderBox, 1, currentRow);

        currentRow++;

        // Department
        grid.add(new Label("Department:"), 0, currentRow);
        VBox departmentBox = createDepartmentSelector();
        grid.add(departmentBox, 1, currentRow);

        currentRow++;

        // Buttons
        HBox buttonBox = createButtons();
        grid.add(buttonBox, 1, currentRow);

        return grid;
    }

    private HBox createDateSelector() {
        dayCombo = new ComboBox<>();
        for (int i = 1; i <= 31; i++) {
            dayCombo.getItems().add(String.valueOf(i));
        }
        dayCombo.setPromptText("Select Day");

        monthCombo = new ComboBox<>();
        String[] months = {"January", "February", "March", "April", "May", "June",
                          "July", "August", "September", "October", "November", "December"};
        monthCombo.getItems().addAll(months);
        monthCombo.setPromptText("Select Month");

        yearCombo = new ComboBox<>();
        for (int i = 2010; i >= 1950; i--) {
            yearCombo.getItems().add(String.valueOf(i));
        }
        yearCombo.setPromptText("Select Year");

        HBox dateBox = new HBox(8, dayCombo, monthCombo, yearCombo);
        return dateBox;
    }

    private HBox createGenderSelector() {
        ToggleGroup genderGroup = new ToggleGroup();
        
        maleRadio = new RadioButton("Male");
        maleRadio.setToggleGroup(genderGroup);
        
        femaleRadio = new RadioButton("Female");
        femaleRadio.setToggleGroup(genderGroup);

        HBox genderBox = new HBox(15, maleRadio, femaleRadio);
        return genderBox;
    }

    private VBox createDepartmentSelector() {
        ToggleGroup departmentGroup = new ToggleGroup();
        
        civilRadio = new RadioButton("Civil");
        civilRadio.setToggleGroup(departmentGroup);
        
        computerRadio = new RadioButton("Computer Science and Engineering");
        computerRadio.setToggleGroup(departmentGroup);
        
        electricalRadio = new RadioButton("Electrical");
        electricalRadio.setToggleGroup(departmentGroup);
        
        electronicRadio = new RadioButton("Electronics and Communication");
        electronicRadio.setToggleGroup(departmentGroup);
        
        mechanicalRadio = new RadioButton("Mechanical");
        mechanicalRadio.setToggleGroup(departmentGroup);

        VBox departmentBox = new VBox(5);
        departmentBox.getChildren().addAll(
            civilRadio,
            computerRadio,
            electricalRadio,
            electronicRadio,
            mechanicalRadio
        );

        return departmentBox;
    }

    private HBox createButtons() {
        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-background-color: #4A90E2; -fx-text-fill: white;");
        submitButton.setOnAction(e -> handleSubmit());

        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle("-fx-background-color: #E74C3C; -fx-text-fill: white;");
        cancelButton.setOnAction(e -> handleCancel());

        HBox buttonBox = new HBox(10, submitButton, cancelButton);
        return buttonBox;
    }

    /**
     * Validates email format using regex pattern
     */
    private boolean isValidEmail(String email) {
        // Basic email validation pattern
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email != null && email.matches(emailRegex);
    }

    /**
     * Validates password strength
     * Requirements: At least 8 characters, contains letter and number
     */
    private boolean isValidPassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        
        boolean hasLetter = password.matches(".*[A-Za-z].*");
        boolean hasDigit = password.matches(".*\\d.*");
        
        return hasLetter && hasDigit;
    }

    /**
     * Validates all form inputs before submission
     */
    private boolean validateForm() {
        StringBuilder errors = new StringBuilder();

        // Validate First Name
        if (firstNameField.getText().trim().isEmpty()) {
            errors.append("- First name is required\n");
        }

        // Validate Last Name
        if (lastNameField.getText().trim().isEmpty()) {
            errors.append("- Last name is required\n");
        }

        // Validate Email
        String email = emailField.getText().trim();
        String confirmEmail = confirmEmailField.getText().trim();

        if (email.isEmpty()) {
            errors.append("- Email address is required\n");
        } else if (!isValidEmail(email)) {
            errors.append("- Email address is not valid (e.g., user@example.com)\n");
        }

        if (confirmEmail.isEmpty()) {
            errors.append("- Confirm email address is required\n");
        } else if (!email.equals(confirmEmail)) {
            errors.append("- Email addresses do not match\n");
        }

        // Validate Password
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (password.isEmpty()) {
            errors.append("- Password is required\n");
        } else if (!isValidPassword(password)) {
            errors.append("- Password must be at least 8 characters and contain letters and numbers\n");
        }

        if (confirmPassword.isEmpty()) {
            errors.append("- Confirm password is required\n");
        } else if (!password.equals(confirmPassword)) {
            errors.append("- Passwords do not match\n");
        }

        // Validate Date of Birth
        if (dayCombo.getValue() == null || monthCombo.getValue() == null || yearCombo.getValue() == null) {
            errors.append("- Complete date of birth is required\n");
        }

        // Validate Gender
        if (!maleRadio.isSelected() && !femaleRadio.isSelected()) {
            errors.append("- Gender selection is required\n");
        }

        // Validate Department
        if (!civilRadio.isSelected() && !computerRadio.isSelected() && 
            !electricalRadio.isSelected() && !electronicRadio.isSelected() && 
            !mechanicalRadio.isSelected()) {
            errors.append("- A department must be selected\n");
        }

        // Show errors if any
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Please correct the following errors:");
            alert.setContentText(errors.toString());
            alert.showAndWait();
            return false;
        }

        return true;
    }

    private void handleSubmit() {
        // Validate form first
        if (!validateForm()) {
            return; // Stop if validation fails
        }

        // If validation passes, display the data
        StringBuilder data = new StringBuilder();
        
        data.append("STUDENT REGISTRATION DETAILS\n");
        data.append("=".repeat(40)).append("\n\n");
        
        data.append("Name: ").append(firstNameField.getText())
            .append(" ").append(lastNameField.getText()).append("\n");
        
        data.append("Email: ").append(emailField.getText()).append("\n");
        
        String dob = (dayCombo.getValue() != null ? dayCombo.getValue() : "") + " " +
                     (monthCombo.getValue() != null ? monthCombo.getValue() : "") + " " +
                     (yearCombo.getValue() != null ? yearCombo.getValue() : "");
        data.append("Date of Birth: ").append(dob.trim()).append("\n");
        
        String gender = maleRadio.isSelected() ? "Male" : 
                       (femaleRadio.isSelected() ? "Female" : "Not specified");
        data.append("Gender: ").append(gender).append("\n");
        
        // Display selected department
        String department = "Not selected";
        if (civilRadio.isSelected()) department = "Civil";
        else if (computerRadio.isSelected()) department = "Computer Science and Engineering";
        else if (electricalRadio.isSelected()) department = "Electrical";
        else if (electronicRadio.isSelected()) department = "Electronics and Communication";
        else if (mechanicalRadio.isSelected()) department = "Mechanical";
        
        data.append("Department: ").append(department).append("\n");
        
        outputArea.setText(data.toString());

        // Show success message
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Registration Successful");
        successAlert.setHeaderText(null);
        successAlert.setContentText("Student registration completed successfully!");
        successAlert.showAndWait();
    }

    private void handleCancel() {
        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        confirmEmailField.clear();
        passwordField.clear();
        confirmPasswordField.clear();
        dayCombo.setValue(null);
        monthCombo.setValue(null);
        yearCombo.setValue(null);
        maleRadio.setSelected(false);
        femaleRadio.setSelected(false);
        civilRadio.setSelected(false);
        computerRadio.setSelected(false);
        electricalRadio.setSelected(false);
        electronicRadio.setSelected(false);
        mechanicalRadio.setSelected(false);
        outputArea.clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
