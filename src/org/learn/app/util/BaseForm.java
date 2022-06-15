package org.learn.app.util;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;


public class BaseForm extends JFrame
{
    private static String APP_TITLE = "LearnAPP";
    private static Image APP_ICON = null;
    static {
        try {
            APP_ICON = ImageIO.read(BaseForm.class.getClassLoader().getResource("icon.png"));
        } catch (Exception e) {
            e.printStackTrace();
            DialogUtil.showError(null, "Не удалось загрузить иконку");
        }
    }

    public BaseForm(int width, int height)
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(width, height));
        setLocation(
                Toolkit.getDefaultToolkit().getScreenSize().width/2-width/2,
                Toolkit.getDefaultToolkit().getScreenSize().height/2-height/2
        );

        setTitle(APP_TITLE);
        if(APP_ICON!=null){
            setIconImage(APP_ICON);
        }
    }
}
