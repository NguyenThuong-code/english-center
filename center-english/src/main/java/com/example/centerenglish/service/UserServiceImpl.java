package com.example.centerenglish.service;

import com.example.centerenglish.model.Schedule;
import com.example.centerenglish.model.User;
import com.example.centerenglish.repo.ScheduleRepository;
import com.example.centerenglish.repo.UserRepository;
import com.example.centerenglish.request.UserRequest;
import com.example.centerenglish.request.UserRequestFilter;
import com.example.centerenglish.response.UserResponse;
import com.example.centerenglish.specification.UserRequestSpecification;
import com.example.centerenglish.type.MarkType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Override
    public UserResponse createUserWithSchedule(UserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setStartWorkingDate(request.getStartWorkingDate());
        user.setMarkType(MarkType.getInstance(request.getMarkType()).getId());
        Schedule schedule = new Schedule(request.getSchedule());
        schedule.setUser(user);
        user.setSchedule(schedule);
        userRepository.save(user);

        return new UserResponse(user);
    }
    @Override
    public Map<String, Object> getAllUsers(UserRequestFilter userRequestFilter) {

        Pageable paging = PageRequest.of(userRequestFilter.getPage(), userRequestFilter.getSize(), Sort.by(Sort.Direction.ASC, "name"));
        Page<User> filterUserPage = userRepository.findAll(
                UserRequestSpecification.nameLike(userRequestFilter.getName()),
                paging);
        List<UserResponse> users = filterUserPage.getContent().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
        long totalItems = filterUserPage.getTotalElements();
        int totalPages = filterUserPage.getTotalPages();
        // Constructing the response map
        Map<String, Object> response = new HashMap<>();
        response.put("users", users);
        response.put("currentPage", filterUserPage.getNumber());
        response.put("totalItems", totalItems);
        response.put("totalPages", totalPages);
        return response;
    }
    @Override
    public UserResponse getUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return new UserResponse(user);
        } else {
            return null;
        }
    }
    @Override
    public Boolean updateUser(Map<String, Object> req) {
        Boolean result = false;
        try {
            Long idUser = Long.valueOf(req.get("id").toString());

            Optional<User> requestFixUser = this.userRepository.findById(idUser);
            if (requestFixUser.isPresent()) {
                User user = requestFixUser.get();

                // Update user information
                user.setName(req.get("name").toString());
                user.setDateOfBirth(LocalDate.parse(req.get("dateOfBirth").toString()));
                user.setStartWorkingDate(LocalDate.parse(req.get("startWorkingDate").toString()));
                user.setMarkType(Integer.valueOf(req.get("markType").toString()));

                // Update or create Schedule for the user
                Object scheduleObject = req.get("schedule");
                if (scheduleObject != null && scheduleObject instanceof Map) {
                    Map<String, Boolean> scheduleMap = (Map<String, Boolean>) scheduleObject;

                    Schedule userSchedule = user.getSchedule();
                    if (userSchedule == null) {
                        userSchedule = new Schedule();
                        userSchedule.setUser(user);
                    }

                    userSchedule.setShift1(Boolean.valueOf(scheduleMap.get("shift1")));
                    userSchedule.setShift2(Boolean.valueOf(scheduleMap.get("shift2")));
                    userSchedule.setShift3(Boolean.valueOf(scheduleMap.get("shift3")));
                    userSchedule.setShift4(Boolean.valueOf(scheduleMap.get("shift4")));
                    userSchedule.setShift5(Boolean.valueOf(scheduleMap.get("shift5")));
                    userSchedule.setShift6(Boolean.valueOf(scheduleMap.get("shift6")));
                    userSchedule.setShift7(Boolean.valueOf(scheduleMap.get("shift7")));
                    userSchedule.setShift8(Boolean.valueOf(scheduleMap.get("shift8")));
                    userSchedule.setShift9(Boolean.valueOf(scheduleMap.get("shift9")));
                    userSchedule.setShift10(Boolean.valueOf(scheduleMap.get("shift10")));
                    userSchedule.setShift11(Boolean.valueOf(scheduleMap.get("shift11")));
                    userSchedule.setShift12(Boolean.valueOf(scheduleMap.get("shift12")));

                    user.setSchedule(userSchedule);
                }
                this.userRepository.save(user);
                result= true;
            }
        } catch (Exception e) {
            return false;
        }
        return result;
    }
    @Transactional
    @Override
    public Boolean deleteRequestUserById(Long id) {
        Boolean result= false;
        try {
            userRepository.deleteById(id);
            result=true;
        }catch (Exception e){
            return  false;
        }
        return result;
    }

}
