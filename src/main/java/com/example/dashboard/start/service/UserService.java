package com.example.dashboard.start.service;

import com.example.dashboard.start.dto.user.UserRequestDTO;
import com.example.dashboard.start.dto.user.UserResponseDTO;
import com.example.dashboard.start.entity.UserEntity;
import com.example.dashboard.start.exception.UserNotFoundException;
import com.example.dashboard.start.repository.UserRepository;
import com.example.dashboard.start.repository.projection.UserProjection;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    final UserRepository userRepository;
    final ModelMapper modelMapper;
//    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
//        this.userRepository = userRepository;
//        this.modelMapper = modelMapper;
//    }

    public List <UserResponseDTO> findAll(){

        List<UserEntity> entities = userRepository.findAll();

        if(entities.isEmpty()){
            throw new UserNotFoundException("User not found");
        }

        return entities.stream()
                .map(entity -> modelMapper.map(entity, UserResponseDTO.class))
                .toList();
    }

    public UserResponseDTO findById(Long id){
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + id));

        return modelMapper.map(entity, UserResponseDTO.class);
    }

    public UserResponseDTO save(UserRequestDTO dto){

        UserEntity saveUserEntity = modelMapper.map(dto, UserEntity.class);
        UserEntity userEntity = userRepository.save(saveUserEntity);

        return modelMapper.map(userEntity, UserResponseDTO.class);
    }

    public UserResponseDTO editById(UserRequestDTO dto,  Long id){
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id:" + id));

        modelMapper.map(dto, entity);

        UserEntity updated = userRepository.save(entity);
        return modelMapper.map(updated, UserResponseDTO.class);
    }

    public UserResponseDTO patchUpdate(Long id, UserRequestDTO dto) {
        UserEntity getUserEntity = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id:" + id));

        // PATCH logic → update only non-null fields
        if (dto.getName() != null) {
            getUserEntity.setName(dto.getName());
        }
        if (dto.getEmail() != null) {
            getUserEntity.setEmail(dto.getEmail());
        }
        if (dto.getAge() != null) {
            getUserEntity.setAge(dto.getAge());
        }

        UserEntity updated = userRepository.save(getUserEntity);
        return modelMapper.map(updated, UserResponseDTO.class);
    }
    public void deleteById(Long id){
        if(!userRepository.existsById(id)) { throw new UserNotFoundException("User not found with id " + id);}
        userRepository.deleteById(id);
    }

    public void clear(){
        userRepository.deleteAll();
    }

    public List<UserResponseDTO> getUserDetail(){
        List<UserProjection> userProjections = userRepository.findAllUserDetailBy();
        return userProjections.stream()
                .map(
                p -> new  UserResponseDTO(
                        p.getId(),
                        p.getName(),
                        p.getEmail(),
                        p.getAge(),
                        p.getBloodGroupType()
                )
        ).toList();
    }
}