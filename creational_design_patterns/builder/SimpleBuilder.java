package builder;

// product class with internal builder
class Product {
    private String name;
    private double price;

    private Product(Builder builder) {
        this.name = builder.name;
        this.price = builder.price;
    }

    @Override
    public String toString() {
        return "Product [name=" + name + ", price=" + price + "]";
    }

     // static inner builder class
    public static class Builder {
        private String name;
        private double price;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }

}

public class SimpleBuilder {
    public static void main(String[] args) {

        Product product = new Product.Builder()
                .setName("laptop")
                .setPrice(1200.00)
                .build();

        System.out.println(product);
    }
}