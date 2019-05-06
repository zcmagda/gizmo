package to.gizmo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import to.gizmo.services.DatabaseUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests().antMatchers("/", "/index", "/register", "/addWorkspace","/css/**", "/js/**").permitAll().anyRequest().authenticated();
        http.formLogin().loginPage("/login").permitAll().and().logout().logoutSuccessUrl("/");
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService()
    {
        return new DatabaseUserDetailsService();
    }
}
