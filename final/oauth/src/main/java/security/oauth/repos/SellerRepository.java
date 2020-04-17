package security.oauth.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import security.oauth.entities.Seller;

public interface SellerRepository extends CrudRepository<Seller,Long> {

    Page<Seller> findAll(Pageable pageable);
    Seller findByGst(String gst);
}
