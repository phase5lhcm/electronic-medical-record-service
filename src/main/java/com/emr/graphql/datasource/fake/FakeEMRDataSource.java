package com.emr.graphql.datasource.fake;

import com.netflix.dgs.codegen.generated.types.Address;
import com.netflix.dgs.codegen.generated.types.Patient;
import com.netflix.dgs.codegen.generated.types.PrimaryCareDoctor;
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
            var address = Address.newBuilder().street(faker.address().streetAddress())
                        .city(faker.address().cityName()).state(faker.address().stateAbbr())
                        .zipCode(faker.address().zipCode())
                        .build();
            var patient = Patient.newBuilder().name(faker.name().fullName())
                    .medicalRecordNumber(faker.random().nextInt(2000))
                    .address(address).gender(faker.gender().binaryTypes())
                    .contactNumber(String.valueOf(faker.phoneNumber().phoneNumber()))
                    .build();

            PATIENT_LIST.add(patient);
        }
    }
}
