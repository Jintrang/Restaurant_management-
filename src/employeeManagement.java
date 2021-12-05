import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class employeeManagement {
    //danh sách tất cả nhân viên
    static int newEmployeeId = 0;
    static List<employee> allEmployee = new ArrayList<>();


    public static int getEmployeeIdx(employee e) {
        return allEmployee.indexOf(e);
    }

    public static int getEmployeeIdxById(int id) {
        for(employee f : allEmployee) {
            if(f.getEmployeeId() == id) return getEmployeeIdx(f);
        }
        return -1;
    }

    //thêm nhân viên vào list
    public static void addEmployeeToList(employee e) {
        int idx = getEmployeeIdx(e);
        if(idx < 0) {
            allEmployee.add(e);
        }
    }

    //Thêm nhân viên mới vào list và SQL
    public static void addEmployee(String lastName, String firstName, LocalDate birthday, String jobTitle, String phoneNumber) {
        int eid = ++newEmployeeId;
        employee e = new employee(eid, lastName, firstName, birthday, jobTitle, phoneNumber, LocalDate.now());
        int idx = getEmployeeIdx(e);
        if(idx < 0) {
            allEmployee.add(e);
            SQL.addEmployeeSQL(e);
        }
    }

    //xóa nhân viên khỏi list
    public static void removeEmployee(int id) {
        int idx = getEmployeeIdxById(id);
        if(idx >= 0){
            allEmployee.remove(idx);
            SQL.deleteEmployee(id);
        }
    }

    //sửa thông tin nhân viên vào list và SQL
    public boolean fixInfoEmployee(int id, String lastName, String firstName, LocalDate birth, String jobTiltle, String phone) {
        int idx = getEmployeeIdxById(id);
        if(idx>=0) {
            if(!SQL.fixInfoEmployee(id, lastName, firstName, birth, jobTiltle, phone)) return false;
            allEmployee.get(idx).setFirstName(firstName);
            allEmployee.get(idx).setLastName(lastName);
            allEmployee.get(idx).setBirthday(birth);
            allEmployee.get(idx).setPhoneNumber(phone);
            return true;
        }
        return false;
    }
}
