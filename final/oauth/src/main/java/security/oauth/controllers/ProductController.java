package security.oauth.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "product")
public class ProductController {


    //API's if User is logged in,have appropiate rights and have activated account else  show error message



    //Logged in as a seller

    @PostMapping(path = "add-product")
    public String addProduct(){
        return "Product-added";
    }

    @PostMapping(path = "add-product-variation")
    public String addProductVariation(){
        return "hh";
    }

    @GetMapping(path = "view-product")
    public String viewProduct(){
        return " s";
    }


    @GetMapping(path = "view-all-product")
    public String viewAllProduct(){
        return " s";
    }


    @PostMapping(path = "view-products-all-variation")
    public String viewProductAllVariation(){
        return " s";
    }


    @PostMapping(path = "delete-product")
    public String deleteProduct(){
        return " s";
    }


    @PostMapping(path = "update-product")
    public String updateProduct(){
        return " s";
    }

    @PostMapping(path = "update-product-variation")
    public String updateProductVariation(){
        return "ss";
    }



}
