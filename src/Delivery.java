import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

class Delivery {
    private static final int ASSEMBLY_TIME = 5; // Принцип Avoid Magic Numbers
    private static final int CANCEL_TIME = ASSEMBLY_TIME;

    private long deliveryTime = 0;
    private long startDeliveryTime = 0;
    private Cart cart;
    private List<Product> tmpCart = new ArrayList<>();

    public Delivery(Cart cart) {
        this.cart = cart;
    }

    public void startDelivery() {
        if (!cart.isEmpty()) {
            tmpCart = new ArrayList<>(cart.getProducts());
            cart.clearCartWithoutReturning();
            Random random = new Random();
            deliveryTime = random.nextInt(16) + 15; // генерируем случайное время доставки от 15 до 30 минут
            System.out.println("Время доставки: " + deliveryTime + " минут(ы)");
            System.out.println("Время сборки: " + ASSEMBLY_TIME + " минут");
            System.out.println("Вы можете отменить заказ в течение: " + CANCEL_TIME + " минут");
            startDeliveryTime = System.currentTimeMillis();
        } else {
            System.out.println("Положите в корзину хотя бы один товар");
        }
    }

    public String getDeliveryStatus() {
        if (deliveryTime == 0) {
            return "Нет активных заказов";
        }
        long remainingTime = startDeliveryTime + deliveryTime * 60 * 1000 - System.currentTimeMillis();
        if (remainingTime <= 0) {
            tmpCart.clear();
            return "Доставка выполнена";
        } else {
            long minutes = TimeUnit.MILLISECONDS.toMinutes(remainingTime);
            long seconds = TimeUnit.MILLISECONDS.toSeconds(remainingTime) - TimeUnit.MINUTES.toSeconds(minutes);
            return "Осталось времени до доставки: " + minutes + " минут " + seconds + " секунд";
        }
    }

    public void cancelDelivery() {
        if (TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis()) - TimeUnit.MILLISECONDS.toMinutes(startDeliveryTime) < CANCEL_TIME) {
            deliveryTime = 0;
            startDeliveryTime = 0;
            cart.addProducts(tmpCart);
            System.out.println("Доставка отменена");
        } else if (deliveryTime != 0) {
            System.out.println("Отменить доставку уже нельзя");
        } else {
            System.out.println("Нет активных заказов");
        }
    }
}
