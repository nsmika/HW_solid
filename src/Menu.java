import java.util.Scanner;

class Menu {
    private static final String MENU_DEFAULT = "Некорректный выбор, попробуйте снова";
    private final Cart cart = new Cart();
    private final Delivery delivery = new Delivery(cart);
    private final ProductService productService = new ProductService();

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int menu = -1;
        while (menu != 0) {
            System.out.println("1 - Показать доступные товары");
            System.out.println("2 - Корзина и оформление заказа");
            System.out.println("3 - Статус заказа");
            System.out.println("4 - Оценить товар");
            System.out.println("0 - Выход");
            System.out.println("Введите ваш выбор:");
            menu = scanner.nextInt();
            switch (menu) {
                case 1:
                    productService.showAvailableProducts();
                    break;
                case 2:
                    showCartMenu(scanner);
                    break;
                case 3:
                    showDeliveryStatusMenu(scanner);
                    break;
                case 4:
                    productService.rateProduct(scanner);
                    break;
                case 0:
                    System.out.println("Выход");
                    break;
                default:
                    System.out.println(MENU_DEFAULT);
                    break;
            }
        }
    }

    private void showCartMenu(Scanner scanner) {
        int cartMenu = -1;
        while (cartMenu != 0) {
            System.out.println("1 - Показать корзину");
            System.out.println("2 - Добавить товар");
            System.out.println("3 - Удалить товар");
            System.out.println("4 - Оформить заказ");
            System.out.println("5 - Очистить корзину");
            System.out.println("0 - Назад");
            cartMenu = scanner.nextInt();
            switch (cartMenu) {
                case 1 -> cart.showCart();
                case 2 -> cart.addProductToCart(scanner, productService);
                case 3 -> cart.removeProductFromCart(scanner, productService);
                case 4 -> delivery.startDelivery();
                case 5 -> cart.clearCart(productService);
                case 0 -> {
                }
                default -> System.out.println(Menu.MENU_DEFAULT);
            }
        }
    }

    private void showDeliveryStatusMenu(Scanner scanner) {
        int deliveryMenu = -1;
        while (deliveryMenu != 0) {
            System.out.println("1 - Узнать статус заказа");
            System.out.println("2 - Отменить заказ");
            System.out.println("0 - Назад");
            deliveryMenu = scanner.nextInt();
            switch (deliveryMenu) {
                case 1 -> System.out.println(delivery.getDeliveryStatus());
                case 2 -> delivery.cancelDelivery();
                case 0 -> {
                }
                default -> System.out.println(Menu.MENU_DEFAULT);
            }
        }
    }
}
