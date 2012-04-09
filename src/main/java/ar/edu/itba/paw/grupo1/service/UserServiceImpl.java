package ar.edu.itba.paw.grupo1.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;

import ar.edu.itba.paw.grupo1.dao.UserDao;
import ar.edu.itba.paw.grupo1.dao.UserDao.UserAlreadyExistsException;
import ar.edu.itba.paw.grupo1.model.User;

public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	
	public UserServiceImpl(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public void register(String name, String surname, String email,
			String phone, String username, String password)
			throws UserAlreadyExistsException {

		String hash = hashPassword(password);
		userDao.register(name, surname, email, phone, username, hash);
	}

	private String hashPassword(String password) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			Logger.getLogger(UserServiceImpl.class).fatal("Failed to hash the user password", e);
			throw new RuntimeException(e);
		}
		
		byte[] hash;
		try {
			hash = md.digest(password.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			Logger.getLogger(UserServiceImpl.class).fatal("Failed to hash the user password", e);
			throw new RuntimeException(e);
		}
		
		return DatatypeConverter.printHexBinary(hash);
	}

	@Override
	public User login(String username, String password) {
		return loginWithHash(username, hashPassword(password));
	}

	@Override
	public User get(int userId) {
		return userDao.get(userId);
	}

	@Override
	public User loginWithHash(String username, String hash) {
		return userDao.login(username, hash);
	}

}
