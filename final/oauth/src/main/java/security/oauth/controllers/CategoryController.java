package security.oauth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import security.oauth.entities.CategoryMetadataField;
import security.oauth.services.CategoryMetadataFieldService;
import security.oauth.services.CategoryService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping(path = "/category")
@RestController
public class CategoryController {

//    @Autowired
//    CategoryMetadataField categoryMetadataField;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryMetadataFieldService categoryMetadataFieldService;

    //As a logged in admin user, I should be able to create
    // and update product categories along with
    // their metadata properties, to be further used while creating products.

    //1 add a metadata field

//Done:) success message with created field-id?
    @PostMapping("/admin/add-metadata-field")
    public String addMetaDataField(@RequestParam String fieldName){
        return categoryMetadataFieldService.addNewMetaDataField(fieldName);
    }


    //2 view all metadata fields

    //Done:)

@GetMapping("admin/view-all-field")
    public List<CategoryMetadataField> viewAllFields()
{
    return categoryMetadataFieldService.getAllFields();
}

    //3 add categorty

//    "1. Category name should be unique at root level and along breadth/depth in a tree
//            2. Parent Category ID if passed, should not be associated with any existing (non-deleted) product"

//Done:)

    @PutMapping("/admin/add-new-category")
    public String addNewCategory(@RequestParam String categoryName, @RequestParam(required = false) Long parentId, HttpServletResponse httpServletResponse){
        return categoryService.addCategory(categoryName,parentId,httpServletResponse);
    }




    //4 view a category// responce return with category details
    //id should be valid
    //Responce:Category details with parent categories upto root level and immediate
    // children categories, and associated fields along with possible values

    //make dto and improve service
@GetMapping("admin/get-category-detail/{id}")
    public ResponseEntity getCategoryDetail(@PathVariable(name = "id") Long categoryId,HttpServletResponse httpServletResponse){
        return categoryService.getCategoryDetails(categoryId,httpServletResponse);
}


    //5 view all category
    @GetMapping("/view -all-categories")
    public ResponseEntity<String> getAllCategories(@RequestParam(defaultValue = "0") String offset,
                                                   @RequestParam(defaultValue = "10") String size,
                                                   @RequestParam(defaultValue = "id") String sortByField,
                                                   @RequestParam(defaultValue = "ascending") String order) {

        return categoryService.getAllCategories(offset, size, sortByField, order);
    }




    //6 delete category

    //"1. ID should be valid category ID
    //2. Category should exist for that ID
    //3. Category should not be the parent of any other category
    //4. Category should not be associated with any existing (non-deleted) product"

    //Responce:Success message!!

    //7 update category

    //8 add new category metadata for a category

    //9 update values for category metadata field



    //As as seller

    //1. List of all categories


    //as a customer

    //1.list of all category

    //2.fetch filter detail for a category

}
