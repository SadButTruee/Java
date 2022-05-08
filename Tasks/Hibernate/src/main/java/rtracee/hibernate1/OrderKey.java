package rtracee.hibernate1;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class OrderKey implements Serializable {

    static final long serialVersionUID = 1L;

    @Column(name = "perdon_id")
    private Long personId;

    @Column(name = "product_id")
    private Long productId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderKey orderKey = (OrderKey) o;
        return Objects.equals(getPersonId(), orderKey.getPersonId()) &&
                Objects.equals(getProductId(), orderKey.getProductId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPersonId(), getProductId());
    }

    @Override
    public String toString() {
        return "Order Key " + personId + " " + productId;
    }
}
