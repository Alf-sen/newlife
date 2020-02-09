## 秀森的新生活

## 资料
[maven 文档](https://mvnrepository.com/)  
[spring 文档](https://docs.spring.io/spring/docs/current/spring-framework-reference/)  
[Bootstrap 文档](https://v3.bootcss.com/components/?#navbar-default)  
[Github OAuth 文档](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)  
[OKHttp 文档](https://square.github.io/okhttp/)  
[Lombok 省略get,set](https://projectlombok.org/)  
[thymeleaf 模板文档](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html)
[jquery 文档](https://www.jquery.com)  

##工具
git  
ideal


##注意
1、增加interceptor后会导致静态资源被拦截。处理方法有三步：  
  第一步：实现WebMvcConfigurer的类中拦截器中添加排除资源处添加静态资源文件路径：  
          
          @Override
          public void addResourceHandlers(ResourceHandlerRegistry registry) {
              registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
              WebMvcConfigurer.super.addResourceHandlers(registry);
          }  
  第二步：配置静态文件路径  
          
          @Override
          public void addResourceHandlers(ResourceHandlerRegistry registry) {
              registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
              WebMvcConfigurer.super.addResourceHandlers(registry);
          }  
  第三步：在页面访问静态资源时，需要这样写 
   
          <link rel="stylesheet" href="/static/css/bootstrap.min.css">

