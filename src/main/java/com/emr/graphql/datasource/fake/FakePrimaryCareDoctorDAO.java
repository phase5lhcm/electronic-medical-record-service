package com.emr.graphql.datasource.fake;

import com.emr.graphql.component.fake.FakePatientDataResolver;
import com.netflix.dgs.codegen.generated.types.Address;
import com.netflix.dgs.codegen.generated.types.Patient;
import com.netflix.dgs.codegen.generated.types.PrimaryCareDoctor;
import jakarta.annotation.PostConstruct;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


@Configuration
public class FakePrimaryCareDoctorDAO {

    private Faker faker;
    @Autowired
    public FakePrimaryCareDoctorDAO(Faker faker) {this.faker = faker;}

    public static final List<PrimaryCareDoctor> PRIMARY_CARE_DOCTOR_LIST = new ArrayList<>();
    @PostConstruct
    private void PostConstruct(){
        for(int i = 0; i < 5; i++){
            var addresses = new ArrayList<Address>();
            for(int j = 0; j < ThreadLocalRandom.current().nextInt(1, 3); j++){
                var address = Address.newBuilder().street(faker.address().streetAddress())
                        .city(faker.address().cityName()).state(faker.address().stateAbbr())
                        .zipCode(faker.address().zipCode())
                        .build();
                addresses.add(address);
            }
            // TODO - come up with a better method for generating an email address
            var primaryCareDoctor = PrimaryCareDoctor.newBuilder().name(faker.name().fullName())
                    .locations(addresses).emailAddress(faker.name().lastName() + "@mail.com").build();

            PRIMARY_CARE_DOCTOR_LIST.add(primaryCareDoctor);
        }
    }





}
