package com.lnTime.api;

import com.lnTime.dto.item.ItemDTO;
import com.lnTime.dto.subCategory.CreateSubCategoryDTO;
import com.lnTime.dto.subCategory.SubCategoryDTO;
import com.lnTime.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subCategories/")
public class SubCategoryController {

    @Autowired
    private SubCategoryService subCategoryService;

    @GetMapping("{id}/show")
    SubCategoryDTO findById(@PathVariable Long id){
        return subCategoryService.findById(id);
    }

    @DeleteMapping("{id}/delete")
    @PreAuthorize(value = "hasAuthority('ROLE_RECTOR')")
    void remove(@PathVariable Long id){
        subCategoryService.remove(id);
    }

    @PostMapping("add")
    @PreAuthorize(value = "hasAuthority('ROLE_RECTOR')")
    void save(@RequestBody CreateSubCategoryDTO createSubCategoryDTO){
        subCategoryService.save(createSubCategoryDTO);
    }

    @PutMapping("{id}/update")
    @PreAuthorize(value = "hasAuthority('ROLE_RECTOR')")
    void update(@RequestBody SubCategoryDTO subCategoryDTO, @PathVariable  Long id){
        subCategoryService.update(subCategoryDTO, id);
    }

    @GetMapping("all/show")
    List<SubCategoryDTO> getAll(){
        return subCategoryService.getAll();
    }

    @GetMapping("{subCategoryId}/show/items")
    List<ItemDTO> getItems(@PathVariable Long subCategoryId){
        return subCategoryService.getItems(subCategoryId);
    }

    @GetMapping("{id}/path")
    String getPath(@PathVariable Long id){
        return subCategoryService.getPath(id);
    }
}