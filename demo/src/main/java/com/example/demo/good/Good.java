package com.example.demo.good;

import com.example.demo.category.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Good")
@Table(
        name = "good",
        uniqueConstraints = {
                @UniqueConstraint(name="good_name_unique", columnNames = "good_name")
        }
)
public class Good {
    @Id
    @SequenceGenerator(
            name = "good_sequence",
            sequenceName = "good_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "good_sequence"
    )
    @Column(
            name = "good_id"
    )
    @ApiModelProperty(notes = "The unique id of the good", readOnly = true)
    private long goodId;

    @Column(
            name = "good_name"
    )
    @ApiModelProperty(notes = "The name of the good")
    private String goodName;

    @ManyToOne
    @JoinColumn(
            name = "category_id",
            columnDefinition = "integer default 0"
    )
    private Category category;

    @Column(name = "good_price")
    @ApiModelProperty(notes = "The price of the good")
    private double goodPrice;

    public Good() {
    }

    public Good(String goodName, double goodPrice){
        this.goodName = goodName;
        this.goodPrice = goodPrice;
    }

    public Good(String goodName, Category category, double goodPrice){
        this.goodName = goodName;
        this.category = category;
        this.goodPrice = goodPrice;
    }

    public long getGoodId() {
        return goodId;
    }

    public void setGoodId(int good_id) {
        this.goodId = good_id;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String good_name) {
        this.goodName = good_name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(double price) {
        this.goodPrice = price;
    }

    @Override
    public String toString() {
        return "Good{" +
                "goodId=" + goodId +
                ", goodName='" + goodName + '\'' +
                ", goodPrice=" + goodPrice +
                '}';
    }
}
