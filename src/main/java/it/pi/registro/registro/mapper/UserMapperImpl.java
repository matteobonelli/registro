package it.pi.registro.registro.mapper;

import it.pi.registro.registro.dto.UserSubjectDTO;
import it.pi.registro.registro.dto.response.UserDataResponseDTO;
import it.pi.registro.registro.dto.response.UserInfoResponseDTO;
import it.pi.registro.registro.entity.User;

import java.util.ArrayList;

public class UserMapperImpl implements UserMapper{
    @Override
    public UserDataResponseDTO toDataResponseDTO(User user) {


        UserDataResponseDTO userDataResponseDTO = new UserDataResponseDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getUserDetail()!=null && user.getUserDetail().getAddress() != null ? user.getUserDetail().getAddress() : "NO ADDRESS",
                user.getUserDetail()!=null && user.getUserDetail().getCity() != null ? user.getUserDetail().getCity() : "NO CITY",
                user.getUserDetail()!=null && user.getUserType().getDescription() != null ? user.getUserType().getType() : "NO TYPE",
                new ArrayList<>()
        );

        user.getSubjectUsers()
                .forEach(subjectUser -> {
                    userDataResponseDTO.getVotes().add(
                            new UserSubjectDTO(
                                    subjectUser.getSubject().getName(),
                                    subjectUser.getSubject().getDescription(),
                                    subjectUser.getTeacher().getFirstName() + " " + subjectUser.getTeacher().getLastName() + " " + subjectUser.getTeacher().getEmail(),
                                    subjectUser.getVote(),
                                    subjectUser.getNotes(),
                                    subjectUser.getVote_date()
                            )
                    );
                });

        return userDataResponseDTO;
    }
}
