package com.lnTime.api;

import com.lnTime.domain.ImageEntity;
import com.lnTime.domain.ItemEntity;
import com.lnTime.dto.item.CreateItemDTO;
import com.lnTime.dto.item.ItemDTO;
import com.lnTime.repository.ImageRepository;
import com.lnTime.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/items/")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ImageRepository imageRepository;

    @DeleteMapping("delete/{id}")
    @PreAuthorize(value = "hasAuthority('ROLE_RECTOR')")
    public void remove(@PathVariable Long id) {
        itemService.remove(id);
    }

    @GetMapping("{id}/all-images")
    public List<ImageEntity> getAllImages(@PathVariable Long id, @PageableDefault Pageable pageable) {
        return itemService.getAlImages(id);
    }

    @GetMapping("{id}/images/{imageId}")
    public @ResponseBody ResponseEntity<byte[]> getImageWithMediaType(@PathVariable("id") Long id,
                                                                      @PathVariable("imageId") Long imageId,
                                                                      HttpServletResponse response) throws IOException {
    final byte[] picture = imageRepository.findById(imageId).get().getPicture();
    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(picture);
    }

    @GetMapping("{id}/show")
    public ItemEntity getById(@PathVariable Long id) {
        return itemService.findById(id);
    }

    @GetMapping("{param}/show")
    public Page<ItemDTO> findByTitleOrDescription(@PathVariable String param, @PageableDefault Pageable pageable) {
        return itemService.findByTitleOrDescription(param, pageable);
    }

    @PostMapping("add")
    @PreAuthorize(value = "isAuthenticated()")
    public void save(@RequestBody CreateItemDTO item){
        itemService.save(item);
    }

    @PutMapping("update/{id}")
    @PreAuthorize(value = "hasAuthority('ROLE_RECTOR')")
    public void update(@RequestBody ItemDTO item, @PathVariable Long id){
       itemService.update(item, id);
    }

    @PutMapping(value = "{id}/add-image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize(value = "isAuthenticated()")
    public void saveImage(@PathVariable Long id, MultipartFile image){
        itemService.saveImage(image, id);
    }

    @DeleteMapping("{id}/image-{image_id}/delete")
    @PreAuthorize(value = "isAuthenticated()")
    public void deleteImage(@PathVariable Long id, @PathVariable Long image_id){
       itemService.deleteImage(image_id, id);
    }
}
