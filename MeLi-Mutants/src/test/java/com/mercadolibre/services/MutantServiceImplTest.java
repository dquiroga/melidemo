package com.mercadolibre.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mercadolibre.exceptions.InvalidMatrixException;
import com.mercadolibre.repository.PersonRepository;

@RunWith(SpringRunner.class)
public class MutantServiceImplTest {
	@TestConfiguration
    static class MutantServiceImplTestContextConfiguration {
  
        @Bean
        public MutantService mutantService() {
            return new MutantServiceImpl();
        }
    }
 
    @Autowired
    private MutantService mutantService;
 
    @MockBean
    private PersonRepository personRepository;
    
    @Test
    public void testDnaSuccess() throws InvalidMatrixException {
    		String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
    		boolean rslt = mutantService.isMutant(dna);
    		assertThat(rslt, equalTo(true));
    }
    
    @Test
    public void testDnaNotMutant() throws InvalidMatrixException {
    		String[] dna = {"QWGCGA","CADTGC","TTATGT","GHJKLM","ZXCVBN","POIUYT"};
    		boolean rslt = mutantService.isMutant(dna);
    		assertThat(rslt, not(equalTo(true) ) );
    }
  
    
    @Test(expected = InvalidMatrixException.class)
    public void testDnaException() throws InvalidMatrixException {
    		String[] dna = {"QWGCGA","CADTGC","TTATGT","GHJKLM","ZXVBN","POIUYT"};
    		mutantService.isMutant(dna);
    }
    
}
