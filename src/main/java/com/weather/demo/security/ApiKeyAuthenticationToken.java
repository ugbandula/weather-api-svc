/**
 * Implements API Key security configuration for the application.
 *
 * @Author Bandula Gamage
 * Date: 20/08/2024
 */

package com.weather.demo.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.Transient;

import java.util.Collection;

@Transient
public class ApiKeyAuthenticationToken extends AbstractAuthenticationToken {

    private String apiKey;

    public ApiKeyAuthenticationToken(String apiKey, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.apiKey = apiKey;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return apiKey;
    }
}
