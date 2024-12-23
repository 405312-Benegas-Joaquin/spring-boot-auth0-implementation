package ar.edu.utn.frc.tup.lc.iv.domain.dummy.services;

import ar.edu.utn.frc.tup.lc.iv.domain.dummy.dtos.ResponseDummyDTO;
import ar.edu.utn.frc.tup.lc.iv.domain.dummy.dtos.SaveDummyDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DummyService {
    List<ResponseDummyDTO> getDummyList(String auth0Id);

    ResponseDummyDTO getDummyById(Long id);

    ResponseDummyDTO createDummy(String auth0Id, SaveDummyDTO dummy) throws IllegalArgumentException;

    ResponseDummyDTO updateDummy(Long id, SaveDummyDTO dummy);

    void deleteDummyById(Long id);

    ResponseDummyDTO getDummyBySomething(String something);
}
