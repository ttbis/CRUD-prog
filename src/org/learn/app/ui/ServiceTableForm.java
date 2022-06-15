package org.learn.app.ui;

import org.learn.app.entity.ServiceEntity;
import org.learn.app.manager.ServiceEntityManager;
import org.learn.app.util.BaseForm;
import org.learn.app.util.CustomTableModel;
import org.learn.app.util.DialogUtil;

import javax.swing.*;
import java.sql.SQLException;

public class ServiceTableForm extends BaseForm {
    private JPanel mainPanel;
    private JTable table;
    private JButton addButton;
    private JButton button2;
    private JButton button3;
    private JButton deleteButton;

    private CustomTableModel<ServiceEntity> model;

    public ServiceTableForm() {
        super(1200, 800);
        setContentPane(mainPanel);

        initTable();
        initButtons();

        setVisible(true);
    }

    private void initTable()
    {
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(50);
        try {
            model = new CustomTableModel<>(
                    ServiceEntity.class,
                    new String[]{},
                    ServiceEntityManager.selectAll()
            );
            table.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initButtons()
    {
        addButton.addActionListener(e -> {
            dispose();
            new ServiceCreateForm();
        });

        deleteButton.addActionListener(e -> {
            dispose();
            new ServiceDeleteForm();
        });
    }
}
