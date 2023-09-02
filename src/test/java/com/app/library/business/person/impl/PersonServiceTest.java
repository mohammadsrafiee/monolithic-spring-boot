package com.app.library.business.person.impl;

import com.app.library.business.person.IPersonMapper;
import com.app.library.business.person.IPersonRepository;
import com.app.library.common.search.SearchModel;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class PersonServiceTest {

    @InjectMocks
    private PersonService personService;
    @Mock
    private IPersonRepository personRepository;
    @Mock
    private IPersonMapper personMapper;

    @Test
    public void testSaveOrUpdate() {
        PersonModel person = new PersonModel();
        person.setId(1L);
        person.setName("Mohammad");
        when(personRepository.save(any())).thenReturn(new PersonEntity());
        when(personMapper.toEntity(any())).thenReturn(new PersonEntity());
        when(personMapper.toModel(any())).thenReturn(person);
        PersonModel result = personService.saveOrUpdate(person);
        assertNotNull(result);
        assertEquals(1L, result.getId().longValue());
        assertEquals("Mohammad", result.getName());
    }

    @Test
    public void testGetById() {
        when(personRepository.getReferenceById(1L)).thenReturn(new PersonEntity());
        when(personMapper.toModel(any())).thenReturn(new PersonModel());
        PersonModel result = personService.getById(1L);
        assertNotNull(result);
    }

    @Test
    public void testDelete() {
        personService.delete(1L);
        verify(personRepository).deleteById(1L);
    }

    @Test
    public void testGetAll() {
        SearchModel<PersonEntity> searchModel = new SearchModel<>();
        Page<PersonEntity> mockPage = mock(Page.class);
        when(personRepository.findAll(any(), any(PageRequest.class))).thenReturn(mockPage);
        when(personMapper.toModel(any())).thenReturn(new PersonModel());
        List<PersonModel> result = personService.getAll(searchModel);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}