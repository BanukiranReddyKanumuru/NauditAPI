package com.ensar.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.ensar.entity.User;
import com.ensar.repository.UserRepository;
import com.ensar.request.dto.CreateUpdateUserDto;
import com.ensar.security.CurrentUser;
import com.ensar.security.EnsarUserDetails;
import com.ensar.service.UserService;

import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Api(tags = "User Mgmt")
@RestController
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/user")
public class UserController {
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	UserRepository userRepository;

	@GetMapping("/current")
	public ResponseEntity<Object> getLoggedInUser() {
		User user = userService.getLoggedInUser();
		Map<String, User> result = new HashMap<>();
		result.put("user", user);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/email/{email}")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<User> getUserByEmail(@NotBlank @PathVariable("email") String email) {
		User user = userService.getUserByEmail(email);
		if (user == null)
			throw new ResourceNotFoundException();
		return ResponseEntity.ok(user);
	}

	@GetMapping("/")
	@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
	public ResponseEntity<List<User>> getUsers(@RequestParam(name = "orgId", required = false) final String orgId) {
		return ResponseEntity.ok(userService.getAllAccessibleUsers(orgId));
	}

	@PostMapping("/create")
	@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
	public ResponseEntity<User> createUser(@ApiIgnore @CurrentUser final EnsarUserDetails currentUser,
			@Valid @RequestBody CreateUpdateUserDto createUpdateUserDto) {
		if (createUpdateUserDto.getOrganizationId() == null) {
			createUpdateUserDto.setOrganizationId(currentUser.getOrganization().getId());
		}
		if (createUpdateUserDto.getRoleName() == null) {
			createUpdateUserDto.setRoleName(User.Role.ROLE_USER);
		}
		return ResponseEntity.ok(userService.createOrUpdateUser(Optional.empty(), createUpdateUserDto));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
	public ResponseEntity<User> updateUser(@PathVariable String id,
			@Valid @RequestBody CreateUpdateUserDto createUpdateUserDto) {
		return ResponseEntity.ok(userService.createOrUpdateUser(Optional.of(id), createUpdateUserDto));

	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
	public ResponseEntity<User> getUser(@PathVariable String id) {
		return ResponseEntity.ok(userService.getUserById(id));
	}

	@DeleteMapping("{id}")

	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")

	public String deleteCrm(@PathVariable String id) {

		userService.delete(id);

		return id;

	}

	@PutMapping("/enable")
	@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
	public ResponseEntity<Void> enableUsers(@RequestBody List<String> idList) {
		userService.enableOrDisableUsers(idList, false);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/disable")
	@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
	public ResponseEntity<Void> disableUsers(@RequestBody List<String> idList) {
		userService.enableOrDisableUsers(idList, true);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/updateuser/{id}")
	public User replaceEmployee(@RequestBody CreateUpdateUserDto newUser, @PathVariable String id) {

		userRepository.findById(id);
		Optional<User> user = userRepository.findById(id);
		User userUpdate = user.get();

		userUpdate.setEmail(newUser.getEmail());
		userUpdate.setFirstName(newUser.getFirstName());
		userUpdate.setLastName(newUser.getLastName());
		userUpdate.setMobile(newUser.getMobile());
		userUpdate.setJoinDate(newUser.getJoinDate());
		userUpdate.setEmployeeId(newUser.getEmployeeId());
		userUpdate.setRole(newUser.getRoleName());
		return userRepository.save(userUpdate);
	}

	@GetMapping("/fetchuser")
	public ResponseEntity<?> all() {
		return ResponseEntity.ok(userRepository.findAll());
	}

	@PostMapping("/adduser")
	public ResponseEntity<User> adduser(@RequestBody CreateUpdateUserDto userAddRequest) {

		// Create new user's account
		User user = new User();
		user.setEmail(userAddRequest.getEmail());
		user.setFirstName(userAddRequest.getFirstName());
		user.setLastName(userAddRequest.getLastName());
		user.setMobile(userAddRequest.getMobile());
		user.setJoinDate(userAddRequest.getJoinDate());
		user.setEmployeeId(userAddRequest.getEmployeeId());
		user.setRole(userAddRequest.getRoleName());
//        user.setOrganization(userAddRequest.getOrganization());

//        if (strRoles == null) {
//          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//          roles.add(userRole);
//        } else {
//          strRoles.forEach(role -> {
//            switch (role) {
//            case "admin":
//              Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//                  .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//              roles.add(adminRole);
//
//              break;
//            case "mod":
//              Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
//                  .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//              roles.add(modRole);
//
//              break;
//            default:
//              Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                  .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//              roles.add(userRole);
//            }
//          });
//        }
//
//        user.setRoles(roles);
//        
		userRepository.save(user);

		return ResponseEntity.ok(user);
	}

}