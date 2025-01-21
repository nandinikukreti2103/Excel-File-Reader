package com.file.reader.Excel_File_Reader.service;

import com.file.reader.Excel_File_Reader.auth.AuthenticationRequest;
import com.file.reader.Excel_File_Reader.auth.AuthenticationResponse;
import com.file.reader.Excel_File_Reader.auth.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
