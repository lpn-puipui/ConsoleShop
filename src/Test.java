import java.io.File;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        System.out.println("请输入用户名：");

        Scanner sc = new Scanner(System.in);
        String username = sc.next();
        System.out.println("你输入的用户名"+username);

        System.out.println("请输入密码：");
        String password=sc.next();
        System.out.println("你输入的密码"+password);

        File file = new File("G:\\Lanqiao\\ConsoleShop\\src\\list.xlsx");
        ReadExcel readExcel = new ReadExcel();
        User users[] = readExcel.readExcel(file);
        for(int i=0;i<users.length;i++)
        {
            /*System.out.print(users[i].getUsername());
            System.out.print("\t"+users[i].getPassword());
            System.out.print("\t"+users[i].getAddress());
            System.out.println(users[i].getPhone());

             */
            if(username.equals(users[i].getUsername())&&password.equals(users[i].getPassword()))
            {
                System.out.println("登陆成功");
                break;
            }else {
                System.out.println("登录失败");
            }
        }
    }
}
