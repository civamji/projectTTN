package security.oauth.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class CategoryMetadataFieldValues implements Serializable {


//composite key as a primary key MUST BE PUBLIC
    //primary key
    @EmbeddedId()
    private CategoryMetadataFieldValuesId categoryMetadataFieldValuesId;

    @ManyToOne(optional = false,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryMetadataId",insertable = false,updatable = false,nullable = false)

    private CategoryMetadataField categoryMetadataField;


    @ManyToOne(optional = false)
    @JoinColumn(name = "categoryId",insertable = false,updatable = false,nullable = false)
    private Category category;



    //composite key as primary key

    private String fieldValues;

    public CategoryMetadataFieldValuesId getCategoryMetadataFieldValuesId() {
        return categoryMetadataFieldValuesId;
    }

    public void setCategoryMetadataFieldValuesId(CategoryMetadataFieldValuesId categoryMetadataFieldValuesId) {
        this.categoryMetadataFieldValuesId = categoryMetadataFieldValuesId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public CategoryMetadataField getCategoryMetadataField() {
        return categoryMetadataField;
    }

    public void setCategoryMetadataField(CategoryMetadataField categoryMetadataField) {
        this.categoryMetadataField = categoryMetadataField;
    }

    public String getFieldValues() {
        return fieldValues;
    }

    public void setFieldValues(String fieldValues) {
        this.fieldValues = fieldValues;
    }
}
