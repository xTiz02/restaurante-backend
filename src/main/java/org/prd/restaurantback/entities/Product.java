package org.prd.restaurantback.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.prd.restaurantback.dtos.ProductDto;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String price;
    private String description;
    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)//muchos productos pueden tener una categoria
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)//si se elimina una categoria se eliminan todos los productos que tengan esa categoria
    @JsonIgnore
    private Category category;
    //opcional = false significa que la categoria es requerida para crear un producto
    //nullable = false significa que la categoria no puede ser nula para crear un producto
    public Product() {

    }

    public Product(Long id, String name, String price, String description, byte[] img, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.img = img;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public ProductDto getProductDto() {
        ProductDto productDto = new ProductDto();
        productDto.setId(this.id);
        productDto.setName(this.name);
        productDto.setPrice(this.price);
        productDto.setDescription(this.description);
        productDto.setReturnedImg(this.img);
        productDto.setCategoryId(this.category.getId());
        productDto.setCategoryName(this.category.getName());
        return productDto;
    }
}
