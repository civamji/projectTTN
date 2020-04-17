package security.oauth.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Seller extends User{
    private String gst;
    private String companyName;
    private String companyContact;

    @OneToMany(mappedBy = "seller",cascade = CascadeType.ALL)
    private Set<Product> products;

    public Seller()
    {
    }


    public String getgst() {
        return gst;
    }

    public void setgst(String GST) {
        this.gst = gst;
    }

    public String getCompnayContact() {
        return companyContact;
    }

    public void setCompnayContact(String compnayContact) {
        this.companyContact = compnayContact;
    }

    public String getComapnyName() {
        return companyName;
    }

    public void setComapnyName(String comapnyName) {
        this.companyName = comapnyName;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }


    @Override
    public String toString() {
        return "Seller{" +
                super.toString() +
                "gst='" + gst + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyContact='" + companyContact + '\'' +
                '}';
    }
//
//    public void addProduct(Product product){
//        if(product != null){
//            if(products == null)
//                products = new HashSet<Product>();
//
//            products.add(product);
//            product.setSeller(this);
//        }
//    }
}
