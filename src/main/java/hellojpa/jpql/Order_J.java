package hellojpa.jpql;

import javax.persistence.*;

@Entity
public class Order_J {

    @Id @GeneratedValue
    private Long id;
    private int orderAmount;

    @Embedded
    private Address_J address;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product_J product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Address_J getAddress() {
        return address;
    }

    public void setAddress(Address_J address) {
        this.address = address;
    }

    public Product_J getProduct() {
        return product;
    }

    public void setProduct(Product_J product) {
        this.product = product;
    }
}
