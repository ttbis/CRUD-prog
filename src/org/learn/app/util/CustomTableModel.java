package org.learn.app.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;
import java.util.List;

@AllArgsConstructor
public class CustomTableModel<T> extends AbstractTableModel
{
    Class<T> cls;
    String[] columnNames;
    @Setter
    @Getter
    List<T> rows;

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return cls.getDeclaredFields().length;
    }

    @Override
    public String getColumnName(int column) {
        return column < columnNames.length ? columnNames[column] : "Column Title";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return cls.getDeclaredFields()[columnIndex].getType();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Field field = cls.getDeclaredFields()[columnIndex];
        field.setAccessible(true);
        try {
            return field.get(rows.get(rowIndex));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return "Error field";
        }
    }
}
