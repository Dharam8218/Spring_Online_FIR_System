package com.accio.Online_FIR_System.service;


import com.accio.Online_FIR_System.dto.request.OfficerRequest;
import com.accio.Online_FIR_System.entity.Officer;
import com.accio.Online_FIR_System.repository.OfficerRepository;
import com.accio.Online_FIR_System.transformer.OfficerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class OfficerService {

    @Autowired
    OfficerRepository officerRepository;

    @Autowired
    OfficerTransformer officerTransformer;

    @Autowired
    JavaMailSender javaMailSender;

    String passwordWithoutEncoding;


    public List<String> addOfficer(OfficerRequest officerRequest) {

        String password = generatePassword(10);
        String officerId = generateUniqueOfficerId();
        //System.out.println(password);
        //System.out.println(officerId);
        List<String> list = new ArrayList<>();

        while (officerRepository.findByOfficerId(officerId).isPresent()) {
            officerId = generateUniqueOfficerId();
        }

        while (officerRepository.findByPassword(password).isPresent()) {
            password = generatePassword(10);
        }

        Officer officer = officerTransformer.officerRequestToOfficer(officerRequest);
        officer.setOfficerId(officerId);
        officer.setPassword(passwordEncoder().encode(password));
        officerRepository.save(officer);
        list.add("Officer added Successfully!!");
        list.add("Officer Id: " + officerId);
        list.add("Password: " + password);
        passwordWithoutEncoding = password;
        sendEmail(officer);
        return list;

    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public Officer findOfficerByUserName(String userName) {
        return officerRepository.findByUserName(userName);
    }

    private static String generateUniqueOfficerId() {
        String characters = "0123456789";
        Random random = new Random();
        StringBuilder id = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            id.append(characters.charAt(random.nextInt(characters.length())));
        }

        return id.toString();
    }

    public static String generatePassword(int length) {
        // Ensure minimum length is 10
        if (length < 10) {
            length = 10;
        }

        // Define character sets for each required category
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String symbols = "!@#$%^&*()-_=+[]{}|;:,.<>?";

        Random random = new Random();
        ArrayList<Character> passwordChars = new ArrayList<>();

        // Add one mandatory character from each category
        passwordChars.add(upperCaseLetters.charAt(random.nextInt(upperCaseLetters.length())));
        passwordChars.add(lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length())));
        passwordChars.add(digits.charAt(random.nextInt(digits.length())));
        passwordChars.add(symbols.charAt(random.nextInt(symbols.length())));

        // Combine all characters into one string for remaining positions
        String allAllowed = upperCaseLetters + lowerCaseLetters + digits + symbols;

        // Fill the rest of the password length with random characters from allAllowed
        for (int i = 4; i < length; i++) {
            passwordChars.add(allAllowed.charAt(random.nextInt(allAllowed.length())));
        }

        // Shuffle the list to randomize the order of characters
        Collections.shuffle(passwordChars);

        // Build the final password string
        StringBuilder password = new StringBuilder();
        for (Character ch : passwordChars) {
            password.append(ch);
        }
        return password.toString();
    }

    private void sendEmail(Officer officer) {
        String text = "Congrats! " + officer.getUserName() + ".\n\nYour id is created successfully.\nNow you can login through you username and password and start your service.\n\nUserName: " + officer.getUserName() + "\nID: " + officer.getOfficerId() + "\nPassword: " + passwordWithoutEncoding + "\n\nThis is System generated password, you can change it further as per your convenience.\n\n\nThank You.";
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("d4263835@gmail.com");
        simpleMailMessage.setTo(officer.getEmail());
        simpleMailMessage.setSubject("Congratulation! ID Created Successfully!!");
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);

    }
}
