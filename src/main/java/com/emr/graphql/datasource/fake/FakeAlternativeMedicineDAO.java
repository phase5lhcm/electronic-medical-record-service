package com.emr.graphql.datasource.fake;

import com.netflix.dgs.codegen.generated.types.Address;
import com.netflix.dgs.codegen.generated.types.AlternativeMedicine;
import com.netflix.dgs.codegen.generated.types.Patient;
import com.netflix.dgs.codegen.generated.types.PrimaryCareDoctor;
import jakarta.annotation.PostConstruct;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class FakeAlternativeMedicineDAO {
    public static final List<AlternativeMedicine> ALTERNATIVE_MEDICINE_LIST = new ArrayList<>();

    @Autowired
    private Faker faker;

    @PostConstruct
    private void postConstruct() throws MalformedURLException {
        for (int i = 0; i < 5; i++) {
            var pcpLocations = new ArrayList<Address>();
            var patientAddress = Address.newBuilder().street(faker.address().streetAddress())
                    .city(faker.address().city())
                    .state(faker.address().stateAbbr())
                    .zipCode(faker.address().zipCode())
                    .build();
            for (int j = 0; j < ThreadLocalRandom.current().nextInt(1, 4); j++) {
                var pcpLocation = Address.newBuilder().street(faker.address().streetAddress())
                        .city(faker.address().cityName()).state(faker.address().stateAbbr())
                        .zipCode(faker.address().zipCode())
                        .build();
                pcpLocations.add(pcpLocation);
            }

                var patientPcp = PrimaryCareDoctor.newBuilder()
                        .name(faker.name().fullName())
                        .locations(pcpLocations)
                        .emailAddress(faker.name().lastName() + "@mail.com")
                        .build();
                var patient = Patient.newBuilder().name(faker.name().fullName())
                        .medicalRecordNumber(faker.random().nextInt(3000))
                        .address(patientAddress)
                        .gender(String.valueOf(faker.gender()))
                        .contactNumber(String.valueOf(faker.phoneNumber().phoneNumber()))
                        .primary(patientPcp)
                        .build();
                var alternativeMedicine = AlternativeMedicine.newBuilder()
                        .service(randomAlternativeMedicineGenerator())
                        .description(faker.lorem().characters(20))
                        .patient(patient)
                        .build();

                ALTERNATIVE_MEDICINE_LIST.add(alternativeMedicine);

            }

    }
    private List<String> randomAlternativeMedicineGenerator(){
        switch (ThreadLocalRandom.current().nextInt(16) % 5){
            case 0:
                return List.of("Chiropraxy");
            case 1:
                return List.of("Accupuncture");
            case 2:
                return List.of("Osteopathy");
            case 3:
                return List.of("Homeopathy");
            default:
                return List.of("Osteopathy", "Chiropraxy");
        }
    }


}
