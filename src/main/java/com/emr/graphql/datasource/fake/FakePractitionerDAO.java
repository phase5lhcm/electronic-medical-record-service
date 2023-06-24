package com.emr.graphql.datasource.fake;

import com.netflix.dgs.codegen.generated.types.*;
import jakarta.annotation.PostConstruct;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class FakePractitionerDAO {

    public static final List<Practitioner> PRACTITIONER_LIST = new ArrayList<>();

    @Autowired
    private Faker faker;

    @PostConstruct
    private void postConstruct() throws MalformedURLException {
        for (int i = 0; i < 4; i++){
            var address = Address.newBuilder()
                    .street(faker.address().streetAddress())
                    .city(faker.address().city())
                    .state(faker.address().stateAbbr())
                    .zipCode(faker.address().zipCode())
                    .build();
            Practitioner practitioner = switch (1 % 3){
                case 0:
                    yield NursePractitioner.newBuilder()
                            .practitionerName(faker.name().fullName())
                            .practitionerSpecialtyType(PractitionerSpecialtyType.CARDIOLOGY)
                            .practitionerEmailAddress(faker.internet().emailAddress())
                            .practitionerPhoneNumber(String.valueOf(faker.phoneNumber()))
                            .practiceLocation(address)
                            .practiceWebsite(new URL("https://" + faker.internet().url()))
                            .degreeType(randomDegreeGenerator())
                            .build();
                case 1:
                    yield PhysicianAssistant.newBuilder()
                            .practitionerName(faker.name().fullName())
                            .practitionerSpecialtyType(PractitionerSpecialtyType.HOMEOPATHY)
                            .practitionerEmailAddress(faker.internet().emailAddress())
                            .practitionerPhoneNumber(String.valueOf(faker.phoneNumber()))
                            .practiceLocation(address)
                            .practiceWebsite(new URL("https://" + faker.internet().url()))
                            .university(faker.educator().university())
                            .certification("Homeopathic Services")
                            .build();
                default:
                    yield Attending.newBuilder()
                            .practitionerName(faker.name().fullName())
                            .practitionerSpecialtyType(PractitionerSpecialtyType.ONCOLOGY)
                            .practitionerEmailAddress(faker.internet().emailAddress())
                            .practitionerPhoneNumber(String.valueOf(faker.phoneNumber()))
                            .practiceLocation(address)
                            .practiceWebsite(new URL("https://" + faker.internet().url()))
                            .university(faker.educator().university())
                            .build();
            };
            PRACTITIONER_LIST.add(practitioner);
        }
    }

    private String randomDegreeGenerator() {
        switch (ThreadLocalRandom.current().nextInt(6) % 3) {
            case 0:
                return new String("BSN");
            case 1:
                return new String("RN");
            default:
                return new String("MSN");
        }
    }

    // TODO - use enum values to generate random enums for each type of practitioner
    private String randomSpecialtyGenerator(){
        switch (ThreadLocalRandom.current().nextInt(14) % 2){
            case 0:
                return String.valueOf(PractitionerSpecialtyType.ONCOLOGY)
            case 1:
                return new String("RN");
            default:
                return new String("MSN");
        }
    }}
