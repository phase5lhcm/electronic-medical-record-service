package com.emr.graphql.datasource.fake;

import com.netflix.dgs.codegen.generated.types.Patient;
import jakarta.annotation.PostConstruct;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class FakeEMRDataSource {
    @Autowired
    private Faker faker;

    public static final List<Patient> PATIENT_LIST = new ArrayList<>();

    @PostConstruct
    private void postConstruct() {
        for (int i = 0; i < 5; i++){
            var patient = Patient.newBuilder().medicalNumber(faker.random().nextInt(2000))
                    .text(faker.company().name())
                    .build();

            PATIENT_LIST.add(patient);
        }
    }
}
