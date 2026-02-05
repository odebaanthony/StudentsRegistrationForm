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
    private CheckBox civilCheckBox;
    private CheckBox computerCheckBox;
    private CheckBox electricalCheckBox;
    private CheckBox electronicCheckBox;
    private CheckBox mechanicalCheckBox;
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
        Scene scene = new Scene(mainContainer, 650, 550);
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
        civilCheckBox = new CheckBox("Civil");
        computerCheckBox = new CheckBox("Computer Science and Engineering");
        electricalCheckBox = new CheckBox("Electrical");
        electronicCheckBox = new CheckBox("Electronics and Communication");
        mechanicalCheckBox = new CheckBox("Mechanical");

        VBox departmentBox = new VBox(5);
        departmentBox.getChildren().addAll(
            civilCheckBox,
            computerCheckBox,
            electricalCheckBox,
            electronicCheckBox,
            mechanicalCheckBox
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

    private void handleSubmit() {
        StringBuilder data = new StringBuilder();
        
        data.append("STUDENT REGISTRATION DETAILS\n");
        data.append("=" .repeat(40)).append("\n\n");
        
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
        
        data.append("\nSelected Departments:\n");
        if (civilCheckBox.isSelected()) data.append("  - Civil\n");
        if (computerCheckBox.isSelected()) data.append("  - Computer Science and Engineering\n");
        if (electricalCheckBox.isSelected()) data.append("  - Electrical\n");
        if (electronicCheckBox.isSelected()) data.append("  - Electronics and Communication\n");
        if (mechanicalCheckBox.isSelected()) data.append("  - Mechanical\n");
        
        outputArea.setText(data.toString());
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
        civilCheckBox.setSelected(false);
        computerCheckBox.setSelected(false);
        electricalCheckBox.setSelected(false);
        electronicCheckBox.setSelected(false);
        mechanicalCheckBox.setSelected(false);
        outputArea.clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
