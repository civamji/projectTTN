package security.oauth.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


//Composite primary key class

//The composite primary key class must be public
//It must have a no-arg constructor
//It must define equals() and hashCode() methods
//It must be Serializable


@Embeddable
public class CategoryMetadataFieldValuesId implements Serializable {
   @Column(name = "categoryId")
   private Long cid;

   @Column(name = "categoryMetadataId")
   private Long mid;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryMetadataFieldValuesId)) return false;
        CategoryMetadataFieldValuesId that = (CategoryMetadataFieldValuesId) o;
        return Objects.equals(cid, that.cid) &&
                Objects.equals(mid, that.mid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, mid);
    }

    //no arg Constructor
    public CategoryMetadataFieldValuesId() {
    }



}
