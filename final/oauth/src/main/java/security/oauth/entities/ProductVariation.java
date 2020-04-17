package security.oauth.entities;


import javax.persistence.*;

@Entity
public class ProductVariation {
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Integer quantityAvailable;
    private Double price;
   // private String primaryImageName;

    //updated
    private Boolean isActive=true;

    private String metadata;
    private boolean isDeleted=false;
    @ManyToOne
    @JoinColumn(name = "produt_id")
    private Product product;
    @ManyToOne
    private Cart cart;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductVariation() {
    }

    public ProductVariation(Integer quantityAvailable, Double price) {
        this.quantityAvailable = quantityAvailable;
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductVariation{" +
                "id=" + id +
                ", quantityAvailable=" + quantityAvailable +
                ", price=" + price +
                ", isActive=" + isActive +
                ", metadata='" + metadata + '\'' +
                ", isDeleted=" + isDeleted +
                ", product=" + product +
                '}';
    }
}
