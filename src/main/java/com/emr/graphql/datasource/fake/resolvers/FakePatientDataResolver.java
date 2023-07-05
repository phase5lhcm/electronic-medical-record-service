package com.emr.graphql.datasource.fake.resolvers;

import com.emr.graphql.datasource.fake.FakePatientDAO;
import com.netflix.dgs.codegen.generated.DgsConstants;
import com.netflix.dgs.codegen.generated.types.Patient;
import com.netflix.dgs.codegen.generated.types.PatientsByPrimaryCareDoctorFilter;
import com.netflix.dgs.codegen.generated.types.PrimaryCareDoctor;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
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
     * @param dataFetchingEnvironment = the name and email address for a primary care doctor
     * @return list of patients per primary care doctor
     */
    @DgsQuery(field = "patientsByPrimaryCareDoctorFilter")
            public List<Patient> getPatientsByPrimaryCareDoctorFilter(
            DataFetchingEnvironment dataFetchingEnvironment, @InputArgument PatientsByPrimaryCareDoctorFilter patientsByPrimaryCareDoctorFilter) {
        var patientsToPrimaryCareDoctorMap = (Map<String, Object>) dataFetchingEnvironment.getArgument("patientsByPrimaryCareDoctorFilter");
         patientsByPrimaryCareDoctorFilter = PatientsByPrimaryCareDoctorFilter.newBuilder()
                    .name((String) patientsToPrimaryCareDoctorMap.get(DgsConstants.PATIENTSBYPRIMARYCAREDOCTORFILTER.Name))
                    .emailAddress((String) patientsToPrimaryCareDoctorMap.get(
                            DgsConstants.PATIENTSBYPRIMARYCAREDOCTORFILTER.EmailAddress))
                    .build();

         /** TODO - test this again when I connect to a db so that my input data actually has something to verify against
          * ok! so I think given that I am using fake data that isn't stored anywhere,
         my input data has nothing to reference! Because I am getting a response if I negate my boolean!
         i will try this again when I actually connect to a db but I'm going to move on for now.

          **/
        PatientsByPrimaryCareDoctorFilter finalPatientsByPrimaryCareDoctorFilter = patientsByPrimaryCareDoctorFilter;
        return FakePatientDAO.PATIENT_LIST.stream().filter(
                    p -> !this.matchPatientsToPrimaryCareDoctor(finalPatientsByPrimaryCareDoctorFilter, p.getPrimary())
            ).collect(Collectors.toList());
        }
        // let us add some validation to ensure that our patient input matches what we have stored
        private boolean matchPatientsToPrimaryCareDoctor(PatientsByPrimaryCareDoctorFilter filter, PrimaryCareDoctor element) {
            return filter.getName().equals(element.getName())
                    && filter.getEmailAddress().equals(element.getEmailAddress()); }
    }
