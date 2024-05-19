class Product {
    private final String name;
    private final double price;
    private double rating;
    private int votes;
    private int amount;

    public Product(String name, double price, double rating, int votes, int amount) {
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.votes = votes;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }

    public int getVotes() {
        return votes;
    }

    public int getAmount() {
        return amount;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
