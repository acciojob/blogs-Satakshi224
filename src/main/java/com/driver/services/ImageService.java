package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog= blogRepository2.findById(blogId).get();
        Image image=new Image();
        image.setDescription(description);
        image.setDimension(dimensions);
        blog.getImageList().add(image);
        blogRepository2.save(blog);
        return image;


    }

    public void deleteImage(Integer id){
      imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image=imageRepository2.findById(id).get();
        int count=0;
        String dimensions=image.getDimension();
        String dim[]=dimensions.split("X");
        int lengthd=Integer.valueOf(dim[0]);
        int widthd=Integer.valueOf(dim[1]);
        String screen[]=screenDimensions.split("X");
        int lengths=Integer.valueOf(screen[0]);
        int widths=Integer.valueOf(screen[1]);
        count=(lengths/lengthd)*(widths/widthd);
        return count;
    }
}
