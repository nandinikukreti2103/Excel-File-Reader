package com.file.reader.Excel_File_Reader.auth;

import com.file.reader.Excel_File_Reader.entity.Role;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

    private String userName;
    private String email;
    private String userPassword;
    private Role role;
}
