import org.apache.tools.ant.taskdefs.condition.Os
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jlleitschuh.gradle.ktlint")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("jacoco")
    kotlin("jvm")
    kotlin("plugin.spring")
}

allprojects {
    apply(plugin = "java")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "jacoco")

    group = "com.tenmm"
    version = "0.0.1-SNAPSHOT"
    java.sourceCompatibility = JavaVersion.VERSION_17

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        }
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
        implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.springframework.boot:spring-boot-starter-webflux")
        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("org.apache.commons:commons-lang3:${property("commonsLang3Version")}")
        implementation("io.github.microutils:kotlin-logging:${property("kotlinLoggingVersion")}")

        testImplementation("io.projectreactor:reactor-test")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("io.mockk:mockk:${property("mockkVersion")}")
        testImplementation("com.willowtreeapps.assertk:assertk-jvm:${property("assertkVersion")}")

        if (Os.isFamily(Os.FAMILY_MAC)) {
            // for-mac
            implementation("io.netty:netty-resolver-dns-native-macos:4.1.86.Final:osx-aarch_64")
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    ktlint {
        filter {
            exclude { it.file.path.contains("$buildDir/generated/") }
        }

        disabledRules.set(setOf("import-ordering"))
    }

    jacoco {
        toolVersion = "0.8.10"
    }

    tasks.test {
        finalizedBy(tasks.jacocoTestReport)
    }

    tasks.jacocoTestReport {
        reports {
            html.required.set(true)
            html.outputLocation.set(file("$buildDir/reports/jacocoReport.html"))
        }
        finalizedBy(tasks.jacocoTestCoverageVerification)
    }

    tasks.jacocoTestCoverageVerification {
        val excludeList = listOf(
            "*Config",
            "*.logger.*",
            "*Exception",
            "*.exception.*",
            "com.tenmm.tilserver.TilServerApplicationKt",
            "com.tenmm.tilserver.TilServerApplication",
            "com.tenmm.tilserver.draft.adapter.inbound.controller.model.GetDraftResponse.Companion",
            "com.tenmm.tilserver.draft.adapter.inbound.controller.model.SyncDraftRequest",
            "com.tenmm.tilserver.draft.adapter.inbound.controller.model.GetDraftResponseKt",
            "com.tenmm.tilserver.draft.adapter.inbound.controller.model.SaveDraftResponse",
            "com.tenmm.tilserver.draft.adapter.inbound.controller.model.GetDraftResponse",
            "com.tenmm.tilserver.draft.adapter.inbound.controller.model.SaveDraftRequest",
            "com.tenmm.tilserver.draft.adapter.inbound.controller.model.SyncDraftResponse",
            "com.tenmm.tilserver.post.application.service.RecommendedPostService",
            "com.tenmm.tilserver.post.application.service.ModifyPostService.UpdatePostType",
            "com.tenmm.tilserver.post.application.service.ModifyPostService",
            "com.tenmm.tilserver.post.application.service.GetPostService*",
            "com.tenmm.tilserver.post.application.service.SavePostService",
            "com.tenmm.tilserver.security.adapter.outbound.RefreshTokenAdapterKt",
            "com.tenmm.tilserver.security.adapter.outbound.RefreshTokenAdapter",
            "com.tenmm.tilserver.security.adapter.outbound.RefreshTokenAdapter.save.2",
            "com.tenmm.tilserver.blog.application.service.ModifyBlogServiceKt",
            "com.tenmm.tilserver.blog.application.service.GetUserBlogService",
            "com.tenmm.tilserver.blog.application.service.ModifyBlogService",
            "com.tenmm.tilserver.blog.application.service.ModifyBlogService.modify.1",
            "com.tenmm.tilserver.security.adapter.inbound.scheduler.DeleteExpiredTokenScheduler",
            "com.tenmm.tilserver.security.adapter.inbound.scheduler.DeleteExpiredTokenScheduler.deleteAllExpiredTokens.1",
            "com.tenmm.tilserver.category.domain.Category",
            "com.tenmm.tilserver.crawler.util.UrlCheck",
            "com.tenmm.tilserver.crawler.util.UrlType",
            "com.tenmm.tilserver.post.domain.PostDetail.Companion",
            "com.tenmm.tilserver.post.domain.Post",
            "com.tenmm.tilserver.post.domain.PostDetail",
            "com.tenmm.tilserver.user.adapter.inbound.GetUserMyProfileController",
            "com.tenmm.tilserver.user.adapter.inbound.ModifyUserProfileController",
            "com.tenmm.tilserver.user.adapter.inbound.GetUserProfileController",
            "com.tenmm.tilserver.common.domain.UrlKt",
            "com.tenmm.tilserver.common.domain.Identifier",
            "com.tenmm.tilserver.common.domain.OperationResult.Companion",
            "com.tenmm.tilserver.common.domain.ModifyPostFailType",
            "com.tenmm.tilserver.common.domain.IdentifierKt",
            "com.tenmm.tilserver.common.domain.ModifyUserFailType",
            "com.tenmm.tilserver.common.domain.OperationResult",
            "com.tenmm.tilserver.common.domain.Url",
            "com.tenmm.tilserver.common.domain.Identifier.Companion",
            "com.tenmm.tilserver.common.domain.ResultWithToken",
            "com.tenmm.tilserver.common.domain.Email",
            "com.tenmm.tilserver.account.application.service.CertifyService",
            "com.tenmm.tilserver.account.application.service.GetGoogleOAuthUserInfoService",
            "com.tenmm.tilserver.account.application.service.ModifyAccountService",
            "com.tenmm.tilserver.account.application.service.ModifyAccountServiceKt",
            "com.tenmm.tilserver.account.application.service.GetAccountService",
            "com.tenmm.tilserver.account.application.service.ModifyAccountService.modifyMailAgreement.1",
            "com.tenmm.tilserver.post.adapter.inbound.rest.model.PostResponse",
            "com.tenmm.tilserver.post.adapter.inbound.rest.model.ConfirmUploadPostResponse.Companion",
            "com.tenmm.tilserver.post.adapter.inbound.rest.model.ModifyPostResponse",
            "com.tenmm.tilserver.post.adapter.inbound.rest.model.GetPostMetaResponse",
            "com.tenmm.tilserver.post.adapter.inbound.rest.model.ConfirmUploadPostResponse",
            "com.tenmm.tilserver.post.adapter.inbound.rest.model.RequestUploadPostResponse",
            "com.tenmm.tilserver.post.adapter.inbound.rest.model.GetPostListResponse.Companion",
            "com.tenmm.tilserver.post.adapter.inbound.rest.model.GetPostResponse.Companion",
            "com.tenmm.tilserver.post.adapter.inbound.rest.model.ModifyRecommendedPostResponse",
            "com.tenmm.tilserver.post.adapter.inbound.rest.model.GetPostMetaResponse.Companion",
            "com.tenmm.tilserver.post.adapter.inbound.rest.model.RequestUploadPostResponse.Companion",
            "com.tenmm.tilserver.post.adapter.inbound.rest.model.ConfirmUploadPostRequest",
            "com.tenmm.tilserver.post.adapter.inbound.rest.model.GetPostResponse",
            "com.tenmm.tilserver.post.adapter.inbound.rest.model.RequestUploadPostRequest",
            "com.tenmm.tilserver.post.adapter.inbound.rest.model.GetPostMetaResponseKt",
            "com.tenmm.tilserver.post.adapter.inbound.rest.model.DeletePostResponse.Companion",
            "com.tenmm.tilserver.post.adapter.inbound.rest.model.ModifyPostRequest",
            "com.tenmm.tilserver.post.adapter.inbound.rest.model.PostResponse.Companion",
            "com.tenmm.tilserver.post.adapter.inbound.rest.model.DeletePostResponse",
            "com.tenmm.tilserver.post.adapter.inbound.rest.model.ModifyRecommendedPostResponse.Companion",
            "com.tenmm.tilserver.post.adapter.inbound.rest.model.GetPostListResponse",
            "com.tenmm.tilserver.account.adapter.outbound.google.model.GoogleOAuthLoginResponse",
            "com.tenmm.tilserver.account.adapter.outbound.google.model.GoogleOAuthUserInfoResponse",
            "com.tenmm.tilserver.draft.application.service.SyncDraftServiceKt",
            "com.tenmm.tilserver.draft.application.service.SaveDraftService",
            "com.tenmm.tilserver.draft.application.service.SyncDraftService",
            "com.tenmm.tilserver.draft.application.service.GetDraftService",
            "com.tenmm.tilserver.post.adapter.inbound.rest.ModifyPostController",
            "com.tenmm.tilserver.post.adapter.inbound.rest.AddRecommendedPostController",
            "com.tenmm.tilserver.post.adapter.inbound.rest.GetPostController",
            "com.tenmm.tilserver.post.adapter.inbound.rest.SavePostController",
            "com.tenmm.tilserver.post.adapter.inbound.rest.DeletePostController",
            "com.tenmm.tilserver.common.config.properties.ProfileImgProperties",
            "com.tenmm.tilserver.common.security.SecurityProperties",
            "com.tenmm.tilserver.common.security.JwtConfigProperties.TokenProperties",
            "com.tenmm.tilserver.common.security.SecurityConfig",
            "com.tenmm.tilserver.common.security.JwtConfigProperties",
            "com.tenmm.tilserver.common.security.AES128",
            "com.tenmm.tilserver.common.security.AES128.validation.1",
            "com.tenmm.tilserver.common.security.AES128.validation.2",
            "com.tenmm.tilserver.post.application.outbound.model.ParsedPostResult",
            "com.tenmm.tilserver.account.adapter.inboud.rest.CertifyController",
            "com.tenmm.tilserver.post.application.inbound.model.GetPostResult",
            "com.tenmm.tilserver.post.application.inbound.model.PostSaveConfirmResult",
            "com.tenmm.tilserver.post.application.inbound.model.PostSaveRequestResult",
            "com.tenmm.tilserver.post.application.inbound.model.PostSaveConfirmCommand",
            "com.tenmm.tilserver.post.application.inbound.model.PostSaveRequestCommand",
            "com.tenmm.tilserver.post.application.inbound.model.GetPostMetaResult",
            "com.tenmm.tilserver.post.application.inbound.model.GetPostListResult",
            "com.tenmm.tilserver.post.application.inbound.model.ModifyPostCommand",
            "com.tenmm.tilserver.security.adapter.inbound.rest.RefreshTokenController",
            "com.tenmm.tilserver.crawler.application.service.DoCrawlingService",
            "com.tenmm.tilserver.blog.adapter.inbound.rest.GetBlogController",
            "com.tenmm.tilserver.category.application.service.GetCategoryService",
            "com.tenmm.tilserver.common.config.grpc.GrpcConfig",
            "com.tenmm.tilserver.common.config.grpc.GrpcProperties",
            "com.tenmm.tilserver.common.config.grpc.GrpcServerInfo",
            "com.tenmm.tilserver.account.application.model.CreateAccountCommand",
            "com.tenmm.tilserver.account.application.model.OAuthUserInfo",
            "com.tenmm.tilserver.account.application.model.LogInResult",
            "com.tenmm.tilserver.account.application.model.OAuthTokenResult",
            "com.tenmm.tilserver.account.application.model.LogInCommand",
            "com.tenmm.tilserver.blog.domain.Blog",
            "com.tenmm.tilserver.draft.adapter.inbound.controller.GetDraftController",
            "com.tenmm.tilserver.draft.adapter.inbound.controller.SaveDraftController",
            "com.tenmm.tilserver.draft.adapter.inbound.controller.SyncDraftController",
            "com.tenmm.tilserver.security.adapter.inbound.rest.model.RefreshTokenResponse",
            "com.tenmm.tilserver.security.adapter.inbound.rest.model.RefreshTokenRequest",
            "com.tenmm.tilserver.user.application.inbound.model.ModifyUserCommand",
            "com.tenmm.tilserver.user.application.inbound.model.OnBoardingUserCommand",
            "com.tenmm.tilserver.user.application.inbound.model.CreateUserCommand",
            "com.tenmm.tilserver.category.adapter.inbound.rest.GetCategoryController",
            "com.tenmm.tilserver.account.adapter.outbound.persistence.SaveAccountAdapter",
            "com.tenmm.tilserver.account.adapter.outbound.persistence.SaveAccountAdapter.save.1",
            "com.tenmm.tilserver.account.adapter.outbound.persistence.GetAccountAdapter",
            "com.tenmm.tilserver.account.adapter.outbound.persistence.SaveAccountAdapterKt",
            "com.tenmm.tilserver.account.adapter.outbound.persistence.converters.Model_convertersKt",
            "com.tenmm.tilserver.blog.adapter.outbound.persistence.DeleteBlogAdapter",
            "com.tenmm.tilserver.blog.adapter.outbound.persistence.GetBlogAdapter",
            "com.tenmm.tilserver.blog.adapter.outbound.persistence.SaveBlogAdapter",
            "com.tenmm.tilserver.blog.adapter.outbound.persistence.SaveBlogAdapter.saveAll.1",
            "com.tenmm.tilserver.blog.adapter.outbound.persistence.SaveBlogAdapterKt",
            "com.tenmm.tilserver.blog.adapter.outbound.persistence.DeleteBlogAdapter.deleteAllByUserIdentifier.1",
            "com.tenmm.tilserver.blog.adapter.outbound.persistence.DeleteBlogAdapterKt",
            "com.tenmm.tilserver.crawler.application.service.crawler.VelogCrawlingService",
            "com.tenmm.tilserver.crawler.application.service.crawler.CrawlerMetaService",
            "com.tenmm.tilserver.crawler.application.service.crawler.CrawlerConfig",
            "com.tenmm.tilserver.crawler.application.service.crawler.MediumCrawlingService",
            "com.tenmm.tilserver.crawler.application.service.crawler.NaverCrawlingService",
            "com.tenmm.tilserver.crawler.application.service.crawler.EtcCrawlingService",
            "com.tenmm.tilserver.crawler.application.service.crawler.TistoryCrawlingService",
            "com.tenmm.tilserver.account.domain.OAuthType",
            "com.tenmm.tilserver.account.domain.AccountStatus",
            "com.tenmm.tilserver.account.domain.Account",
            "com.tenmm.tilserver.security.application.service.RefreshTokenService",
            "com.tenmm.tilserver.security.application.service.DeleteTokenService",
            "com.tenmm.tilserver.security.application.service.ResolveTokenService",
            "com.tenmm.tilserver.security.application.service.GenerateTokenService",
            "com.tenmm.tilserver.account.adapter.outbound.google.GetGoogleOAuthAdapter.getOAuthTokens.response.1",
            "com.tenmm.tilserver.account.adapter.outbound.google.GetGoogleOAuthAdapterKt",
            "com.tenmm.tilserver.account.adapter.outbound.google.GetGoogleOAuthAdapter",
            "com.tenmm.tilserver.draft.domain.Draft",
            "com.tenmm.tilserver.category.adapter.outbound.Model_convertersKt",
            "com.tenmm.tilserver.category.adapter.outbound.GetCategoryAdapter",
            "com.tenmm.tilserver.post.adapter.outbound.GetPostAdapter.PageTokenSearchPostWithCategoryIdentifierTimePeriod",
            "com.tenmm.tilserver.post.adapter.outbound.RecommendedPostAdapter",
            "com.tenmm.tilserver.post.adapter.outbound.ModifyPostAdapter.updateCreatedAt.1",
            "com.tenmm.tilserver.post.adapter.outbound.ModifyPostAdapter",
            "com.tenmm.tilserver.post.adapter.outbound.RecommendedPostAdapter.addByPostIdentifier.1",
            "com.tenmm.tilserver.post.adapter.outbound.ModifyPostAdapter.updateSummary.1",
            "com.tenmm.tilserver.post.adapter.outbound.ModifyPostAdapterKt",
            "com.tenmm.tilserver.post.adapter.outbound.ModifyPostAdapter.deleteByIdentifier.1",
            "com.tenmm.tilserver.post.adapter.outbound.ModifyPostAdapter.updateTitle.1",
            "com.tenmm.tilserver.post.adapter.outbound.ModifyPostAdapter.save.1",
            "com.tenmm.tilserver.post.adapter.outbound.GetPostAdapter.PageTokenSearchPostWithCategoryIdentifier",
            "com.tenmm.tilserver.post.adapter.outbound.RecommendedPostAdapterKt",
            "com.tenmm.tilserver.post.adapter.outbound.Model_convertersKt",
            "com.tenmm.tilserver.post.adapter.outbound.GetPostAdapter",
            "com.tenmm.tilserver.user.application.outbound.model.CreateUserRequest",
            "com.tenmm.tilserver.common.utils.CryptoHandler",
            "com.tenmm.tilserver.user.adapter.outbound.UserAdapter.updateUserCategory.1",
            "com.tenmm.tilserver.user.adapter.outbound.UserAdapter.updateUserPath.1",
            "com.tenmm.tilserver.user.adapter.outbound.UserAdapter.save.1",
            "com.tenmm.tilserver.user.adapter.outbound.UserAdapter.updateUserName.1",
            "com.tenmm.tilserver.user.adapter.outbound.UserAdapter.updateUserIntroduction.1",
            "com.tenmm.tilserver.user.adapter.outbound.UserAdapter.updateUserStatus.1",
            "com.tenmm.tilserver.user.adapter.outbound.UserAdapter.updateUserProfileImgSrc.1",
            "com.tenmm.tilserver.user.adapter.outbound.Model_convertersKt",
            "com.tenmm.tilserver.user.adapter.outbound.UserAdapterKt",
            "com.tenmm.tilserver.user.adapter.outbound.UserAdapter",
            "com.tenmm.tilserver.user.application.service.GetUserService",
            "com.tenmm.tilserver.user.application.service.CreateUserService",
            "com.tenmm.tilserver.user.application.service.ModifyUserService.UpdateUserType",
            "com.tenmm.tilserver.user.application.service.ModifyUserService",
            "com.tenmm.tilserver.user.application.service.ModifyUserServiceKt",
            "com.tenmm.tilserver.crawler.domain.CssSelectorInfo",
            "com.tenmm.tilserver.crawler.domain.ParsedPost",
            "com.tenmm.tilserver.crawler.adapter.outbound.DoURLConnectionCrawlingAdapter",
            "com.tenmm.tilserver.user.adapter.inbound.model.GetUserProfileResponse.Companion",
            "com.tenmm.tilserver.user.adapter.inbound.model.GetUserProfileResponse",
            "com.tenmm.tilserver.user.adapter.inbound.model.ModifyUserProfileResponse.Companion",
            "com.tenmm.tilserver.user.adapter.inbound.model.ModifyBlogInfoRequest",
            "com.tenmm.tilserver.user.adapter.inbound.model.ModifyUserRequest",
            "com.tenmm.tilserver.user.adapter.inbound.model.ModifyUserProfileResponse",
            "com.tenmm.tilserver.user.adapter.inbound.model.GetMyProfileResponse.Companion",
            "com.tenmm.tilserver.user.adapter.inbound.model.GetMyProfileResponse",
            "com.tenmm.tilserver.user.adapter.inbound.model.OnBoardingUserRequest",
            "com.tenmm.tilserver.account.adapter.inboud.rest.model.LogInRequest",
            "com.tenmm.tilserver.account.adapter.inboud.rest.model.LogInResponse",
            "com.tenmm.tilserver.account.adapter.inboud.rest.model.LogInResponse.Companion",
            "com.tenmm.tilserver.account.adapter.inboud.rest.model.LogOutResponse",
            "com.tenmm.tilserver.category.application.inbound.model.GetCategoryResult",
            "com.tenmm.tilserver.draft.adapter.outbound.SaveDraftAdapterKt",
            "com.tenmm.tilserver.draft.adapter.outbound.SaveDraftAdapter",
            "com.tenmm.tilserver.draft.adapter.outbound.SaveDraftAdapter.upsert.1",
            "com.tenmm.tilserver.draft.adapter.outbound.Model_convertersKt",
            "com.tenmm.tilserver.draft.adapter.outbound.SyncDraftAdapter",
            "com.tenmm.tilserver.draft.adapter.outbound.GetDraftAdapter",
            "com.tenmm.tilserver.draft.adapter.outbound.SyncDraftAdapterKt",
            "com.tenmm.tilserver.common.security.annotation.RequiredUserArgumentResolver.resolveArgument.1",
            "com.tenmm.tilserver.common.security.annotation.OptionalUserArgumentResolver",
            "com.tenmm.tilserver.common.security.annotation.RequiredUserArgumentResolver",
            "com.tenmm.tilserver.common.security.annotation.RequiredUserArgumentResolverKt",
            "com.tenmm.tilserver.common.constants.RegexKt",
            "com.tenmm.tilserver.blog.application.inbound.model.ModifyBlogCommand",
            "com.tenmm.tilserver.blog.application.inbound.model.GetBlogResult",
            "com.tenmm.tilserver.category.adapter.inbound.rest.model.GetCategoriesResponseKt",
            "com.tenmm.tilserver.category.adapter.inbound.rest.model.GetCategoriesResponse",
            "com.tenmm.tilserver.category.adapter.inbound.rest.model.CategoryResult",
            "com.tenmm.tilserver.user.domain.UserStatus",
            "com.tenmm.tilserver.user.domain.User",
            "com.tenmm.tilserver.security.domain.UserAuthInfo",
            "com.tenmm.tilserver.security.domain.SecurityTokenType",
            "com.tenmm.tilserver.security.domain.SecurityToken",
            "com.tenmm.tilserver.blog.adapter.outbound.persistence.converters.Model_convertersKt",
            "com.tenmm.tilserver.crawler.adapter.outbound.DoCrawlingAdapter",
            "com.tenmm.tilserver.common.config.rest.WebFluxConfig",
            "com.tenmm.tilserver.common.config.rest.SwaggerConfig",
            "com.tenmm.tilserver.common.config.rest.FeignConfig",
            "com.tenmm.tilserver.blog.adapter.inbound.rest.model.BlogResult",
            "com.tenmm.tilserver.blog.adapter.inbound.rest.model.GetBlogResponse",
        )
        violationRules {
            rule {
                enabled = true
                element = "CLASS"

                limit {
                    counter = "INSTRUCTION"
                    value = "COVEREDRATIO"
                    minimum = "0.80".toBigDecimal()
                }

                excludes = excludeList
            }
        }
    }
}
