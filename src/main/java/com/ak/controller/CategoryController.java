package com.ak.controller;

import com.ak.dto.CategoryDto;
import com.ak.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(
        name = "CRUD REST APIS for Category Resource",
        description = "CRUD REST API for Category Resource -create Category +" +
                "Update Category, Get Category, Delete Category"
)
@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class CategoryController {

    public static final String CATEGORY_DELETED_SUCCESSFULLY = "Category deleted Successfully with id : ";
    private CategoryService categoryService;

    //    Build create Category REST API
    @Operation(
            summary = "CREATE Category REST API",
            description = "create Category REST API to save Category into Database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http status code 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto category = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    //    Build Get category by id REST API
    @Operation(
            summary = "Get Category REST API",
            description = "fetch details of  Category REST API from Database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status code 200 OK"
    )
    @GetMapping("{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Long categoryId) {
        CategoryDto categoryById = this.categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(categoryById, HttpStatus.OK);
    }

    //    Build Get AllCategories REST API
    @Operation(
            summary = "Get ALL Category REST API",
            description = "fetch details of  All Categories REST API from Database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status code 200 OK"
    )
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> allCategories = this.categoryService.getAllCategories();
        return new ResponseEntity<>(allCategories, HttpStatus.OK);
    }

    //    Build updateCategory REST API
    @Operation(
            summary = "Update Category REST API",
            description = "Update details of Category REST API in Database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status code 200 OK"
    )
    @PutMapping("{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable("id") Long categoryId,
                                                      @RequestBody CategoryDto categoryDto) {
        CategoryDto updateCategory = this.categoryService.updateCategory(categoryId, categoryDto);
        return new ResponseEntity<>(updateCategory, HttpStatus.OK);

    }

    @Operation(
            summary = "Delete Category REST API",
            description = "Delete Category REST API from Database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status code 200 OK"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryId){
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(CATEGORY_DELETED_SUCCESSFULLY+" "+categoryId,HttpStatus.OK);
    }


}
