package com.mymedia.web.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.mymedia.web.dao.RoleDAO;
import com.mymedia.web.dao.UserDAO;
import com.mymedia.web.dto.UserBeanEntity;
import com.mymedia.web.exceptions.MusicHubGenericException;
import com.mymedia.web.mvc.model.Role;
import com.mymedia.web.mvc.model.User;
import com.mymedia.web.requestmodel.CreateUserRequestModel;
import com.mymedia.web.requestmodel.GoogleLoginReqModel;
import com.mymedia.web.requestmodel.LoginRequestModel;
import com.mymedia.web.responsemodel.TokenResponseModel;
import com.mymedia.web.utils.CryptUtils;

@Service
@EnableTransactionManagement
public class UserService {

	private static final Logger LOG = LogManager.getLogger(UserService.class);

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private RoleDAO roleDAO;

	@Autowired
	private TokenService tokenService;

	@Transactional
	public User createUser(GoogleLoginReqModel model) {
		try {
			User user = new User();
			user.setGoogleId(model.getId());
			user.setUsername(model.getUsername());
			user.setEmail(model.getEmail());
			user.setPassword(model.getEmail());
			return userDAO.addUser(user);

		} catch (

		MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to get User", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public User createUser(CreateUserRequestModel model) {
		try {
			if (validateUserRequestModel(model)) {
				User user = new User();
				user.setPassword(model.getPassword());
				user.setEmail(model.getEmail());
				user.setGoogleId("");
				//user.setPassword(CryptUtils.generateHashSHA1(model.getPassword()));
				user.setUsername(model.getUsername());
				user.setGoogleId(model.getEmail());
				return userDAO.addUser(user);
			} else {
				throw new MusicHubGenericException("Passwords do not match", HttpStatus.BAD_REQUEST);
			}
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to get User", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Transactional
	public User addRole(User user, String id) {
		try {
			Role role = roleDAO.getRole(id);
			user.setRole(role);
			return userDAO.updateUser(user);
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to set User Role", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public List<UserBeanEntity> getAllUsers() {
		try {
			List<User> users = userDAO.getAllUsers();
			List<UserBeanEntity> list = new ArrayList<>();
			users.forEach(e -> list.add(userToUserEntity(e)));
			return list;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to get User Collection", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public boolean userExists(String id) {
		return userDAO.getUser(id) != null || userDAO.getUniqueUserByField("googleId", id)!=null;
	}

	@Transactional
	public UserBeanEntity getUser(String id) {
		try {
			User user = userDAO.getUser(id);
			if (user == null) {
				throw new MusicHubGenericException("User with that id does not exist!", HttpStatus.NOT_FOUND);
			}
			return userToUserEntity(user);
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to get User", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public User getByUsername(String username) {
		try {
			User user = userDAO.getUniqueUserByField("username", username);
			return user;
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to get User", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public User getByGoogleId(String googleId) {
		try {
			User user = userDAO.getUniqueUserByField("googleId", googleId);

			if (user == null) {
				throw new MusicHubGenericException("User with that name does not exist",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return user;
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to get User", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public UserBeanEntity addUser(UserBeanEntity entity) {
		try {
			User user = userDAO.addUser(userEntityToUser(entity));
			if (user == null) {
				throw new MusicHubGenericException("Not a valid request!", HttpStatus.BAD_REQUEST);
			}
			return userToUserEntity(user);
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to add User", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public User addUser(User user) {
		try {
			User u = userDAO.addUser(user);
			if (u == null) {
				throw new MusicHubGenericException("Not a valid request!", HttpStatus.BAD_REQUEST);
			}
			return u;
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to add User", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public UserBeanEntity updateUser(UserBeanEntity entity) {
		try {
			User user = userDAO.updateUser(userEntityToUser(entity));
			if (user == null) {
				throw new MusicHubGenericException("Not a valid request!", HttpStatus.BAD_REQUEST);
			}
			return userToUserEntity(user);
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to update User", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public void deleteUser(String id) {
		try {
			userDAO.deleteUser(id);
		} catch (Exception exc) {
			throw new MusicHubGenericException("Faield to delete User", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public TokenResponseModel getToken(LoginRequestModel model) {
		try {
			User u = getByUsername(model.getUsername());
			// if
			// (u.getPassword().trim().equals(CryptUtils.generateHashSHA1(model.getPassword().trim())))
			// {
			if (u.getPassword().trim().equals(model.getPassword().trim())) {
				String token = tokenService.createJWT(u);
				TokenResponseModel respModel = new TokenResponseModel();
				respModel.setAccessToken(token);
				return respModel;
			}
			throw new MusicHubGenericException("Invalid username or password!", HttpStatus.BAD_REQUEST);
		} catch (MusicHubGenericException exc) {
			throw exc;
		} catch (Exception exc) {
			throw new MusicHubGenericException("Failed to authenticate user", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private boolean validateUserRequestModel(CreateUserRequestModel model) {
		return model.getPassword().trim().equals(model.getConfirmPassword().trim());
	}

	public User userEntityToUser(UserBeanEntity entity) {
		User user = new User();
		user.setId(entity.getId());
		user.setUsername(entity.getUsername());
		user.setRole(roleDAO.getRole(entity.getRoleId()));
		user.setEmail(entity.getEmail());
		return user;
	}

	public UserBeanEntity userToUserEntity(User user) {
		UserBeanEntity entity = new UserBeanEntity();
		entity.setId(user.getId());
		entity.setEmail(user.getEmail());
		entity.setUsername(user.getUsername());
		entity.setRoleId(user.getRole().getId());
		entity.setRoleName(user.getRole().getName());
		return entity;
	}
}
