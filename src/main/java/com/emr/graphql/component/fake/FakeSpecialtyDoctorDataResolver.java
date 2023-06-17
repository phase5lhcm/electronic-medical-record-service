package com.emr.graphql.component.fake;

import com.emr.graphql.datasource.fake.FakeSpecialtyDoctorDataSource;
import com.netflix.dgs.codegen.generated.types.SpecialtyDoctor;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@DgsComponent
public class FakeSpecialtyDoctorDataResolver {

    @DgsQuery(field = "specialtyDoctors")
            public List<SpecialtyDoctor> doctorsBySpecialty(@InputArgument String specialty) {
                if(specialty.isEmpty() || StringUtils.isBlank(specialty)) {
                    return FakeSpecialtyDoctorDataSource.SPECIALTY_DOCTOR_LIST;
                }

                // return filtered specialtyDoctors. Identify by specialty
                return FakeSpecialtyDoctorDataSource.SPECIALTY_DOCTOR_LIST.stream()
                        .filter(s -> StringUtils.containsIgnoreCase(
                                // for some reason, an error is being thrown to explicitly cast
                                // the return value for Specialty to a CharSequence. But this makes it impossible for the
                                // user to use a string to search for specialty doctors of a certain type,
                                // hence the reason I am then casting specialty from CharSequnce back to a string
                                (CharSequence) s.getSpecialty().toString(), specialty
                        )).collect(Collectors.toList());
    }
}
