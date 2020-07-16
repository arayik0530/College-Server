package com.lnTime.api;

import com.lnTime.dto.category.CategoryDTO;
import com.lnTime.dto.category.CreateCategoryDTO;
import com.lnTime.dto.subCategory.SubCategoryDTO;
import com.lnTime.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories/")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("{id}/show")
    CategoryDTO findById(@PathVariable Long id){
        return categoryService.findById(id);
    }

    @DeleteMapping("{id}/delete")
    @PreAuthorize(value = "hasAuthority('ROLE_RECTOR')")
    void remove(@PathVariable Long id){
        categoryService.remove(id);
    }

    @PostMapping("add")
    @PreAuthorize(value = "hasAuthority('ROLE_RECTOR')")
    void save(@RequestBody CreateCategoryDTO createCategoryDTO){
       categoryService.save(createCategoryDTO);
    }

    @PutMapping("{id}/update")
    @PreAuthorize(value = "hasAuthority('ROLE_RECTOR')")
    void update(@RequestBody CategoryDTO categoryDTO, @PathVariable  Long id){
        categoryService.update(categoryDTO, id);
    }

    @GetMapping("all/show")
    List<CategoryDTO> getAll(){
        return categoryService.getAll();
    }

    @GetMapping("{categoryId}/show/subCategories")
    List<SubCategoryDTO> getSubCategories(@PathVariable Long categoryId){
        return categoryService.getSubCategories(categoryId);
    }

    @GetMapping("{id}/path")
    String getPath(@PathVariable Long id){
        return categoryService.getPath(id);
    }
}
