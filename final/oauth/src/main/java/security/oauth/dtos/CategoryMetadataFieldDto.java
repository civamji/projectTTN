package security.oauth.dtos;

import java.util.Set;


public class CategoryMetadataFieldDto {
    private Long id;
    private String name;
    private Set<String> values;

    public CategoryMetadataFieldDto() {
    }

    public CategoryMetadataFieldDto(Long id, String name, Set<String> values) {
        this.id = id;
        this.name = name;
        this.values = values;
    }

    @Override
    public String toString() {
        return "CategoryMetadataFieldDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", values=" + values +
                '}';
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

    public Set<String> getValues() {
        return values;
    }

    public void setValues(Set<String> values) {
        this.values = values;
    }
}
