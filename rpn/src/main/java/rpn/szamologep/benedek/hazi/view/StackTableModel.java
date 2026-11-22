package rpn.szamologep.benedek.hazi.view;
import rpn.szamologep.benedek.hazi.model.RpnEngine;
import javax.swing.table.AbstractTableModel;

public class StackTableModel extends AbstractTableModel {
    private RpnEngine engine;
    private final String[] columnNames = {"Index", "Value"};

    public StackTableModel(RpnEngine engine) {
        this.engine = engine;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return engine.getSize();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return rowIndex + 1; // Row number
        } else if (columnIndex == 1) {
            return engine.getElementAt(rowIndex); // Stack value
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void refresh() {
        fireTableDataChanged();
    }

}
