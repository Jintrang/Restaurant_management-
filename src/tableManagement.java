import java.util.ArrayList;
import java.util.List;

public class tableManagement {
    public static int newTableId = 0;
    static List<table> tables = new ArrayList<>();

    //Thêm bàn vào danh sách(gọi ở bên class SQL)
    public static void addTableToList(table tb){
        tables.add(tb);
    }

    //trả về idx của bàn trong list
    public static int getIdxTableById(int id){
        for(table tb : tables) {
            if(tb.tableId == id) return tables.indexOf(tb);
        }
        return -1;
    }

    // Trả về danh sách bàn trống
    public static List<Integer> getEmptyTable() {
        List<Integer> emptyTable = new ArrayList<>();
        for(table tb : tables) {
            if(tb.billId == 0) {
                emptyTable.add(tb.tableId);
                System.out.print(tb.tableId + " - ");
            }
        }
        return emptyTable;
    }

    // thanh toán hóa đơn -> xóa hóa đơn của bàn
    public static void clearTable(int tableId) {
        tables.get(tableId -1).billId = 0;
        tables.get(tableId -1).foods = new ArrayList<>();
    }

    //tạo hóa đơn mới dựa theo số bàn
}
