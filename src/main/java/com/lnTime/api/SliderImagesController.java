package com.lnTime.api;

import com.lnTime.domain.ImageEntity;
import com.lnTime.domain.ItemEntity;
import com.lnTime.dto.item.CreateItemDTO;
import com.lnTime.dto.item.ItemDTO;
import com.lnTime.repository.ImageRepository;
import com.lnTime.service.ImageService;
import com.lnTime.service.ItemService;
import com.lnTime.service.SliderImagesService;
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
import java.util.Map;

@RestController
@RequestMapping("/api/slider-images/")
@CrossOrigin("*")
public class SliderImagesController {
    @Autowired
    private SliderImagesService sliderImagesService;

    @DeleteMapping("delete/{id}")
    @PreAuthorize(value = "isAuthenticated()")
    public void remove(@PathVariable Integer id) {
        sliderImagesService.delete(id);
    }

    @PostMapping(value = "add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize(value = "isAuthenticated()")
    public void saveImage(MultipartFile image) throws IOException {
        sliderImagesService.save(image.getBytes());
    }

    @GetMapping("{imageId}")
    public @ResponseBody
    ResponseEntity<byte[]> getImageById(
            @PathVariable Integer imageId, HttpServletResponse response
    ) throws IOException {
        final byte[] picture = sliderImagesService.getImageById(imageId);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(picture);
    }

    @GetMapping("allIds")
    public List<Integer> getAllImageIds() {
        return sliderImagesService.getAllIds();
    }


}
