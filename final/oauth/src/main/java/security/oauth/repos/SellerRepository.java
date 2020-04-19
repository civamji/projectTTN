package security.oauth.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import security.oauth.entities.Seller;

import java.util.List;

public interface SellerRepository extends CrudRepository<Seller,Long> {

    Page<Seller> findAll(Pageable pageable);
    Seller findByCompanyName(String companyName);
    List<Seller> findByGst(String gst);
    Seller findByEmail(String email);

}
