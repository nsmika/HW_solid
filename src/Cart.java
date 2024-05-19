import java.util.*;

class Cart {
    private final List<Product> cart = new ArrayList<>();

    public void addProductToCart(Scanner scanner, ProductService productService) {
        productService.showAvailableProducts();
        System.out.println("Введите название товара, который хотите добавить в корзину:");
        String productName = scanner.next();
        System.out.println("Введите количество:");
        int amount = scanner.nextInt();

        Optional<Product> productOpt = productService.getProductByName(productName);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            if (amount > product.getAmount()) {
                System.out.printf("Доступно только: %d единиц(ы) %s.\n", product.getAmount(), product.getName());
            } else {
                for (int i = 0; i < amount; i++) {
                    cart.add(product);
                }
                product.setAmount(product.getAmount() - amount);
                System.out.printf("%d единиц(ы) %s добавлено в корзину.\n", amount, product.getName());
            }
        } else {
            System.out.println("Товар не найден.");
        }
    }

    public void removeProductFromCart(Scanner scanner, ProductService productService) {
        System.out.println("Введите название товара, который хотите удалить из корзины:");
        String productName = scanner.next();
        System.out.println("Введите количество, которое хотите удалить:");
        int amount = scanner.nextInt();

        Iterator<Product> iterator = cart.iterator();
        int removedCount = 0;
        while (iterator.hasNext() && removedCount < amount) {
            Product product = iterator.next();
            if (product.getName().equals(productName)) {
                iterator.remove();
                productService.returnProduct(product);
                removedCount++;
            }
        }
        if (removedCount > 0) {
            System.out.printf("%d единиц(ы) %s удалено из корзины и возвращено на склад.\n", removedCount, productName);
        } else {
            System.out.println("Товар не найден в корзине или количество больше чем в корзине.");
        }
    }

    public void showCart() {
        if (cart.isEmpty()) {
            System.out.println("Ваша корзина пуста");
        } else {
            Set<Product> uniqueProducts = new LinkedHashSet<>(cart);
            double finalCost = 0;
            System.out.println("Ваша корзина: ");
            for (Product product : uniqueProducts) {
                int count = countProducts(product);
                double price = product.getPrice() * count;
                System.out.println("Наименование: " + product.getName() + " Количество: " + count + " Цена: " + price);
                finalCost += price;
            }
            System.out.printf("Общая стоимость продуктов: %.1f\n", finalCost);
        }
    }

    public void clearCart(ProductService productService) {
        cart.forEach(productService::returnProduct);
        cart.clear();
        System.out.println("Корзина очищена и все товары возвращены на склад!");
    }

    public void clearCartWithoutReturning() {
        cart.clear();
    }

    private int countProducts(Product product) {
        int count = 0;
        for (Product p : cart) {
            if (p.getName().equals(product.getName())) {
                count++;
            }
        }
        return count;
    }

    public List<Product> getProducts() {
        return new ArrayList<>(cart);
    }

    public boolean isEmpty() {
        return cart.isEmpty();
    }

    public void addProducts(List<Product> products) {
        cart.addAll(products);
    }
}