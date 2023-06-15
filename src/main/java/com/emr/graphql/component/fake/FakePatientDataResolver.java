package com.emr.graphql.component.fake;

import com.emr.graphql.datasource.fake.FakeEMRDataSource;
import com.netflix.dgs.codegen.generated.types.Patient;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@DgsComponent
public class FakePatientDataResolver {
    @DgsQuery
    public List<Patient> allPatients() {
        return FakeEMRDataSource.PATIENT_LIST;

    }
    @DgsQuery
    public Patient onePatient() {
        return FakeEMRDataSource.PATIENT_LIST.get(
                ThreadLocalRandom.current().nextInt(FakeEMRDataSource.PATIENT_LIST.size())
        );
    }
}
