package org.learn.app.ui;

import org.learn.app.entity.ServiceEntity;
import org.learn.app.manager.ServiceEntityManager;
import org.learn.app.util.BaseForm;

import javax.swing.*;
import java.sql.SQLException;

public class ServiceCreateForm extends BaseForm {
    private JPanel mainPanel;
    private JTextField titleField;
    private JSpinner durationSpinner;
    private JTextField costField;
    private JTextField discountField;
    private JTextField descField;
    private JTextField imagePathField;
    private JButton backButton;
    private JButton saveButton;

    public ServiceCreateForm()
    {
        super(600,400);
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

        saveButton.addActionListener(e -> {
            String title = titleField.getText();
            int duration = (int) durationSpinner.getValue();
            double cost = -1;
            try {
                cost = Double.parseDouble(costField.getText());
            }catch (Exception ex){
                return;
            }
            double discount = -1;
            try {
                discount = Double.parseDouble(discountField.getText());
            }catch (Exception ex){
                return;
            }
            String desc = descField.getText();
            String imagePath = imagePathField.getText();

            ServiceEntity service = new ServiceEntity(
                    title,
                    duration,
                    cost,
                    discount,
                    desc,
                    imagePath
            );

            try {
                ServiceEntityManager.insert(service);
                dispose();
                new ServiceTableForm();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
