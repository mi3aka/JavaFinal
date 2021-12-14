import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class FrontEnd {
    /*
     * 前端界面管理
     */
    private final JFrame login_register_frame = new JFrame("JavaFinal");
    private final Container login_register_container = login_register_frame.getContentPane();
    /*
     * 登录页面部件
     */
    private final JLabel login_title = new JLabel("登录页面", JLabel.CENTER);
    private final JPanel login_input_field = new JPanel();
    private final JLabel username_label = new JLabel("用户名:", JLabel.CENTER);
    private final JTextField username_field = new JTextField();
    private final JLabel password_label = new JLabel("密码:", JLabel.CENTER);
    private final JPasswordField password_field = new JPasswordField();
    /*
     * 注册页面部件
     */
    private final JLabel register_title = new JLabel("注册页面", JLabel.CENTER);
    private final JPanel register_input_field = new JPanel();
    private final JLabel major_label = new JLabel("专业:", JLabel.CENTER);
    private final JTextField major_field = new JTextField();
    /*
     * 登录注册页面按钮
     */
    private final JPanel login_register_button_field = new JPanel();
    private final JButton login_button = new JButton("登录");
    private final JButton register_button = new JButton("注册");
    private final JButton exit_button = new JButton("退出");
    /*
     * 普通用户个人信息管理
     */
    private final JFrame user_manager_frame = new JFrame("UserManager");
    private final JTabbedPane user_manager_tab_panel = new JTabbedPane();
    /*
     * 普通用户更新个人信息
     */
    private final JPanel user_manager_panel_update_information = new JPanel();
    private final JPanel user_information = new JPanel();
    private final JLabel user_id_label = new JLabel("ID:", JLabel.CENTER);
    private final JLabel user_show_id_label = new JLabel("", JLabel.CENTER);
    private final JLabel user_name_label = new JLabel("用户名:", JLabel.CENTER);
    private final JTextField update_user_name_field = new JTextField();
    private final JLabel user_major_label = new JLabel("专业:", JLabel.CENTER);
    private final JTextField update_user_major_field = new JTextField();
    private final JPanel user_information_button_field = new JPanel();
    private final JButton update_information_button = new JButton("更新");
    private final JButton update_information_exit_button = new JButton("退出");
    /*
     * 普通用户修改密码
     */
    private final JPanel user_manager_panel_update_password = new JPanel();
    private final JPanel user_update_password = new JPanel();
    private final JLabel user_type_old_password_label = new JLabel("原密码:", JLabel.CENTER);
    private final JPasswordField user_type_old_password_field = new JPasswordField();
    private final JLabel user_type_new_password_label = new JLabel("新密码:", JLabel.CENTER);
    private final JPasswordField user_type_new_password_field = new JPasswordField();
    private final JLabel user_retype_new_password_label = new JLabel("重复新密码:", JLabel.CENTER);
    private final JPasswordField user_retype_new_password_field = new JPasswordField();
    private final JPanel user_password_button_field = new JPanel();
    private final JButton update_password_button = new JButton("修改");
    private final JButton update_password_exit_button = new JButton("退出");
    /*
     * 管理员管理界面
     */
    private final JFrame admin_manager_frame = new JFrame("AdminManager");
    private final JTabbedPane admin_manager_tab_panel = new JTabbedPane();
    /*
     * 管理员对用户进行查询
     */
    private final JPanel admin_manager_panel1 = new JPanel();
    private final JLabel admin_manager_column_name_combobox_label = new JLabel("查询选项:", JLabel.CENTER);
    private final JComboBox<String> admin_manager_column_name_combobox = new JComboBox<>();//下拉框
    private final JLabel admin_manager_inquire_field_label = new JLabel("查询内容:", JLabel.CENTER);
    private final JTextField admin_manager_inquire_field = new JTextField(30);//查询输入框
    private final JButton admin_manager_inquire_button = new JButton("查询");//查询按钮

    /*
     * 管理员对用户进行管理
     */
    private final JPanel admin_manager_panel2 = new JPanel();


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
        /*
         * 初始化登录输入
         */
        login_input_field.setLayout(new GridLayout(2, 2, 5, 5));//2行2列
        login_input_field.add(username_label);
        login_input_field.add(username_field);
        login_input_field.add(password_label);
        login_input_field.add(password_field);
    }

    private void register_input_field_init() {
        /*
         * 初始化注册输入
         */
        register_input_field.setLayout(new GridLayout(3, 2, 0, 0));//3行2列
        register_input_field.add(username_label);
        register_input_field.add(username_field);
        register_input_field.add(password_label);
        register_input_field.add(password_field);
        register_input_field.add(major_label);
        register_input_field.add(major_field);
    }

    private void container_remove() {
        /*
         * 清空部件,为刷新做准备
         */
        login_register_container.remove(login_title);
        login_register_container.remove(login_input_field);
        login_register_container.remove(register_title);
        login_register_container.remove(register_input_field);
        login_register_container.remove(login_register_button_field);
    }

    private void draw_login_register_interface() {
        login_input_field_init();
        login_register_container.setLayout(new GridLayout(3, 1, 0, 5));//3行1列
        login_register_container.add(login_title);
        login_register_container.add(login_input_field);

        login_register_button_field.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
        login_register_button_field.add(login_button);
        login_register_button_field.add(register_button);
        login_register_button_field.add(exit_button);
        login_register_container.add(login_register_button_field);

        if (Arrays.toString(login_button.getActionListeners()).equals("[]")) {
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
                        login_register_container.add(login_register_button_field);
                        login_register_container.validate();//重构界面
                        login_register_container.repaint();//重新绘制
                    } else {//todo 登录操作
                        String uname = username_field.getText();
                        String upwd = String.valueOf(password_field.getPassword());
                        if (!Objects.equals(uname, "") && !upwd.equals("")) {
                            try {
                                if (operate.verify_user(uname, upwd)) {
                                    login_register_frame.dispose();
                                    if (operate.verify_admin(uname)) {
//                                        JOptionPane.showMessageDialog(login_register_frame, "成功登录");
                                        draw_admin_manager_interface();
                                    } else {
                                        username = uname;
                                        draw_user_manager_interface();
                                    }
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
        }

        if (Arrays.toString(register_button.getActionListeners()).equals("[]")) {
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
                        login_register_container.add(login_register_button_field);
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
                                        login_register_container.add(login_register_button_field);
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
        }

        if (Arrays.toString(exit_button.getActionListeners()).equals("[]")) {
            exit_button.addActionListener(new ActionListener() {//添加按钮监听事件
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    System.exit(0);
                }
            });
        }


        login_register_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login_register_frame.setSize(300, 200);
        login_register_frame.setLocationRelativeTo(null);
        login_register_frame.setVisible(true);
        login_register_frame.setResizable(false);
    }

    private void draw_user_manager_interface() {
        /*
         * 普通用户个人信息管理窗体绘制
         */
        user_manager_tab_panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));//与窗体的距离
        user_manager_tab_panel.add("个人信息", user_manager_panel_update_information);
        user_manager_tab_panel.add("修改密码", user_manager_panel_update_password);
        String[] information = operate.get_user_information("uname", username);
        /*
         * 个人信息窗体设置
         */
        user_manager_panel_update_information.setBorder(BorderFactory.createEmptyBorder(30, 20, 0, 20));//与窗体的距离
        user_manager_panel_update_information.setLayout(new GridLayout(2, 1, 5, 50));//2行1列
        user_information.setLayout(new GridLayout(3, 2, 5, 5));//3行2列
        user_information.add(user_id_label);
        user_show_id_label.setText(information[0]);
        user_information.add(user_show_id_label);
        user_information.add(user_name_label);
        update_user_name_field.setText(information[1]);
        user_information.add(update_user_name_field);
        user_information.add(user_major_label);
        update_user_major_field.setText(information[2]);
        user_information.add(update_user_major_field);
        user_information_button_field.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
        user_information_button_field.add(update_information_button);
        user_information_button_field.add(update_information_exit_button);
        user_manager_panel_update_information.add(user_information);
        user_manager_panel_update_information.add(user_information_button_field);
        /*
         * 密码修改窗体设置
         */
        user_manager_panel_update_password.setBorder(BorderFactory.createEmptyBorder(30, 20, 0, 20));//与窗体的距离
        user_manager_panel_update_password.setLayout(new GridLayout(2, 1, 5, 50));//2行1列
        user_update_password.setLayout(new GridLayout(3, 2, 5, 5));//3行2列
        user_update_password.add(user_type_old_password_label);
        user_update_password.add(user_type_old_password_field);
        user_update_password.add(user_type_new_password_label);
        user_update_password.add(user_type_new_password_field);
        user_update_password.add(user_retype_new_password_label);
        user_update_password.add(user_retype_new_password_field);
        user_password_button_field.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
        user_password_button_field.add(update_password_button);
        user_password_button_field.add(update_password_exit_button);
        user_manager_panel_update_password.add(user_update_password);
        user_manager_panel_update_password.add(user_password_button_field);

        if (Arrays.toString(update_information_button.getActionListeners()).equals("[]")) {
            update_information_button.addActionListener(new ActionListener() {//添加按钮监听事件
                /*
                 * 更新普通用户信息
                 */
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    String old_username = operate.get_user_information("id", information[0])[1];
                    String new_username = update_user_name_field.getText();
                    String major = update_user_major_field.getText();
                    if (Objects.equals(old_username, new_username)) {
                        try {
                            operate.update("major", major, information[0]);
                            JOptionPane.showMessageDialog(user_manager_frame, "成功修改专业");
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }
                    } else {
                        if (operate.check_user_exist(new_username)) {
                            try {
                                operate.update("uname", new_username, information[0]);
                                operate.update("major", major, information[0]);
                                JOptionPane.showMessageDialog(user_manager_frame, "成功修改个人信息");
                            } catch (NoSuchAlgorithmException e) {
                                e.printStackTrace();
                            }
                        } else {
                            JOptionPane.showMessageDialog(user_manager_frame, "用户名重复");
                        }
                    }
                }
            });
        }

        if (Arrays.toString(update_password_button.getActionListeners()).equals("[]")) {
            update_password_button.addActionListener(new ActionListener() {//添加按钮监听事件
                /*
                 * 修改普通用户密码
                 */
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    String type_old_password = String.valueOf(user_type_old_password_field.getPassword());
                    String type_new_password = String.valueOf(user_type_new_password_field.getPassword());
                    String retype_new_password = String.valueOf(user_retype_new_password_field.getPassword());
                    if (!Objects.equals(type_old_password, "") && !type_new_password.equals("") && !Objects.equals(retype_new_password, "")) {
                        if (type_new_password.equals(retype_new_password)) {
                            if (type_new_password.length() >= 5) {
                                String uname = operate.get_user_information("id", information[0])[1];
                                try {
                                    if (operate.verify_user(uname, type_old_password)) {
                                        operate.update("upwd", type_new_password, information[0]);
                                        JOptionPane.showMessageDialog(user_manager_frame, "密码更改成功,请重新登录");
                                        user_type_old_password_field.setText(null);
                                        user_type_new_password_field.setText(null);
                                        user_retype_new_password_field.setText(null);
                                        user_manager_frame.remove(user_manager_tab_panel);
                                        user_manager_frame.dispose();
                                        draw_login_register_interface();
                                    } else {
                                        JOptionPane.showMessageDialog(user_manager_frame, "原始密码错误");
                                    }
                                } catch (NoSuchAlgorithmException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                JOptionPane.showMessageDialog(user_manager_frame, "密码长度需不小于5");
                            }
                        } else {
                            JOptionPane.showMessageDialog(user_manager_frame, "两次输入的新密码不相同");
                        }
                    }
                }
            });
        }

        if (Arrays.toString(update_information_exit_button.getActionListeners()).equals("[]")) {
            update_information_exit_button.addActionListener(new ActionListener() {//添加按钮监听事件
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    System.exit(0);
                }
            });
        }

        if (Arrays.toString(update_password_exit_button.getActionListeners()).equals("[]")) {
            update_password_exit_button.addActionListener(new ActionListener() {//添加按钮监听事件
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    System.exit(0);
                }
            });
        }


        user_manager_frame.add(user_manager_tab_panel);
        user_manager_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        user_manager_frame.setSize(400, 300);
        user_manager_frame.setLocationRelativeTo(null);
        user_manager_frame.setVisible(true);
        user_manager_frame.setResizable(false);
    }

    private void draw_admin_manager_interface() {

        admin_manager_tab_panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));//与窗体的距离
        admin_manager_tab_panel.add("用户查询", admin_manager_panel1);
        admin_manager_tab_panel.add("用户管理", admin_manager_panel2);


        admin_manager_column_name_combobox.addItem("id");
        admin_manager_column_name_combobox.addItem("uname");
        admin_manager_column_name_combobox.addItem("major");
        admin_manager_panel1.add(admin_manager_column_name_combobox_label);
        admin_manager_panel1.add(admin_manager_column_name_combobox);
        admin_manager_panel1.add(admin_manager_inquire_field_label);
        admin_manager_panel1.add(admin_manager_inquire_field);
        admin_manager_panel1.add(admin_manager_inquire_button);


        if (Arrays.toString(admin_manager_inquire_button.getActionListeners()).equals("[]")) {
            admin_manager_inquire_button.addActionListener(new ActionListener() {//添加按钮监听事件
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    String data = admin_manager_inquire_field.getText();
                    if (!Objects.equals(data, "")) {
                        ArrayList<String[]> information = operate.inquire((String) admin_manager_column_name_combobox.getSelectedItem(), data);
                        if (information.size() != 0) {
                            String[] column_name = new String[]{"ID", "用户名", "专业"};
                            String[][] column_data = information.toArray(new String[information.size()][]);
                            for (int i = 0; i < admin_manager_panel1.getComponents().length; ++i) {
                                if (admin_manager_panel1.getComponents()[i].getClass().toString().equals("class javax.swing.JScrollPane")) {
                                    admin_manager_panel1.remove(i);
                                }
                            }
                            NewJTable jtable = new NewJTable(column_data, column_name);
                            jtable.addMouseListener(new MouseAdapter() {
                                public void mousePressed(MouseEvent mouseEvent) {
                                    JTable table =(JTable) mouseEvent.getSource();
                                    if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                                        System.out.println(table.getSelectedRow());
                                    }
                                }
                            });
                            JScrollPane scrollPane = new JScrollPane(table);
                            admin_manager_panel1.add(scrollPane, BorderLayout.CENTER);
                        } else {
                            for (int i = 0; i < admin_manager_panel1.getComponents().length; ++i) {
                                if (admin_manager_panel1.getComponents()[i].getClass().toString().equals("class javax.swing.JScrollPane")) {
                                    admin_manager_panel1.remove(i);
                                }
                            }
                        }
                        admin_manager_panel1.validate();//重构界面
                        admin_manager_panel1.repaint();//重新绘制
                    }
                }
            });
        }


        admin_manager_frame.add(admin_manager_tab_panel);

        admin_manager_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        admin_manager_frame.setSize(800, 600);
        admin_manager_frame.setLocationRelativeTo(null);
        admin_manager_frame.setVisible(true);
        admin_manager_frame.setResizable(false);
    }
}