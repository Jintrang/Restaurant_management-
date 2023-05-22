package employees;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class employee {
    private int employeeId;
    private  String lastName;
    private  String firstName;
    private  LocalDate birthday;
    private  String jobTitle;
    private  String phoneNumber;
    private  LocalDate joinDate;
    private String passCode;

    private TextField textId = new TextField();
    private TextField textLastName = new TextField();
    private TextField textFirstName = new TextField();
    private TextField textBirthday = new TextField();
    private TextField textJobTitle = new TextField();
    private TextField textPhone = new TextField();
    private TextField textJoinDate = new TextField();
    private Button fixButton;
    private Button deleteButton;

    public employee() {

    }

    public employee(int employeeId, String lastName, String firstName, LocalDate birthday
            , String jobTitle, String phoneNumber, LocalDate joinDate) {
        this.employeeId = employeeId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.jobTitle = jobTitle;
        this.phoneNumber = phoneNumber;
        this.joinDate = joinDate;
//        this.passCode = passCode;
    }

    public employee(TextField textId, TextField textLastName, TextField textFirstName,
                    TextField textBirthday, TextField textJobTitle, TextField textPhone, TextField textJoinDate,
                    Button fixButton, Button deleteButton) {
        this.textId = textId;
        this.textLastName = textLastName;
        this.textFirstName = textFirstName;
        this.textBirthday = textBirthday;
        this.textJobTitle = textJobTitle;
        this.textPhone = textPhone;
        this.textJoinDate = textJoinDate;
        this.fixButton = fixButton;
        this.deleteButton = deleteButton;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public String getPassCode() {
        return passCode;
    }

    public void setPassCode(String passCode) {
        this.passCode = passCode;
    }

    public Button getFixButton() {
        return fixButton;
    }

    public void setFixButton(Button fixButton) {
        this.fixButton = fixButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(Button deleteButton) {
        this.deleteButton = deleteButton;
    }

    public TextField getTextId() {
        return textId;
    }

    public void setTextId(TextField textID) {
        this.textId = textID;
    }

    public TextField getTextLastName() {
        return textLastName;
    }

    public void setTextLastName(TextField textLastName) {
        this.textLastName = textLastName;
    }

    public TextField getTextFirstName() {
        return textFirstName;
    }

    public void setTextFirstName(TextField textFirstName) {
        this.textFirstName = textFirstName;
    }

    public TextField getTextBirthday() {
        return textBirthday;
    }

    public void setTextBirthday(TextField textBirthday) {
        this.textBirthday = textBirthday;
    }

    public TextField getTextJobTitle() {
        return textJobTitle;
    }

    public void setTextJobTitle(TextField textJobTitle) {
        this.textJobTitle = textJobTitle;
    }

    public TextField getTextJoinDate() {
        return textJoinDate;
    }

    public void setTextJoinDate(TextField textJoinDate) {
        this.textJoinDate = textJoinDate;
    }

    public TextField getTextPhone() {
        return textPhone;
    }

    public void setTextPhone(TextField textPhone) {
        this.textPhone = textPhone;
    }
}