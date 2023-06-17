package com.emr.graphql.component.fake;

import com.emr.graphql.datasource.fake.FakeEMRDataSource;
import com.emr.graphql.datasource.fake.FakePrimaryCareDoctorDAO;
import com.netflix.dgs.codegen.generated.types.Patient;
import com.netflix.dgs.codegen.generated.types.PrimaryCareDoctor;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@DgsComponent
public class FakePrimaryCareDoctorResourceResolver {
    @DgsQuery
    public List<PrimaryCareDoctor> allPrimaryCareDoctors() {
        return FakePrimaryCareDoctorDAO.PRIMARY_CARE_DOCTOR_LIST;
    }
    @DgsQuery
    public PrimaryCareDoctor onePrimaryCareDoctor() {
        return FakePrimaryCareDoctorDAO.PRIMARY_CARE_DOCTOR_LIST.get(
                ThreadLocalRandom.current().nextInt(FakePrimaryCareDoctorDAO.PRIMARY_CARE_DOCTOR_LIST.size())
        );
    }
}
