package com.study.ddd.application.mapper;


import com.study.ddd.application.dto.UserDTO;
import com.study.ddd.domain.model.Address;
import com.study.ddd.domain.model.User;
import com.study.ddd.infrastructure.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", source = "id", qualifiedByName = "uuidToString")
    @Mapping(target = "address", expression = "java(formatAddress(user.getAddress()))")
    UserDTO toDTO(User user);

    // DTO -> 도메인 모델
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true) // 기본 역할은 생성자에서 추가
    @Mapping(target = "address", expression = "java(parseAddress(dto.getAddress()))")
    User toDomain(UserDTO dto);

    // 도메인 모델 -> Entity
    @Mapping(target = "id", source = "id")
    @Mapping(target = "street", source = "address.street")
    @Mapping(target = "city", source = "address.city")
    @Mapping(target = "state", source = "address.state")
    @Mapping(target = "zipCode", source = "address.zipCode")
    UserEntity toEntity(User user);

    // Entity -> 도메인 모델
    @Mapping(target = "address", expression = "java(new com.example.userapp.domain.model.Address(entity.getStreet(), entity.getCity(), entity.getState(), entity.getZipCode()))")
    User toDomain(UserEntity entity);

    // Helper methods for address formatting and parsing
    default String formatAddress(Address address) {
        return address.getStreet() + ", " + address.getCity() + ", " + address.getState() + ", " + address.getZipCode();
    }

    default Address parseAddress(String addressStr) {
        String[] parts = addressStr.split(", ");
        if(parts.length != 4){
            throw new IllegalArgumentException("Address must include street, city, state, and zip code.");
        }
        return Address.builder()
                .street(parts[0])
                .city(parts[1])
                .state(parts[2])
                .zipCode(parts[3])
                .build();
    }

    default String uuidToString(java.util.UUID uuid) {
        return uuid != null ? uuid.toString() : null;
    }
}
