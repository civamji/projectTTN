package security.oauth.entities;

import jdk.jfr.MemoryAddress;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String description;
    private String brand;
    private boolean isCancelleable=true;
    private boolean isReturnable=true;
    private boolean isActive=true;
    private boolean isDeleted=false;
    @ManyToOne
    @JoinColumn(name="seller_user_id")
    private Seller seller;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private Set<ProductVariation> variations;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;


    @OneToMany(mappedBy="product", cascade = CascadeType.ALL)
    private List<ProductReview> reviews;

    public Product() {
    }

    public Product(String name, String description, String brand) {
        this.name = name;
        this.description = description;
        this.brand = brand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isCancelleable() {
        return isCancelleable;
    }

    public void setCancelleable(boolean cancelleable) {
        isCancelleable = cancelleable;
    }

    public boolean isReturnable() {
        return isReturnable;
    }

    public void setReturnable(boolean returnable) {
        isReturnable = returnable;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Set<ProductVariation> getVariations() {
        return variations;
    }

    public void setVariations(Set<ProductVariation> variations) {
        this.variations = variations;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<ProductReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<ProductReview> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", brand='" + brand + '\'' +
                ", isCancelleable=" + isCancelleable +
                ", isReturnable=" + isReturnable +
                ", isActive=" + isActive +
                ", isDeleted=" + isDeleted +
                ", seller=" + seller +
                ", variations=" + variations +
                ", category=" + category +
                ", reviews=" + reviews +
                '}';
    }
}
