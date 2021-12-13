import java.io.BufferedReader;
import java.io.FileReader;
import java.security.NoSuchAlgorithmException;

public class ReadFile {

    private static final String filename = "user.txt";
    private final MysqlOperate operate;

    public ReadFile(MysqlOperate mysqloperate) {
        operate = mysqloperate;
        if (operate.check_table_empty()) {
            read_from_file();
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        new ReadFile(new MysqlOperate());
    }

    private void read_from_file() {
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String str;
            in.readLine();//跳过第一行
            while ((str = in.readLine()) != null) {
                String[] personal_information = str.split(",");
                operate.add_user(personal_information[1], personal_information[2], personal_information[3], false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("File Open Error");
            System.exit(-1);
        }
    }
}
