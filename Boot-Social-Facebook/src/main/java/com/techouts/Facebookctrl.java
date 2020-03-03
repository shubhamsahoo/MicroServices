package com.techouts;

import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Facebookctrl {
	// Creates a facebook connection using the given application id and secret key.
	private FacebookConnectionFactory factory = new FacebookConnectionFactory("133533464681546",
			"5c107bfa301c98748e781eec46495e19");

	// Index page.
	@GetMapping(value = "/")
	public ModelAndView index() {
		return new ModelAndView("welcome");
	}

	// Redirection uri.
	@GetMapping(value = "/useapp")
	public String redirect() {
		// Creates the OAuth2.0 flow and performs the oauth handshake on behalf of the user.
		OAuth2Operations operations = factory.getOAuthOperations();

		// Builds the OAuth2.0 authorize url and the scope parameters.
		OAuth2Parameters params = new OAuth2Parameters();
		params.setRedirectUri("http://localhost:8102/forwardLogin");
		params.setScope("email, public_profile");

		// Url to redirect the user for authentication via OAuth2.0 authorization code grant.
		String authUrl = operations.buildAuthenticateUrl(params);
		System.out.println("Generated url is= " + authUrl);
		return "redirect:" + authUrl;
	}

	// Welcome page.
	@GetMapping(value = "/forwardLogin")
	public ModelAndView prodducer(@RequestParam("code") String authorizationCode) {
		// Creates the OAuth2.0 flow and performs the oauth handshake on behalf of the user.
		OAuth2Operations operations = factory.getOAuthOperations();

		// OAuth2.0 access token.
		// "exchangeForAccess()" method exchanges the authorization code for an access grant.
		AccessGrant accessToken = operations.exchangeForAccess(authorizationCode, "http://localhost:8102/forwardLogin",
				null);

		Connection<Facebook> connection = factory.createConnection(accessToken);

		// Getting the connection that the current user has with facebook.
		Facebook facebook = connection.getApi();
		// Fetching the details from the facebook.
		String[] fields = { "id", "name", "email", "about", "birthday" };
		User userProfile = facebook.fetchObject("me", User.class, fields);

		ModelAndView model = new ModelAndView("details");
		model.addObject("user", userProfile);
		return model;
	}
}