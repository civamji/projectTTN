package security.oauth.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class PagingService {
    public Pageable pagingOrderwise(String offset, String size, String sortByField, String order) {
        Integer pageNo = Integer.parseInt(offset);
        Integer pageSize = Integer.parseInt(size);

        Pageable pageable;
        if(order.equals("ascending"))
            pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortByField).ascending());
        else
            pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortByField).descending());

        return pageable;
    }

}
