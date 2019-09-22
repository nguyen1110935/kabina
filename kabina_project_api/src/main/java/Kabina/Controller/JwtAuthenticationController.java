package Kabina.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import Kabina.Config.JwtTokenUtil;
import Kabina.DTO.ApiResponse;
import Kabina.DTO.JwtRequest;
import Kabina.DTO.JwtResponse;
import Kabina.DTO.UserDTO;
import Kabina.Model.User;
import Kabina.Service.UserService;
import Kabina.Service.impl.JwtUserDetailsService;


@RestController
@CrossOrigin
public class JwtAuthenticationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

//	@Autowired
//	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
			System.out.println(authenticationRequest.toString());	
			final User user = userService.findByUserName(authenticationRequest.getUsername());
			final String token = jwtTokenUtil.generateToken(user);
			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
//			final SecurityUser userDetails = (SecurityUser) userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
//			return ResponseEntity.ok(new JwtResponse(token,userDetails.getRole(),userDetails.getId(),userDetails.getUsername()));
			return ResponseEntity.ok(new JwtResponse(token, user.getUserId(), user.getUserName(), "", user.getShortName(), user.getFullName(), user.getPhone(), user.getEmail(), user.getRole(), user.getBusiness()));
	}

	private void authenticate(String username, String password) throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
