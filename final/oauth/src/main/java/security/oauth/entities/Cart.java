package security.oauth.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private long quantity;
    private boolean isWishListItem;
    @OneToOne
    private Customer customer;
    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
    private Set<ProductVariation> productvariations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public boolean isWishListItem() {
        return isWishListItem;
    }

    public void setWishListItem(boolean wishListItem) {
        isWishListItem = wishListItem;
    }

    public Customer getCustomer() {
        return customer;
    }


    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<ProductVariation> getProduct_variations() {
        return productvariations;
    }

    public void setProduct_variations(Set<ProductVariation> product_variations) {
        this.productvariations = product_variations;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", isWishListItem=" + isWishListItem +
                ", customer=" + customer +
                ", product_variations=" + productvariations +
                '}';
    }
}