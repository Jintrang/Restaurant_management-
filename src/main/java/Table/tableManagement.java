package Table;

import java.util.ArrayList;
import java.util.List;

public class tableManagement {
    public static int newTableId = 0;
    public static List<table> tables = new ArrayList<>();

    //Thêm bàn vào danh sách(gọi ở bên class SQL)
    public static void addTableToList(table tb){
        tables.add(tb);
    }

    //trả về idx của bàn trong list
    public static int getIdxTableById(int id){
        for(table tb : tables) {
            if(tb.getTableId() == id) return tables.indexOf(tb);
        }
        return -1;
    }

    // Trả về danh sách bàn trống
    public static int emptyTable() {
        int dem = 0;
        List<Integer> emptyTable = new ArrayList<>();
        for(table tb : tables) {
            if(tb.getBillId() == 0) {
                emptyTable.add(tb.getTableId());
                dem++;
//                System.out.print(tb.getTableId() + " - ");
            }
        }
        return dem;
    }

    // thanh toán hóa đơn -> xóa hóa đơn của bàn
    public static void clearTable(int tableId, List<table> list) {
        list.get(tableId -1).setBillId(0);
        list.get(tableId -1).setFoods(new ArrayList<>());
    }

    //tạo hóa đơn mới dựa theo số bàn
}