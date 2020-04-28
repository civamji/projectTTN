package security.oauth.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Long id;
    private String name;
    private boolean isDeleted=false;


    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private Set<Product> products;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Category> subCategories;


    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<CategoryMetadataFieldValues> categoryMetadataFieldValues;


    public Set<CategoryMetadataFieldValues> getCategoryMetadataFieldValues() {
        return categoryMetadataFieldValues;
    }

    public void setCategoryMetadataFieldValues(Set<CategoryMetadataFieldValues> categoryMetadataFieldValues) {
        this.categoryMetadataFieldValues = categoryMetadataFieldValues;
    }

    public void addSubCategory(Category category){
        if(category != null){
            if(subCategories == null)
                subCategories = new HashSet<>();

            subCategories.add(category);
            category.setParentCategory(this);
        }
    }

    public void addProduct(Product product){
        if(product != null){
            if(products == null)
                products = new HashSet<Product>();

            products.add(product);

            product.setCategory(this);
        }
    }

    public void addFieldValues(CategoryMetadataFieldValues fieldValue){
        if(fieldValue != null){
            if(categoryMetadataFieldValues==null)
                categoryMetadataFieldValues = new HashSet<>();

            categoryMetadataFieldValues.add(fieldValue);
            fieldValue.setCategory(this);
        }
    }

    public Category() {
    }

    public Category(String name) {
        this.name = name;
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public Set<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(Set<Category> subCategories) {
        this.subCategories = subCategories;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isDeleted=" + isDeleted +
                ", products=" + products +
                ", parentCategory=" + parentCategory +
                ", subCategories=" + subCategories +
                '}';
    }
}
