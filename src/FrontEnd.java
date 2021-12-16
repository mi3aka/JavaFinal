import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
    private final JPanel user_password_update_button_field = new JPanel();
    private final JButton user_update_password_button = new JButton("修改");
    private final JButton user_update_password_exit_button = new JButton("退出");
    /*
     * 管理员管理界面
     */
    private final JFrame admin_manager_frame = new JFrame("AdminManager");
    private final JTabbedPane admin_manager_tab_panel = new JTabbedPane();
    /*
     * 管理员对用户进行查询
     */
    private final JPanel admin_manager_panel_inquire_user = new JPanel();
    private final JLabel admin_manager_column_name_combobox_label = new JLabel("查询选项:", JLabel.CENTER);
    private final JComboBox<String> admin_manager_column_name_combobox = new JComboBox<>();//下拉框
    private final JLabel admin_manager_inquire_field_label = new JLabel("查询内容:", JLabel.CENTER);
    private final JTextField admin_manager_inquire_field = new JTextField(30);//查询输入框
    private final JButton admin_manager_inquire_button = new JButton("查询");//查询按钮
    private final HashMap<String, String> column_name_map = new HashMap<>();
    /*
     * 管理员对用户信息进行编辑
     */
    private final JTabbedPane admin_manager_user_tab_panel = new JTabbedPane();
    private final JPanel admin_modify_user_information_panel = new JPanel();
    private final JPanel admin_modify_user_information = new JPanel();
    private final JLabel admin_modify_user_id_label = new JLabel("ID:", JLabel.CENTER);
    private final JLabel admin_modify_user_show_id_label = new JLabel("", JLabel.CENTER);
    private final JLabel admin_modify_user_name_label = new JLabel("用户名:", JLabel.CENTER);
    private final JTextField admin_modify_update_user_name_field = new JTextField();
    private final JLabel admin_modify_user_major_label = new JLabel("专业:", JLabel.CENTER);
    private final JTextField admin_modify_update_user_major_field = new JTextField();
    private final JLabel admin_modify_user_password_label = new JLabel("密码(留空则不变):", JLabel.CENTER);
    private final JPasswordField admin_modify_update_user_password_field = new JPasswordField();
    private final JPanel admin_modify_user_information_button_field = new JPanel();
    private final JButton admin_modify_update_information_button = new JButton("更新");
    /*
     * 管理员删除用户
     */
    private final JPanel admin_delete_user_panel = new JPanel();
    private final JPanel admin_delete_user_information_panel = new JPanel();
    private final JLabel admin_delete_user_id_label = new JLabel("ID:", JLabel.CENTER);
    private final JLabel admin_delete_user_show_id_label = new JLabel("", JLabel.CENTER);
    private final JLabel admin_delete_user_name_label = new JLabel("用户名:", JLabel.CENTER);
    private final JLabel admin_delete_user_show_name_label = new JLabel("", JLabel.CENTER);
    private final JLabel admin_delete_user_major_label = new JLabel("专业:", JLabel.CENTER);
    private final JLabel admin_delete_user_show_major_label = new JLabel("", JLabel.CENTER);
    private final JPanel admin_delete_user_button_field = new JPanel();
    private final JButton admin_delete_user_button = new JButton("删除该用户");
    /*
     * 管理员新增用户
     */
    private final JPanel admin_add_user_panel = new JPanel();
    private final JPanel admin_add_user_type_panel = new JPanel();
    private final JLabel admin_add_user_type_username_label = new JLabel("新用户的用户名:", JLabel.CENTER);
    private final JTextField admin_add_user_type_username_field = new JTextField();
    private final JLabel admin_add_user_type_password_label = new JLabel("新用户的密码:", JLabel.CENTER);
    private final JPasswordField admin_add_user_type_password_field = new JPasswordField();
    private final JLabel admin_add_user_retype_password_label = new JLabel("重复新用户的密码:", JLabel.CENTER);
    private final JPasswordField admin_add_user_retype_password_field = new JPasswordField();
    private final JLabel admin_add_user_type_major_label = new JLabel("新用户的专业:", JLabel.CENTER);
    private final JTextField admin_add_user_type_major_field = new JTextField();
    private final JPanel admin_add_user_type_admin_panel = new JPanel();
    private final JLabel admin_add_user_type_admin_label = new JLabel("新用户是否为管理员:", JLabel.CENTER);
    private final JRadioButton admin_add_user_type_admin_false_radio_button = new JRadioButton("否", true);
    private final JRadioButton admin_add_user_type_admin_true_radio_button = new JRadioButton("是", false);
    private final ButtonGroup admin_add_user_type_admin_group = new ButtonGroup();
    private final JPanel admin_add_user_button_panel = new JPanel();
    private final JButton admin_add_user_button = new JButton("新增用户");
    /*
     * 管理员导出用户信息
     */
    private final JPanel admin_backup_user_panel = new JPanel();
    private final JLabel admin_backup_user_pathname_label = new JLabel("导出文件名:", JLabel.CENTER);
    private final JTextField admin_backup_user_pathname_field = new JTextField(30);
    private final JButton admin_backup_user_button = new JButton("导出");//查询按钮
    /*
     * 修改管理员密码
     */
    private final JPanel admin_manager_panel_update_admin_password = new JPanel();
    private final JPanel admin_update_password = new JPanel();
    private final JLabel admin_type_old_password_label = new JLabel("原密码:", JLabel.CENTER);
    private final JPasswordField admin_type_old_password_field = new JPasswordField();
    private final JLabel admin_type_new_password_label = new JLabel("新密码:", JLabel.CENTER);
    private final JPasswordField admin_type_new_password_field = new JPasswordField();
    private final JLabel admin_retype_new_password_label = new JLabel("重复新密码:", JLabel.CENTER);
    private final JPasswordField admin_retype_new_password_field = new JPasswordField();
    private final JPanel admin_password_update_button_field = new JPanel();
    private final JButton admin_update_password_button = new JButton("修改");

    private final MysqlOperate operate;

    private String username;

    public FrontEnd(MysqlOperate mysqloperate) {
        operate = mysqloperate;
        column_name_map.put("ID", "id");
        column_name_map.put("用户名", "uname");
        column_name_map.put("专业", "major");
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

    private void update_password_button_add_action_listener(String[] information, JButton update_password_button, JPasswordField type_old_password_field, JPasswordField type_new_password_field, JPasswordField retype_new_password_field, JFrame manager_frame) {
        if (Arrays.toString(update_password_button.getActionListeners()).equals("[]")) {
            update_password_button.addActionListener(new ActionListener() {//添加按钮监听事件
                /*
                 * 修改用户/管理员密码
                 */
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    String type_old_password = String.valueOf(type_old_password_field.getPassword());
                    String type_new_password = String.valueOf(type_new_password_field.getPassword());
                    String retype_new_password = String.valueOf(retype_new_password_field.getPassword());
                    if (!Objects.equals(type_old_password, "") && !type_new_password.equals("") && !Objects.equals(retype_new_password, "")) {
                        if (type_new_password.equals(retype_new_password)) {
                            if (type_new_password.length() >= 5) {
                                String uname = operate.get_user_information("id", information[0])[1];
                                try {
                                    if (operate.verify_user(uname, type_old_password)) {
                                        operate.update("upwd", type_new_password, information[0]);
                                        JOptionPane.showMessageDialog(manager_frame, "密码更改成功,请重新登录");
                                        type_old_password_field.setText(null);
                                        type_new_password_field.setText(null);
                                        retype_new_password_field.setText(null);
                                        manager_frame.remove(user_manager_tab_panel);
                                        manager_frame.dispose();
                                        draw_login_register_interface();
                                    } else {
                                        JOptionPane.showMessageDialog(manager_frame, "原始密码错误");
                                    }
                                } catch (NoSuchAlgorithmException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                JOptionPane.showMessageDialog(manager_frame, "密码长度需不小于5");
                            }
                        } else {
                            JOptionPane.showMessageDialog(user_manager_frame, "两次输入的新密码不相同");
                        }
                    }
                }
            });
        }
    }

    private boolean check_username_available_while_updating_information(String old_username, String new_username, JFrame frame) {
        /*
         * 检查用户名可用性
         */
        if (!new_username.equals(old_username) && !operate.check_user_exist(new_username)) {
            JOptionPane.showMessageDialog(frame, "用户名重复");
            return true;
        }
        return false;
    }

    private void draw_admin_modify_delete_user_information_panel(String id, String uname, String major) {
        admin_modify_user_show_id_label.setText(id);
        admin_modify_update_user_name_field.setText(uname);
        admin_modify_update_user_major_field.setText(major);

        admin_delete_user_show_id_label.setText(id);
        admin_delete_user_show_name_label.setText(uname);
        admin_delete_user_show_major_label.setText(major);
        admin_modify_update_user_password_field.setText("");

        admin_manager_user_tab_panel.validate();//重构界面
        admin_manager_user_tab_panel.repaint();//重新绘制
    }

    private void draw_login_register_interface() {
        /*
         * 绘制登录/注册页面
         */
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
                                    username = uname;
                                    if (operate.verify_admin(uname)) {
                                        draw_admin_manager_interface();
                                    } else {
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
                    } else {
                        String uname = username_field.getText();
                        String upwd = String.valueOf(password_field.getPassword());
                        String major = major_field.getText();
                        if (!Objects.equals(uname, "") && !upwd.equals("") && !Objects.equals(major, "")) {
                            if (upwd.length() >= 5) {
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
                                JOptionPane.showMessageDialog(login_register_frame, "密码长度需不小于5");
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
        user_password_update_button_field.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
        user_password_update_button_field.add(user_update_password_button);
        user_password_update_button_field.add(user_update_password_exit_button);
        user_manager_panel_update_password.add(user_update_password);
        user_manager_panel_update_password.add(user_password_update_button_field);

        if (Arrays.toString(update_information_button.getActionListeners()).equals("[]")) {
            update_information_button.addActionListener(new ActionListener() {//添加按钮监听事件
                /*
                 * 更新普通用户信息
                 */
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    String old_username = operate.get_user_information("id", information[0])[1];
                    String new_username = update_user_name_field.getText();
                    String new_major = update_user_major_field.getText();
                    try {
                        if (!Objects.equals(new_username, "") && !Objects.equals(new_major, "")) {
                            if (check_username_available_while_updating_information(old_username, new_username, user_manager_frame)) return;
                            operate.update("uname", new_username, admin_modify_user_show_id_label.getText());
                            operate.update("major", new_major, admin_modify_user_show_id_label.getText());
                            JOptionPane.showMessageDialog(user_manager_frame, "成功修改个人信息");
                        }
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        update_password_button_add_action_listener(information, user_update_password_button, user_type_old_password_field, user_type_new_password_field, user_retype_new_password_field, user_manager_frame);

        if (Arrays.toString(update_information_exit_button.getActionListeners()).equals("[]")) {
            update_information_exit_button.addActionListener(new ActionListener() {//添加按钮监听事件
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    System.exit(0);
                }
            });
        }

        if (Arrays.toString(user_update_password_exit_button.getActionListeners()).equals("[]")) {
            user_update_password_exit_button.addActionListener(new ActionListener() {//添加按钮监听事件
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
        String[] information = operate.get_user_information("uname", username);

        admin_manager_tab_panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));//与窗体的距离
        admin_manager_tab_panel.add("用户查询", admin_manager_panel_inquire_user);
        admin_manager_tab_panel.add("用户管理", admin_manager_user_tab_panel);
        admin_manager_tab_panel.add("修改管理员密码", admin_manager_panel_update_admin_password);


        admin_manager_column_name_combobox.addItem("ID");
        admin_manager_column_name_combobox.addItem("用户名");
        admin_manager_column_name_combobox.addItem("专业");
        admin_manager_panel_inquire_user.add(admin_manager_column_name_combobox_label);
        admin_manager_panel_inquire_user.add(admin_manager_column_name_combobox);
        admin_manager_panel_inquire_user.add(admin_manager_inquire_field_label);
        admin_manager_panel_inquire_user.add(admin_manager_inquire_field);
        admin_manager_panel_inquire_user.add(admin_manager_inquire_button);
        if (Arrays.toString(admin_manager_inquire_button.getActionListeners()).equals("[]")) {
            admin_manager_inquire_button.addActionListener(new ActionListener() {//添加按钮监听事件
                /*
                 * 点击按钮,执行模糊查询,并将结果绘制为表格
                 */
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    String data = admin_manager_inquire_field.getText();
                    if (!Objects.equals(data, "")) {
                        ArrayList<String[]> information = operate.inquire(column_name_map.get((String) admin_manager_column_name_combobox.getSelectedItem()), data);
                        if (information.size() != 0) {
                            String[] column_name = new String[]{"ID", "用户名", "专业"};
                            String[][] column_data = information.toArray(new String[information.size()][]);
                            for (int i = 0; i < admin_manager_panel_inquire_user.getComponents().length; ++i) {
                                if (admin_manager_panel_inquire_user.getComponents()[i].getClass().toString().equals("class javax.swing.JScrollPane")) {
                                    admin_manager_panel_inquire_user.remove(i);
                                }
                            }
                            NewJTable inquire_result_table = new NewJTable(column_data, column_name);
                            inquire_result_table.addMouseListener(new MouseAdapter() {
                                public void mousePressed(MouseEvent mouseEvent) {
                                    JTable table = (JTable) mouseEvent.getSource();
                                    if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                                        if (operate.verify_admin((String) table.getValueAt(table.getSelectedRow(), 1))) {
                                            JOptionPane.showMessageDialog(admin_manager_frame, "无法对管理员级别用户进行修改");
                                        } else {
                                            draw_admin_modify_delete_user_information_panel((String) table.getValueAt(table.getSelectedRow(), 0), (String) table.getValueAt(table.getSelectedRow(), 1), (String) table.getValueAt(table.getSelectedRow(), 2));
                                            admin_manager_tab_panel.setSelectedComponent(admin_manager_user_tab_panel);//切换到用户管理
                                            admin_manager_user_tab_panel.setSelectedComponent(admin_modify_user_information_panel);
                                        }
                                    }
                                }
                            });
                            JScrollPane scroll_pane = new JScrollPane(inquire_result_table);
                            admin_manager_panel_inquire_user.add(scroll_pane, BorderLayout.CENTER);
                        } else {
                            for (int i = 0; i < admin_manager_panel_inquire_user.getComponents().length; ++i) {
                                if (admin_manager_panel_inquire_user.getComponents()[i].getClass().toString().equals("class javax.swing.JScrollPane")) {
                                    admin_manager_panel_inquire_user.remove(i);
                                }
                            }
                        }
                        admin_manager_panel_inquire_user.validate();//重构界面
                        admin_manager_panel_inquire_user.repaint();//重新绘制
                    }
                }
            });
        }


        admin_manager_user_tab_panel.add("修改用户", admin_modify_user_information_panel);
        admin_modify_user_information_panel.setBorder(BorderFactory.createEmptyBorder(100, 120, 100, 120));//与窗体的距离
        admin_modify_user_information_panel.setLayout(new GridLayout(2, 1, 5, 10));//3行1列
        admin_modify_user_information.setLayout(new GridLayout(4, 2, 5, 5));//4行2列
        admin_modify_user_information.add(admin_modify_user_id_label);
        admin_modify_user_information.add(admin_modify_user_show_id_label);
        admin_modify_user_information.add(admin_modify_user_name_label);
        admin_modify_user_information.add(admin_modify_update_user_name_field);
        admin_modify_user_information.add(admin_modify_user_major_label);
        admin_modify_user_information.add(admin_modify_update_user_major_field);
        admin_modify_user_information.add(admin_modify_user_password_label);
        admin_modify_user_information.add(admin_modify_update_user_password_field);
        admin_modify_user_information_button_field.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
        admin_modify_user_information_button_field.add(admin_modify_update_information_button);
        admin_modify_user_information_panel.add(admin_modify_user_information);
        admin_modify_user_information_panel.add(admin_modify_user_information_button_field);
        if (Arrays.toString(admin_modify_update_information_button.getActionListeners()).equals("[]")) {
            admin_modify_update_information_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    String old_username = operate.get_user_information("id", admin_modify_user_show_id_label.getText())[1];
                    String new_username = admin_modify_update_user_name_field.getText();
                    String old_major = operate.get_user_information("id", admin_modify_user_show_id_label.getText())[2];
                    String new_major = admin_modify_update_user_major_field.getText();
                    String new_password = String.valueOf(admin_modify_update_user_password_field.getPassword());
                    if (Objects.equals(old_username, new_username) && Objects.equals(old_major, new_major) && new_password.equals("")) {
                        return;
                    }
                    try {
                        if (!Objects.equals(new_username, "") && !Objects.equals(new_major, "")) {
                            if (new_password.equals("")) {
                                if (check_username_available_while_updating_information(old_username, new_username, admin_manager_frame)) return;
                            } else {
                                if (new_password.length() >= 5) {
                                    if (!new_username.equals(old_username) && !operate.check_user_exist(new_username)) {
                                        JOptionPane.showMessageDialog(admin_manager_frame, "用户名重复");
                                        return;
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(admin_manager_frame, "密码字段长度需不小于5");
                                    return;
                                }
                                operate.update("upwd", new_password, admin_modify_user_show_id_label.getText());
                            }
                            operate.update("uname", new_username, admin_modify_user_show_id_label.getText());
                            operate.update("major", new_major, admin_modify_user_show_id_label.getText());
                            draw_admin_modify_delete_user_information_panel("", "", "");
                            for (int i = 0; i < admin_manager_panel_inquire_user.getComponents().length; ++i) {
                                if (admin_manager_panel_inquire_user.getComponents()[i].getClass().toString().equals("class javax.swing.JScrollPane")) {
                                    admin_manager_panel_inquire_user.remove(i);
                                }
                            }
                            JOptionPane.showMessageDialog(admin_manager_frame, "成功更新信息");
                        }
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                }
            });
        }


        admin_manager_user_tab_panel.add("删除用户", admin_delete_user_panel);
        admin_delete_user_panel.setBorder(BorderFactory.createEmptyBorder(50, 200, 100, 200));
        admin_delete_user_panel.setLayout(new GridLayout(2, 1, 0, 0));//2行1列
        admin_delete_user_information_panel.setLayout(new GridLayout(3, 2, 5, 5));//2行1列
        admin_delete_user_information_panel.add(admin_delete_user_id_label);
        admin_delete_user_information_panel.add(admin_delete_user_show_id_label);
        admin_delete_user_information_panel.add(admin_delete_user_name_label);
        admin_delete_user_information_panel.add(admin_delete_user_show_name_label);
        admin_delete_user_information_panel.add(admin_delete_user_major_label);
        admin_delete_user_information_panel.add(admin_delete_user_show_major_label);
        admin_delete_user_button_field.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
        admin_delete_user_button_field.add(admin_delete_user_button);
        admin_delete_user_panel.add(admin_delete_user_information_panel);
        admin_delete_user_panel.add(admin_delete_user_button_field);
        if (Arrays.toString(admin_delete_user_button.getActionListeners()).equals("[]")) {
            admin_delete_user_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    String uname = admin_delete_user_show_name_label.getText();
                    String admin_password = JOptionPane.showInputDialog(admin_manager_frame, "请输入管理员密码");
                    if (admin_password != null) {
                        try {
                            if (operate.verify_user(information[1], admin_password)) {
                                if (operate.delete_user(uname)) {
                                    draw_admin_modify_delete_user_information_panel("", "", "");
                                    for (int i = 0; i < admin_manager_panel_inquire_user.getComponents().length; ++i) {
                                        if (admin_manager_panel_inquire_user.getComponents()[i].getClass().toString().equals("class javax.swing.JScrollPane")) {
                                            admin_manager_panel_inquire_user.remove(i);
                                        }
                                    }
                                    JOptionPane.showMessageDialog(admin_manager_frame, "成功删除用户");
                                } else {
                                    JOptionPane.showMessageDialog(admin_manager_frame, "用户不存在/无法删除");
                                }
                            } else {
                                JOptionPane.showMessageDialog(admin_manager_frame, "管理员密码错误");
                            }
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }


        admin_manager_user_tab_panel.add("新增用户", admin_add_user_panel);
        admin_add_user_panel.setBorder(BorderFactory.createEmptyBorder(50, 120, 100, 120));
        admin_add_user_panel.setLayout(new GridLayout(2, 1, 0, 0));//2行1列
        admin_add_user_type_admin_panel.setLayout(new GridLayout(1, 2, 0, 0));//1行2列
        admin_add_user_type_panel.setLayout(new GridLayout(5, 2, 10, 10));//5行2列
        admin_add_user_type_panel.add(admin_add_user_type_username_label);
        admin_add_user_type_panel.add(admin_add_user_type_username_field);
        admin_add_user_type_panel.add(admin_add_user_type_password_label);
        admin_add_user_type_panel.add(admin_add_user_type_password_field);
        admin_add_user_type_panel.add(admin_add_user_retype_password_label);
        admin_add_user_type_panel.add(admin_add_user_retype_password_field);
        admin_add_user_type_panel.add(admin_add_user_type_major_label);
        admin_add_user_type_panel.add(admin_add_user_type_major_field);
        admin_add_user_type_admin_group.add(admin_add_user_type_admin_false_radio_button);
        admin_add_user_type_admin_group.add(admin_add_user_type_admin_true_radio_button);
        admin_add_user_type_admin_panel.add(admin_add_user_type_admin_false_radio_button);
        admin_add_user_type_admin_panel.add(admin_add_user_type_admin_true_radio_button);
        admin_add_user_type_panel.add(admin_add_user_type_admin_label);
        admin_add_user_type_panel.add(admin_add_user_type_admin_panel);
        admin_add_user_button_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
        admin_add_user_button_panel.add(admin_add_user_button);
        admin_add_user_panel.add(admin_add_user_type_panel);
        admin_add_user_panel.add(admin_add_user_button_panel);
        if (Arrays.toString(admin_add_user_button.getActionListeners()).equals("[]")) {
            admin_add_user_button.addActionListener(new ActionListener() {//添加按钮监听事件
                /*
                 * 点击按钮,如果条件满足,则添加新的用户
                 */
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    String type_username = admin_add_user_type_username_field.getText();
                    String type_password = String.valueOf(admin_add_user_type_password_field.getPassword());
                    String retype_password = String.valueOf(admin_add_user_retype_password_field.getPassword());
                    String type_major = admin_add_user_type_major_field.getText();
                    if (!Objects.equals(type_username, "") && !type_password.equals("") && !retype_password.equals("") && !Objects.equals(type_major, "")) {
                        if (type_password.equals(retype_password)) {
                            if (type_username.length() >= 5 && type_password.length() >= 5 && type_major.length() >= 5) {
                                try {
                                    if (operate.add_user(type_username, type_password, type_major, admin_add_user_type_admin_true_radio_button.isSelected())) {
                                        JOptionPane.showMessageDialog(admin_manager_frame, "成功注册新用户");
                                    } else {
                                        JOptionPane.showMessageDialog(admin_manager_frame, "已存在该用户");
                                    }
                                } catch (NoSuchAlgorithmException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                JOptionPane.showMessageDialog(admin_manager_frame, "用户名/密码/专业字段长度需不小于5");
                            }
                        } else {
                            JOptionPane.showMessageDialog(admin_manager_frame, "两次输入的密码不相同");
                        }
                    }
                }
            });
        }


        admin_manager_user_tab_panel.add("导出所有用户", admin_backup_user_panel);
        admin_backup_user_panel.add(admin_backup_user_pathname_label);
        admin_backup_user_panel.add(admin_backup_user_pathname_field);
        admin_backup_user_panel.add(admin_backup_user_button);
        if (Arrays.toString(admin_backup_user_button.getActionListeners()).equals("[]")) {
            admin_backup_user_button.addActionListener(new ActionListener() {//添加按钮监听事件
                /*
                 * 执行数据库操作类中的导出函数
                 */
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    String pathname = admin_backup_user_pathname_field.getText();
                    if (!Objects.equals(pathname, "")) {
                        operate.backup_database(pathname);
                        JOptionPane.showMessageDialog(admin_manager_frame, "导出完成");
                    }
                }
            });
        }


        admin_manager_panel_update_admin_password.setBorder(BorderFactory.createEmptyBorder(100, 80, 150, 80));//与窗体的距离
        admin_manager_panel_update_admin_password.setLayout(new GridLayout(2, 1, 5, 50));//2行1列
        admin_update_password.setLayout(new GridLayout(3, 2, 5, 5));//3行2列
        admin_update_password.add(admin_type_old_password_label);
        admin_update_password.add(admin_type_old_password_field);
        admin_update_password.add(admin_type_new_password_label);
        admin_update_password.add(admin_type_new_password_field);
        admin_update_password.add(admin_retype_new_password_label);
        admin_update_password.add(admin_retype_new_password_field);
        admin_password_update_button_field.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
        admin_password_update_button_field.add(admin_update_password_button);
        admin_manager_panel_update_admin_password.add(admin_update_password);
        admin_manager_panel_update_admin_password.add(admin_password_update_button_field);
        update_password_button_add_action_listener(information, admin_update_password_button, admin_type_old_password_field, admin_type_new_password_field, admin_retype_new_password_field, admin_manager_frame);


        admin_manager_frame.add(admin_manager_tab_panel);
        admin_manager_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        admin_manager_frame.setSize(800, 600);
        admin_manager_frame.setLocationRelativeTo(null);
        admin_manager_frame.setVisible(true);
        admin_manager_frame.setResizable(false);
    }
}