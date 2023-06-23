package com.emr.graphql.component.resolvers;

import com.netflix.dgs.codegen.generated.types.AlternativeMedicine;
import com.netflix.dgs.codegen.generated.types.AlternativeMedicineFilter;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.emr.graphql.datasource.fake.FakeAlternativeMedicineDAO.ALTERNATIVE_MEDICINE_LIST;

@DgsComponent
@Configuration
public class FakeAlternativeMedicineDataResolver {

    @DgsQuery(field = "alternativeMedicine")
            public List<AlternativeMedicine> getAlternativeMedicine(@InputArgument(name = "alternativeMedicineFilter",
    collectionType = AlternativeMedicineFilter.class) Optional<AlternativeMedicineFilter> filter) {
        return filter.map(alternativeMedicineFilter -> ALTERNATIVE_MEDICINE_LIST.stream().filter(
                alternativeMedicine -> this.matchAlternativeMedicineFilter(
                        alternativeMedicineFilter, alternativeMedicine)
        ).collect(Collectors.toList())).orElse(ALTERNATIVE_MEDICINE_LIST);

    }

    private boolean matchAlternativeMedicineFilter(AlternativeMedicineFilter filter, AlternativeMedicine alternativeMedicine){
        if(StringUtils.isNotBlank(filter.getService())
            && alternativeMedicine.getService().contains(
                    filter.getService().toLowerCase())){
                        return false;
        }

        // TODO - add validation for patient MRN as well
        // realistically patients are verified via DOB, name and MRN
        if(filter.getPatient() != null
                && !StringUtils.containsIgnoreCase(filter.getPatient().getName(),
                StringUtils.defaultIfBlank(filter.getPatient().getName(), StringUtils.EMPTY)
        )){return false;}
        return true;
    }
}
