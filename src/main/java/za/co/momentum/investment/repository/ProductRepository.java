package za.co.momentum.investment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.momentum.investment.model.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
