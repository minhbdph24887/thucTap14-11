# thucTap14-11

### Project demo1, file Demo1Application.java có đoạn code này:
    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner runRole(RoleService roleService){
        return args -> {
            roleService.saveRole(new Role(null, "ROLE_USER"));
            roleService.saveRole(new Role(null, "ROLE_MANAGER"));
            roleService.saveRole(new Role(null, "ROLE_ADMIN"));
        };
    }

    @Bean
    CommandLineRunner runAccount(AccountService accountService){
        return args -> {
            accountService.saveAccount(new Account(null, "account01", "Bui Duc Minh", "minhbdph24887@gmail.com", "123456", new HashSet<>()));
            accountService.saveAccount(new Account(null, "account02", "Nguyen Van A", "anv12@gmail.com", "123456", new HashSet<>()));
        };
    }

    @Bean
    CommandLineRunner runAuthentication(AuthenticationService authenticationService){
        return args -> {
            authenticationService.addAuthentication("minhbdph24887@gmail.com", "ROLE_ADMIN");
            authenticationService.addAuthentication("minhbdph24887@gmail.com", "ROLE_MANAGER");
            authenticationService.addAuthentication("anv12@gmail.com", "ROLE_USER");
        };
    }
### Đoạn code này là code để add dữ liệu vào sql nên sau khi chạy lần đầu tiền thì phải comment lại nó tránh trường hợp login tài khoản lỗi...

