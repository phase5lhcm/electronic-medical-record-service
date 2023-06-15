package com.emr.graphql;

import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.DgsQueryExecutor;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FakePatientDataResolverTest {
    @Autowired
    DgsQueryExecutor dgsQueryExecuter;

    @Test
    void testOnePatient(){
        var graphqlQuery = """
                {
                 onePatient {
                    text
                    medicalNumber
                    }
                }
              
                """;
        String text = dgsQueryExecuter.executeAndExtractJsonPath(graphqlQuery, "data.onePatient.text");
        Integer medicalRecordNumber = dgsQueryExecuter.executeAndExtractJsonPath(
                graphqlQuery, "data.onePatient.medicalNumber");
        assertFalse(StringUtils.isBlank((text)));
        assertNotNull(medicalRecordNumber);
    }

    @Test
    void testAllPatients(){
        var graphqlQuery = """
                {
                 allPatients {
                    text
                    medicalNumber
                    }
                }
                """;
        List<String> texts = dgsQueryExecuter.executeAndExtractJsonPath(
                graphqlQuery, "data.allPatients[*].text");
        List<Integer> medicalNumbers = dgsQueryExecuter.executeAndExtractJsonPath(
                graphqlQuery, "data.allPatients[*].medicalNumber"
        );
        assertNotNull((texts));
        assertFalse(texts.isEmpty());
        assertNotNull(medicalNumbers);
        assertEquals(texts.size(), medicalNumbers.size());
     }
}
