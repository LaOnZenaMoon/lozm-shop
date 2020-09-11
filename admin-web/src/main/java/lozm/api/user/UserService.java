package lozm.api.user;

import lombok.RequiredArgsConstructor;
import lozm.entity.user.User;
import lozm.global.exception.ServiceException;
import lozm.object.dto.user.GetUserDto;
import lozm.object.vo.user.UserVo;
import lozm.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public List<GetUserDto> getUserList() throws Exception {
        List<User> userList = userRepository.selectUserList();
        List<GetUserDto> rtnList = new ArrayList<>();
        for (User user : userList) {
            GetUserDto dto = GetUserDto.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .identifier(user.getIdentifier())
                    .password(null)
                    .type(user.getType())
                    .build();

            rtnList.add(dto);
        }

        return rtnList;
    }

    @Transactional
    public void save(UserVo userVo) throws Exception {
        User user = new User();
        user.insertUser(userVo);

        //1. check ID duplicated
        List<User> findUsersIdDuplicated = userRepository.findByIdentifier(user.getIdentifier());
        if(findUsersIdDuplicated.size() > 0) throw new ServiceException("USER_0001", "User Identifier is duplicated.");

        userRepository.save(user);
    }

    @Transactional
    public void update(UserVo userVo) throws Exception {
        Optional<User> findUser = findUser(userVo.getId());
        findUser.get().updateUser(userVo);
    }

    @Transactional
    public void delete(UserVo userVo) throws Exception {
        Optional<User> findUser = findUser(userVo.getId());
        findUser.get().deleteUser(userVo);
    }

    private Optional<User> findUser(Long userId) {
        Optional<User> findUser = userRepository.findById(userId);
        findUser.orElseThrow(() -> {
            throw new ServiceException("USER_0002", "User doesn't exist.");
        });
        return findUser;
    }

}
