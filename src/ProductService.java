import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

class ProductService {
    private final List<Product> products = Arrays.asList(
            new Product("Томаты", 100, 4.5, 10, 5),
            new Product("Картофель", 40, 4.8, 14, 5),
            new Product("Огурцы", 140, 4.7, 30, 9),
            new Product("Морковь", 50, 4.8, 43, 6),
            new Product("Груши", 140, 4.7, 16, 2),
            new Product("Бананы", 160, 4.4, 27, 3),
            new Product("Яблоки", 80, 4.9, 19, 4),
            new Product("Киви", 140, 4.7, 30, 0)
    );

    public void showAvailableProducts() {
        for (Product product : products) {
            if (product.getAmount() > 0) {
                System.out.printf("Название: %s | Количество: %d\nЦена: %.2f | Рейтинг: %.2f\n",
                        product.getName(), product.getAmount(), product.getPrice(), product.getRating());
            } else {
                System.out.println(product.getName() + " нет в наличии.");
            }
        }
    }

    public void rateProduct(Scanner scanner) {
        System.out.println("Введите название товара, который хотите оценить:");
        String productName = scanner.next();
        System.out.println("Введите вашу оценку (от 0 до 5):");
        int rating = scanner.nextInt();

        for (Product product : products) {
            if (product.getName().equals(productName)) {
                ProductRating productRating = new ProductRating(product);
                productRating.vote(rating);
                System.out.printf("Новый рейтинг товара %s: %.2f\n", productName, product.getRating());
            }
        }
    }
    public void returnProduct(Product product) {
        for (Product p : products) {
            if (p.getName().equals(product.getName())) {
                p.setAmount(p.getAmount() + 1);
                break;
            }
        }
    }

    public Optional<Product> getProductByName(String productName) {
        return products.stream().filter(product -> product.getName().equals(productName)).findFirst();
    }
}
