import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class FrontEnd {
    //前端界面管理
    private final JFrame login_register_frame = new JFrame("JavaFinal");
    private final Container login_register_container = login_register_frame.getContentPane();


    private final JLabel login_title = new JLabel("登录页面", JLabel.CENTER);
    private final JPanel login_input_field = new JPanel();
    private final JLabel username_label = new JLabel("用户名:", JLabel.CENTER);
    private final JTextField username_field = new JTextField();
    private final JLabel password_label = new JLabel("密码:", JLabel.CENTER);
    private final JPasswordField password_field = new JPasswordField();

    private final JLabel register_title = new JLabel("注册页面", JLabel.CENTER);
    private final JPanel register_input_field = new JPanel();
    private final JLabel major_label = new JLabel("专业:", JLabel.CENTER);
    private final JTextField major_field = new JTextField();

    private final JPanel button_field = new JPanel();
    private final JButton login_button = new JButton("登录");
    private final JButton register_button = new JButton("注册");
    private final JButton exit_button = new JButton("退出");

    private final JFrame user_manager_frame = new JFrame("UserManager");
    private final Container user_manager_container = user_manager_frame.getContentPane();

    private final JTabbedPane user_manager_tab_panel = new JTabbedPane();
    private final JPanel user_manager_panel1 = new JPanel();//个人信息
    private final JPanel user_manager_panel2 = new JPanel();//修改密码

    private final JFrame admin_manager_frame = new JFrame("AdminManager");
    private final Container admin_manager_container = admin_manager_frame.getContentPane();


    private final MysqlOperate operate;
    private String username;

    public FrontEnd(MysqlOperate mysqloperate) {
        operate = mysqloperate;
        draw_login_register_interface();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        new FrontEnd(new MysqlOperate());
    }

    private void login_input_field_init() {
        login_input_field.setLayout(new GridLayout(2, 2, 5, 5));//2行2列
        login_input_field.add(username_label);
        login_input_field.add(username_field);
        login_input_field.add(password_label);
        login_input_field.add(password_field);
    }

    private void register_input_field_init() {
        register_input_field.setLayout(new GridLayout(3, 2, 0, 0));//3行2列
        register_input_field.add(username_label);
        register_input_field.add(username_field);
        register_input_field.add(password_label);
        register_input_field.add(password_field);
        register_input_field.add(major_label);
        register_input_field.add(major_field);
    }

    private void container_remove() {
        login_register_container.remove(login_title);
        login_register_container.remove(login_input_field);
        login_register_container.remove(register_title);
        login_register_container.remove(register_input_field);
        login_register_container.remove(button_field);
    }

    private void draw_login_register_interface() {
        login_input_field_init();
        login_register_container.setLayout(new GridLayout(3, 1, 0, 5));//3行1列

        login_register_container.add(login_title);
        login_register_container.add(login_input_field);

        button_field.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
        button_field.add(login_button);
        button_field.add(register_button);
        button_field.add(exit_button);
        login_register_container.add(button_field);

        login_button.addActionListener(new ActionListener() {//添加按钮监听事件
            /*
             * 当位于登录页面点击登录时,执行登录操作
             * 当位于注册页面点击登录时,切换到登录页面
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JLabel component = (JLabel) login_register_container.getComponents()[0];
                String text = component.getText();
                if (Objects.equals(text, "注册页面")) {
                    container_remove();
                    login_input_field_init();
                    login_register_container.add(login_title);
                    login_register_container.add(login_input_field);
                    login_register_container.add(button_field);
                    login_register_container.validate();//重构界面
                    login_register_container.repaint();//重新绘制
                } else {//todo 登录操作
                    String uname = username_field.getText();
                    String upwd = String.valueOf(password_field.getPassword());
                    if (!Objects.equals(uname, "") && !upwd.equals("")) {
                        try {
                            if (operate.verify_user(uname, upwd)) {
                                username = uname;
                                //JOptionPane.showMessageDialog(login_register_frame, "成功登录");
                                //todo 根据用户权限切换到对应的主页面


                                //测试user manager frame
                                login_register_frame.dispose();
                                draw_user_manager_interface();


                            } else {
                                JOptionPane.showMessageDialog(login_register_frame, "用户名/密码错误");
                            }
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        register_button.addActionListener(new ActionListener() {//添加按钮监听事件
            /*
             * 当位于注册页面点击注册时,执行注册操作
             * 当位于登录页面点击注册时,切换到注册页面
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JLabel component = (JLabel) login_register_container.getComponents()[0];
                String text = component.getText();
                if (Objects.equals(text, "登录页面")) {
                    container_remove();
                    register_input_field_init();
                    login_register_container.add(register_title);
                    login_register_container.add(register_input_field);
                    login_register_container.add(button_field);
                    login_register_container.validate();//重构界面
                    login_register_container.repaint();//重新绘制
                } else {//todo 注册操作
                    String uname = username_field.getText();
                    String upwd = String.valueOf(password_field.getPassword());
                    String major = major_field.getText();
                    if (!Objects.equals(uname, "") && !upwd.equals("") && !Objects.equals(major, "")) {
                        if (uname.length() >= 5 && upwd.length() >= 5 && major.length() >= 5) {
                            try {
                                if (operate.add_user(uname, upwd, major, false)) {
                                    JOptionPane.showMessageDialog(login_register_frame, "成功注册");
                                    //跳转到登录页面
                                    container_remove();
                                    login_input_field_init();
                                    login_register_container.add(login_title);
                                    login_register_container.add(login_input_field);
                                    login_register_container.add(button_field);
                                    login_register_container.validate();//重构界面
                                    login_register_container.repaint();//重新绘制
                                } else {
                                    JOptionPane.showMessageDialog(login_register_frame, "已存在该用户");
                                }
                            } catch (NoSuchAlgorithmException e) {
                                e.printStackTrace();
                            }
                        } else {
                            JOptionPane.showMessageDialog(login_register_frame, "用户名/密码/专业字段长度需不小于5");
                        }
                    }
                }
            }
        });

        exit_button.addActionListener(new ActionListener() {//添加按钮监听事件
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        login_register_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login_register_frame.setSize(300, 200);
        login_register_frame.setLocationRelativeTo(null);
        login_register_frame.setVisible(true);
    }


    private void draw_user_manager_interface() {
        user_manager_tab_panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));//与窗体的距离
        user_manager_tab_panel.add("个人信息", user_manager_panel1);
        user_manager_tab_panel.add("修改密码", user_manager_panel2);
        System.out.println(username);

        user_manager_frame.add(user_manager_tab_panel);

        user_manager_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        user_manager_frame.setSize(800, 600);
        user_manager_frame.setLocationRelativeTo(null);
        user_manager_frame.setVisible(true);

    }
}