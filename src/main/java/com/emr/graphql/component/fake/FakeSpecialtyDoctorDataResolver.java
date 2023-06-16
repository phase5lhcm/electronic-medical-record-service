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
                                (CharSequence) s.getSpecialty(), specialty
                        )).collect(Collectors.toList());
    }
}
