package security.oauth.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "user_id")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer extends User {

private String contact;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProductReview> reviews;

//    @OneToOne(mappedBy = "customer",cascade =CascadeType.ALL,fetch = FetchType.EAGER)
//    private Cart cart;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Orders> orders;

    @OneToOne(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Cart cart;

//    public Customer(){
//        this.addRole(new Role(3, "ROLE_CUSTOMER"));
//    }
//
//    public Customer(String email, String firstName, String middleName, String lastName, String contact) {
//        super(email, firstName, middleName, lastName);
//        this.addRole(new Role(3, "ROLE_CUSTOMER"));
//        this.contact = contact;
//    }
//
//    public void addReview(ProductReview review){
//        if(review != null){
//            if(reviews == null)
//                reviews = new ArrayList<>();
//
//            reviews.add(review);
//            review.setAuthor(this);
//        }
//    }


    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<ProductReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<ProductReview> reviews) {
        this.reviews = reviews;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Customer() {
    }

    @Override
    public String toString() {
        return "Customer{" +
                super.toString() +
                "contact='" + contact + '\'' +
                '}';
    }

//    public void addOrder(Order order){
//        if(order!=null){
//            if(orders==null){
//          //      orders = new LinkedHashSet<orders>();
//            }
//            orders.add(order);
//         //   order.setCustomer(this);
//        }
//    }
}