package app;

import javax.swing.table.AbstractTableModel;

public class Tabela extends AbstractTableModel {

    private int countRowTable=5;
    private int countColumnTable=5;
    private int[][] dane = new int[countColumnTable][countRowTable];
    private String[] colName = {"1","2","3","4","5"};

    public Tabela(){
        setZeroTable();
    }

    public String getColumnName(int col) {return colName[col];}

    public void setZeroTable() {
        for(int i=0; i<countRowTable; i++)
            for(int j=0; j<countColumnTable; j++) {
                dane[i][j]=0;
            }
        fireTableDataChanged();
    }

    public void setValueAt(int value, int rowIndex, int columnIndex){
        dane[columnIndex][rowIndex] = value;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return countRowTable;
    }

    @Override
    public int getColumnCount() {
        return countColumnTable;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return dane[rowIndex][columnIndex];
    }
}
