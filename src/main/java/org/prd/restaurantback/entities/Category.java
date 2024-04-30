package org.prd.restaurantback.entities;

import jakarta.persistence.*;
import org.prd.restaurantback.dtos.CategoryDto;

@Entity
@Table (name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Lob
    @Column(columnDefinition = "longblob") //columDefinition = "longblob" es para que se guarde en la base de datos como un blob
    private byte[] img;


    public Category(Long id, String name, String description, byte[] img) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.img = img;
    }

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public CategoryDto getCategoryDto() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(this.id);
        categoryDto.setName(this.name);
        categoryDto.setDescription(this.description);
        categoryDto.setReturnedImg(this.img);
        return categoryDto;
    }
}
