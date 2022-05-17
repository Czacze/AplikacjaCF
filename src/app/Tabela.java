package app;

import javax.swing.table.AbstractTableModel;
import java.util.Random;

/**
 * Klasa <code>Tabela</code> zawiera model tabeli oraz metody związane z obliczeniami na tabeli
 */

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

    public void setRandomTable() {
        Random random = new Random();
        for(int i=0; i<countRowTable; i++)
            for(int j=0; j<countColumnTable; j++) {
                // ograniczenie znaku liczby i zakresu do 10
                dane[i][j] = Math.abs(random.nextInt()) % 10;
            }
        fireTableDataChanged();
    }

    public Integer calculateSum() {
        Integer sum = new Integer(0);
        for(int i=0; i<countRowTable; i++)
            for(int j=0; j<countColumnTable; j++) {
                sum = sum + dane[i][j];
            }
        return sum;
    }
    public Float calculateAverage() {
        Float avg = new Float(0.0);
        Integer sum = calculateSum();
        if(sum > 0) avg = (sum.floatValue())/(countRowTable*countColumnTable);
        return avg;
    }

    public Integer calculateMin(){
        Integer minValue = new Integer(dane[0][0]);
        for(int i=0; i<countRowTable; i++)
            for(int j=0; j<countColumnTable; j++) {
                if (dane[i][j] < minValue){
                    minValue = dane[i][j];
                }
            }
        return minValue;
    }

    public Integer calculateMax(){
        Integer maxValue = new Integer(dane[0][0]);
        for(int i=0; i<countRowTable; i++)
            for(int j=0; j<countColumnTable; j++) {
                if (dane[i][j] > maxValue){
                    maxValue = dane[i][j];
                }
            }
        return maxValue;
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

    @Override
    public Class<?> getColumnClass(int columnIndex) {
            return Integer.class;
    }
}
