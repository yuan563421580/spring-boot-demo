package com.yuansb.demo.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2 配置
 *
 * 要想使用Swagger，必须编写一个配置类来配置 Swagger，这里的配置类如下
 *     通过注解 @Configuration 说明这是一个配置类
 *     通过注解 @EnableSwagger2 开启Swagger2的自动配置
 *
 *
 * Swagger2的常用注解
 *
 *      @Api(tags = "xxx模块说明")：作用在模块类上，表示对类的说明
 *          tags="说明该类的作用，可以在UI界面上看到的注解"
 *          value="该参数没什么意义，在UI界面上也看到，所以不需要配置"
 *
 *      @ApiOperation("xxx接口说明") 作用在接口方法上，说明方法的用途、作用
 *          value="说明方法的用途、作用"
 *          notes="方法的备注说明"
 *
 *      @ApiModel("xxxPOJO说明") 作用在模型类上，表示一个返回响应数据的信息，如VO、BO
 *
 *      @ApiModelProperty(value = "xxx属性说明", hidden = true) 作用在类方法和属性上，hidden设置为true可以隐藏该属性
 *
 *      @ApiParam("xxx参数说明") 作用在参数、方法和字段上，类似@ApiModelProperty
 *
 *      @ApiImplicitParams 用在请求的方法上，表示一组参数说明
 *          @ApiImplicitParam：用在@ApiImplicitParams注解中，指定一个请求参数的各个方面
 *              name：参数名
 *              value：参数的汉字说明、解释
 *              required：参数是否必须传
 *              paramType：参数放在哪个地方
 *                  · header --> 请求参数的获取：@RequestHeader
 *                  · query --> 请求参数的获取：@RequestParam
 *                  · path（用于restful接口）--> 请求参数的获取：@PathVariable
 *                  · body（不常用）
 *                  · form（不常用）
 *              dataType：参数类型，默认String，其它值dataType="Integer"
 *              defaultValue：参数的默认值
 *
 *      @ApiResponses：用在请求的方法上，表示一组响应
 *          @ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息
 *              code：数字，例如400
 *              message：信息，例如"请求参数没填好"
 *              response：抛出异常的类
 *
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        // 构造函数传入初始化规范，这是swagger2规范
        return new Docket(DocumentationType.SWAGGER_2)
                //apiInfo： 添加api详情信息，参数为ApiInfo类型的参数，这个参数包含了第二部分的所有信息比如标题、描述、版本之类的，开发中一般都会自定义这些信息
                .apiInfo(apiInfo())
                .groupName("yuansb")
                //配置是否启用Swagger，如果是false，在浏览器将无法访问，默认是true
                .enable(true)
                .select()
                //apis： 添加过滤条件
                .apis(RequestHandlerSelectors.basePackage("com.yuansb.demo.swagger.controller"))
                //paths： 这里是控制哪些路径的api会被显示出来，
                .paths(PathSelectors.any())
                //比如下方的参数就是除了/user以外的其它路径都会生成api文档
                //.paths((String a) ->
                //        !a.equals("/user"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("spring-boot-demo-swagger")// 标题
                .description("这是一个简单的 Swagger API 演示")//描述
                .version("版本内容：v1.0")// 版本
                .contact(new Contact("Shibo.Yuan", "http://test.com", "563421580@qq.com"))// 联系人信息
                .termsOfServiceUrl("链接：http://terms.service.url/")//组织链接
                .build();
    }

}
