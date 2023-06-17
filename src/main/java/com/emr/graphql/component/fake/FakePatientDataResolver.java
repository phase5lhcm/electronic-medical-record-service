package com.emr.graphql.component.fake;

import com.emr.graphql.datasource.fake.FakePatientDAO;
import com.netflix.dgs.codegen.generated.DgsConstants;
import com.netflix.dgs.codegen.generated.types.Patient;
import com.netflix.dgs.codegen.generated.types.PatientsByPrimaryCareDoctorFilter;
import com.netflix.dgs.codegen.generated.types.PrimaryCareDoctor;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsQuery;
import graphql.schema.DataFetchingEnvironment;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@DgsComponent
public class FakePatientDataResolver {
    @DgsQuery
    public List<Patient> allPatients() {
        return FakePatientDAO.PATIENT_LIST;
    }
    @DgsQuery
    public Patient onePatient() {
        return FakePatientDAO.PATIENT_LIST.get(
                ThreadLocalRandom.current().nextInt(FakePatientDAO.PATIENT_LIST.size())
        );
    }

    /**
     * Get list of patients by PCP
     */

    @DgsComponent
    public class PatientsByPrimaryCareDoctorDatafetcher {
        @DgsQuery(field = "patientsByPrimaryCareDoctor")
                public List<Patient> getPatientsByPrimaryCareDoctor(
                DataFetchingEnvironment dataFetchingEnvironment) {
            var patientsToPrimaryCareDoctorMap = (Map<String, Object>) dataFetchingEnvironment.getArgument("patientsByPrimaryCareDoctorInput");
            var patientsByPrimaryCareDoctorInput = PatientsByPrimaryCareDoctorFilter.newBuilder()
                    .name((String) patientsToPrimaryCareDoctorMap.get(DgsConstants.PATIENTSBYPRIMARYCAREDOCTORFILTER.Name))
                    .emailAddress((String) patientsToPrimaryCareDoctorMap.get(
                            DgsConstants.PATIENTSBYPRIMARYCAREDOCTORFILTER.EmailAddress))
                    .build();

            return FakePatientDAO.PATIENT_LIST.stream().filter(
                    p -> this.matchPatientsToPrimaryCareDoctor(patientsByPrimaryCareDoctorInput, p.getPrimary())
            ).collect(Collectors.toList());
        }
        // let us add some validation to ensure that our patient input matches what we have stored
        private boolean matchPatientsToPrimaryCareDoctor(PatientsByPrimaryCareDoctorFilter filter, PrimaryCareDoctor element)
        {
            return filter.getName().equals(element.getName())
                    && filter.getEmailAddress().equals(element.getEmailAddress());
        }

    }
}
