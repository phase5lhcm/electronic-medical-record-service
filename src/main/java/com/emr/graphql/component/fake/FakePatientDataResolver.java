package com.emr.graphql.component.fake;

import com.emr.graphql.datasource.fake.FakePatientDAO;
import com.netflix.dgs.codegen.generated.types.Patient;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
     * Get patient by name and mrn
     */

//    @DgsComponent
//    public class PatientsByPrimaryCareDoctorDatafetcher {
//        @DgsQuery(field = "patientsByPrimaryCareDoctor")
//                public List<Patient> getPatientsByPrimaryCareDoctor(DataFetchingEnvironment dataFetchingEnvironment) {
//            var patientsToPrimaryCareDoctorMap = (Map<String, Object>) dataFetchingEnvironment.getArgument("patientsByPrimaryCareDoctorInput");
//            var patientsByPrimaryCareDoctorInput = PatientsByPrimaryCareDoctorFilter.newBuilder()
//                    .name((String) patientsToPrimaryCareDoctorMap.get(DgsConstants.PATIENTSBYPRIMARYCAREDOCTORFILTER.Name))
//                    .emailAddress((String) patientsToPrimaryCareDoctorMap.get(DgsConstants.PATIENTSBYPRIMARYCAREDOCTORFILTER.EmailAddress));
//            return
//
//        }
//        }

    // let us add some validation to ensure that our patient input matches what we have stored
//    private boolean matchPatientsToPrimaryCareDoctor(PatientsByPrimaryCareDoctorFilter filter, Patient element){
//        return filter.getName().equals(element.getName())
//                && filter.getEmailAddress(equals(element.g))
//    }

}
