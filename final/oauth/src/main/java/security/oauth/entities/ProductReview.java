package security.oauth.entities;

import javax.persistence.*;


@Entity
public class ProductReview {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Long id;
    private String review;
    private Double rating;
    private boolean isDeleted=false;
    @ManyToOne
    @JoinColumn(name = "customer_user_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Customer getAuthor() {
        return customer;
    }

    public void setAuthor(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductReview() {
    }

    public ProductReview(String review, Double rating) {
        this.review = review;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "ProductReview{" +
                "id=" + id +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                ", isDeleted=" + isDeleted +
                ", customer=" + customer +
                ", product=" + product +
                '}';
    }
}
