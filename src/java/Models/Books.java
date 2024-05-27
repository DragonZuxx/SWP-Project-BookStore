/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.Date;

/**
 *
 * @author Aplal
 */
public class Books {

    private int id;
    private String title, publisher, publicationdate, isbn, price;
    private int stock;
    private String description, coverimage;
    private Date createdat, updateat;

    public Books() {
    }

    public Books(int id, String title, String publisher, String publicationdate, String isbn, String price, int stock, String description, String coverimage, Date createdat, Date updateat) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.publicationdate = publicationdate;
        this.isbn = isbn;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.coverimage = coverimage;
        this.createdat = createdat;
        this.updateat = updateat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublicationdate() {
        return publicationdate;
    }

    public void setPublicationdate(String publicationdate) {
        this.publicationdate = publicationdate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverimage() {
        return coverimage;
    }

    public void setCoverimage(String coverimage) {
        this.coverimage = coverimage;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    public Date getUpdateat() {
        return updateat;
    }

    public void setUpdateat(Date updateat) {
        this.updateat = updateat;
    }

    @Override
    public String toString() {
        return "Books{" + "id=" + id + ", title=" + title + ", publisher=" + publisher + ", publicationdate=" + publicationdate + ", isbn=" + isbn + ", price=" + price + ", stock=" + stock + ", description=" + description + ", coverimage=" + coverimage + ", createdat=" + createdat + ", updateat=" + updateat + '}';
    }

}
