package org.learn.app.ui;

import org.learn.app.entity.ServiceEntity;
import org.learn.app.manager.ServiceEntityManager;
import org.learn.app.util.BaseForm;

import javax.swing.*;
import java.sql.SQLException;

public class ServiceDeleteForm extends BaseForm {
    private JPanel mainPanel;
    private JSpinner idSpinner;
    private JButton backButton;
    private JButton deleteButton;


    public ServiceDeleteForm()
    {
        super(500, 200);
        setContentPane(mainPanel);

        initButtons();

        setVisible(true);
    }

    private void initButtons()
    {
        backButton.addActionListener(e -> {
            dispose();
            new ServiceTableForm();
        });

        deleteButton.addActionListener(e -> {
            int id = (int) idSpinner.getValue();

            ServiceEntity service = new ServiceEntity(id);
            try {
                ServiceEntityManager.delete(id);
                dispose();
                new ServiceTableForm();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        });
    }
}
