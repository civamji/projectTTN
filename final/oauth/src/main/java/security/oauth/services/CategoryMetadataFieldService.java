package security.oauth.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import security.oauth.dtos.CategoryMetadataFieldDto;
import security.oauth.entities.Category;
import security.oauth.entities.CategoryMetadataField;
import security.oauth.entities.CategoryMetadataFieldValues;
import security.oauth.repos.CategoryMetadataFieldRepository;
import security.oauth.repos.CategoryMetadataFieldValuesRepostiory;
import security.oauth.repos.CategoryRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.*;


@Service
public class CategoryMetadataFieldService {
    @Autowired
    CategoryMetadataFieldRepository categoryMetadataFieldRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PagingService pagingService;




//
//    @Autowired
//    CategoryMetadataFieldValuesRepostiory categoryMetadataFieldValuesRepostiory;




    //categoryMetadataFieldDto to category metadataField
    public CategoryMetadataField toCategoryMetadataField(CategoryMetadataFieldDto fieldDto){
        if(fieldDto == null)
            return null;
        return modelMapper.map(fieldDto, CategoryMetadataField.class);
    }


    //categorymetadata field to DTO
    public CategoryMetadataFieldDto toCategoryMetadataFieldDto(CategoryMetadataField field){
        if(field == null)
            return null;
        return modelMapper.map(field, CategoryMetadataFieldDto.class);
    }





    public String addNewMetaDataField(String fieldName) {
        CategoryMetadataField OldCategoryfield=categoryMetadataFieldRepository.findByName(fieldName);
        if(OldCategoryfield!=null){
            return "Invalid operation field name alreay exists";
        }
        OldCategoryfield=new CategoryMetadataField();
        OldCategoryfield.setName(fieldName);
        categoryMetadataFieldRepository.save(OldCategoryfield);
        return "field name added sucessfully!!";
    }


    public ResponseEntity<String> getAllMetadataFieldsByCategoryId(String offset, String size, String sortByField, String order, Long categoryId) {
        Optional<Category> savedCategory = categoryRepository.findById(categoryId);

        Pageable pageable = pagingService.pagingOrderwise(offset, size, sortByField, order);

        if(!savedCategory.isPresent()){
           /// response = new ErrorVO("Not found", "No category exists with this id.", new Date());
            return new ResponseEntity<>("Not found\", \"No category exists with this id", HttpStatus.NOT_FOUND);
        }


        Category category = savedCategory.get();
        Set<CategoryMetadataFieldValues> fieldValues = category.getCategoryMetadataFieldValues();

        if(fieldValues == null || fieldValues.isEmpty()){

           // String responce=HttpServletResponse.SC_SERVICE_UNAVAILABLE;
         //   response = new ResponseVO<Set>(null, "No metadata fields are associated with this category.", new Date());
            return new ResponseEntity<>("No metadata fields are associated with this category.",HttpStatus.NOT_FOUND );
        }

        List<CategoryMetadataFieldDto> fields = new ArrayList<>();

        fieldValues.forEach((e)->{
            CategoryMetadataFieldDto dto = toCategoryMetadataFieldDto(e.getCategoryMetadataField());
            dto.setValues(null);
            fields.add(dto);
        });
            return new ResponseEntity<>("response", HttpStatus.OK);
    }

    public List<CategoryMetadataField> getAllFields() {
        return (List<CategoryMetadataField>) categoryMetadataFieldRepository.findAll();
    }



}
