import java.io.InputStream;
import java.util.Scanner;

public class Test {
    static Scanner sc = new Scanner(System.in);
    static Product carts[] = new Product[3];
    static int count = 0;
    public static void main(String[] args) throws ClassNotFoundException {
        //user1	111
        //user2	user222222
        //user3	1234.5
        boolean bool = true;
        while (bool)
        {
            System.out.println("请输入用户名：");
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
                    while (true){
                        System.out.println("按1：购买商品");
                        System.out.println("按2：购物车");
                        System.out.println("按3：下单");
                        System.out.println("按4：退出");
                        int choose = sc.nextInt();
                        if(choose == 1){
                            shopping(inProduct);
                        }else if(choose == 2){
                            ShowCarts();
                        }else if(choose == 3){
                            Order order = new Order();
                            order.setUser(users[i]);//订单关联用户
                            //订单关联商品
                            Product products[] = new Product[count];

                            for(int j=0;j<carts.length;j++){
                                if(carts[j]!=null){
                                    products[j]=carts[j];
                                }
                            }
                            order.setProducts(products);
                            //下订单
                            CreateOrder.createOrder(order);
                        }else if(choose == 4){
                            System.out.println("安全退出");
                            break;
                        }
                    }
                    break;
                }else {
                    System.out.println("登录失败,请重新登录");
                }
            }
        }
    }

    public static void ShowCarts(){
        System.out.println("当前购物车商品如下：");
        for (Product p : carts) {
            if(p!=null) {
                System.out.print(p.getId());
                System.out.print("\t" + p.getName());
                System.out.print("\t" + p.getPrice());
                System.out.println("\t" + p.getDesc());
            }
        }
    }

    public static void shopping(InputStream in) throws ClassNotFoundException {
        ReadProductExcel readProductExcel = new ReadProductExcel();
        Product products[] = readProductExcel.getALLProduct(in);
        for (Product product : products){
            System.out.print(product.getId());
            System.out.print("\t" + product.getName());
            System.out.print("\t" + product.getPrice());
            System.out.println("\t" + product.getDesc());
        }
        /*
        选择商品ID
         */
        System.out.println("请输入商品ID把该商品加入购物车");
        String pId = sc.next();
        //int count = 0;
        //改为全局变量
        /*
        创建一个购物车的数组：存的是商品，根据此ID去Excel中去查找是否有该ID的商品信息，如果有则返回该商品即可
         */
        in = null;
        in = Class.forName("Test").getResourceAsStream("/product.xlsx");
        Product product = readProductExcel.getProductById(pId, in);
        System.out.println("要购买的商品价格：" + product.getPrice());
        if(product!=null){
            carts[count++] = product;
        }
    }
}
