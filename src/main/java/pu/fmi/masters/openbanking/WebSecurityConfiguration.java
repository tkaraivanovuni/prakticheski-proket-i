package pu.fmi.masters.openbanking;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * This class is used to configure the behavior of Spring Security for
 * authentication.
 */
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private AppUserDetailService userDetailService;

	/**
	 * Constructor
	 * 
	 * @param userDetailService - the {@link AppUserDetailService} user to get user
	 *                          data.
	 */
	public WebSecurityConfiguration(AppUserDetailService userDetailService) {
		this.userDetailService = userDetailService;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
		auth.userDetailsService(userDetailService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/**").permitAll().requestMatchers(EndpointRequest.toAnyEndpoint())
				.permitAll().and().csrf().disable();
		http.headers().frameOptions().disable();
	}

}
