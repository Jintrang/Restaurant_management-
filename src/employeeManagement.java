import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class employeeManagement {
    static List<employee> allEmployee = new ArrayList<>();

    public static int getEmployeeIdx(employee e) {
        return allEmployee.indexOf(e);
    }

    public static int getEmployeeIdxById(int id) {
        for(employee f : allEmployee) {
            if(f.geteID() == id) return getEmployeeIdx(f);
        }
        return -1;
    }

    public static boolean addEmployee(employee e) {
        int idx = getEmployeeIdx(e);
        if(idx >= 0) {
            allEmployee.add(e);
            return true;
        }
        return false;
    }

    public static boolean removeEmployee(int id) {
        int idx = getEmployeeIdxById(id);
        if(idx >= 0){
            allEmployee.remove(idx);
            return true;
        } else return false;
    }

    public boolean fixFood(int id, String lastName, String firstName, LocalDate birth, String phone) {
        int idx = getEmployeeIdxById(id);
        if(idx>=0) {
            allEmployee.get(idx).setFirstName(firstName);
            allEmployee.get(idx).setLastName(lastName);
            allEmployee.get(idx).setBirthday(birth);
            allEmployee.get(idx).setPhoneNumber(phone);
            return true;
        }
        return false;
    }
}
