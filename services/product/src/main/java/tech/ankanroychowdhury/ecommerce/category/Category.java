package tech.ankanroychowdhury.ecommerce.category;

import jakarta.persistence.*;
import lombok.*;
import tech.ankanroychowdhury.ecommerce.product.Product;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Product> products;
}
