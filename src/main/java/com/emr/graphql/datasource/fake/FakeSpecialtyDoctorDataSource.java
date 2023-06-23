package com.emr.graphql.datasource.fake;

import com.netflix.dgs.codegen.generated.types.Specialty;
import com.netflix.dgs.codegen.generated.types.SpecialtyDoctor;
import jakarta.annotation.PostConstruct;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
public class FakeSpecialtyDoctorDataSource {
    private Faker faker;
    public static final List<SpecialtyDoctor> SPECIALTY_DOCTOR_LIST = new ArrayList<>();
    public FakeSpecialtyDoctorDataSource(Faker faker) {
        this.faker = faker;
    }

    @PostConstruct
    private void postConstruct() {
        for (int i = 0; i < 4; i++) {
            var specialty = Specialty.newBuilder().specialty(String.valueOf(faker.science().element())).build();
            var specialtyDoctor = SpecialtyDoctor.newBuilder().name(faker.name().fullName())
                    .id(UUID.randomUUID().toString())
                    .specialty(specialty)
                    .contactNumber(String.valueOf(faker.phoneNumber().phoneNumber()))
                    .build();
            SPECIALTY_DOCTOR_LIST.add(specialtyDoctor);
        }
    }
}
