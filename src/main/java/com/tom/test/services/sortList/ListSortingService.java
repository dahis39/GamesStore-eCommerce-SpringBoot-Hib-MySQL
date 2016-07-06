package com.tom.test.services.sortList;

import com.tom.test.domain.AbstartDomainClass;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by tom on 7/5/2016.
 */
@Service
public class ListSortingService {

    public List<AbstartDomainClass> SortListByDateCreated(List<AbstartDomainClass> list){
        Collections.sort(list, new Comparator<AbstartDomainClass>() {
            @Override
            public int compare(AbstartDomainClass o1, AbstartDomainClass o2) {
                if (o1.getDateCreated()==null || o2.getDateCreated()==null)
                    return 0;
                return o2.getDateCreated().compareTo(o1.getDateCreated());    // descending sort.
            }
        });
        return list;
    }
}
