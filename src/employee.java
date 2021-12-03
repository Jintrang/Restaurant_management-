import java.time.LocalDate;

public class employee {
    int eID;
    String lastName;
    String firstName;
    LocalDate birthday;
    String jobTitle;
    String phoneNumber;
    LocalDate joinDate;

    public employee(int eID, String lastName, String firstName, LocalDate birthday
            , String jobTitle, String phoneNumber, LocalDate joinDate) {
        this.eID = eID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.jobTitle = jobTitle;
        this.phoneNumber = phoneNumber;
        this.joinDate = joinDate;
    }

    public int geteID() {
        return eID;
    }

    public void seteID(int eID) {
        this.eID = eID;
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
}
