import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class MysqlOperate {
    private static final String mysql_connect_username = "user";
    private static final String mysql_connect_password = "b134ce403d14f75df399f0fc54ff2155";
    private static final String mysql_connect_jdbc_driver = "com.mysql.jdbc.Driver";
    private static final String mysql_connect_url = "jdbc:mysql://localhost:33060/jdbc?useSSL=false";

    private Connection connection;

    public MysqlOperate() throws NoSuchAlgorithmException {
        try {
            Class.forName(mysql_connect_jdbc_driver);
            connection = DriverManager.getConnection(mysql_connect_url, mysql_connect_username, mysql_connect_password);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Connection Error");
            System.exit(-1);
        }
        this.mysql_init();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        MysqlOperate operate = new MysqlOperate();
        operate.inquire("uname", "骆");
    }

    private void mysql_init() throws NoSuchAlgorithmException {
        new ReadFile(this);
        this.add_user("admin", "admin", "admin", true);
    }

    private String MD5SUM(String str) throws NoSuchAlgorithmException {
        /*
         * 用于计算字符串的md5哈希值
         */
        byte[] str_bytes = str.getBytes(StandardCharsets.UTF_8);
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] str_hash_bytes = md5.digest(str_bytes);
        StringBuilder result = new StringBuilder();
        for (byte str_hash_byte : str_hash_bytes) {
            result.append(Integer.toString((str_hash_byte & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }

    private boolean check(PreparedStatement statement) throws SQLException {
        /*
         * 执行与check相关的sql语句并返回执行结果是否与预期结果相同
         */
        ResultSet resultset = statement.executeQuery();
        boolean result = resultset.next();
        if (result) {
            int count_number = resultset.getInt(1);
            return count_number == 0;
        } else {
            System.out.println("SQLQuery Error");
            System.exit(-1);
            return false;
        }
    }

    public boolean check_table_empty() {
        /*
         * 如果check(statement)返回true则说明该表为空
         */
        String query = "SELECT count(*) FROM user";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            return check(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLQuery Error");
            System.exit(-1);
            return false;
        }
    }

    public boolean check_user_exist(String uname) {
        /*
         * 如果check(statement)返回true则说明该uname不存在
         */
        String query = "SELECT count(*) FROM user WHERE uname = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, uname);
            return check(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLQuery Error");
            System.exit(-1);
            return false;
        }
    }

    public boolean add_user(String uname, String upwd, String major, boolean admin) throws NoSuchAlgorithmException {
        /*
         * 添加用户,如果用户存在则返回false,如果用户不存在则进行新建
         */
        if (!this.check_user_exist(uname)) {
            return false;
        }
        String query = "INSERT INTO user (id,uname,upwd,major,admin) VALUES (NULL,?,?,?,?)";
        upwd = MD5SUM(upwd);
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, uname);
            statement.setString(2, upwd);
            statement.setString(3, major);
            statement.setBoolean(4, admin);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLQuery Error");
            System.exit(-1);
            return false;
        }
    }

    public boolean delete_user(String uname) {
        /*
         * 删除用户,如果用户不存在则返回false,如果用户存在则进行删除操作
         */
        if (this.check_user_exist(uname)) {
            return false;
        }
        String query = "DELETE FROM user WHERE uname=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, uname);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLQuery Error");
            System.exit(-1);
            return false;
        }
    }

    public ArrayList<String[]> inquire(String column_name, String data) {
//        String query = "SELECT id,uname,major FROM user WHERE " + column_name + " LIKE ? LIMIT ?";
        String query = "SELECT id,uname,major FROM user WHERE " + column_name + " LIKE ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + data + "%");
            ArrayList<String[]> information = new ArrayList<>();
            ResultSet resultset = statement.executeQuery();
            boolean result = resultset.next();
            while (result) {
                information.add(new String[]{resultset.getString(1),resultset.getString(2),resultset.getString(3)});
                result = resultset.next();
            }
            return information;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLQuery Error");
            System.exit(-1);
        }
        return null;
    }

    public void update(String column_name, String data, String id) throws NoSuchAlgorithmException {
        String query = "UPDATE user SET " + column_name + " = ? WHERE id = ?";
        if (Objects.equals(column_name, "upwd")) {
            data = MD5SUM(data);
        }
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, data);
            statement.setString(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLQuery Error");
            System.exit(-1);
        }
    }

    public boolean verify_user(String uname, String upwd) throws NoSuchAlgorithmException {
        if (this.check_user_exist(uname)) {
            return false;
        }
        String query = "SELECT upwd FROM user WHERE uname = ?";
        String upwd_query_result = null;
        upwd = MD5SUM(upwd);
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, uname);
            ResultSet resultset = statement.executeQuery();
            boolean result = resultset.next();
            if (result) {
                upwd_query_result = resultset.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLQuery Error");
            System.exit(-1);
        }
        return Objects.equals(upwd_query_result, upwd);
    }

    public boolean verify_admin(String uname) {
        String query = "SELECT admin FROM user WHERE uname = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, uname);
            ResultSet resultset = statement.executeQuery();
            boolean result = resultset.next();
            if (result) {
                return resultset.getBoolean(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLQuery Error");
            System.exit(-1);
        }
        return false;
    }

    public String[] get_user_information(String column_name, String data) {
        String query = "SELECT id,uname,major,upwd FROM user WHERE " + column_name + " = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, data);
            ResultSet resultset = statement.executeQuery();
            boolean result = resultset.next();
            String[] information = new String[4];
            while (result) {
                information[0] = resultset.getString(1);
                information[1] = resultset.getString(2);
                information[2] = resultset.getString(3);
                information[3] = resultset.getString(4);
                result = resultset.next();
            }
            return information;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLQuery Error");
            System.exit(-1);
        }
        return null;
    }

    public void backup_database() {
        String query = "SELECT * FROM user";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultset = statement.executeQuery();
            boolean result = resultset.next();
            File file = new File("backup.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter file_writer = new FileWriter(file.getName());
            BufferedWriter buffered_writer = new BufferedWriter(file_writer);
            String column_name = "id,uname,upwd,major,admin\n";
            buffered_writer.write(column_name);
            buffered_writer.close();
            while (result) {
                file_writer = new FileWriter(file.getName(), true);
                buffered_writer = new BufferedWriter(file_writer);
                String id = resultset.getString(1);
                String uname = resultset.getString(2);
                String upwd = resultset.getString(3);
                String major = resultset.getString(4);
                boolean admin = resultset.getBoolean(5);
                String data = id + ',' + uname + ',' + upwd + ',' + major + ',' + admin + '\n';
                buffered_writer.write(data);
                buffered_writer.close();
                result = resultset.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLQuery Error");
            System.exit(-1);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO Error");
            System.exit(-1);
        }
    }

}
// java -classpath $PWD:$PWD/mysql-connector-java-5.1.49-bin.jar MysqlOperate