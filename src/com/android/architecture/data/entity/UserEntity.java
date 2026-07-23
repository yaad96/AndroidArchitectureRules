package com.android.architecture.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Room Entity for User table.
 * COMPLIANT: Has @Entity annotation and @PrimaryKey field.
 */
@Entity
public class UserEntity {
    @PrimaryKey
    private String userId;
    private String name;
    private String email;

    public UserEntity() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
