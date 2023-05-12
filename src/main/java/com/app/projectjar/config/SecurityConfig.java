package com.app.projectjar.config;

import com.app.projectjar.type.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor @Slf4j
public class SecurityConfig /*extends WebSecurityConfigurerAdapter*/ {
//    메인
    private static final String IGNORE_MAIN_PATH = "/main/**";
//    관리자
    private static final String ADMIN_PATH = "/admin/**";
//    마이 페이지
    private static final String MYPAGE_PATH = "/mypage/**";
//    게시판
    private static final String IGNORE_BOARD_CHALLENGE_PATH = "/board/challenge/**";

    private static final String IGNORE_BOARD_GROUP_CHALLENGE_PATH = "/board/group-challenge/**";

    private static final String IGNORE_BOARD_SUGGEST_LIST_PATH = "/board/suggest/list";
    private static final String IGNORE_BOARD_SUGGEST_DETAIL_PATH = "/board/suggest/detail";
    private static final String BOARD_SUGGEST_WRITE_PATH = "/board/suggest/write";

    private static final String IGNORE_BOARD_DIARY_LIST_PATH = "/board/diary/list";
    private static final String IGNORE_BOARD_DIARY_DETAIL_PATH = "/board/detail/list";
    private static final String BOARD_DIARY_WRITE_PATH = "/board/write/list";

    private static final String IGNORE_BOARD_INQUIRE_LIST_PATH = "/board/inquire/list";
    private static final String IGNORE_BOARD_INQUIRE_DETAIL_PATH = "/board/inquire/detail";
    private static final String BOARD_INQUIRE_WRITE_PATH = "/board/inquire/write";

    private static final String IGNORE_BOARD_NOTICE_LIST_PATH = "/board/notice/list";
    private static final String IGNORE_BOARD_NOTICE_DETAIL_PATH = "/board/notice/detail";

//    서비스
    private static final String IGNORE_SERVICE_PATH = "/service/**";

//    faq
    private static final String IGNORE_FAQ_PATH = "/faq/**";

//    파비콘
    private static final String IGNORE_FAVICON = "/favicon.ico";

//    static 하위 폴더 (css, js, img)

//    로그인
    private static final String LOGIN_PAGE = "/member/login";
    private static final String LOGIN_PROCESSING_URL = "/member/login";
    private static final String LOGOUT_URL = "/member/logout";
    private static final String LOGOUT_SUCCESS_URL = "/member/login";


    private static final String REMEMBER_ME_TOKEN_KEY = "have a nice day";
    private static final int REMEMBER_ME_TOKEN_EXPIRED = 86400 * 14;

    private final AccessDeniedHandler accessDeniedHandler;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationFailureHandler authenticationFailureHandler;
    private final UserDetailsService userDetailsService;

//    비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}


//    SecurityContextHolder 설정:
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
//
//        // SecurityContextHolder 설정
//        http.addFilterAfter(new SecurityContextPersistenceFilter(), HeaderWriterFilter.class);
//    }

//    세션 설정
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
//
//        // 세션 관리 설정
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                .sessionFixation().migrateSession();
//     }

//    CSRF 설정:
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
//
//        // CSRF 설정
//        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
//        WebSecurity에서 관여하지 않을 경로
//        즉, 권한이 없어도 사용이 가능한 경로
        return web -> web.ignoring()
                .mvcMatchers(IGNORE_FAVICON) //favicon은 필터에서 제외
                .antMatchers(IGNORE_SERVICE_PATH)
                .antMatchers(IGNORE_FAQ_PATH)
                .antMatchers(IGNORE_MAIN_PATH)
                .antMatchers(IGNORE_BOARD_CHALLENGE_PATH)
                .antMatchers(IGNORE_BOARD_GROUP_CHALLENGE_PATH)
                .antMatchers(IGNORE_BOARD_SUGGEST_LIST_PATH)
                .antMatchers(IGNORE_BOARD_SUGGEST_DETAIL_PATH)
                .antMatchers(IGNORE_BOARD_DIARY_LIST_PATH)
                .antMatchers(IGNORE_BOARD_DIARY_DETAIL_PATH)
                .antMatchers(IGNORE_BOARD_INQUIRE_LIST_PATH)
                .antMatchers(IGNORE_BOARD_INQUIRE_DETAIL_PATH)
                .antMatchers(IGNORE_BOARD_NOTICE_LIST_PATH)
                .antMatchers(IGNORE_BOARD_NOTICE_DETAIL_PATH)

                /* 미안해요 !여러분 여기다 경로를 올리면 다 접근이 가능해서 사용이 가능해질 겁니다!. */
//                .antMatchers(MYPAGE_PATH)
//                .antMatchers(BOARD_SUGGEST_WRITE_PATH)
//                .antMatchers(BOARD_DIARY_WRITE_PATH)
//                .antMatchers(BOARD_INQUIRE_WRITE_PATH)
//                .antMatchers(ADMIN_PATH)
                /* 여기 까지 */
                
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()); //static 경로도 필터에서 제외
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(ADMIN_PATH).hasRole(Role.ADMIN.name())
                .antMatchers(BOARD_SUGGEST_WRITE_PATH).hasRole(Role.MEMBER.name())
                .antMatchers(BOARD_DIARY_WRITE_PATH).hasRole(Role.MEMBER.name())
                .antMatchers(BOARD_INQUIRE_WRITE_PATH).hasRole(Role.MEMBER.name())
                .antMatchers(MYPAGE_PATH).hasRole(Role.MEMBER.name())
                .and()
                .csrf().disable()
                .exceptionHandling()
                /* 인가, 인증 Exception Handler */
                .accessDeniedHandler(accessDeniedHandler) //인가 실패
                .authenticationEntryPoint(authenticationEntryPoint); //인증 실패

        log.info(userDetailsService.toString());

        http
                .formLogin()
                .loginPage(LOGIN_PAGE)
                .usernameParameter("memberEmail")
                .passwordParameter("memberPassword")
                .loginProcessingUrl(LOGIN_PROCESSING_URL)
                .successHandler(authenticationSuccessHandler) // 로그인 성공 시
                .failureHandler(authenticationFailureHandler) // 로그인 실패 시
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT_URL))
                .logoutSuccessUrl(LOGOUT_SUCCESS_URL)
                .invalidateHttpSession(Boolean.TRUE)
                .and()
                .rememberMe()
                .rememberMeParameter("remember-me")
                .key(REMEMBER_ME_TOKEN_KEY)
                .tokenValiditySeconds(REMEMBER_ME_TOKEN_EXPIRED)
                .userDetailsService(userDetailsService)
                .authenticationSuccessHandler(authenticationSuccessHandler);

        return http.build();
    }
}
