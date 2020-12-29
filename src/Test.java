import java.io.InputStream;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        //user1	111
        //user2	user222222
        //user3	1234.5
        boolean bool = true;
        while (bool)
        {
            System.out.println("请输入用户名：");
            Scanner sc = new Scanner(System.in);
            String username = sc.next();
            System.out.println("请输入密码：");
            String password=sc.next();

            //File file = new File("G:\\Lanqiao\\ConsoleShop\\src\\list.xlsx");
            InputStream in = Class.forName("Test").getResourceAsStream("/list.xlsx");
            InputStream inProduct = Class.forName("Test").getResourceAsStream("/product.xlsx");
            ReadExcel readExcel = new ReadExcel();
            User users[] = readExcel.readExcel(in);
            for(int i=0;i<users.length;i++)
            {
                if(username.equals(users[i].getUsername())&&password.equals(users[i].getPassword()))
                {
                    System.out.println("登陆成功");
                    bool=false;
                    /*
                    显示商品
                     */
                    ReadProductExcel readProductExcel = new ReadProductExcel();
                    Product products[] = readProductExcel.getALLProduct(inProduct);
                    for (Product product : products){
                        System.out.print(product.getId());
                        System.out.print("\t" + product.getName());
                        System.out.print("\t" + product.getPrice());
                        System.out.println("\t" + product.getDesc());
                    }
                    break;
                }else {
                    System.out.println("登录失败,请重新登录");
                }
            }
        }
    }
}
