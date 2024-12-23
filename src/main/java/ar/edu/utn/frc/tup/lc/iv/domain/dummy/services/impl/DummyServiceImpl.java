package ar.edu.utn.frc.tup.lc.iv.domain.dummy.services.impl;

import ar.edu.utn.frc.tup.lc.iv.domain.dummy.dtos.ResponseDummyDTO;
import ar.edu.utn.frc.tup.lc.iv.domain.dummy.dtos.SaveDummyDTO;
import ar.edu.utn.frc.tup.lc.iv.domain.dummy.entities.DummyEntity;
import ar.edu.utn.frc.tup.lc.iv.domain.dummy.repositories.jpa.DummyJpaRepository;
import ar.edu.utn.frc.tup.lc.iv.domain.dummy.services.DummyService;
import ar.edu.utn.frc.tup.lc.iv.domain.user.entities.UserEntity;
import ar.edu.utn.frc.tup.lc.iv.domain.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DummyServiceImpl implements DummyService {

    private final DummyJpaRepository dummyJpaRepository;
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<ResponseDummyDTO> getDummyList(String auth0Id) {
        List<DummyEntity> dummyEntities = dummyJpaRepository.findByUser_Auth0Id(auth0Id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<ResponseDummyDTO> dummyList = new ArrayList<>();

        for (DummyEntity dummyEntity : dummyEntities) {
            dummyList.add(modelMapper.map(dummyEntity, ResponseDummyDTO.class));
        }

        return dummyList;
    }

    @Override
    public ResponseDummyDTO getDummyById(Long id) {
        DummyEntity dummyEntity = dummyJpaRepository.getReferenceById(id);
        return modelMapper.map(dummyEntity, ResponseDummyDTO.class);
    }

    @Override
    public ResponseDummyDTO createDummy(String auth0Id, SaveDummyDTO dummy) throws IllegalArgumentException {
        Optional<DummyEntity> dummyEntityFound = dummyJpaRepository.findByDummy(dummy.getDummy());

        if (dummyEntityFound.isPresent()) {
            throw new IllegalArgumentException("Dummy already exists");
        }

        DummyEntity dummyEntity = modelMapper.map(dummy, DummyEntity.class);
        List<UserEntity> userEntityList = userRepository.findAll();
        UserEntity userEntity = userRepository.findByAuth0Id(auth0Id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        dummyEntity.setUser(userEntity);
        DummyEntity dummyEntitySaved = dummyJpaRepository.save(dummyEntity);

        return modelMapper.map(dummyEntitySaved, ResponseDummyDTO.class);
    }

    @Override
    public ResponseDummyDTO updateDummy(Long id, SaveDummyDTO dummy) {
        DummyEntity dummyEntity = modelMapper.map(dummy, DummyEntity.class);
        dummyEntity.setId(id);
        DummyEntity dummyEntitySaved = dummyJpaRepository.save(dummyEntity);

        return modelMapper.map(dummyEntitySaved, ResponseDummyDTO.class);
    }

    @Override
    public void deleteDummyById(Long id) {
        dummyJpaRepository.deleteById(id);
    }

    @Override
    public ResponseDummyDTO getDummyBySomething(String something) {
        Optional<DummyEntity> dummyEntityFound = dummyJpaRepository.findByDummy(something);

        if (dummyEntityFound.isPresent()) {
            throw new IllegalArgumentException("Dummy already exists");
        }

        return modelMapper.map(dummyEntityFound, ResponseDummyDTO.class);
    }
}
