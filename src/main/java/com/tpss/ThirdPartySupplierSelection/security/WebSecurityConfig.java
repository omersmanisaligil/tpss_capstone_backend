package com.tpss.ThirdPartySupplierSelection.security;

import com.tpss.ThirdPartySupplierSelection.entity.constants.EPermission;
import com.tpss.ThirdPartySupplierSelection.security.jwt.AuthEntryPointJwt;
import com.tpss.ThirdPartySupplierSelection.security.jwt.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter jwtAuthenticationFilter() {
	return new AuthTokenFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
	return super.authenticationManagerBean();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
        cors().and().csrf().disable()
        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests().antMatchers("/","/home","/auth/**").permitAll()
        .antMatchers(HttpMethod.GET, "/certificates/*", "/orders/*","/products/*",
                    "/providers/*","/routes/*","/tech/*","/topsis/*","/vehicles/*").hasAuthority(EPermission.PERSONNEL_READ.getPermission())
        .antMatchers(HttpMethod.DELETE, "/certificates/*", "/orders/*","/products/*",
                    "/providers/*","/routes/*","/tech/*","/topsis/*","/vehicles/*").hasAuthority(EPermission.PERSONNEL_WRITE.getPermission())
        .antMatchers(HttpMethod.POST, "/certificates/*", "/orders/*","/products/*",
                    "/providers/*","/routes/*","/tech/*","/topsis/*","/vehicles/*").hasAuthority(EPermission.PERSONNEL_WRITE.getPermission())
        .antMatchers(HttpMethod.PUT, "/certificates/*", "/orders/*","/products/*",
                    "/providers/*","/routes/*","/tech/*","/topsis/*","/vehicles/*").hasAuthority(EPermission.PERSONNEL_WRITE.getPermission())
        .antMatchers(HttpMethod.GET, "/users/*").hasAuthority((EPermission.USER_READ.getPermission()))
        .antMatchers(HttpMethod.DELETE, "/users/*").hasAuthority(EPermission.USER_WRITE.getPermission())
        .antMatchers(HttpMethod.POST,"/users/*").hasAuthority(EPermission.USER_WRITE.toString())
        .antMatchers(HttpMethod.PUT,"/users/*").hasAuthority(EPermission.USER_WRITE.toString())
        .anyRequest().authenticated();

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
