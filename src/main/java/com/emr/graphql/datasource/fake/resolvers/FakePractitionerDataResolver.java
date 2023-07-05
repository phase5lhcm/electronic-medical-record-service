package com.emr.graphql.datasource.fake.resolvers;

import com.emr.graphql.datasource.fake.FakePractitionerDAO;
import com.netflix.dgs.codegen.generated.types.Practitioner;
import com.netflix.dgs.codegen.generated.types.PractitionerFilter;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import graphql.schema.DataFetchingEnvironment;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@DgsComponent
public class FakePractitionerDataResolver {
    @DgsQuery(field = "practitioners")
            public List<Practitioner> getPractitioners(@InputArgument PractitionerFilter practitionerFilter) {
        if(practitionerFilter == null){
            System.out.println("filter is empty");
            return FakePractitionerDAO.PRACTITIONER_LIST;
        }
        System.out.println("filter is full " + practitionerFilter);

        return FakePractitionerDAO.PRACTITIONER_LIST.stream().filter(
                practitioner -> this.matchPractitionerFilter(practitionerFilter, practitioner)
        ).collect(Collectors.toList());
    }
    private boolean matchPractitionerFilter(PractitionerFilter filter, Practitioner practitioner) {
        if (filter.getPractitionerSpecialtyType().equalsIgnoreCase(practitioner.getPractitionerSpecialtyType())) {
            return true;
        }
        return false;
    }

}
