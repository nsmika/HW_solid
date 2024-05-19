class ProductRating {
    private final Product product;

    public ProductRating(Product product) {
        this.product = product;
    }

    public double getRating() {
        return product.getRating();
    }

    public int getVotes() {
        return product.getVotes();
    }

    public void vote(int rating) {
        if (rating >= 0 && rating <= 5) {
            double newRating = (product.getRating() * product.getVotes() + rating) / (product.getVotes() + 1);
            int newVotes = product.getVotes() + 1;
            product.setRating(newRating);
            product.setVotes(newVotes);
        } else {
            System.out.println("Рейтинг может быть от 0 до 5.");
        }
    }
}
