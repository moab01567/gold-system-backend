package com.rizamo.goldStore.features.user;

import com.rizamo.goldStore.features.auth.interfrace.AuthServiceToUserService;
import com.rizamo.goldStore.features.user.DTO.GetUserDTO;
import com.rizamo.goldStore.features.user.DTO.PostChangePwdDTO;
import com.rizamo.goldStore.features.user.DTO.PostNewUserDTO;
import com.rizamo.goldStore.features.user.DTO.PutUserDTO;
import com.rizamo.goldStore.features.user.userException.BadRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements AuthServiceToUserService {
    private final UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;

    }
    @Override
    public User getUserByUsername(String username) {
        return userRepo.findUserByUsername(username).orElseThrow(()->new UsernameNotFoundException("Username Not found"));
    }


    public GetUserDTO getUser(String username) {
        User user = getUserByUsername(username);
        return GetUserDTO.builder()
                .username(user.getUsername())
                .userId(user.getUserId())
                .authority(user.getAuthority())
                .invoiceName(user.getInvoiceName())
                .disabled(user.isDisabled())
                .build();
    }

    @Transactional
    public void createUser(PostNewUserDTO postNewUserDTO, String username) {
        if (postNewUserDTO.getUsername().isEmpty())
            throw new BadRequest("Username can not be empty / لا يمكن أن يكون اسم المستخدم فارغًا");
        if (postNewUserDTO.getInvoiceName().isEmpty())
            throw new BadRequest("Invoice Name can not be empty / لا يمكن أن يكون اسم الفاتورة فارغًا");

        User reqUser = getUserByUsername(username);
        if (reqUser.getAuthority() != Authority.admin)
            throw new BadRequest("This operation should not be possible, from website someone is trying something funny");

        Optional<User> optionalUser = userRepo.findUserByUsername(postNewUserDTO.getUsername());
        if (optionalUser.isPresent()) throw new BadRequest("Username already exists / اسم المستخدم موجود بالفعل ");
        if (!postNewUserDTO.getPassword().equals(postNewUserDTO.getConfirmPassword())){
            throw new BadRequest("The new password and confirmation do not match / كلمة المرور الجديدة وتأكيدها غير متطابقين");
        }
        if (postNewUserDTO.getPassword().length() < 10){
            throw new BadRequest("The password must be at least 10 characters / كلمة المرور يجب أن تتكون من 10 أحرف على الأقل");
        }
        try{
            PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            userRepo.save(User.builder()
                    .username(postNewUserDTO.getUsername())
                    .invoiceName(postNewUserDTO.getInvoiceName())
                    .password(passwordEncoder.encode(postNewUserDTO.getPassword()))
                    .authority(Authority.user)
                    .build()
            );
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new BadRequest("Not valid input / إدخال غير صالح");
        }
    }

    @Transactional
    public void changePassword(PostChangePwdDTO postChangePwdDTO, String name) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        User user = getUserByUsername(name);
        if (!passwordEncoder.matches(postChangePwdDTO.currentPassword(), user.getPassword())){
            throw new BadRequest("Current password does not match / كلمة المرور الحالية غير صحيحة");
        }
        if (!postChangePwdDTO.newPassword().equals(postChangePwdDTO.confirmNewPassword())){
            throw new BadRequest("The new password and confirmation do not match / كلمة المرور الجديدة وتأكيدها غير متطابقين");
        }
        if (postChangePwdDTO.newPassword().length() < 10){
            throw new BadRequest("The password must be at least 10 characters / كلمة المرور يجب أن تتكون من 10 أحرف على الأقل");
        }
        user.setPassword(passwordEncoder.encode(postChangePwdDTO.newPassword()));
        userRepo.save(user);
    }


    public List<GetUserDTO> getAllUser() {
         return userRepo.findAll()
                 .stream().map(user -> GetUserDTO.builder()
                         .userId(user.getUserId())
                         .username(user.getUsername())
                         .authority(user.getAuthority())
                         .invoiceName(user.getInvoiceName())
                         .disabled(user.isDisabled())
                         .build())
                 .toList();
    }
    @Transactional
    public void updateUser(PutUserDTO putUserDTO, String name) {
        User reqUser = getUserByUsername(name);
        if (reqUser.getAuthority() != Authority.admin)
            throw new BadRequest("This operation should not be possible, from website someone is trying something funny");
        try{
            User user = userRepo.findUserByUserId(putUserDTO.getUserId());
            user.setUsername(putUserDTO.getUsername());
            user.setInvoiceName(putUserDTO.getInvoiceName());
            user.setDisabled(putUserDTO.isDisabled());
            userRepo.save(user);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new BadRequest("Not valid input / إدخال غير صالح");
        }
    }
}
