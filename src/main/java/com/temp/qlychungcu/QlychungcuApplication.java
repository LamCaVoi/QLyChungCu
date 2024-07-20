package com.temp.qlychungcu;

import com.temp.LoginForm;
import com.temp.Services.Services;
import javax.swing.JFrame;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.temp")
public class QlychungcuApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(QlychungcuApplication.class)
                .headless(false)
                .run(args);

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLChungCu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        
        java.awt.EventQueue.invokeLater(() -> {
            QLChungCu frame = new QLChungCu(context.getBean(Services.class));
            LoginForm frame2 = new LoginForm(frame);
            frame2.setVisible(true);
            frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });

//        java.awt.EventQueue.invokeLater(() -> {
//            RoomPic frame = new RoomPic();
//            frame.setVisible(true);
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        });

//        java.awt.EventQueue.invokeLater(() -> {
//            temp2 frame = new temp2();
//        });


    }
}
