package com.rizamo.goldStore.features.auth.interfrace;


import com.rizamo.goldStore.features.user.User;

public interface AuthServiceToUserService {
    User getUserByUsername(String username);
}
