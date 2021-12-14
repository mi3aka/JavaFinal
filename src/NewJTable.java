import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class NewJTable extends JTable {
    public NewJTable(DefaultTableModel TableModel) {
        super(TableModel);
    }

    public NewJTable(Object[][] rowData, Object[] columnNames) {
        super(rowData,columnNames);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
