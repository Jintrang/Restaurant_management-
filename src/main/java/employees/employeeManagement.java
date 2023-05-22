package employees;

import SQL.SQL;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class employeeManagement {
    public static List<employee> allEmployee = new ArrayList<>();

    public static int getEmployeeIdx(employee e) {
        return allEmployee.indexOf(e);
    }

    public static int getEmployeeIdxById(int id) {
        for(employee f : allEmployee) {
            if(f.getEmployeeId() == id) return getEmployeeIdx(f);
        }
        return -1;
    }

    public static void addEmployee(employee e) {
        int dem = 0;
        for(employee employee : allEmployee) {
            if (employee.getEmployeeId() == e.getEmployeeId()) {
                dem++;
            }
        }
        if (dem == 0) {
            allEmployee.add(e);
        }
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
