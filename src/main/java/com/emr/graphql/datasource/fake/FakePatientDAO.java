package com.emr.graphql.datasource.fake;

import com.netflix.dgs.codegen.generated.types.Address;
import com.netflix.dgs.codegen.generated.types.Patient;
import com.netflix.dgs.codegen.generated.types.PrimaryCareDoctor;
import jakarta.annotation.PostConstruct;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class FakePatientDAO {
    private Faker faker;
    @Autowired
    public FakePatientDAO(Faker faker) {this.faker = faker;}

    public static final List<Patient> PATIENT_LIST = new ArrayList<>();

    @PostConstruct
    private void postConstruct() {
        for (int i = 0; i < 5; i++){
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            var pcpLocations =  new ArrayList<Address>();
            var patientAddress = Address.newBuilder().street(faker.address().streetAddress())
                        .city(faker.address().cityName()).state(faker.address().stateAbbr())
                        .zipCode(faker.address().zipCode())
                        .build();
            for(int j = 0; j < ThreadLocalRandom.current().nextInt(1, 2); j++){
                var pcpLocation = Address.newBuilder().street(faker.address().streetAddress())
                        .city(faker.address().cityName()).state(faker.address().stateAbbr())
                        .zipCode(faker.address().zipCode())
                        .build();
                pcpLocations.add(pcpLocation);
            }
            var pcp = PrimaryCareDoctor.newBuilder().name(faker.name().fullName())
                    .locations(pcpLocations).emailAddress(faker.name().lastName() + "@mail.com")
                    .build();
            var patient = Patient.newBuilder().name(faker.name().fullName())
                    .patientID(UUID.randomUUID().toString())
                    .medicalRecordNumber(faker.random().nextInt(2000))
                    .address(patientAddress).gender(faker.gender().binaryTypes())
                    .contactNumber(String.valueOf(faker.phoneNumber().phoneNumber()))
                    .primary(pcp)
                    .DOB(sdf.format(faker.date().birthday()))
                    .dateProfileCreated(LocalDate.now())
                    .build();

            PATIENT_LIST.add(patient);
        }
    }
}
