package com.emr.graphql.component.resolvers;

import com.netflix.dgs.codegen.generated.types.AlternativeMedicine;
import com.netflix.dgs.codegen.generated.types.AlternativeMedicineFilter;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.emr.graphql.datasource.fake.FakeAlternativeMedicineDAO.ALTERNATIVE_MEDICINE_LIST;

@Configuration
public class FakeAlternativeMedicineDataResolver {

    @DgsQuery(field = "alternativeMedicine")
            public List<AlternativeMedicine> getAlternativeMedicine(@InputArgument(name = "alternativeMedicineFilter",
    collectionType = AlternativeMedicineFilter.class) Optional<AlternativeMedicineFilter> filter) {
        if(filter.isEmpty()){
            return ALTERNATIVE_MEDICINE_LIST;
        }

       return ALTERNATIVE_MEDICINE_LIST.stream().filter(
               alternativeMedicine -> this.matchAlternativeMedicineFilter(
                       filter.get(), alternativeMedicine)
       ).collect(Collectors.toList());
    }

    private boolean matchAlternativeMedicineFilter(AlternativeMedicineFilter filter, AlternativeMedicine alternativeMedicine){
        if(StringUtils.isNotBlank(filter.getService().toString())
            && alternativeMedicine.getService().contains(
                    filter.getService().toString().toLowerCase())){
                        return false;
        }

        if(filter.getPatient() != null
                && !StringUtils.containsIgnoreCase(filter.getPatient().getName(),
                StringUtils.defaultIfBlank(filter.getPatient().getName(), StringUtils.EMPTY)
        )){return false;}
        return true;
    }
}
