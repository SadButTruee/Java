package rtracee.hibernate1;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "person_product")
@NoArgsConstructor
public class Order {

    @EmbeddedId
    private OrderKey orderKey;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", insertable = false, updatable = false)
    private Person person;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @Column(name = "price")
    private Double price;

    @Override
    public String toString() {
        return "Order " + orderKey + " " + price;
    }
}
