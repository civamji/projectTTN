package security.oauth.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import security.oauth.dtos.CategoryDto;
import security.oauth.dtos.CategoryMetadataFieldDto;
import security.oauth.dtos.CategoryResponceDto;
import security.oauth.entities.Category;
import security.oauth.entities.Product;
import security.oauth.repos.CategoryRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryMetadataFieldService categoryMetadataFieldService;

    @Autowired
    PagingService pagingService;

//to map dtos to entities
    @Autowired
    ModelMapper modelMapper;


    //category to CategoryDto
    private CategoryDto toCategoryDto(Category category) {
        if(category == null)
            return null;
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        CategoryDto parentDto = toCategoryDto(category.getParentCategory());
        categoryDto.setParent(parentDto);
        return categoryDto;
    }

    //category to categoryDto not recursive

    private CategoryDto toCategoryDtoNonRecursive(Category category) {
        if(category == null)
            return null;
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        categoryDto.setParent(null);
        return categoryDto;
    }


    //category to CategoryResponseDto
    private CategoryResponceDto toCategoryResponseDto(Category category) {
        CategoryResponceDto categoryResponceDto=new CategoryResponceDto();


        //get actual category with all parent tree
        CategoryDto categoryDto = toCategoryDto(category);
        categoryResponceDto.setCategoryDto(categoryDto);

        //get child categories

        Set<CategoryDto> subCategories;
        if(category.getSubCategories() != null) {
            subCategories = new HashSet<>();


            category.getSubCategories().forEach((e) -> {
                subCategories.add(toCategoryDtoNonRecursive(e));
            });
            categoryResponceDto.setSubCategories(subCategories);
        }
        // get the possible metadata fields and valucategoryDtoes
        Set<CategoryMetadataFieldDto> fieldValues;
        if(category.getCategoryMetadataFieldValues() != null) {
            fieldValues = new HashSet<>();

            category.getCategoryMetadataFieldValues().forEach((e) -> {
                CategoryMetadataFieldDto dto = categoryMetadataFieldService.toCategoryMetadataFieldDto(e.getCategoryMetadataField());
              //  dto.setValues(StringToSetParser.toSetOfValues(e.getValue()));
                fieldValues.add(dto);
            });
            categoryResponceDto.setFieldValues(fieldValues);
        }
        return categoryResponceDto;
    }




    public String addCategory(String categoryName, Long parentId,HttpServletResponse httpServletResponse) {
        String message=validateNewCategory(categoryName,parentId);
        if(!message.equals("valid")){
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "Not a valid entry";
        }
        Category category = new Category(categoryName);
        Category parent;
        if(parentId==null){
            category.setParentCategory(null);
            categoryRepository.save(category);
        }
        else{
            Optional<Category> parentCategory = categoryRepository.findById(parentId);
            if(!parentCategory.isPresent()){
               httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return "Parent category not found";
            }
            else{
                parent = parentCategory.get();
                category.setParentCategory(parent);
                parent.addSubCategory(category);
                categoryRepository.save(parent);
            }
        }
        httpServletResponse.setStatus(HttpServletResponse.SC_CREATED);
        return "Success";
    }

//        Category category = new Category();
//        category = categoryRepository.findByName(categoryName);
//        if (category != null) {
//            return "This category already exists!!!";
//        }
//
//        category.setName(categoryName);
//        categoryRepository.save(category);
//        return "new category added sucessfully!!";


    public String validateNewCategory(String categoryName, Long parentId) {
        if (parentId == null) {
            Category storedCategory = categoryRepository.findByName(categoryName);
            if (storedCategory == null)
                return "valid";
            return "Category already exist!!!";
        }
        //check unique at root level

        List<Category> storedCategory = categoryRepository.findByParentIdIsNull();
        for (Category category : storedCategory) {
            if (category.getName().equalsIgnoreCase(categoryName))
                return "category already exists at root level";
        }

        Category parent = categoryRepository.findById(parentId).get();


        //check immediate children
        Set<Category> children = parent.getSubCategories();
        for (Category category : children) {
            if (category.getName().equalsIgnoreCase(categoryName))
                return "Sibling category exists with same name";
        }

            // check product associations of parent
            Set<Product> products = parent.getProducts();
            if (!products.isEmpty())
                return "Parent category has product associations.";

            // check path from parent to root
            while (parent != null) {
                if (parent.getName().equalsIgnoreCase(categoryName))
                    return "Category already exists as ancestor.";
                parent = parent.getParentCategory();
            }

            return "valid";
        }




    public ResponseEntity getCategoryDetails(Long categoryId,HttpServletResponse httpServletResponse) {
        Optional<Category> storedCategory =categoryRepository.findById(categoryId);
        if(!storedCategory.isPresent()){
            httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        //add code and change return type
        return (ResponseEntity) httpServletResponse;

        }






    public ResponseEntity<String> getAllCategories(String offset, String size, String sortByField, String order) {

     Pageable pageable = pagingService.pagingOrderwise(offset, size, sortByField, order);

        List<Category> categories = categoryRepository.findAll(pageable);
        List<CategoryResponceDto> categoryDtos = new ArrayList<>();

        categories.forEach((category)->{
            categoryDtos.add(toCategoryResponseDto(category));
        });
        return new ResponseEntity<>("Success!", HttpStatus.OK);
    }





    }



    //Pageable pageable = pagingService.getPageableObject(offset, size, sortByField, order);




//Delete Category:
        //"1. ID should be valid category ID
        //2. Category should exist for that ID
        //3. Category should not be the parent of any other category
        //4. Category should not be associated with any existing (non-deleted) product"

        //Responce:Success message!!












